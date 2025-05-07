package com.example.a7_1justin;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    TextView tvTitle, tvDescription, tvDate, tvLocation, tvContact;
    Button btnRemove;
    DatabaseHelper db;
    Item item; // 🔴 必须声明 item

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail); // ✅ 正确使用 activity_detail.xml

        // 初始化视图
        tvTitle = findViewById(R.id.tvTitle);
        tvDescription = findViewById(R.id.tvDescription);
        tvDate = findViewById(R.id.tvDate);
        tvLocation = findViewById(R.id.tvLocation);
        tvContact = findViewById(R.id.tvContact);
        btnRemove = findViewById(R.id.btnRemove);

        int id = getIntent().getIntExtra("id", -1);
        String title = getIntent().getStringExtra("title");
        String desc = getIntent().getStringExtra("desc");
        String date = getIntent().getStringExtra("date");
        String location = getIntent().getStringExtra("location");
        String contact = getIntent().getStringExtra("contact");

        // 显示数据
        tvTitle.setText(title);
        tvDescription.setText(desc);
        tvDate.setText(date);
        tvLocation.setText(location);
        tvContact.setText(contact);

        // 初始化数据库工具类
        db = new DatabaseHelper(this);

        // 创建 item 实例，供删除使用
        item = new Item(id, title, desc, date, location, contact);

        // 设置删除按钮事件
        btnRemove.setOnClickListener(v -> {
            if (item != null) {
                db.deleteItem(item.getId());
                Toast.makeText(this, "Item removed successfully", Toast.LENGTH_SHORT).show();

                // 返回主界面并刷新数据
                setResult(RESULT_OK);
                finish();  // 只关闭 DetailActivity，不新建 MainActivity

                finish();
            }
        });
    }
}
