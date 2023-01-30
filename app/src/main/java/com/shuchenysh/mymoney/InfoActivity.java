package com.shuchenysh.mymoney;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.util.List;

public class InfoActivity extends AppCompatActivity {

    private RecyclerView recyclerViewInfo;
    private Adapter adapter;
    private Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        initViews();
        database = Database.getInstance(getApplication());
        adapter = new Adapter();
        recyclerViewInfo.setAdapter(adapter);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                setMoney(database.dao().getMoney());
            }
        });
        thread.start();


        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(
                    @NonNull RecyclerView recyclerView,
                    @NonNull RecyclerView.ViewHolder viewHolder,
                    @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                Item item = adapter.getMoney().get(position);
                remove(item.getId());
            }
        });

        itemTouchHelper.attachToRecyclerView(recyclerViewInfo);
    }

    private void setMoney(List<Item> itemList){
        adapter.setMoney(itemList);
    }

    private void remove(int id){
        new Thread(new Runnable() {
            @Override
            public void run() {
             database.dao().remove(id);
            }
        }).start();

    }

    private void removeAll(){
        database.dao().removeAll();
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, InfoActivity.class);
    }

    private void initViews() {
        recyclerViewInfo = findViewById(R.id.recyclerViewInfo);

    }
}