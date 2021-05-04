package com.example.prueba;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private EditText etFecha;
    private Button btnAceptar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etFecha = findViewById(R.id.etFecha);
        btnAceptar = findViewById(R.id.btnAceptar);

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int[] edad = calcularEdad(etFecha.getText().toString());
            }
        });

    }

    private int[] calcularEdad(String fechaStr){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar calendario = Calendar.getInstance();
        int[] edad = new int[3]; //Años, meses, dias
        Date fechaHoy = new Date();
        Date fechaNacimiento;

        try{
            sdf.setLenient(false);
            fechaNacimiento = sdf.parse(fechaStr);
            calendario.setTime(fechaNacimiento);
            int anioNac, mesNac, diaNac;
            int anioAct, mesAct, diaAct;
            anioNac = calendario.get(Calendar.YEAR);
            mesNac = calendario.get(Calendar.MONTH);
            diaNac = calendario.get(Calendar.DAY_OF_MONTH);

            /*
            LocalDate localDateNac = LocalDate.of(anioNac, mesNac, diaNac);
            LocalDate localDateAct = LocalDate.now();
            Period periodo = Period.between(localDateNac, localDateAct);
            */

            calendario.setTime(fechaHoy);
            anioAct = calendario.get(Calendar.YEAR);
            mesAct = calendario.get(Calendar.MONTH);
            diaAct = calendario.get(Calendar.DAY_OF_MONTH);

            //Años
            edad[0] = anioAct - anioNac;
            if(mesAct < mesNac) {
                edad[0] = edad[0] - 1;
            }else if(mesAct == mesNac){
                if(diaAct < diaNac){
                    edad[0] = edad[0] - 1;
                }
            }
        }catch (Exception ex){
            Log.e("TAG_", ex.toString());
        }

        return edad;
    }
}