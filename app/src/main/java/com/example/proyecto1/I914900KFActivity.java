package com.example.proyecto1;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;

public class I914900KFActivity extends AppCompatActivity {

    private ViewPager2 viewPager2;
    private RTX4090ImageAdapter adapter; // Puedes renombrar esto a algo más genérico si quieres
    private TextView productName;
    private TextView productDescription;
    private TextView productSpecs;
    private TextView productPrice;
    private Button buyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.i914900kf);  // Asegúrate de que el nombre del layout coincida con el XML

        // Referencias a las vistas del XML
        viewPager2 = findViewById(R.id.viewPager);
        productName = findViewById(R.id.productName);
        productDescription = findViewById(R.id.productDescription);
        productSpecs = findViewById(R.id.productSpecs);
        productPrice = findViewById(R.id.productPrice);
        buyButton = findViewById(R.id.buyButton);

        // Crear una lista de imágenes (estas son las que has añadido a /res/drawable)
        List<Integer> images = new ArrayList<>();
        images.add(R.drawable.i9_14900kf_image1); // Asegúrate de que estos recursos existan
        images.add(R.drawable.i9_14900kf_image2);
        images.add(R.drawable.i9_14900kf_image3);
        // Añade más imágenes según sea necesario

        // Configurar el adaptador con la lista de imágenes
        adapter = new RTX4090ImageAdapter(images); // Puedes renombrar esto a algo más genérico si quieres
        viewPager2.setAdapter(adapter);

        // Configurar el texto de las vistas (si es necesario, puedes cambiar esto a valores de strings.xml)
        productName.setText("Intel Core i9-14900KF");
        productDescription.setText(getString(R.string.product_description));
        productSpecs.setText(getString(R.string.product_specs));
        productPrice.setText("Precio: $664990.00"); // Actualiza el precio según sea necesario

        // Configurar el botón "Comprar" (puedes agregar un listener para manejar las acciones del botón)
        buyButton.setOnClickListener(v -> {
            // Acción para el botón de compra
            // Por ejemplo, puedes iniciar una nueva actividad o abrir un enlace a una tienda
        });
    }
}

