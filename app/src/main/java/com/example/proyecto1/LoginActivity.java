package com.example.proyecto1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText usernameEditText = findViewById(R.id.usernameEditText);
        EditText passwordEditText = findViewById(R.id.passwordEditText);
        Button loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                // Verificar si el usuario es el administrador
                if (username.equals("CristianMyers") && password.equals("12345")) {
                    // Es administrador
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("IS_ADMIN", true);  // Enviar el estado de administrador
                    setResult(RESULT_OK, resultIntent);
                    Toast.makeText(LoginActivity.this, "Bienvenido, Admin", Toast.LENGTH_SHORT).show();
                    finish();  // Cierra la actividad
                } else if (!username.isEmpty() && !password.isEmpty()) {
                    // Cualquier otro usuario
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("IS_ADMIN", false);  // No es administrador
                    setResult(RESULT_OK, resultIntent);
                    Toast.makeText(LoginActivity.this, "Bienvenido, " + username, Toast.LENGTH_SHORT).show();
                    finish();  // Cierra la actividad
                } else {
                    // Si alguno de los campos está vacío
                    Toast.makeText(LoginActivity.this, "Por favor, introduce un nombre de usuario y contraseña válidos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}






