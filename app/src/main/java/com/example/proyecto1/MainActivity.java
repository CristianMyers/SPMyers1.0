package com.example.proyecto1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Configuración de los insets del sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Buscar el botón de "Ver más" del primer bloque y configurar su listener
        Button button1 = findViewById(R.id.button1);
        button1.setOnClickListener(v -> {
            // Crear un Intent para navegar a RTX4090Activity
            Intent intent = new Intent(MainActivity.this, RTX4090Activity.class);
            startActivity(intent); // Iniciar la actividad RTX4090
        });

        // Buscar el botón y configurar su listener para ProductoActivity
        Button productButton = findViewById(R.id.productButton);
        productButton.setOnClickListener(v -> {
            // Crear un Intent para navegar a ProductoActivity
            Intent intent = new Intent(MainActivity.this, ProductoActivity.class);
            startActivity(intent); // Iniciar la actividad Producto
        });
    }
}



