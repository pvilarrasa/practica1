package cat.urv.deim.padm.comm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

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
        int position = intent.getIntExtra("position", 0);

        this.news = NewsRepository.getNewsAt(position);

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
        this.dateUpdateTextView.setText(this.news.getDateUpdate());

        Uri imgUri = Uri.parse(this.news.getImageUrl());
        this.photoImageView.setImageURI(imgUri);
    }
}