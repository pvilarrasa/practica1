package cat.urv.deim.padm.comm.ui.news;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;;
import android.widget.Button;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import cat.urv.deim.padm.comm.R;
import cat.urv.deim.padm.comm.factories.IntentFactory;
import cat.urv.deim.padm.comm.persistence.News;

public class NewsRecyclerViewAdapter extends RecyclerView.Adapter {
    private List<News> news;
    private int itemResourceId;
    private Context context;

    public NewsRecyclerViewAdapter(Context context, int resource, List<News> news) {
        this.news = news;
        this.context = context;
        itemResourceId = resource;
    }

    public void setNews(List<News> news){
        this.news = news;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        NewsViewHolder newsViewHolder;

        View view = LayoutInflater.from(context)
                .inflate(R.layout.listview_item, parent, false);

        newsViewHolder = new NewsViewHolder(view);
        return newsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            NewsViewHolder newsViewHolder = (NewsViewHolder) holder;
            newsViewHolder.setData(this.news.get(position), position);
    }

    @Override
    public int getItemCount() {
        if (news == null)
            return 0;
        else
            return this.news.size();
    }

    private static class NewsViewHolder extends RecyclerView.ViewHolder{

        private TextView title;
        private TextView date;
        private Button button;

        private final Context context;

        public NewsViewHolder(@NonNull View view) {
            super(view);
            context = view.getContext();
            title = view.findViewById(R.id.title);
            date = view.findViewById(R.id.date);
            button = view.findViewById(R.id.btnOpenNews);
        }

        public void setData(News news, int position) {
            this.title.setText(news.getTitle());
            this.date.setText(news.getDate());
            this.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = IntentFactory.buildNewsDetailActivity(view.getContext(), news);
                    context.startActivity(intent);
                }
            });
        }
    }
}
