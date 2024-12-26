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
        productList.add(new Product("Corsair Vengeance LPX 16GB", R.drawable.corsair_vengeance_lpx_16gb, 10));
        productList.add(new Product("G.Skill Ripjaws V 16GB", R.drawable.gskill_ripjaws, 10));
        productList.add(new Product("Kingston HyperX Fury 16GB", R.drawable.kingston_hyperx, 10));
        productList.add(new Product("Crucial Ballistix 16GB", R.drawable.crucial_ballistix, 10));
        productList.add(new Product("Patriot Viper Steel 16GB", R.drawable.patriot_viper, 10));
        productList.add(new Product("Corsair Dominator Platinum 16GB", R.drawable.corsair_dominator, 10));
        productList.add(new Product("G.Skill Trident Z 16GB", R.drawable.gskill_trident, 10));
        productList.add(new Product("Team T-Force Vulcan 16GB", R.drawable.team_t_force, 10));
        productList.add(new Product("HyperX Fury Beast RGB 32GB", R.drawable.hyperx_fury, 10));
        productList.add(new Product("Crucial Ballistix Max 32GB", R.drawable.crucial_ballistix_max, 10));
        productList.add(new Product("Intel Core i9-14900KF", R.drawable.i9_14900kf_image1, 10));
        productList.add(new Product("AMD Ryzen 9 9950X", R.drawable.ryzen3, 10));
        productList.add(new Product("Intel Core i5-12600KF", R.drawable.i5_12600kf_image1, 10));
        productList.add(new Product("Intel Core i9-13900K", R.drawable.i9_13900k, 10));
        productList.add(new Product("AMD Ryzen 9 7950X", R.drawable.ryzen9_7950x, 10));
        productList.add(new Product("AMD Ryzen 7 7700X", R.drawable.ryzen_7_7700x, 10));
        productList.add(new Product("Intel Core i3-12100F", R.drawable.i3_12100f, 10));
        productList.add(new Product("AMD Ryzen 3 4100", R.drawable.ryzen_3_4100, 10));
        productList.add(new Product("Intel Core i9-12900KF", R.drawable.i9_12900kf, 10));
        productList.add(new Product("AMD Ryzen Threadripper 3990X", R.drawable.ryzen_threadripper_3990x, 10));
        productList.add(new Product("NVIDIA GeForce RTX 4090", R.drawable.rtx_4090_image1, 10));
        productList.add(new Product("NVIDIA GeForce RTX 4070", R.drawable.rtx_4070_image1, 10));
        productList.add(new Product("NVIDIA GeForce RTX 4060", R.drawable.rtx_4060, 10));
        productList.add(new Product("NVIDIA GeForce RTX 4080", R.drawable.rtx_4080, 10));
        productList.add(new Product("AMD Radeon RX 6800 XT", R.drawable.rx_6800_xt, 10));
        productList.add(new Product("NVIDIA GeForce RTX 3060 Ti", R.drawable.rtx_3060_ti, 10));
        productList.add(new Product("NVIDIA GeForce GTX 1660 Super", R.drawable.gtx_1660_super, 10));
        productList.add(new Product("AMD Radeon RX 6700 XT", R.drawable.radeon_rx_6700_xt, 10));
        productList.add(new Product("NVIDIA GeForce RTX 3050", R.drawable.rtx_3050, 10));
        productList.add(new Product("AMD Radeon RX 6600", R.drawable.radeon_rx_6600, 10));

        // Crear y establecer el adaptador para el RecyclerView
        productAdapter = new ProductAdapter(productList);
        recyclerViewProducts.setAdapter(productAdapter);
    }
}





