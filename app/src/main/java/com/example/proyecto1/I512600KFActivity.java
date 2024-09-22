package com.example.proyecto1;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import java.util.ArrayList;
import java.util.List;

public class I512600KFActivity extends AppCompatActivity {

    private ViewPager2 viewPager2;
    private I512600KFImageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_i512600kf);  // Asegúrate de que el nombre del layout coincide con el XML correcto

        // Referencias a las vistas del XML
        viewPager2 = findViewById(R.id.viewPager);

        // Crear una lista de imágenes
        List<Integer> images = new ArrayList<>();
        images.add(R.drawable.i5_12600kf_image1);
        images.add(R.drawable.i5_12600kf_image2);
        images.add(R.drawable.i5_12600kf_image3);
        images.add(R.drawable.i5_12600kf_image4);
        // Añade más imágenes según sea necesario

        // Configurar el adaptador con la lista de imágenes
        adapter = new I512600KFImageAdapter(images);
        viewPager2.setAdapter(adapter);
    }
}

