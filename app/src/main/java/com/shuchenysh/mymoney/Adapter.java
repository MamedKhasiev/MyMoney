package com.shuchenysh.mymoney;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private List<Item> money = new ArrayList<>();

    public List<Item> getMoney() {
        return new ArrayList<>(money);
    }

    public void setMoney(List<Item> money) {
        this.money = money;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_note, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Item item = money.get(position);

        holder.textViewDate.setText(item.getDate());
        holder.textViewMoney.setText(item.getMoney());

        int colorResId;

        if (item.getMoney().charAt(0) == '-') {
            colorResId = android.R.color.holo_red_light;
        } else {
            colorResId = R.color.green;
        }
        int color = ContextCompat.getColor(holder.itemView.getContext(), colorResId);
        holder.textViewMoney.setTextColor(color);

    }

    @Override
    public int getItemCount() {
        return money.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView textViewMoney;
        private TextView textViewDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewMoney = itemView.findViewById(R.id.textViewMoney);
            textViewDate = itemView.findViewById(R.id.textViewDate);

        }
    }

}



