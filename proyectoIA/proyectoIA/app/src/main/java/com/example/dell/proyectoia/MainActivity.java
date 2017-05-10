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
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button button = (Button) findViewById(R.id.button);
        final TextView err = (TextView)findViewById(R.id.texto);
        Context context = null;
        final Context finalContext = context;

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                registerReceiver(new BroadcastReceiver() {

                    @Override
                    public void onReceive(Context context, Intent intent) {
                        final WifiManager wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
                        int state = wifi.getWifiState();
                        if(state == WifiManager.WIFI_STATE_ENABLED) {
                            List<ScanResult> results = wifi.getScanResults();

                            for (ScanResult result : results) {

                                if(result.BSSID.equals(wifi.getConnectionInfo().getBSSID())) {
                                    int level = WifiManager.calculateSignalLevel(wifi.getConnectionInfo().getRssi(),
                                            result.level);
                                    int difference = level * 100 / result.level;
                                    int signalStrangth= 0;

                                    if(difference >= 100)
                                        signalStrangth = 4;
                                    else if(difference >= 75)
                                        signalStrangth = 3;
                                    else if(difference >= 50)
                                        signalStrangth = 2;
                                    else if(difference >= 25)
                                        signalStrangth = 1;
                                    err.setText(err.getText() + "\nDifference :" + difference + " signal state:" + signalStrangth);

                                }

                            }
                        }
                    }
                }, new IntentFilter(WifiManager.RSSI_CHANGED_ACTION));


                double senal00 = 10;
                double senal01 = 10;
                double senal10 = 10;

                double x = calcularAlturaTriangulo( MINSENALx00, senal00, senal10);
                double y = calcularAlturaTriangulo( MINSENALx00, senal00, senal01);

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
