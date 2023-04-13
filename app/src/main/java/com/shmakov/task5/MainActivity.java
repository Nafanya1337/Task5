package com.shmakov.task5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.sql.Time;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    EditText phone, web;
    TextView phone_text, web_text, info;
    DatePicker picker;
    TimePicker time;
    SeekBar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        phone = findViewById(R.id.editText);
        web = findViewById(R.id.editText2);
        phone_text = findViewById(R.id.textView);
        web_text = findViewById(R.id.web);

        phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                phone_text.setText(s);
            }
        });

        web.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                web_text.setText(s);
            }
        });
        EditText four = findViewById(R.id.editTextTextPersonName4);
        EditText three = findViewById(R.id.editTextTextPersonName3);
        three.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                four.setText(three.getText());
            }
        });

        RadioGroup gr = findViewById(R.id.radiogroup);
        System.out.println(gr.getCheckedRadioButtonId());
        gr.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                changeTarif(group, checkedId);
            }
        });

        picker = findViewById(R.id.picker);
        picker.init(2023, 03, 14, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                TextView t = findViewById(R.id.picked_date);
                t.setText("Выбранная дата : " + Integer.toString(dayOfMonth) + " " + Integer.toString(monthOfYear + 1) + " " + Integer.toString(year));
            }
        });

        time = findViewById(R.id.time_picker);
        time.setIs24HourView(true);
        time.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                TextView chosen_time = findViewById(R.id.chosen_time);
                chosen_time.setText("Выбранное время : " + Integer.toString(hourOfDay) + " " + Integer.toString(minute));
            }
        });

        TextView msgs = findViewById(R.id.msgs);
        bar = findViewById(R.id.seekbar);
        bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {


            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                msgs.setText(Integer.toString(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void checkboxIsClicked(View view) {
        CheckBox box = (CheckBox) view;
        boolean checked = box.isChecked();
        switch (box.getId()) {
            case R.id.box1:
                if (checked)
                    makeSnackBar(view, "Вы подписались на оповещения на почту");
                else
                    makeSnackBar(view, "Вы отписались от оповещений на почту");
                break;
            case R.id.box2:
                if (checked)
                    makeSnackBar(view, "Вы подписались на смс");
                else
                    makeSnackBar(view, "Вы отписались от смс");
                break;
            case R.id.box3:
                if (checked)
                    makeSnackBar(view, "Вы подписались на рекламные рассылки");
                else
                    makeSnackBar(view, "Вы отписались от рекламных рассылок");
                break;
        }
    }


    public void makeToast(View view) {
        Toast toast = Toast.makeText(this,"Информация сохранена!", Toast.LENGTH_LONG);
        toast.show();
        Toast toast2 = Toast.makeText(this, "Спасибо за отзыв", Toast.LENGTH_SHORT);
        toast2.setGravity(Gravity.TOP,0,160);
        toast2.show();
    }

    public void makeSnackBar(View view, String txt){
        Snackbar snackbar = Snackbar.make(view, "Изменения внесены", BaseTransientBottomBar.LENGTH_LONG);
        snackbar.setAction("Детально", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast3 = Toast.makeText(MainActivity.this, txt, Toast.LENGTH_LONG);
                toast3.show();
            }
        });
        snackbar.show();
    }

    public RadioGroup.OnCheckedChangeListener changeTarif(RadioGroup arg0, int id) {
        TextView t = findViewById(R.id.tarif);
        switch (id) {
            case R.id.tar1:
                t.setText("Тариф " + "50 руб./мес");
                break;
            case R.id.tar2:
                t.setText("Тариф " + "150 руб./мес");
                break;
            case R.id.tar3:
                t.setText("Тариф " + "500 руб./мес");
                break;
        }
        return null;
    }

    public void makeInfo(View view) {
        info = findViewById(R.id.info);
        info.setText("Ваш номер телефона : " + phone_text.getText() + "\nИ ваш любимый сайт - " + web_text.getText());
    }
}