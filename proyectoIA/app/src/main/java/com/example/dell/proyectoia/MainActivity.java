package com.example.dell.proyectoia;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button2);
        final Spinner sp1 = (Spinner) findViewById(R.id.spinner13);
        final Spinner sp2 = (Spinner) findViewById(R.id.spinner12);
        final Spinner sp3 = (Spinner) findViewById(R.id.spinner14);


        final Handler hand = new Handler();
        final TextView err = (TextView) findViewById(R.id.textView);
        final TextView err2 = (TextView) findViewById(R.id.textView2);
        final TextView err3 = (TextView) findViewById(R.id.textView3);
        final TextView err4 = (TextView) findViewById(R.id.textView4);
        final TextView err5 = (TextView) findViewById(R.id.textView5);



        sp1.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {

                float level = WifiManager.calculateSignalLevel(CONSTANTS.wifis.get(sp1.getSelectedItemPosition()).level, CONSTANTS.LEVEL_OF_SIGNAL);
                CONSTANTS.BSSID1=CONSTANTS.wifis.get(sp1.getSelectedItemPosition()).BSSID;
                System.out.println(level);
                System.out.println("test==========");
                err.setText(Float.toString(level));
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });
        sp2.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {

                float level = WifiManager.calculateSignalLevel(CONSTANTS.wifis.get(sp2.getSelectedItemPosition()).level, CONSTANTS.LEVEL_OF_SIGNAL);
                CONSTANTS.BSSID2=CONSTANTS.wifis.get(sp2.getSelectedItemPosition()).BSSID;
                System.out.println(level);
                System.out.println("test==========");
                err2.setText(Float.toString(level));
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });
        sp3.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {

                float level = WifiManager.calculateSignalLevel(CONSTANTS.wifis.get(sp3.getSelectedItemPosition()).level, CONSTANTS.LEVEL_OF_SIGNAL);
                CONSTANTS.BSSID3=CONSTANTS.wifis.get(sp3.getSelectedItemPosition()).BSSID;
                System.out.println(level);
                System.out.println("test==========");
                err3.setText(Float.toString(level));
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });



        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                loadList(getApplicationContext());

                sp1.setAdapter(CONSTANTS.listAdapter);
                sp2.setAdapter(CONSTANTS.listAdapter);
                sp3.setAdapter(CONSTANTS.listAdapter);

                double senal00 = 10;
                double senal01 = 10;
                double senal10 = 10;

                //double x = calcularAlturaTriangulo( MINSENALx00, senal00, senal10);
                //double y = calcularAlturaTriangulo( MINSENALx00, senal00, senal01);

                double panelx, panely;
                hand.postDelayed(new Runnable(){
                    public void run(){

                        List<ScanResult> wifiList = getList(MainActivity.this);
                        CONSTANTS.wifis=wifiList;

                        for (ScanResult scanResult : wifiList) {
                            float level = WifiManager.calculateSignalLevel(scanResult.level, CONSTANTS.LEVEL_OF_SIGNAL);

                            if (scanResult.BSSID.equals(CONSTANTS.BSSID1)){
                                err.setText(Float.toString(level));
                                CONSTANTS.level1 = level;
                            }

                            if (scanResult.BSSID.equals(CONSTANTS.BSSID2)){
                                err2.setText(Float.toString(level));
                                CONSTANTS.level2 = level;
                            }

                            if (scanResult.BSSID.equals(CONSTANTS.BSSID3)){
                                err3.setText(Float.toString(level));
                                CONSTANTS.level3 = level;
                            }

                        }

                        CONSTANTS.XActual = calcularAlturaTriangulo(CONSTANTS.LEVEL_OF_SIGNAL, CONSTANTS.LEVEL_OF_SIGNAL - CONSTANTS.level1, CONSTANTS.LEVEL_OF_SIGNAL-CONSTANTS.level2);
                        CONSTANTS.YActual = calcularAlturaTriangulo(CONSTANTS.LEVEL_OF_SIGNAL, CONSTANTS.LEVEL_OF_SIGNAL - CONSTANTS.level2, CONSTANTS.LEVEL_OF_SIGNAL-CONSTANTS.level3);

                        err4.setText(Double.toString(CONSTANTS.XActual));
                        err5.setText(Double.toString(CONSTANTS.YActual));

                        hand.postDelayed(this, CONSTANTS.delay);
                    }
                }, CONSTANTS.delay);
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



    public void loadList(Context c){
        WifiManager wifiManager = (WifiManager) c.getSystemService(Context.WIFI_SERVICE);
        CONSTANTS.WIFIMAN = wifiManager;

        List<String> list = new ArrayList<String>();

        // Level of a Scan Result
        List<ScanResult> wifiList = wifiManager.getScanResults();
        for (ScanResult scanResult : wifiList) {
            list.add(scanResult.SSID);
        }
        CONSTANTS.wifis=wifiList;



        CONSTANTS.listAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        CONSTANTS.listAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }

    public List<ScanResult> getList(Context c){

        WifiManager wifiManager = (WifiManager) c.getSystemService(Context.WIFI_SERVICE);
        wifiManager.startScan();
        CONSTANTS.WIFIMAN = wifiManager;


        List<ScanResult> wifiList = wifiManager.getScanResults();
        CONSTANTS.wifis=wifiList;

        return wifiList;
    }



}
