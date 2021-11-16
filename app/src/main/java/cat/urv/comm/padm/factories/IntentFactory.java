package cat.urv.comm.padm.factories;

import android.content.Context;
import android.content.Intent;

import cat.urv.comm.padm.AppConfig;
import cat.urv.comm.padm.MenuActivity;
import cat.urv.comm.padm.ProfileActivity;


public class IntentFactory {

    private static final  String PARAMETER_USERNAME ="PARAMETER_USERNAME";
    private static final  String PARAMETER_PASSWORD ="PARAMETER_PASSWORD";


    private IntentFactory(){}

    public static Intent buildProfileActivity(Context context){
        Intent intent = new Intent();
        intent.setClass(context, ProfileActivity.class);
        return intent;
    }

    public static Intent buildProfileActivity(Context context, String username, String password){
        Intent intent = IntentFactory.buildProfileActivity(context);
        intent.putExtra(IntentFactory.PARAMETER_USERNAME, username);
        intent.putExtra(IntentFactory.PARAMETER_PASSWORD, password);
        return intent;
    }

    public static Intent buildMenuActivity(Context context){
        Intent intent = new Intent();
        intent.setClass(context, MenuActivity.class);
        return intent;
    }

    public static String getUserFromIntent(Intent intent){
        if (intent.getExtras() == null) return AppConfig.DEFAULT_USERNAME;
        return intent.getExtras().getString(PARAMETER_USERNAME);
    }

    public static String getPasswordFromIntent(Intent intent){
        if (intent.getExtras() == null) return AppConfig.DEFAULT_PASSWORD;
        return intent.getExtras().getString(PARAMETER_PASSWORD);
    }


}
