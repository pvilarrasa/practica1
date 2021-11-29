package cat.urv.deim.padm.comm;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import cat.urv.deim.padm.comm.exceptions.LoginException;
import cat.urv.deim.padm.comm.factories.IntentFactory;
import cat.urv.deim.padm.comm.persistence.UserRepository;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = LoginActivity.class.getSimpleName();

    private EditText usernameView;
    private EditText emailView;
    private EditText passwordView;
    private Button loginAccessButton;
    private TextView errorTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.usernameView = this.findViewById(R.id.login_username);
        this.emailView = this.findViewById(R.id.login_email);
        this.passwordView = this.findViewById(R.id.login_password);
        this.errorTextView = this.findViewById(R.id.errorTextView);

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
                String credentialEmail = emailView.getText().toString();
                String credentialPassword = passwordView.getText().toString();

                // obtenir token
                UserRepository.obtainUserToken(LoginActivity.this, credentialEmail, credentialUsername, credentialPassword);
                if(UserRepository.validLogin){
                    Intent intent = IntentFactory.buildMenuActivity(view.getContext());
                    startActivity(intent);
                    finish(); // Close the current activity
                }else{
                    errorTextView.setText(UserRepository.errorMessage);
                    errorTextView.setVisibility(TextView.VISIBLE);
                }
            }
        });
    }

}