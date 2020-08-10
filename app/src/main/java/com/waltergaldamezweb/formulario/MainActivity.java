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

    private static final int SECOND_ACTIVITY_REQUEST_CODE = 0;
    protected String nombre;
    protected String fecha;
    protected String telefono;
    protected String email;
    protected String descripcion;
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

        edtNombre =  findViewById(R.id.edtNombre);
        edtTelefono = findViewById(R.id.edtTelefono);
        edtEmail = findViewById(R.id.edtEmail);
        edtDescrContacto = findViewById(R.id.edtDescrContacto);
        btnSiguiente = findViewById(R.id.btnSiguiente);
        edtFechaN =  findViewById(R.id.edtFechaN);

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

        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nombre = edtNombre.getText().toString();
                fecha = edtFechaN.getText().toString();
                telefono = edtTelefono.getText().toString();
                email = edtEmail.getText().toString();
                descripcion = edtDescrContacto.getText().toString();

                datos = new ModeloDatos(nombre,fecha,telefono,email,descripcion);

                Intent intent = new Intent(MainActivity.this, DetalleDatos.class);
                intent.putExtra("Nombre",datos.getNombre());
                intent.putExtra("Fecha",datos.getFechaNacimiento());
                intent.putExtra("Telefono",datos.getTelefono());
                intent.putExtra("Email",datos.getCorreo());
                intent.putExtra("DescripcionContacto",datos.getDescripcionContacto());
                startActivityForResult(intent, SECOND_ACTIVITY_REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SECOND_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {

                nombre = data.getStringExtra("NombreEditar");
                fecha = data.getStringExtra("FechaEditar");
                telefono = data.getStringExtra("TelefonoEditar");
                email = data.getStringExtra("EmailEditar");
                descripcion = data.getStringExtra("DescripcionContactoEditar");

                edtNombre.setText(nombre);
                edtFechaN.setText(fecha);
                edtTelefono.setText(telefono);
                edtEmail.setText(email);
                edtDescrContacto.setText(descripcion);
            }
        }
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