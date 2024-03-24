package com.awais.laptopplanet;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.awais.laptopplanet.databinding.ActivityLaptopDetailsBinding;
import com.google.gson.Gson;

public class LaptopDetailsActivity extends AppCompatActivity {

    ActivityLaptopDetailsBinding binding;
    Laptop laptop;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLaptopDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Laptop Details");
        }

        laptop = new Gson().fromJson(getIntent().getStringExtra("data"), Laptop.class);

        binding.name.setText(laptop.getName());
        binding.type.setText(laptop.getType());
        binding.specification.setText(laptop.getSpecification());
        binding.price.setText(laptop.getPrice());


        // Set the image resource based on the laptop's type
        if (laptop.getType().equalsIgnoreCase("laptop")) {
            binding.image.setImageResource(R.drawable.image1);
        } else if (laptop.getType().equalsIgnoreCase("mobile")) {
            binding.image.setImageResource(R.drawable.image2);
        }
        binding.btnSold.setOnClickListener(v -> {
            laptop.setStatus("Sold");
            AppDatabase.getDatabase(this).laptopItemDao().update(laptop);
            Toast.makeText(this, "Marked as Sold", Toast.LENGTH_SHORT).show();
        });


        binding.btnNotSold.setOnClickListener(v -> {
            laptop.setStatus("IN Stock");
            AppDatabase.getDatabase(this).laptopItemDao().update(laptop);
            Toast.makeText(this, "Marked as Sold", Toast.LENGTH_SHORT).show();
        });


        // Set click listeners for edit and delete buttons
        binding.edit.setOnClickListener(v -> {
            startActivity(new Intent(this, AddEditLaptopActivity.class).putExtra("data", new Gson().toJson(laptop)));
            finish();
        });
        binding.delete.setOnClickListener(v -> {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Sure to delete?");
            alert.setMessage("Are you sure you want to delete?");
            alert.setCancelable(false);
            alert.setPositiveButton("Yes, delete", (dialog, which) -> {
                AppDatabase.getDatabase(this).laptopItemDao().delete(laptop);
                Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show();
                finish();
            });
            alert.setNegativeButton(android.R.string.no, (dialog, which) -> {
                dialog.dismiss();
            });
            alert.show();
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
