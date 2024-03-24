package com.awais.laptopplanet;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    LaptopAdapter adapter;
    List<Laptop> data = new ArrayList<>();
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new LaptopAdapter(this, data);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        // Set up FloatingActionButton click listener
        findViewById(R.id.addFab).setOnClickListener(v -> {
            startActivity(new Intent(this, AddEditLaptopActivity.class));
        });

        // Set up BottomNavigationView item selection listener
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnItemSelectedListener(menuItem -> {
            if (menuItem.getItemId() == R.id.home) {
                // Show all items
                loadAllItems();
            } else if (menuItem.getItemId() == R.id.sold) {
                // Show only sold items
                loadSoldItems();
            } else if (menuItem.getItemId() == R.id.instock) {
                // Show only instock items
                loadInStockItems();
            }
            return true;
        });

        // Set up DrawerLayout and ActionBarDrawerToggle
        drawerLayout = findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(
                this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    // Method to load all items
    private void loadAllItems() {
        data.clear();
        data.addAll(AppDatabase.getDatabase(this).laptopItemDao().getAll());
        adapter.notifyDataSetChanged();
    }

    // Method to load sold items
    private void loadSoldItems() {
        data.clear();
        data.addAll(AppDatabase.getDatabase(this).laptopItemDao().getAllSold());
        adapter.notifyDataSetChanged();
    }

    // Method to load in-stock items
    private void loadInStockItems() {
        data.clear();
        data.addAll(AppDatabase.getDatabase(this).laptopItemDao().getAllInStock());
        adapter.notifyDataSetChanged();
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onResume() {
        super.onResume();
        // Load in-stock items by default
        loadInStockItems();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // Pass the selected item to ActionBarDrawerToggle
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
