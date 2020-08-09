package com.waltergaldamezweb.formulario;

import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    TextInputEditText edtNombre;
    TextInputEditText edtFechaN;
    TextInputEditText edtTelefono;
    TextInputEditText edtEmail;
    TextInputEditText edtDescrContacto;
    Button btnSiguiente;


    ModeloDatos datos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtNombre = (TextInputEditText) findViewById(R.id.edtNombre);
        edtTelefono = (TextInputEditText) findViewById(R.id.edtTelefono);
        edtEmail = (TextInputEditText) findViewById(R.id.edtEmail);
        edtDescrContacto = (TextInputEditText) findViewById(R.id.edtDescrContacto);



        edtFechaN = (TextInputEditText) findViewById(R.id.edtFechaN);
        edtFechaN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.edtFechaN:
                        showDatePickerDialog();
                        break;
                }
            }
        });

        datos = new ModeloDatos(edtNombre.toString(),edtFechaN.toString(),edtTelefono.toString(),edtEmail.toString(),edtDescrContacto.toString());

        btnSiguiente = (Button) findViewById(R.id.btnSiguiente);
        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DetalleDatos.class);
                intent.putExtra("Nombre",datos.getNombre());
                intent.putExtra("Fecha",datos.getFechaNacimiento());
                intent.putExtra("Telefono",datos.getTelefono());
                intent.putExtra("Email",datos.getCorreo());
                intent.putExtra("DescripcionContacto",datos.getDescripcionContacto());
                startActivity(intent);
            }
        });



    }
    private void showDatePickerDialog() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 because January is zero
                final String selectedDate = day + " / " + (month+1) + " / " + year;
                edtFechaN.setText(selectedDate);
            }
        });
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }


}