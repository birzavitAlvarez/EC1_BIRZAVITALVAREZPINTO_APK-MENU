package com.birzavit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Menu extends AppCompatActivity {

    TextView tvdni,tvmenu,tvbolsas,tvdelivery,tvadicional , tvtotal;
    EditText edtnombre, edtcantidad;
    Spinner spnplatos;
    RadioButton rbefectivo, rbtarjeta;
    CheckBox chkdelivery, chkbolsas;
    Button btncalcular, btnimprimir;

    private MediaPlayer mp = null;

    double costoplato = 0, tadicional = 0, subtotal = 0, total = 0, recargo = 0, delivery = 0, bolsas = 0;

    String mpago = "Efectivo";


    void Controles(){
        tvdni = findViewById(R.id.tvDni);
        tvmenu = findViewById(R.id.tvMenu);
        tvbolsas = findViewById(R.id.tvBolsas);
        tvdelivery = findViewById(R.id.tvDelivery);
        tvadicional = findViewById(R.id.tvAdicional);
        tvtotal = findViewById(R.id.tvTotal);

        edtnombre = findViewById(R.id.edtNombre);
        edtcantidad = findViewById(R.id.edtCantidad);

        spnplatos = findViewById(R.id.spnPlatos);

        rbefectivo = findViewById(R.id.rbtEfectivo);
        rbtarjeta = findViewById(R.id.rbtTarjeta);

        chkdelivery = findViewById(R.id.chkDelivery);
        chkbolsas = findViewById(R.id.chkBolsas);

        btncalcular = findViewById(R.id.btnCalcular);
        btnimprimir = findViewById(R.id.btnImprimir);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        mp = MediaPlayer.create(this, R.raw.menu);
        if (mp != null) {
            mp.start();
        }

        Controles();

        Intent x = getIntent();
        if (x != null){
            tvdni.setText(x.getStringExtra("DNI"));
        }

        btncalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    calcular();
                }catch (Exception e){
                    Toast.makeText(Menu.this, "Error Debes ingresar datos \nantes de calcular", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnimprimir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    imprimir();
                }catch (Exception e){
                    Toast.makeText(Menu.this, "Error Debes ingresar datos \nantes de imprimir", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void calcular() {

        int cantidad = Integer.parseInt(edtcantidad.getText().toString());

        try {
            if (spnplatos.getSelectedItemPosition() == 1) {
                costoplato = 32.0;
            } else if (spnplatos.getSelectedItemPosition() == 2) {
                costoplato = 25.0;
            } else if (spnplatos.getSelectedItemPosition() == 3) {
                costoplato = 28.0;
            } else if (spnplatos.getSelectedItemPosition() == 4) {
                costoplato = 24.0;
            } else {
                costoplato = 0;
            }

            subtotal = cantidad * costoplato;

            if (rbtarjeta.isChecked()){ recargo = 0.05*subtotal; mpago="Tarjeta";}

            if (rbefectivo.isChecked()){ recargo = 0; mpago="Efectivo";}

            if (chkdelivery.isChecked()){ delivery = 5;}

            if (chkbolsas.isChecked()){ bolsas = cantidad;}

            tadicional = delivery + bolsas;
            total = subtotal + recargo + tadicional;

            tvmenu.setText(String.valueOf(cantidad));
            tvbolsas.setText(String.valueOf(bolsas));
            tvdelivery.setText(String.valueOf(delivery));
            tvadicional.setText(String.valueOf(tadicional));
            tvtotal.setText(String.valueOf(total));
        } catch (Exception e){
            //
        }

    }

    private void imprimir() {

        calcular();
        String nombrecliente = edtnombre.getText().toString();
        String dnicliente = tvdni.getText().toString();
        String nombreplato = (String) spnplatos.getSelectedItem();
        String costodelplato = String.valueOf(costoplato);
        String Bolsas = String.valueOf(bolsas);
        String Delivery = String.valueOf(delivery);
        String Adicionales = String.valueOf(tadicional);
        String Total = String.valueOf(total);

        Intent z = new Intent(Menu.this,Resumen.class);
        z.putExtra("Nombre", nombrecliente);
        z.putExtra("Dni", dnicliente);
        z.putExtra("NombrePlato", nombreplato);
        z.putExtra("CostoPlato",costodelplato);
        z.putExtra("MedioPago", mpago);
        z.putExtra("Bolsas", Bolsas);
        z.putExtra("Delivery", Delivery);
        z.putExtra("Adicionales", Adicionales);
        z.putExtra("TOTAL", Total);

        startActivity(z);

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mp != null) {
            mp.pause();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mp != null) {
            mp.release();
            mp = null;
        }
    }

}