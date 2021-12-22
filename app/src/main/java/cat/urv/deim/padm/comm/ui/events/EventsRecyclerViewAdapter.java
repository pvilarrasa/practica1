package cat.urv.deim.padm.comm.ui.events;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cat.urv.deim.padm.comm.R;
import cat.urv.deim.padm.comm.factories.IntentFactory;
import cat.urv.deim.padm.comm.persistence.Contact;
import cat.urv.deim.padm.comm.persistence.Event;
import cat.urv.deim.padm.comm.persistence.News;
import cat.urv.deim.padm.comm.ui.news.NewsRecyclerViewAdapter;

public class EventsRecyclerViewAdapter extends RecyclerView.Adapter {
    private List<Event> events;
    private int itemResourceId;
    private Context context;

    public EventsRecyclerViewAdapter(Context context, int resource, List<Event> events) {
        this.events = events;
        this.context = context;
        itemResourceId = resource;
    }

    public void setEvents(List<Event> events){
        this.events = events;
    }

    @NonNull
    @Override
    public EventsRecyclerViewAdapter.EventsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        EventsRecyclerViewAdapter.EventsViewHolder eventsViewHolder;

        View view = LayoutInflater.from(context)
                .inflate(R.layout.listview_item, parent, false);

        eventsViewHolder = new EventsRecyclerViewAdapter.EventsViewHolder(view);
        return eventsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        EventsRecyclerViewAdapter.EventsViewHolder eventsViewHolder = (EventsRecyclerViewAdapter.EventsViewHolder) holder;
        eventsViewHolder.setData(this.events.get(position), position);
    }

    @Override
    public int getItemCount() {
        if (events == null)
            return 0;
        else
            return this.events.size();
    }

    private static class EventsViewHolder extends RecyclerView.ViewHolder{

        private TextView name;
        private TextView type;
        private Button button;

        private final Context context;

        public EventsViewHolder(@NonNull View view) {
            super(view);
            context = view.getContext();
            name = view.findViewById(R.id.title);
            type = view.findViewById(R.id.date);
            button = view.findViewById(R.id.btnOpenNews);
        }

        public void setData(Event event, int position) {
            this.name.setText(event.getName());
            this.type.setText(event.getType());
            this.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = IntentFactory.buildNewsDetailActivity(view.getContext(), position);
                    context.startActivity(intent);
                }
            });
        }
    }
}
