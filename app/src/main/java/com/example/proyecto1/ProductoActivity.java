package com.example.proyecto1;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ProductoActivity extends AppCompatActivity {

    private RecyclerView recyclerViewProducts;
    private ProductAdapter productAdapter;
    private List<Product> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.producto);

        // Configuración del RecyclerView
        recyclerViewProducts = findViewById(R.id.recyclerViewProducts); // Verifica que este ID coincida con el XML
        recyclerViewProducts.setLayoutManager(new LinearLayoutManager(this));

        // Inicializar la lista de productos
        productList = new ArrayList<>();

        // Añadir productos a la lista
        productList.add(new Product("RTX 4090", R.drawable.picture1, 10));
        productList.add(new Product("I9 14900KF", R.drawable.picture2, 15));
        productList.add(new Product("Ryzen 9 9950X", R.drawable.picture3, 8));
        productList.add(new Product("RTX 4070", R.drawable.picture4, 20));
        productList.add(new Product("i5-12600KF", R.drawable.picture5, 12));
        productList.add(new Product("Odyssey G6", R.drawable.picture6, 5));

        // Crear y establecer el adaptador para el RecyclerView
        productAdapter = new ProductAdapter(productList);
        recyclerViewProducts.setAdapter(productAdapter);
    }
}





