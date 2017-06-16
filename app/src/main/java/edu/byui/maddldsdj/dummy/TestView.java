package edu.byui.maddldsdj.dummy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import edu.byui.maddldsdj.R;
import edu.byui.maddldsdj.SignInRegister;

public class TestView extends AppCompatActivity {

    private String _userEmail;
    private Intent intentEmail;
    private Intent intentSignin;
    private TextView textView;
    private FirebaseAuth mAuth;
    private String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_view);

        mAuth = FirebaseAuth.getInstance();
        intentSignin = new Intent(this, SignInRegister.class);
        intentEmail = getIntent();
        _userEmail = intentEmail.getStringExtra("userEmail");

        textView = (TextView) findViewById(R.id.testView);
        text = "Email is: " + _userEmail;
        textView.setText(text);
    }

    public void signOut(View view) {
        mAuth.signOut();
        startActivity(intentSignin);
    }
}
