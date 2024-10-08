package com.example.proyecto1;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import java.util.ArrayList;
import java.util.List;

public class RTX4090Activity extends AppCompatActivity {

    private ViewPager2 viewPager2;
    private RTX4090ImageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rtx4090);  // Asegúrate de que el nombre del layout coincida con el XML correcto

        // Referencias a las vistas del XML
        viewPager2 = findViewById(R.id.viewPager);

        // Crear una lista de imágenes (estas son las que has añadido a /res/drawable)
        List<Integer> images = new ArrayList<>();
        images.add(R.drawable.rtx_4090_image1);
        images.add(R.drawable.rtx_4090_image2);
        images.add(R.drawable.rtx_4090_image3);
        images.add(R.drawable.rtx_4090_image4);
        // Añade más imágenes según sea necesario

        // Configurar el adaptador con la lista de imágenes
        adapter = new RTX4090ImageAdapter(images);
        viewPager2.setAdapter(adapter);
    }
}




