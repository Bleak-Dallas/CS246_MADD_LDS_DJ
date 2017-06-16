package edu.byui.maddldsdj;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import edu.byui.maddldsdj.dummy.TestView;

public class SignInRegister extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "DALLAS LOG";

    private Button buttonSignin;
    private Button buttonRegister;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private Intent intent;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseUser firebaseUser;
    FirebaseDatabase fDatabase;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_register);

        // Intnet
        intent = new Intent(this, TestView.class); // Matt change this to the proper class
        // Buttons
        buttonSignin = (Button) findViewById(R.id.button_signin);
        buttonRegister = (Button) findViewById(R.id.button_register);
        // Views
        editTextEmail = (EditText) findViewById(R.id.text_email);
        editTextPassword = (EditText) findViewById(R.id.text_password);
        // assign onClickListener to buttons
        buttonSignin.setOnClickListener(this);
        buttonRegister.setOnClickListener(this);

        // get instance of Firebase Authorization
        mAuth = FirebaseAuth.getInstance();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        fDatabase = FirebaseDatabase.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseUser != null) {
                    // User is signed in
                    user = new User(firebaseUser.getEmail(), firebaseUser.getUid());
                    Log.d(SignInRegister.class.getName(), "onAuthStateChanged:signed_in:" + firebaseUser.getUid());
                    Toast.makeText(SignInRegister.this, "User signed_in:" + firebaseUser.getEmail(), Toast.LENGTH_SHORT).show();
                    intent.putExtra("userEmail", user.getUserEmail()); // needs to be removed once CatalogView is working
                    startActivity(intent);
                } else {
                    // User is signed out
                    Log.d(SignInRegister.class.getName(), "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    private void registerUser() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        if (TextUtils.isEmpty(email)) {
            //email is empty
            Toast.makeText(this, "Please enter your email", Toast.LENGTH_SHORT).show();
            // stop further execution
            return;
        }
        if (TextUtils.isEmpty(password)) {
            // password is empty
            Toast.makeText(this, "Please enter your password", Toast.LENGTH_SHORT).show();
            // stop further execution
            return;
        }

        // create the user on firebase
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(SignInRegister.class.getName(), "createUserWithEmail:success");
                            Toast.makeText(SignInRegister.this, "Registration Successful",
                                    Toast.LENGTH_SHORT).show();
                            firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                            if (firebaseUser != null) {
                                // Get user information and set it to user
                                user = new User(firebaseUser.getEmail(), firebaseUser.getUid());
                                registerToDatabase(user);
                                Log.v(TAG, user.getUserEmail());
                                Log.v(TAG, user.getUserID());
                                intent.putExtra("userEmail", user.getUserEmail()); // needs to be removed once CatalogView is working
                                startActivity(intent);
                            }
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(SignInRegister.class.getName(), "createUserWithEmail:failure");
                            Toast.makeText(SignInRegister.this, "Registration failed...Please try again",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void signIn() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            //email is empty
            Toast.makeText(this, "Please enter your email", Toast.LENGTH_SHORT).show();
            // stop further execution
            return;
        }
        if (TextUtils.isEmpty(password)) {
            // password is empty
            Toast.makeText(this, "Please enter your password", Toast.LENGTH_SHORT).show();
            // stop further execution
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(SignInRegister.class.getName(), "signInWithEmail:onComplete:" + task.isSuccessful());
                        Toast.makeText(SignInRegister.this, "SignIn Success",
                                Toast.LENGTH_SHORT).show();
                        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.w(SignInRegister.class.getName(), "signInWithEmail:failed", task.getException());
                            Toast.makeText(SignInRegister.this, "SignIn failed...Please try again",
                                    Toast.LENGTH_SHORT).show();
                        }
                        if (firebaseUser != null) {
                            // Get user information and set it to user
                            user = new User(firebaseUser.getEmail(), firebaseUser.getUid());
                            Log.v(TAG, user.getUserEmail());
                            Log.v(TAG, user.getUserID());
                            intent.putExtra("userEmail", user.getUserEmail());  // needs to be removed once CatalogView is working
                            startActivity(intent);
                        }
                    }
                });
    }

    public void registerToDatabase(User user) {
        fDatabase.getReference().child("users").push().setValue(user);
    }

    @Override
    public void onClick(View view) {
        if(view == buttonSignin) {
            signIn();
        }
        if(view == buttonRegister ) {
            registerUser();
        }
    }
}
