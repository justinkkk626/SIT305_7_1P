// MainActivity.java
package com.example.a7_1justin;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    Button btnLost, btnFound;
    ArrayList<Item> itemList;
    DatabaseHelper db;
    ItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        btnLost = findViewById(R.id.btnLost);
        btnFound = findViewById(R.id.btnFound);

        db = new DatabaseHelper(this);
        itemList = new ArrayList<>();

        loadItemsFromDatabase();

        btnLost.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddItemActivity.class);
            intent.putExtra("postType", "Lost");
            startActivity(intent);
        });

        btnFound.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddItemActivity.class);
            intent.putExtra("postType", "Found");
            startActivity(intent);
        });

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Item selectedItem = itemList.get(position);
            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            intent.putExtra("id", selectedItem.getId());
            intent.putExtra("title", selectedItem.getTitle());
            intent.putExtra("desc", selectedItem.getDesc());
            intent.putExtra("date", selectedItem.getDate());
            intent.putExtra("location", selectedItem.getLocation());
            intent.putExtra("contact", selectedItem.getContact());
            startActivity(intent);
        });

        listView.setOnItemLongClickListener((parent, view, position, id) -> {
            Item selectedItem = itemList.get(position);

            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Delete Item")
                    .setMessage("Are you sure you want to delete this item: " + selectedItem.getTitle() + "?")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        db.deleteItem(selectedItem.getId());
                        itemList.remove(position);
                        adapter.notifyDataSetChanged();
                        Toast.makeText(MainActivity.this, "Item Deleted", Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton("No", null)
                    .show();

            return true;
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadItemsFromDatabase();
    }

    private void loadItemsFromDatabase() {
        itemList.clear();
        itemList.addAll(db.getAllItems());
        if (adapter == null) {
            adapter = new ItemAdapter(this, itemList);
            listView.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
    }
}
