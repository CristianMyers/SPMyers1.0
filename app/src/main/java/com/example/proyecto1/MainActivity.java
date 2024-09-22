package com.example.proyecto1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import androidx.core.view.GravityCompat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private NavigationView cartNavigationView;
    private LinearLayout cartItemsContainer;
    private List<CartItem> cartItems = new ArrayList<>();
    private boolean isAdmin = false; // Variable para verificar si es admin

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

        // Configuración del Drawer y NavigationView
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        cartNavigationView = findViewById(R.id.cart_navigation_view);
        cartItemsContainer = cartNavigationView.findViewById(R.id.cart_items_container);

        // Mostrar la pantalla de inicio de sesión
        Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
        startActivityForResult(loginIntent, 1); // Cambié el diálogo a la actividad de login

        // Listener para los ítems del menú de navegación
        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_processors) {
                navigateToCategory("Procesadores");
            } else if (id == R.id.nav_graphics) {
                navigateToCategory("Gráficas");
            } else if (id == R.id.nav_ram) {
                navigateToCategory("RAM");
            } else if (id == R.id.nav_power_supplies) {
                navigateToCategory("Fuentes de Poder");
            } else if (id == R.id.nav_cabinets) {
                navigateToCategory("Gabinetes");
            } else if (id == R.id.nav_peripherals) {
                navigateToCategory("Periféricos");
            }

            // Cierra el Drawer después de seleccionar un ítem
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });

        // Configura el botón del menú de hamburguesa
        findViewById(R.id.menu_button).setOnClickListener(v -> {
            drawerLayout.openDrawer(GravityCompat.START);  // Abre el Drawer de navegación
        });

        // Configura el botón del carrito
        findViewById(R.id.cart_button).setOnClickListener(v -> {
            drawerLayout.openDrawer(GravityCompat.END);  // Abre el Drawer del carrito
        });

        // Configura botones "Ver más"
        setupViewMoreButtons();

        // Modifica las llamadas a addToCart para incluir el precio del producto
        setupProductButtons();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            isAdmin = data.getBooleanExtra("IS_ADMIN", false);
            if (isAdmin) {
                Toast.makeText(this, "Bienvenido, Admin.", Toast.LENGTH_SHORT).show();
                setupAdminButtons();  // Aquí se muestra y configura los botones de admin
                showAdminSnackbar();  // Mostrar Snackbar para admin
            } else {
                Toast.makeText(this, "Iniciaste sesión como usuario.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void showAdminSnackbar() {
        Snackbar.make(findViewById(R.id.main),
                        "Has iniciado sesión como Admin. Puedes ver y gestionar los productos.",
                        Snackbar.LENGTH_INDEFINITE)
                .setAction("Cerrar", v -> { /* Acción opcional */ })
                .show();
    }

    private void setupAdminButtons() {
        // Asegúrate de que los ids coincidan con los del XML
        Button productButton = findViewById(R.id.productButton);
        Button salesButton = findViewById(R.id.salesButton);

        // Verifica si los botones no son null (por si hay algún problema al cargarlos)
        if (productButton == null || salesButton == null) {
            Toast.makeText(this, "Error al encontrar los botones de administrador", Toast.LENGTH_SHORT).show();
            return;
        }

        // Mostrar los botones solo si es admin
        productButton.setVisibility(View.VISIBLE);
        salesButton.setVisibility(View.VISIBLE);

        // Configurar acciones para los botones de productos y ventas
        productButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ProductoActivity.class);
            startActivity(intent);
        });

        salesButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, VentasActivity.class);
            startActivity(intent);
        });
    }

    private void setupProductButtons() {
        Button addToCartButton1 = findViewById(R.id.addToCartButton1);
        addToCartButton1.setOnClickListener(v -> addToCart(R.drawable.rtx_4090_image1, "RTX 4090 MSI", 1992780.00));

        Button addToCartButton2 = findViewById(R.id.addToCartButton2);
        addToCartButton2.setOnClickListener(v -> addToCart(R.drawable.picture2, "I9 14900KF", 664990.00));

        Button addToCartButton3 = findViewById(R.id.addToCartButton3);
        addToCartButton3.setOnClickListener(v -> addToCart(R.drawable.picture3, "Ryzen 9 9950X", 777407.00));

        Button addToCartButton4 = findViewById(R.id.addToCartButton4);
        addToCartButton4.setOnClickListener(v -> addToCart(R.drawable.picture4, "RTX 4070", 699990.00));

        Button addToCartButton5 = findViewById(R.id.addToCartButton5);
        addToCartButton5.setOnClickListener(v -> addToCart(R.drawable.picture5, "i5-12600KF", 207490.00));

        Button addToCartButton6 = findViewById(R.id.addToCartButton6);
        addToCartButton6.setOnClickListener(v -> addToCart(R.drawable.picture6, "Odyssey G6", 416990.00));
    }

    private void setupViewMoreButtons() {
        // Botón "Ver más" del bloque 1 - RTX 4090
        Button button1 = findViewById(R.id.button1);
        button1.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, RTX4090Activity.class);
            startActivity(intent);  // Inicia la actividad RTX4090
        });

        // Botón "Ver más" del bloque 2 - i9 14900KF
        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, I914900KFActivity.class);
            startActivity(intent);  // Inicia la actividad i914900KF
        });
        // Botón "Ver más" del bloque 3 - Ryzen 9 9950X
        Button button3 = findViewById(R.id.button3);
        button3.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Ryzen9950XActivity.class);
            startActivity(intent);  // Inicia la actividad Ryzen9950X
        });
        // Botón "Ver más" del bloque 4 - RTX 4070
        Button button4 = findViewById(R.id.button4);
        button4.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, RTX4070Activity.class);
            startActivity(intent);  // Inicia la actividad RTX4070
        });
        // Botón "Ver más" del bloque 5 - Intel Core i5-12600KF
        Button button5 = findViewById(R.id.button5);
        button5.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, I512600KFActivity.class);
            startActivity(intent);  // Inicia la actividad I512600KF
        });
        // Botón "Ver más" del bloque 6 - Samsung Odyssey G6
        Button button6 = findViewById(R.id.button6);
        button6.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SamsungOdysseyG6Activity.class);
            startActivity(intent);  // Inicia la actividad SamsungOdysseyG6
        });
    }

    private void navigateToCategory(String category) {
        Intent intent = new Intent(MainActivity.this, CategoryActivity.class);
        intent.putExtra("CATEGORY_NAME", category);
        startActivity(intent);
    }

    private void addToCart(int imageResId, String productName, double price) {
        CartItem item = new CartItem(imageResId, productName, price, 1);
        cartItems.add(item);
        updateCart();
        Toast.makeText(this, productName + " añadido al carrito", Toast.LENGTH_SHORT).show();
    }

    private void updateCart() {
        cartItemsContainer.removeAllViews();
        double totalCartPrice = 0.0;

        for (CartItem item : cartItems) {
            View cartItemView = getLayoutInflater().inflate(R.layout.cart_item, cartItemsContainer, false);
            ImageView imageView = cartItemView.findViewById(R.id.cart_item_image);
            TextView nameTextView = cartItemView.findViewById(R.id.cart_item_name);
            Button decreaseButton = cartItemView.findViewById(R.id.decrease_quantity);
            Button increaseButton = cartItemView.findViewById(R.id.increase_quantity);
            Button removeButton = cartItemView.findViewById(R.id.remove_product);
            TextView quantityTextView = cartItemView.findViewById(R.id.cart_item_quantity);
            TextView priceTextView = cartItemView.findViewById(R.id.cart_item_price);

            imageView.setImageResource(item.getImageResId());
            nameTextView.setText(item.getProductName());
            quantityTextView.setText(String.valueOf(item.getQuantity()));
            double itemPrice = item.getPrice();
            priceTextView.setText(String.format("Precio: $%.2f", itemPrice));

            double itemTotal = itemPrice * item.getQuantity();
            totalCartPrice += itemTotal;

            decreaseButton.setOnClickListener(v -> {
                if (item.getQuantity() > 1) {
                    item.setQuantity(item.getQuantity() - 1);
                    updateCart();
                }
            });

            increaseButton.setOnClickListener(v -> {
                item.setQuantity(item.getQuantity() + 1);
                updateCart();
            });

            removeButton.setOnClickListener(v -> {
                cartItems.remove(item);
                updateCart();
            });

            cartItemsContainer.addView(cartItemView);
        }

        TextView totalCartPriceTextView = findViewById(R.id.total_cart_price);
        totalCartPriceTextView.setText(String.format("Total del carrito: $%.2f", totalCartPrice));
    }

    private static class CartItem {
        private final int imageResId;
        private final String productName;
        private final double price;
        private int quantity;

        CartItem(int imageResId, String productName, double price, int quantity) {
            this.imageResId = imageResId;
            this.productName = productName;
            this.price = price;
            this.quantity = quantity;
        }

        public int getImageResId() {
            return imageResId;
        }

        public String getProductName() {
            return productName;
        }

        public double getPrice() {
            return price;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public double getTotalPrice() {
            return price * quantity;
        }
    }
}













