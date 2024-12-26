package com.example.proyecto1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = new DatabaseHelper(this);
        EditText usernameEditText = findViewById(R.id.usernameEditText);
        EditText passwordEditText = findViewById(R.id.passwordEditText);
        Button loginButton = findViewById(R.id.loginButton);
        Button registerButton = findViewById(R.id.registerButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                if (username.equals("CristianMyers") && password.equals("12345")) {
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("IS_ADMIN", true);
                    setResult(RESULT_OK, resultIntent);
                    Toast.makeText(LoginActivity.this, "Bienvenido, Admin", Toast.LENGTH_SHORT).show();
                    finish();
                } else if (db.checkUser(username, password)) {
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("IS_ADMIN", false);
                    setResult(RESULT_OK, resultIntent);
                    Toast.makeText(LoginActivity.this, "Bienvenido, " + username, Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                registerIntent.putExtra("IS_ADMIN", true);
                startActivity(registerIntent);
            }
        });
    }
}







