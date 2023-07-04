package com.birzavit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Resumen extends AppCompatActivity {

    TextView tvrcliente, tvrdni, tvrplato, tvrcostoplato, tvrmediopago,
             tvrbolsas, tvrdelivery, tvrtotaladicionales, tvrtotal;
    Button btnrvolver;

    private MediaPlayer mp = null;

    void Controles(){
        tvrcliente = findViewById(R.id.tvRCliente);
        tvrdni = findViewById(R.id.tvRDni);
        tvrplato = findViewById(R.id.tvRPlato);
        tvrcostoplato = findViewById(R.id.tvRCostoPlato);
        tvrmediopago = findViewById(R.id.tvRMedioPago);
        tvrbolsas = findViewById(R.id.tvRBolsas);
        tvrdelivery = findViewById(R.id.tvRDelivery);
        tvrtotaladicionales = findViewById(R.id.tvRTotalAdicionales);
        tvrtotal = findViewById(R.id.tvRTotal);
        btnrvolver = findViewById(R.id.btnRVolver);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumen);

        mp = MediaPlayer.create(this, R.raw.resumen);
        if (mp != null) {
            mp.start();
        }

        Controles();

        Intent x = getIntent();
        if (x != null) {

            tvrcliente.setText(x.getStringExtra("Nombre"));
            tvrdni.setText(x.getStringExtra("Dni"));
            tvrplato.setText(x.getStringExtra("NombrePlato"));
            tvrcostoplato.setText(x.getStringExtra("CostoPlato"));
            tvrmediopago.setText(x.getStringExtra("MedioPago"));
            tvrbolsas.setText(x.getStringExtra("Bolsas"));
            tvrdelivery.setText(x.getStringExtra("Delivery"));
            tvrtotaladicionales.setText(x.getStringExtra("Adicionales"));
            tvrtotal.setText(x.getStringExtra("TOTAL"));


        }

        btnrvolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

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