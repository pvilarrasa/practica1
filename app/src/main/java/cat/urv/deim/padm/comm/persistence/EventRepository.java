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

import cat.urv.deim.padm.comm.ui.events.EventsRecyclerViewAdapter;

public class EventRepository {

    public static String errorMessage;
    public static boolean validEvents;

    public static List<Event> events;

    // crida volley per a obtenir events usuari
    public static void obtainEvents(Context context, String email, String username, String token, EventsRecyclerViewAdapter adapter){
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

                        validEvents = true;
                        adapter.setEvents(events);
                        adapter.notifyDataSetChanged();
                    }else{
                        //errorMessage = message;
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

    public static Event getEventsAt(int position){
        return events.get(position);
    }
}
