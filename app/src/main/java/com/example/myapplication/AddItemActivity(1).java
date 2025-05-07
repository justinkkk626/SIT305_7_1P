package com.example.a7_1justin;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddItemActivity extends AppCompatActivity {

    EditText etTitle, etDesc, etDate, etLocation, etContact;
    Button btnSave;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        // 初始化控件
        etTitle = findViewById(R.id.etTitle);
        etDesc = findViewById(R.id.etDesc);
        etDate = findViewById(R.id.etDate);
        etLocation = findViewById(R.id.etLocation);
        etContact = findViewById(R.id.etContact);
        btnSave = findViewById(R.id.btnSave);

        db = new DatabaseHelper(this);

        btnSave.setOnClickListener(v -> {
            db.insertItem(
                    etTitle.getText().toString(),
                    etDesc.getText().toString(),
                    etDate.getText().toString(),
                    etLocation.getText().toString(),
                    etContact.getText().toString()
            );
            Toast.makeText(this, "Item Added Successfully", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(AddItemActivity.this, MainActivity.class));
            finish();
        });
    }
}

