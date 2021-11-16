package cat.urv.comm.padm.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import cat.urv.comm.padm.R;
import cat.urv.comm.padm.databinding.FragmentContactsBinding;
import cat.urv.comm.padm.persistence.UserRepository;

public class ContactsFragment extends Fragment {

    private FragmentContactsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentContactsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ListView listView = view.findViewById(R.id.contacts_listview);
        if (listView != null) {
            String from[] = {UserRepository.FIELD_NAME,
                    UserRepository.FIELD_SURNAMES,
                    UserRepository.FIELD_AGE};
            int to[] = {R.id.name, R.id.surname, R.id.age};
            SimpleAdapter simpleAdapter = new SimpleAdapter(view.getContext(),
                    UserRepository.getContactsMap(view.getContext()),
                    R.layout.listview_item, from, to);

            listView.setAdapter(simpleAdapter);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}