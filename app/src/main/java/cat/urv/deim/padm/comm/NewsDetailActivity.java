package cat.urv.deim.padm.comm;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import cat.urv.deim.padm.comm.factories.IntentFactory;
import cat.urv.deim.padm.comm.persistence.News;
import cat.urv.deim.padm.comm.persistence.NewsRepository;

public class NewsDetailActivity extends AppCompatActivity {

    private TextView titleTextView;
    private TextView dateTextView;
    private TextView subtitleTextView;
    private TextView textTextView;
    private TextView tagsTextView;
    private TextView dateUpdateTextView;
    private ImageView photoImageView;

    private News news;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        Intent intent = getIntent();
        this.news = (News)intent.getSerializableExtra("newsObject");

        // agafem les vistes
        this.titleTextView = this.findViewById(R.id.newsTitle);
        this.dateTextView = this.findViewById(R.id.newsDate);
        this.subtitleTextView = this.findViewById(R.id.newsSubtitle);
        this.textTextView = this.findViewById(R.id.newsText);
        this.tagsTextView = this.findViewById(R.id.newsTags);
        this.dateUpdateTextView = this.findViewById(R.id.newsDateUpdate);
        this.photoImageView = this.findViewById(R.id.newsPhoto);

        // omplim les vistes amb les dades
        this.titleTextView.setText(this.news.getTitle());
        this.dateTextView.setText(this.news.getDate());
        this.subtitleTextView.setText(this.news.getSubtitle());
        this.textTextView.setText(this.news.getText());
        this.tagsTextView.setText(this.news.getTagsAsString());
        this.dateUpdateTextView.setText("Last updated: " + this.news.getDateUpdate());

        Picasso.get()
                .load(this.news.getImageUrl())
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_foreground)
                .into(this.photoImageView);
    }
}