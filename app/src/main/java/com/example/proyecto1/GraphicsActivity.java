package com.example.proyecto1;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Filter;
import android.widget.Filterable;

import java.util.ArrayList;

public class GraphicsActivity extends AppCompatActivity {

    private ListView listViewGraphics;
    private SearchView searchViewGraphics;
    private GraphicsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphics);

        listViewGraphics = findViewById(R.id.listViewGraphics);
        searchViewGraphics = findViewById(R.id.searchViewGraphics);

        // Crear lista de gráficas (ejemplo)
        ArrayList<GraphicsCard> graphicsCards = new ArrayList<>();
        graphicsCards.add(new GraphicsCard("NVIDIA GeForce RTX 4090", "$1992780.00", R.drawable.rtx_4090_image1));
        graphicsCards.add(new GraphicsCard("NVIDIA GeForce RTX 4070", "$699990.00", R.drawable.rtx_4070_image1));
        graphicsCards.add(new GraphicsCard("NVIDIA GeForce RTX 4060", "$321856.00", R.drawable.rtx_4060));
        graphicsCards.add(new GraphicsCard("NVIDIA GeForce RTX 4080", "$1159000.00", R.drawable.rtx_4080));
        graphicsCards.add(new GraphicsCard("AMD Radeon RX 6800 XT", "$799900.00", R.drawable.rx_6800_xt));
        graphicsCards.add(new GraphicsCard("NVIDIA GeForce RTX 3060 Ti", "$389990.00", R.drawable.rtx_3060_ti));
        graphicsCards.add(new GraphicsCard("NVIDIA GeForce GTX 1660 Super", "$189990.00", R.drawable.gtx_1660_super));
        graphicsCards.add(new GraphicsCard("AMD Radeon RX 6700 XT", "$469900.00", R.drawable.radeon_rx_6700_xt));
        graphicsCards.add(new GraphicsCard("NVIDIA GeForce RTX 3050", "$185190.00", R.drawable.rtx_3050));
        graphicsCards.add(new GraphicsCard("AMD Radeon RX 6600", "$255980.00", R.drawable.radeon_rx_6600));

        // Configurar adaptador y asignarlo al ListView
        adapter = new GraphicsAdapter(graphicsCards);
        listViewGraphics.setAdapter(adapter);

        // Configurar el SearchView
        searchViewGraphics.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false; // No hacemos nada aquí
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Filtrar la lista cuando el texto cambie
                adapter.getFilter().filter(newText);
                return true;
            }
        });
    }

    // Clase GraphicsCard para almacenar la información de cada tarjeta gráfica
    private class GraphicsCard {
        String name;
        String price;
        int imageResId;

        GraphicsCard(String name, String price, int imageResId) {
            this.name = name;
            this.price = price;
            this.imageResId = imageResId;
        }
    }

    // Adaptador personalizado para mostrar cada tarjeta gráfica en el ListView
    private class GraphicsAdapter extends ArrayAdapter<GraphicsCard> implements Filterable {
        private ArrayList<GraphicsCard> originalList;
        private ArrayList<GraphicsCard> filteredList;

        GraphicsAdapter(ArrayList<GraphicsCard> graphicsCards) {
            super(GraphicsActivity.this, R.layout.graphics_item, graphicsCards);
            this.originalList = new ArrayList<>(graphicsCards);
            this.filteredList = new ArrayList<>(graphicsCards);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.graphics_item, parent, false);
            }

            GraphicsCard currentGraphicsCard = getItem(position);

            ImageView imageView = convertView.findViewById(R.id.imageViewGraphics);
            TextView nameTextView = convertView.findViewById(R.id.textViewGraphicsName);
            TextView priceTextView = convertView.findViewById(R.id.textViewGraphicsPrice);

            imageView.setImageResource(currentGraphicsCard.imageResId);
            nameTextView.setText(currentGraphicsCard.name);
            priceTextView.setText(currentGraphicsCard.price);

            return convertView;
        }

        @Override
        public int getCount() {
            return filteredList.size();
        }

        @Override
        public GraphicsCard getItem(int position) {
            return filteredList.get(position);
        }

        @Override
        public Filter getFilter() {
            return new Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence constraint) {
                    String query = constraint.toString().toLowerCase();
                    FilterResults results = new FilterResults();
                    ArrayList<GraphicsCard> filteredResults = new ArrayList<>();

                    if (query.isEmpty()) {
                        filteredResults.addAll(originalList);
                    } else {
                        for (GraphicsCard card : originalList) {
                            if (card.name.toLowerCase().contains(query)) {
                                filteredResults.add(card);
                            }
                        }
                    }

                    results.values = filteredResults;
                    results.count = filteredResults.size();
                    return results;
                }

                @Override
                protected void publishResults(CharSequence constraint, FilterResults results) {
                    filteredList.clear();
                    if (results.count > 0) {
                        filteredList.addAll((ArrayList<GraphicsCard>) results.values);
                    }
                    notifyDataSetChanged(); // Notificar al adaptador para refrescar la lista
                }
            };
        }
    }
}

