package com.awais.laptopplanet;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.util.List;

public class LaptopAdapter extends RecyclerView.Adapter<LaptopItemViewHolder> {

    private Context context;
    private List<Laptop> data;

    public LaptopAdapter(Context context, List<Laptop> data) {
        this.context = context;
        this.data = data;
    }

    public void updateStatus(int position, String status) {
        data.get(position).setStatus(status);
        notifyItemChanged(position);
    }

    @NonNull
    @Override
    public LaptopItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.grid_item, parent, false);
        return new LaptopItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LaptopItemViewHolder holder, int position) {
        Laptop laptop = data.get(position);
        holder.name.setText(laptop.getName());
        holder.type.setText(laptop.getType());


        holder.status.setText(laptop.getStatus());

        holder.price.setText(String.valueOf(laptop.getPrice()));

        // Set the image based on the type of device
        if (laptop.getType().equalsIgnoreCase("laptop")) {
            holder.image.setImageResource(R.drawable.image1);
        } else if (laptop.getType().equalsIgnoreCase("mobile")) {
            holder.image.setImageResource(R.drawable.image2);
        }

        holder.post.setOnClickListener(v -> {
            Intent intent = new Intent(context, LaptopDetailsActivity.class);
            intent.putExtra("data", new Gson().toJson(laptop));
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
