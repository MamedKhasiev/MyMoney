package com.shuchenysh.mymoney;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainViewModel extends AndroidViewModel {

    private Database database;

    public MainViewModel(@NonNull Application application) {
        super(application);
        database = Database.getInstance(application);
    }

    public void add(Item item) {
        database.dao().add(item)
                .subscribeOn(Schedulers.io())
                .subscribe();
    }

}
