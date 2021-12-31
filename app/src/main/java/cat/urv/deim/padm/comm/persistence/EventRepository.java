package cat.urv.deim.padm.comm.persistence;

import android.content.Context;
import android.provider.CalendarContract;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventRepository {

    public static boolean validEvents;

    private static List<Event> events;

    private static EventDao eventDao;

    public static void setEventDao(EventDao dao){
        eventDao = dao;
    }

    public static List<Event> getAllEvents(){
        return eventDao.getAll();
    }

    public static void insertAllEvents(List<Event> events){
        eventDao.insertAll(events);
    }

    // crida volley per a obtenir events usuari
    public static void obtainEvents(Context context, String email, String username, String token){
        String url = "https://apidev.gdgtarragona.net/api/json/events";
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest sR = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                UserRepository.username = username;
                UserRepository.email = email;
                UserRepository.token = token;
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String jsA = jsonObject.getString("events");
                    int status = jsonObject.getInt("status");
                    if(status == 200){
                        Gson gson = new Gson();
                        Type listEvents = new TypeToken<ArrayList<Event>>(){}.getType();
                        events = new Gson().fromJson(jsA, listEvents);
                        insertAllEvents(events);
                        validEvents = true;
                    }else{
                        validEvents = false;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){
                validEvents = false;
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("mail", email);
                params.put("username", username);
                params.put("token", token);
                return params;
            }
        };

        queue.add(sR);
    }
}
