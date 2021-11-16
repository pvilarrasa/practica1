package cat.urv.deim.padm.comm;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import cat.urv.deim.padm.comm.factories.IntentFactory;

public class ProfileActivity extends AppCompatActivity {

    private static final String TAG = ProfileActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = this.getIntent();

        String username = IntentFactory.getUserFromIntent(intent);
        String password = IntentFactory.getPasswordFromIntent(intent);

        EditText usernameEdittext = this.findViewById(R.id.profile_username_edittext);
        EditText passwordEdittext = this.findViewById(R.id.profile_password_edittext);

        usernameEdittext.setText(username);
        passwordEdittext.setText(password);

        Log.i(TAG,"Main Activity");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.profile_menu, menu);
        return true;
    }
}