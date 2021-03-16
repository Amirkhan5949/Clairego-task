package com.codeinger.roomdb.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codeinger.roomdb.R;
import com.codeinger.roomdb.db.database.Product;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    List<Product> list;

    public ProductAdapter(List<Product> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        holder.tv_Name.setText(list.get(position).getName());

        Log.i("dsfdfdfdgd", "onBindViewHolder: "+list.get(position).getImage().toString());



        if (list.get(position).getImage() == null || list.get(position).getImage().isEmpty()){
            Picasso.get().load(R.drawable.download).into(holder.img);
        }else {
            Picasso.get().load(list.get(position).getImage()).into(holder.img);

        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_Name;
        private ImageView img;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_Name=itemView.findViewById(R.id.tv_Name);
            img=itemView.findViewById(R.id.img);
        }
    }
}
