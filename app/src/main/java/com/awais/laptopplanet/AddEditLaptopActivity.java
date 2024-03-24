package com.awais.laptopplanet;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.awais.laptopplanet.databinding.ActivityAddEditLaptopBinding;
import com.google.gson.Gson;


public class AddEditLaptopActivity extends AppCompatActivity {

    private ActivityAddEditLaptopBinding binding;
    private Laptop laptop = new Laptop();
    private boolean isChecked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddEditLaptopBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Edit items");
        }

        if (getIntent().hasExtra("data")) {
            laptop = new Gson().fromJson(getIntent().getStringExtra("data"), Laptop.class);

            bindLaptopData();
        }

        binding.type.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s != null) {
                    String type = s.toString();
                    if (type.equalsIgnoreCase("laptop")) {
                        binding.image.setImageResource(R.drawable.image1);
                    } else if (type.equalsIgnoreCase("mobile")) {
                        binding.image.setImageResource(R.drawable.image2);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private void bindLaptopData() {
        binding.name.setText(laptop.getName());
        binding.type.setText(laptop.getType());
        binding.specification.setText(laptop.getSpecification());
        binding.price.setText(laptop.getPrice());
        binding.image.setImageResource(laptop.getImage());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_laptops, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.save) {
            saveLaptopData();
            return true;
        } else if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveLaptopData() {
        String name = binding.name.getText().toString();
        String type = binding.type.getText().toString();
        String specification = binding.specification.getText().toString();
        String price = binding.price.getText().toString();

        if (name.isEmpty() || type.isEmpty() || specification.isEmpty() || price.isEmpty()) {
            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
            return;
        }

        laptop.setName(name);
        laptop.setType(type);
        laptop.setSpecification(specification);
        laptop.setPrice(price);

        AppDatabase.getDatabase(this).laptopItemDao().insertOrReplace(laptop);
        Toast.makeText(this, "Laptop details saved successfully", Toast.LENGTH_SHORT).show();
        finish();
    }
}
