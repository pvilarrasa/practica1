package cat.urv.deim.padm.comm.persistence;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.TextView;

import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKeys;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cat.urv.deim.padm.comm.LoginActivity;
import cat.urv.deim.padm.comm.R;
import cat.urv.deim.padm.comm.exceptions.LoginException;

public class UserRepository {

    public static final String FIELD_NAME = "NAME";
    public static final String FIELD_SURNAMES = "SURNAMES";
    public static final String FIELD_AGE = "AGE";

    public static final String CREDENTIAL_USER = "CREDENTIAL_USER";
    public static final String CREDENTIAL_PASSWORD = "CREDENTIAL_PASSWORD";

    // propietats de l'usuari
    public static String username;
    public static String email;
    public static String password;
    public static String token;
    public static String errorMessage;

    public static boolean validLogin;


    public static List<Contact> getContacts(Context context){
        List<Contact> contacts = new ArrayList<Contact>();

        Contact contact_1 = new Contact("pere", "tapies", 89);
        Contact contact_2 = new Contact("joan", "creus", 9);
        Contact contact_3 = new Contact("johan", "puig", 8);
        Contact contact_4 = new Contact("anna", "llop", 18);

        contacts.add(contact_1);
        contacts.add(contact_2);
        contacts.add(contact_3);
        contacts.add(contact_4);
        return contacts;
    }

    public static List<HashMap<String,String>> getContactsMap(Context context){
        List<HashMap<String,String>> data = new ArrayList<>();
        HashMap<String,String> itemData;
        for (Contact contact: UserRepository.getContacts(context)){
            itemData = new HashMap<>();
            itemData.put(UserRepository.FIELD_NAME, contact.getName());
            itemData.put(UserRepository.FIELD_SURNAMES, contact.getSurnames());
            itemData.put(UserRepository.FIELD_AGE, String.valueOf(contact.getAge()));
            data.add(itemData);
        }
        return data;
    }



    public static boolean validateCredentials(Context context, String user, String password){
        boolean condition = true;

        return condition;
    }

    public static boolean saveCredentials(Context context,String user, String password){
        try {
            String masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC);

            SharedPreferences sharedPreferences = EncryptedSharedPreferences.create(
                    "encrypted_credencials",
                    masterKeyAlias,
                    context,
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            );

            // use the shared preferences and editor as you normally would
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(CREDENTIAL_USER, user);
            editor.putString(CREDENTIAL_PASSWORD, password);
            return editor.commit();
        }catch(Exception e){
            return false;
        }
    }
    public static Map<String,String> loadCredentials(Context context){
        HashMap<String, String> credentials = new HashMap<>();
        try {
            String masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC);

            SharedPreferences sharedPreferences = EncryptedSharedPreferences.create(
                    "encrypted_credencials",
                    masterKeyAlias,
                    context,
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            );
            String username =sharedPreferences.getString(CREDENTIAL_USER, "");
            String password =sharedPreferences.getString(CREDENTIAL_PASSWORD, "");
            credentials.put(CREDENTIAL_USER, username);
            credentials.put(CREDENTIAL_PASSWORD, password);
            return credentials;
        }catch(Exception e){
            return credentials;
        }
    }

    // crida volley per a obtenir token usuari
    public static void obtainUserToken(Context context, String email, String username, String password){
        String url = "https://apidev.gdgtarragona.net/api/json/obtain_token";
        String pepe = ";!";
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest sR = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                UserRepository.username = username;
                UserRepository.email = email;
                UserRepository.password = password;
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String message = jsonObject.getString("message");
                    int status = jsonObject.getInt("status");

                    if(status == 200){
                        String token = message.split("Token: ")[1];
                        UserRepository.token = token;
                        validLogin = true;
                    }else{
                        errorMessage = message;
                        validLogin = false;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){
                validLogin = false;
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("mail", email);
                params.put("username", username);
                params.put("password", password);
                return params;
            }
        };

        queue.add(sR);
    }
}
