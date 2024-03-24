package com.awais.laptopplanet;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;


public class LaptopItemViewHolder extends RecyclerView.ViewHolder {


    ImageView image;
    TextView name;
    TextView type;
    public ImageView sold;
    TextView price;
    TextView status;
    ConstraintLayout post;

    public LaptopItemViewHolder(@NonNull View itemView) {

        super(itemView);

        image = itemView.findViewById(R.id.image);
        name = itemView.findViewById(R.id.name);
        type = itemView.findViewById(R.id.type);
        status = itemView.findViewById(R.id.status);
        sold=itemView.findViewById(R.id.sold);
        price = itemView.findViewById(R.id.price);

        post = itemView.findViewById(R.id.post);
    }
}
