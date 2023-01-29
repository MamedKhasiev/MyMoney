package com.shuchenysh.mymoney;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class InfoActivity extends AppCompatActivity {

    private RecyclerView recyclerViewInfo;
    private Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        initViews();
        adapter = new Adapter();
        recyclerViewInfo.setAdapter(adapter);
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, InfoActivity.class);
    }

    private void initViews() {
        recyclerViewInfo = findViewById(R.id.recyclerViewInfo);

    }
}