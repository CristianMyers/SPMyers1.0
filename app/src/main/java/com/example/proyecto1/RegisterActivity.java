package com.example.proyecto1;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class RegisterActivity extends Activity {

    DatabaseHelper db;
    boolean isAdmin;
    ListView usersListView;
    UserAdapter userAdapter;
    List<User> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = new DatabaseHelper(this);
        userList = new ArrayList<>();
        usersListView = findViewById(R.id.usersListView);

        Intent intent = getIntent();
        isAdmin = intent.getBooleanExtra("IS_ADMIN", false);

        EditText usernameEditText = findViewById(R.id.usernameEditText);
        EditText passwordEditText = findViewById(R.id.passwordEditText);
        Button registerButton = findViewById(R.id.registerButton);
        Button viewUsersButton = findViewById(R.id.viewUsersButton);

        if (isAdmin) {
            viewUsersButton.setVisibility(View.VISIBLE);
        } else {
            viewUsersButton.setVisibility(View.GONE);
        }

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                if (!username.isEmpty() && !password.isEmpty()) {
                    boolean isInserted = db.insertUser(username, password);
                    if (isInserted) {
                        Toast.makeText(RegisterActivity.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                        usernameEditText.setText("");
                        passwordEditText.setText("");
                    } else {
                        Toast.makeText(RegisterActivity.this, "Error en el registro", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(RegisterActivity.this, "Completa todos los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        viewUsersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadUsers();
            }
        });
    }

    private void loadUsers() {
        userList.clear();
        Cursor cursor = db.getAllUsers();

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_1));
                    String username = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_2));
                    String password = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_3));
                    userList.add(new User(id, username, password));
                } while (cursor.moveToNext());
            }
            cursor.close();
        }

        if (!userList.isEmpty()) {
            userAdapter = new UserAdapter(RegisterActivity.this, userList, db);
            usersListView.setAdapter(userAdapter);
        } else {
            Toast.makeText(RegisterActivity.this, "No hay usuarios registrados", Toast.LENGTH_SHORT).show();
        }
    }
}
