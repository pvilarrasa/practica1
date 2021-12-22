package cat.urv.deim.padm.comm.factories;

import android.content.Context;
import android.content.Intent;

import cat.urv.deim.padm.comm.AppConfig;
import cat.urv.deim.padm.comm.EventDetailActivity;
import cat.urv.deim.padm.comm.MenuActivity;
import cat.urv.deim.padm.comm.NewsDetailActivity;
import cat.urv.deim.padm.comm.ProfileActivity;
import cat.urv.deim.padm.comm.persistence.Event;
import cat.urv.deim.padm.comm.persistence.News;


public class IntentFactory {

    private static final  String PARAMETER_USERNAME ="PARAMETER_USERNAME";
    private static final  String PARAMETER_PASSWORD ="PARAMETER_PASSWORD";


    private IntentFactory(){}

    public static Intent buildProfileActivity(Context context){
        Intent intent = new Intent();
        intent.setClass(context, ProfileActivity.class);
        return intent;
    }

    public static Intent buildNewsDetailActivity(Context context, News news){
        Intent intent = new Intent();
        intent.setClass(context, NewsDetailActivity.class);
        intent.putExtra("newsObject", news);
        return intent;
    }

    public static Intent buildEventDetailActivity(Context context, Event event){
        Intent intent = new Intent();
        intent.setClass(context, EventDetailActivity.class);
        intent.putExtra("eventObject", event);
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
