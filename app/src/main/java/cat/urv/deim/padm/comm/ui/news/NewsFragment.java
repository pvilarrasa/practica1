package cat.urv.deim.padm.comm.ui.news;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import cat.urv.deim.padm.comm.LoginActivity;
import cat.urv.deim.padm.comm.R;
import cat.urv.deim.padm.comm.databinding.FragmentNewsRecyclerBinding;
import cat.urv.deim.padm.comm.persistence.Event;
import cat.urv.deim.padm.comm.persistence.EventRepository;
import cat.urv.deim.padm.comm.persistence.News;
import cat.urv.deim.padm.comm.persistence.NewsRepository;
import cat.urv.deim.padm.comm.persistence.UserRepository;

public class NewsFragment extends Fragment {

    private FragmentNewsRecyclerBinding binding;
    private RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        NewsRepository.obtainNews(getContext(), UserRepository.email, UserRepository.username, UserRepository.token);

        binding = FragmentNewsRecyclerBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recyclerView = binding.newsRecyclerView;

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (recyclerView != null){
            NewsRecyclerViewAdapter adapter;
            adapter = new NewsRecyclerViewAdapter(
                                            getContext(),
                                            R.layout.listview_item,
                                           NewsRepository.news);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.setAdapter(adapter);

            List<News> news = NewsRepository.getAllNews();
            adapter.setNews(news);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}