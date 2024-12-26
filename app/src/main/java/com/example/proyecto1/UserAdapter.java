package com.example.proyecto1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class UserAdapter extends ArrayAdapter<User> {
    private Context context;
    private List<User> users;
    private DatabaseHelper db;

    public UserAdapter(Context context, List<User> users, DatabaseHelper db) {
        super(context, 0, users);
        this.context = context;
        this.users = users;
        this.db = db;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.user_item, parent, false);
        }

        User user = users.get(position);

        TextView usernameTextView = convertView.findViewById(R.id.usernameTextView);
        EditText passwordEditText = convertView.findViewById(R.id.passwordEditText);
        Button editButton = convertView.findViewById(R.id.editButton);
        Button deleteButton = convertView.findViewById(R.id.deleteButton);

        usernameTextView.setText(user.getUsername());
        passwordEditText.setText(user.getPassword());

        editButton.setOnClickListener(v -> {
            String newPassword = passwordEditText.getText().toString().trim();
            if (db.updateUser(user.getId(), user.getUsername(), newPassword)) {
                Toast.makeText(context, "Usuario actualizado", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Error al actualizar", Toast.LENGTH_SHORT).show();
            }
        });

        deleteButton.setOnClickListener(v -> {
            if (db.deleteUser(user.getId())) {
                users.remove(user);
                notifyDataSetChanged();
                Toast.makeText(context, "Usuario eliminado", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Error al eliminar", Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }
}
