package edu.byui.maddldsdj;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * This is a simple menu that allows the user to sign out. All new menu
 * items can be added here and show up across the application.
 *
 * @author Dallas Bleak
 * @since 7/10/2017.
 */
public class MenuActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        mAuth = FirebaseAuth.getInstance();
        firebaseUser = mAuth.getCurrentUser();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.madd_dj_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Intent signOutIntenet = new Intent(this, SignInRegister.class);
        if(id == R.id.item_sign_out) {
            Toast.makeText(MenuActivity.this, "Sign Out button called", Toast.LENGTH_SHORT).show();
            if (firebaseUser != null) {
                mAuth.signOut();
                startActivity(signOutIntenet);
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
