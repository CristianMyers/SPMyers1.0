package com.example.proyecto1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private List<Product> productList;

    public ProductAdapter(List<Product> productList) {
        this.productList = productList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.productName.setText(product.getName());
        holder.productStock.setText("Stock: " + product.getStock());
        holder.productImage.setImageResource(product.getImageResId());

        holder.addStockButton.setOnClickListener(v -> {
            // Aquí puedes manejar la acción de agregar stock
            int currentStock = product.getStock();
            product.setStock(currentStock + 1);
            // Actualiza el texto del stock
            holder.productStock.setText("Stock: " + product.getStock());
            // Notifica al adaptador que los datos han cambiado
            notifyDataSetChanged();
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView productImage;
        TextView productName;
        TextView productStock;
        Button addStockButton;

        public ProductViewHolder(View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.productImage);
            productName = itemView.findViewById(R.id.productName);
            productStock = itemView.findViewById(R.id.productStock);
            addStockButton = itemView.findViewById(R.id.addStockButton);
        }
    }
}
