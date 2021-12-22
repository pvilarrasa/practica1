package cat.urv.deim.padm.comm.ui.events;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cat.urv.deim.padm.comm.R;
import cat.urv.deim.padm.comm.persistence.Contact;
import cat.urv.deim.padm.comm.persistence.Event;

public class EventsRecyclerViewAdapter extends RecyclerView.Adapter {
    private List<Event> events;
    private int itemResourceId;
    private Context context;

    public EventsRecyclerViewAdapter(Context context, int resource, List<Event> events) {
        this.events = events;
        this.context = context;
        itemResourceId = resource;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        EventViewHolder eventViewHolder;

        View view = LayoutInflater.from(context)
                .inflate(R.layout.listview_item, parent, false);

        eventViewHolder = new EventViewHolder(view);
        return eventViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        EventViewHolder eventViewHolder = (EventViewHolder) holder;
        eventViewHolder.setData(this.events.get(position));

    }

    @Override
    public int getItemCount() {
        if (events == null)
            return 0;
        else
            return this.events.size();
    }

    private static class EventViewHolder extends RecyclerView.ViewHolder{

        private TextView name;
        private TextView description;
        private TextView age;

        public EventViewHolder(@NonNull View view) {
            super(view);
            name = view.findViewById(R.id.title);
            description = view.findViewById(R.id.date);
        }

        public void setData(Event event) {
            this.name.setText(event.getName());
            this.description.setText(event.getDescription());
        }
    }
}
