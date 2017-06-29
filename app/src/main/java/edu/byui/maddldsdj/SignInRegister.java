package edu.byui.maddldsdj;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.ContactsContract;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


/**
 * The SignInRegister class allows the user to sign into the application,
 * register for the application as well as keep the user signed in as long
 * as the user does not close teh app.
 * <p>
 * @author Dallas Bleak
 * @version 1.0
 * @since 2017-06-01
 */
public class SignInRegister extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "SIGNINACT";
    private static final String USERPREF = "UserPref";

    private Button buttonSignin;
    private Button buttonRegister;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private Intent intent;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseUser firebaseUser;
    private FirebaseDatabase fDatabase;
    private DatabaseReference dbRootRef;
    private DatabaseReference dbUserRef;
    private DatabaseReference dbUserAdminRef;
    private User user;
    private boolean userAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_register);

        mAuth = FirebaseAuth.getInstance();

        // Intnet
        intent = new Intent(this, CatalogActivity.class);

        // Buttons
        buttonSignin = (Button) findViewById(R.id.button_signin);
        buttonRegister = (Button) findViewById(R.id.button_register);
        // Views
        editTextEmail = (EditText) findViewById(R.id.text_email);
        editTextPassword = (EditText) findViewById(R.id.text_password);
        // assign onClickListener to buttons
        buttonSignin.setOnClickListener(this);
        buttonRegister.setOnClickListener(this);

        // get instances of Firebase
        mAuth = FirebaseAuth.getInstance();
        firebaseUser = mAuth.getCurrentUser();
        fDatabase = FirebaseDatabase.getInstance();
        dbRootRef = FirebaseDatabase.getInstance().getReference(); // Gets a reference to the entire database
        dbUserRef = dbRootRef.child("users");
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseUser != null) {
                    // User is signed in. Save to user object and save user to shared preferences
                    //GetUserAdminStatus();
                    addUserAndAssignPreferences();
                    //Log.d(TAG, "onAuthStateChanged:signed_in:" + firebaseUser.getUid());
                    Toast.makeText(SignInRegister.this, "User signed_in:" + user.getUserEmail(), Toast.LENGTH_SHORT).show();
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };
        if (firebaseUser != null) {
            startActivity(intent);
        }
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
        if (firebaseUser != null) {
            mAuth.signOut();
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
                                registerToDatabase(user);
                                //GetUserAdminStatus();
                                addUserAndAssignPreferences();
                                Log.v(TAG, user.getUserEmail());
                                Log.v(TAG, user.getUserID());
                            }
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(SignInRegister.class.getName(), "createUserWithEmail:failure");
                            Toast.makeText(SignInRegister.this, "Registration failed...Please try again",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        if (firebaseUser != null) {
            startActivity(intent);
        }
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
                        if (firebaseUser != null) {
                            // Get user information and set it to user
                            //GetUserAdminStatus();
                            addUserAndAssignPreferences();
                            Log.v(TAG, user.getUserEmail());
                            Log.v(TAG, user.getUserID());
                        }
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.w(SignInRegister.class.getName(), "signInWithEmail:failed", task.getException());
                            Toast.makeText(SignInRegister.this, "SignIn failed...Please try again",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        if (firebaseUser != null) {
            startActivity(intent);
        }
    }

    private void registerToDatabase(User user) {
        fDatabase.getReference().child("users").child(firebaseUser.getUid()).setValue(user);
    }

    /*private void GetUserAdminStatus() {
        dbUserAdminRef = dbRootRef.child(firebaseUser.getUid()).child("admin"); // gets reference to the current users admin row
        dbUserAdminRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                userAdmin = dataSnapshot.getValue(Boolean.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        Log.v(TAG, "ValueEventListener called, isAdmin equals \"" + userAdmin + "\"");
    }*/

    private void addUserAndAssignPreferences() {
        // create new User
        user = new User(firebaseUser.getEmail(), firebaseUser.getUid(), userAdmin); // need to change this to get from firebase database
        // Store User in Shared Preferences
        SharedPreferences userPreferences = getSharedPreferences(USERPREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = userPreferences.edit();
        editor.putString("userEmail", user.getUserEmail());
        editor.putString("userID", user.getUserID());
        editor.putBoolean("userAdmin", user.isAdmin());
        editor.apply();
        // For testing purposes
        // Toast.makeText(SignInRegister.this, "User admin:" + user.isAdmin() + " " + user.getUserEmail(), Toast.LENGTH_LONG).show();
        Log.d(TAG, "user saved to shared preferences");
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
