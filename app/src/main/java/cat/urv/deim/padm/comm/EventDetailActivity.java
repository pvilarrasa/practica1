package cat.urv.deim.padm.comm;

import androidx.appcompat.app.AppCompatActivity;
import cat.urv.deim.padm.comm.persistence.Event;
import cat.urv.deim.padm.comm.persistence.EventRepository;
import cat.urv.deim.padm.comm.persistence.News;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class EventDetailActivity extends AppCompatActivity {

    private TextView descriptionTextView;
    private ImageView photoImageView;
    private TextView nameTextView;
    private TextView tagsTextView;
    private TextView typeTextView;
    private TextView webUrlTextView;
    private Event event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);
        Intent intent = getIntent();
        this.event = (Event)intent.getSerializableExtra("eventObject");

        // agafem les vistes
        this.descriptionTextView = this.findViewById(R.id.eventDescription);
        this.photoImageView = this.findViewById(R.id.eventImageURL);
        this.nameTextView = this.findViewById(R.id.eventName);
        this.tagsTextView = this.findViewById(R.id.eventTags);
        this.typeTextView = this.findViewById(R.id.eventType);
        this.webUrlTextView = this.findViewById(R.id.eventWebURL);

        // omplim les vistes amb les dades
        this.descriptionTextView.setText(this.event.getDescription());
        this.nameTextView.setText(this.event.getName());
        this.tagsTextView.setText(this.event.getTagsAsString());
        this.typeTextView.setText(this.event.getType());
        this.webUrlTextView.setText(this.event.getWebURL());

        Picasso.get()
                .load(this.event.getImageURL())
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_foreground)
                .into(this.photoImageView);

    }
}