package com.example.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {
private Button restbtn;

private ToggleButton switchbt;

private EditText Min;

private EditText Sec;

private TextView Time;

private ImageView Anim;

private Timer myTimer;
private int sec;
private int min;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        restbtn = (Button) findViewById(R.id.restart);
        switchbt = (ToggleButton) findViewById(R.id.onoff);
        Min = (EditText) findViewById(R.id.min);
        Sec = (EditText) findViewById(R.id.sec);
        Time = (TextView) findViewById(R.id.TimeTV);
        Anim = (ImageView) findViewById(R.id.TimeIV);
        myTimer = new Timer();
    }

    public void onToggleClicked(View view) {
        try {
            // включена ли кнопка
            boolean on = ((ToggleButton) view).isChecked();
            if (on) {
                // действия если включена
                String str = Sec.getText().toString();
                // если секунды не пусты
                if (!str.isEmpty())
                {
                    sec = Integer.parseInt(str);

                    str = Min.getText().toString();
                    //если минуты не пусты
                    if(!str.isEmpty()) {
                        min = Integer.parseInt(str);
                    }
                    else {
                        //присваиваем 0
                        min = 0;
                    }
                    if(min>60)
                        min = 60;
                    if(sec>60)
                        sec=60;
                    Time.setText(Integer.toString(min) + ":" + Integer.toString(sec));
                    myTimer.schedule(new TimerTask() {
                        public void run() {
                            timerTick();
                        }
                    }, 0, 1000);
                }
                else{
                    //выводим ошибку
                    Toast.makeText(this, "Введите время", Toast.LENGTH_LONG).show();
                }
            } else {
                // действия, если выключена
                myTimer.cancel();
            }
        }
        catch (Exception ex){
            Toast toast = Toast.makeText(this, "Введна ошибка", Toast.LENGTH_LONG);
            toast.show();
        }
    }

    private void timerTick() {
        sec = sec-1;
        if(sec == 0 && min !=0) {
            min = min - 1;
            sec = 60;
        }
        if(sec ==0 && min == 0){
            myTimer.cancel();
        }
        Time.setText(Integer.toString(min) + ":" + Integer.toString(sec));
    }

}