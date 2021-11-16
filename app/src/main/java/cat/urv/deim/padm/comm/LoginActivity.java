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

                    volley();

                    startActivity(intent);
                    finish(); // Close the current activity
                    Log.i(TAG, "Authencation: success");
                } else {
                    Log.e(TAG, "Authentication: access error");
                }

            }
        });
    }

    private void volley(){
        String email = "pol.vilarrasa@estudiants.urv.cat";
        String token = "679270ca0ec20d5ceeda55d2e1d5b17e9277463e";
        String password = "t8BrZdM3QXn7S5mL";
        String url = "https://apidev.gdgtarragona.net/api/json/news";
        final TextView t = (TextView) findViewById(R.id.resultat);

        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest sR = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                t.setText(response);
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){
                t.setText("no va");
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("mail", email);
                params.put("username", password);
                params.put("token", token);

                return params;
            }
        };

        queue.add(sR);
    }
}