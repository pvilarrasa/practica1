package cat.urv.comm.padm.persistence;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKeys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserRepository {

    public static final String FIELD_NAME = "NAME";
    public static final String FIELD_SURNAMES = "SURNAMES";
    public static final String FIELD_AGE = "AGE";

    public static final String CREDENTIAL_USER = "CREDENTIAL_USER";
    public static final String CREDENTIAL_PASSWORD = "CREDENTIAL_PASSWORD";

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
}
