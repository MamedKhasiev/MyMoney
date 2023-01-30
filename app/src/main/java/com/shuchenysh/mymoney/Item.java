package com.shuchenysh.mymoney;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "money")
public class Item {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "date")
    private String date;

    @ColumnInfo(name = "money")
    private String money;

    @ColumnInfo(name = "sum")
    private int sum;

    public Item(int id, String date, String money, int sum) {
        this.id = id;
        this.date = date;
        this.money = money;
        this.sum = sum;
    }

    @Ignore
    public Item(String date, String money, int sum) {
        this.date = date;
        this.money = money;
        this.sum = sum;
    }

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getMoney() {
        return money;
    }

    public int getSum() {
        return sum;
    }
}
