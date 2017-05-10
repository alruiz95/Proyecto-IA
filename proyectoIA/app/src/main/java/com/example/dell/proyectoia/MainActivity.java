package com.example.dell.proyectoia;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button button = (Button) findViewById(R.id.button2);
        final Spinner sp1 = (Spinner) findViewById(R.id.spinner13);
        final Spinner sp2 = (Spinner) findViewById(R.id.spinner12);
        final Spinner sp3 = (Spinner) findViewById(R.id.spinner14);
        final TextView err = (TextView)findViewById(R.id.textView);
        final TextView err2 = (TextView)findViewById(R.id.textView2);
        final TextView err3 = (TextView)findViewById(R.id.textView3);


        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                double senal00 = 10;
                double senal01 = 10;
                double senal10 = 10;

                //double x = calcularAlturaTriangulo( MINSENALx00, senal00, senal10);
                //double y = calcularAlturaTriangulo( MINSENALx00, senal00, senal01);

                double panelx,panely;

            }
        });

    }

    double calcularAlturaTriangulo(double base, double l1, double l2){

        double p = (base + l1 + l2)/2;
        double p2 = p*(p-base)*(p-l1)*(p-l2);
        double a = Math.sqrt(p2);

        double h = 2*(a/base);
        return h;
    }



}
