package cat.urv.comm.padm.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import cat.urv.comm.padm.R;
import cat.urv.comm.padm.databinding.FragmentContactsRecyclerBinding;
import cat.urv.comm.padm.persistence.UserRepository;

public class GalleryFragment extends Fragment {

    private FragmentContactsRecyclerBinding binding;
    private RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentContactsRecyclerBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

         recyclerView = binding.contactsRecyclerView;

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (recyclerView != null){
            ContactsRecyclerViewAdapter adapter;
            adapter = new ContactsRecyclerViewAdapter(
                                            getContext(),
                                            R.layout.listview_item,
                                            UserRepository.getContacts(getContext()));
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.setAdapter(adapter);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}