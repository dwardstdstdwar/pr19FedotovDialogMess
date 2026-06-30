package com.example.pr19homyakov;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements CustomDialogFragment.DialogListener {

    private TextView tvDateTime, tvResult;
    private Button btnDate, btnTime, btnCustom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvDateTime = findViewById(R.id.tvDateTime);
        tvResult = findViewById(R.id.tvResult);
        btnDate = findViewById(R.id.btnDate);
        btnTime = findViewById(R.id.btnTime);
        btnCustom = findViewById(R.id.btnCustom);

        // === ЗАДАНИЕ 2: Установка текущей даты и времени при запуске ===
        String currentDateTime = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.getDefault()).format(new Date());
        tvDateTime.setText("Сейчас: " + currentDateTime);

        // Кнопки Задания 2
        btnDate.setOnClickListener(v -> showDateDialog());
        btnTime.setOnClickListener(v -> showTimeDialog());

        // Кнопка Задания 3
        btnCustom.setOnClickListener(v -> showCustomDialog());
    }

    // === Логика Задания 2: Простые диалоги ===
    private void showDateDialog() {
        String date = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(new Date());
        new AlertDialog.Builder(this)
                .setTitle("Текущая дата")
                .setMessage(date)
                .setPositiveButton("ОК", null)
                .show();
    }

    private void showTimeDialog() {
        String time = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
        new AlertDialog.Builder(this)
                .setTitle("Текущее время")
                .setMessage(time)
                .setPositiveButton("ОК", null)
                .show();
    }

    // === Логика Задания 3: Передача данных В диалог ===
    private void showCustomDialog() {
        CustomDialogFragment dialog = new CustomDialogFragment();
        Bundle args = new Bundle();
        args.putString("current_time", tvDateTime.getText().toString()); // Передаём время
        dialog.setArguments(args);
        dialog.show(getSupportFragmentManager(), "custom_dialog");
    }

    // === Интерфейс Задания 3: Получение данных ИЗ диалога ===
    @Override
    public void onDialogPositiveClick(DialogFragment dialog, String userInput) {
        tvResult.setText("Результат: " + userInput);
        Toast.makeText(this, "Данные успешно получены", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        Toast.makeText(this, "Диалог отменён", Toast.LENGTH_SHORT).show();
    }
}