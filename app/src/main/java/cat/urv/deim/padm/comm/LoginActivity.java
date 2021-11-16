package cat.urv.deim.padm.comm;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Map;

import cat.urv.deim.padm.comm.factories.IntentFactory;
import cat.urv.deim.padm.comm.persistence.UserRepository;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = LoginActivity.class.getSimpleName();

    private EditText usernameView;
    private EditText passwordView;
    private Button loginAccessButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.usernameView = this.findViewById(R.id.login_username);
        this.passwordView = this.findViewById(R.id.login_password);

        Map<String, String> credentials = UserRepository.loadCredentials(this);
        if (credentials.containsKey(UserRepository.CREDENTIAL_USER)){
            usernameView.setText(credentials.get(UserRepository.CREDENTIAL_USER));
        }
        if (credentials.containsKey(UserRepository.CREDENTIAL_PASSWORD)){
            passwordView.setText(credentials.get(UserRepository.CREDENTIAL_PASSWORD));
        }
        this.loginAccessButton = this.findViewById(R.id.login_access_button);

        this.loginAccessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String credentialUsername = usernameView.getText().toString();
                String credentialPassword = passwordView.getText().toString();


                if (UserRepository.validateCredentials(getApplication(), credentialUsername, credentialPassword)) {

                    boolean isSaved = UserRepository.saveCredentials(getApplicationContext(), credentialUsername, credentialPassword);

                    Intent intent = IntentFactory.buildMenuActivity(view.getContext());

                    startActivity(intent);
                    finish(); // Close the current activity
                    Log.i(TAG, "Authencation: success");
                } else {
                    Log.e(TAG, "Authentication: access error");
                }

            }
        });
    }
}