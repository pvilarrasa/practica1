package cat.urv.comm.padm.ui.gallery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cat.urv.comm.padm.R;
import cat.urv.comm.padm.persistence.Contact;

public class ContactsRecyclerViewAdapter extends RecyclerView.Adapter {
    private List<Contact> contacts;
    private int itemResourceId;
    private Context context;

    public ContactsRecyclerViewAdapter(Context context, int resource, List<Contact> contacts) {
        this.contacts = contacts;
        this.context = context;
        itemResourceId = resource;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ContactViewHolder contactViewHolder;

        View view = LayoutInflater.from(context)
                .inflate(R.layout.listview_item, parent, false);

        contactViewHolder = new ContactViewHolder(view);
        return contactViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            ContactViewHolder contactViewHolder = (ContactViewHolder) holder;
            contactViewHolder.setData(this.contacts.get(position));

    }

    @Override
    public int getItemCount() {
        if (contacts == null)
            return 0;
        else
            return this.contacts.size();
    }

    private static class ContactViewHolder extends RecyclerView.ViewHolder{

        private TextView name;
        private TextView surnames;
        private TextView age;

        public ContactViewHolder(@NonNull View view) {
            super(view);
            name = view.findViewById(R.id.name);
            surnames = view.findViewById(R.id.surname);
            age = view.findViewById(R.id.age);
        }

        public void setData(Contact contact) {
            this.name.setText(contact.getName());
            this.surnames.setText(contact.getSurnames());
            this.age.setText(String.valueOf(contact.getAge()));
        }
    }
}
