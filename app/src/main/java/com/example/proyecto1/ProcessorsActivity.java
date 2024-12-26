package com.example.proyecto1;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class ProcessorsActivity extends AppCompatActivity {

    private ListView listViewProcessors;
    private ProcessorAdapter adapter;
    private ArrayList<Processor> processors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_processors);

        listViewProcessors = findViewById(R.id.listViewProcessors);
        SearchView searchViewProcessor = findViewById(R.id.searchViewProcessor);

        // Crear lista de procesadores
        processors = new ArrayList<>();
        processors.add(new Processor("Intel Core i9-14900KF", "$539990.00", R.drawable.i9_14900kf_image1));
        processors.add(new Processor("AMD Ryzen 9 9950X", "$719224.00", R.drawable.ryzen3));
        processors.add(new Processor("Intel Core i5-12600KF", "$189990.00", R.drawable.i5_12600kf_image1));
        processors.add(new Processor("Intel Core i9-13900K", "$554000.00", R.drawable.i9_13900k));
        processors.add(new Processor("AMD Ryzen 9 7950X", "$599990.00", R.drawable.ryzen9_7950x));
        processors.add(new Processor("AMD Ryzen 7 7700X", "$375000.00", R.drawable.ryzen_7_7700x));
        processors.add(new Processor("Intel Core i3-12100F", "$84990.00", R.drawable.i3_12100f));
        processors.add(new Processor("AMD Ryzen 3 4100", "$84000.00", R.drawable.ryzen_3_4100));
        processors.add(new Processor("Intel Core i9-12900KF", "$353900.00", R.drawable.i9_12900kf));
        processors.add(new Processor("AMD Ryzen Threadripper 3990X", "$5000000.00", R.drawable.ryzen_threadripper_3990x));

        // Configurar el adaptador y asignarlo al ListView
        adapter = new ProcessorAdapter(processors);
        listViewProcessors.setAdapter(adapter);

        // Configurar el SearchView para filtrar la lista en tiempo real
        searchViewProcessor.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return true;
            }
        });
    }

    // Clase Processor para almacenar la información de cada procesador
    private class Processor {
        String name;
        String price;
        int imageResId;

        Processor(String name, String price, int imageResId) {
            this.name = name;
            this.price = price;
            this.imageResId = imageResId;
        }
    }

    // Adaptador personalizado con filtro para mostrar y filtrar procesadores
    private class ProcessorAdapter extends ArrayAdapter<Processor> {

        private ArrayList<Processor> originalProcessors;
        private ArrayList<Processor> filteredProcessors;

        ProcessorAdapter(ArrayList<Processor> processors) {
            super(ProcessorsActivity.this, R.layout.processor_item, processors);
            this.originalProcessors = new ArrayList<>(processors);
            this.filteredProcessors = new ArrayList<>(processors);
        }

        @Override
        public int getCount() {
            return filteredProcessors.size();
        }

        @Override
        public Processor getItem(int position) {
            return filteredProcessors.get(position);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.processor_item, parent, false);
            }

            Processor currentProcessor = getItem(position);

            ImageView imageView = convertView.findViewById(R.id.imageViewProcessor);
            TextView nameTextView = convertView.findViewById(R.id.textViewProcessorName);
            TextView priceTextView = convertView.findViewById(R.id.textViewProcessorPrice);

            imageView.setImageResource(currentProcessor.imageResId);
            nameTextView.setText(currentProcessor.name);
            priceTextView.setText(currentProcessor.price);

            return convertView;
        }

        @Override
        public Filter getFilter() {
            return new Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence constraint) {
                    FilterResults results = new FilterResults();
                    ArrayList<Processor> filteredList = new ArrayList<>();

                    if (constraint == null || constraint.length() == 0) {
                        filteredList.addAll(originalProcessors);
                    } else {
                        String filterPattern = constraint.toString().toLowerCase().trim();

                        for (Processor processor : originalProcessors) {
                            if (processor.name.toLowerCase().contains(filterPattern)) {
                                filteredList.add(processor);
                            }
                        }
                    }

                    results.values = filteredList;
                    results.count = filteredList.size();
                    return results;
                }

                @Override
                protected void publishResults(CharSequence constraint, FilterResults results) {
                    filteredProcessors.clear();
                    filteredProcessors.addAll((ArrayList<Processor>) results.values);
                    notifyDataSetChanged();
                }
            };
        }
    }
}


