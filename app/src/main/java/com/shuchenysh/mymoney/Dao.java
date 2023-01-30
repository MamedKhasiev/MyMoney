package com.shuchenysh.mymoney;

import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;

@androidx.room.Dao
public interface Dao {

    @Query("SELECT * FROM money")
    List<Item> getMoney();

    @Insert
    Completable add (Item item);

    @Query("DELETE FROM money WHERE id = :id")
    void remove(int id);

    @Query("DELETE FROM money")
    void removeAll();


}
