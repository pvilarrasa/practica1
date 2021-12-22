package cat.urv.deim.padm.comm.ui.events;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import cat.urv.deim.padm.comm.R;
import cat.urv.deim.padm.comm.databinding.FragmentEventsRecyclerBinding;
import cat.urv.deim.padm.comm.databinding.FragmentNewsRecyclerBinding;
import cat.urv.deim.padm.comm.persistence.EventRepository;
import cat.urv.deim.padm.comm.persistence.NewsRepository;
import cat.urv.deim.padm.comm.persistence.UserRepository;
import cat.urv.deim.padm.comm.ui.news.NewsRecyclerViewAdapter;

public class EventsFragment extends Fragment {

    private FragmentEventsRecyclerBinding binding;
    private RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentEventsRecyclerBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recyclerView = binding.eventsRecyclerView;

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (recyclerView != null){
            EventsRecyclerViewAdapter adapter;
            adapter = new EventsRecyclerViewAdapter(
                    getContext(),
                    R.layout.listview_item,
                    EventRepository.events);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.setAdapter(adapter);
            EventRepository.obtainEvents(getContext(), UserRepository.email, UserRepository.username, UserRepository.token, adapter);
            System.out.println("");
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}