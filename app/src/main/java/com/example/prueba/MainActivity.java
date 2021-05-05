package com.example.prueba;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private EditText etFecha;
    private Button btnAceptar;
    private Spinner spnNombres;
    private ListView lstNombres;
 //   private ArrayList<String> losNombres = new ArrayList<>();
    private String[] losNombres = new String[10];
    private ArrayAdapter adaptador;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etFecha = findViewById(R.id.etFecha);
        btnAceptar = findViewById(R.id.btnAceptar);
        spnNombres = findViewById(R.id.spnNombres);
        lstNombres = findViewById(R.id.lstNombres);

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int[] edad = calcularEdad(etFecha.getText().toString());
            }
        });

        //Poblar el array
        /*
        losNombres[0] = "Seleccione nombre";
        losNombres[1] = "Pepito";
        losNombres[2] = "Maria";
        losNombres[3] = "Luis";
        */
//        for(int x = 0; x < 10; ++x)
//            losNombres[x] = "Nombre " + x;

        adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1, losNombres);
        spnNombres.setAdapter(adaptador);
        lstNombres.setAdapter(adaptador);

        spnNombres.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("TAG_", "Posición del arreglo " + position );
                Toast.makeText(MainActivity.this, "Seleccionó " + losNombres[position], Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        lstNombres.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("TAG_", "Posición del arreglo " + position );
                Toast.makeText(MainActivity.this, "Seleccionó en ListView " + losNombres[position], Toast.LENGTH_LONG).show();
            }
        });

        lstNombres.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("TAG_", "Posición del arreglo " + position );
                Toast.makeText(MainActivity.this, "Seleccionó Long click en ListView " + losNombres[position], Toast.LENGTH_LONG).show();
                return true;
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