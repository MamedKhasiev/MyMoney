package com.shuchenysh.mymoney;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextView textViewMoney;
    private EditText editTextNumber;
    private Button buttonPlus;
    private Button buttonMinus;
    private Button buttonInfo;

    private int summa;
    private MainViewModel viewModel;
    private SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        sharedPreferences = this.getSharedPreferences("shared", Context.MODE_PRIVATE);


        buttonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number = editTextNumber.getText().toString();
                if (number.isEmpty()) {
                    Toast.makeText(
                                    MainActivity.this,
                                    "Error, empty field",
                                    Toast.LENGTH_SHORT)
                            .show();
                } else if (Integer.parseInt(number) <= 0) {
                    Toast.makeText(
                                    MainActivity.this,
                                    "Error, number must be greater than 0",
                                    Toast.LENGTH_SHORT)
                            .show();
                } else {
                    summa += Integer.parseInt(number);
                    show(String.valueOf(summa));
                    Item item = new Item(getDate(), "+" + number, summa);
                    viewModel.add(item);
                }
                editTextNumber.setText("");

            }
        });

        buttonMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number = editTextNumber.getText().toString();
                if (number.isEmpty()) {
                    Toast.makeText(
                                    MainActivity.this,
                                    "Error, empty field",
                                    Toast.LENGTH_SHORT)
                            .show();
                } else if (Integer.parseInt(number) <= 0) {
                    Toast.makeText(
                                    MainActivity.this,
                                    "Error, number must be greater than 0",
                                    Toast.LENGTH_SHORT)
                            .show();
                } else {
                    summa -= Integer.parseInt(number);
                    if(summa < 0) {
                        summa = 0;
                    }
                    show(String.valueOf(summa));
                    Item item = new Item(getDate(), "-" + number, summa);
                    viewModel.add(item);
                }
                editTextNumber.setText("");
            }

        });

        buttonInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = InfoActivity.newIntent(MainActivity.this);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        show(String.valueOf(summa));
        String str = sharedPreferences.getString("tag", String.valueOf(summa));
        summa = Integer.parseInt(str);

    }

    @Override
    protected void onStop() {
        super.onStop();
        sharedPreferences.edit().putString("tag", String.valueOf(summa)).apply();
    }

    private String getDate() {
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        return dateFormat.format(date);
    }

    private void show(String number) {
        textViewMoney.setText(number);
    }

    private void initViews() {
        textViewMoney = findViewById(R.id.textViewMoney);
        editTextNumber = findViewById(R.id.editTextNumber);
        buttonPlus = findViewById(R.id.buttonPlus);
        buttonMinus = findViewById(R.id.buttonMinus);
        buttonInfo = findViewById(R.id.buttonInfo);
    }
}