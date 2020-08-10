package com.waltergaldamezweb.formulario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DetalleDatos extends AppCompatActivity {

    TextView tvNombre;
    TextView tvFechaN;
    TextView tvTelefono;
    TextView tvEmail;
    TextView tvDescripContact;
    Button btnEditar;
    private String nombre;
    private String fechaNacimiento;
    private String telefono;
    private String email;
    private String descripcionContacto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalle_datos);

        tvNombre = findViewById(R.id.tvNombre);
        tvFechaN = findViewById(R.id.tvFechaN);
        tvTelefono = findViewById(R.id.tvTelefono);
        tvEmail = findViewById(R.id.tvEmail);
        tvDescripContact = findViewById(R.id.tvDescripContac);
        btnEditar = findViewById(R.id.btnEditar);

        Bundle intentParametros = getIntent().getExtras();
        this.nombre = intentParametros.getString("Nombre");
        this.fechaNacimiento = intentParametros.getString("Fecha");
        this.telefono = intentParametros.getString("Telefono");
        this.email = intentParametros.getString("Email");
        this.descripcionContacto = intentParametros.getString("DescripcionContacto");

        tvNombre.setText(nombre);
        tvFechaN.setText(fechaNacimiento);
        tvTelefono.setText(telefono);
        tvEmail.setText(email);
        tvDescripContact.setText(descripcionContacto);

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intento = new Intent(DetalleDatos.this, MainActivity.class);
                intento.putExtra("NombreEditar",nombre);
                intento.putExtra("FechaEditar",fechaNacimiento);
                intento.putExtra("TelefonoEditar",telefono);
                intento.putExtra("EmailEditar",email);
                intento.putExtra("DescripcionContactoEditar",descripcionContacto);
                setResult(RESULT_OK, intento);
                finish();
            }
        });
    }
}