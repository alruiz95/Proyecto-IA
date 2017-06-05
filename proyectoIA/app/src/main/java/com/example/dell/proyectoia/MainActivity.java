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
        final TextView err = (TextView) findViewById(R.id.textView);
        final TextView err2 = (TextView) findViewById(R.id.textView2);
        final TextView err3 = (TextView) findViewById(R.id.textView3);
        final TextView err4 = (TextView) findViewById(R.id.textView4);
        final TextView err5 = (TextView) findViewById(R.id.textView5);

        sp1.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {

                int level = WifiManager.calculateSignalLevel(CONSTANTS.wifis.get(sp1.getSelectedItemPosition()).level, CONSTANTS.LEVEL_OF_SIGNAL);
                System.out.println(level);
                System.out.println("test==========");
                err.setText(Integer.toString(level));
                CONSTANTS.signal1=level;
                actualizarVector();
                err4.setText(Double.toString(CONSTANTS.Y));
                err5.setText(Double.toString(CONSTANTS.X));
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });
        sp2.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {

                int level = WifiManager.calculateSignalLevel(CONSTANTS.wifis.get(sp1.getSelectedItemPosition()).level, CONSTANTS.LEVEL_OF_SIGNAL);
                System.out.println(level);
                System.out.println("test==========");
                err2.setText(Integer.toString(level));
                CONSTANTS.signal2=level;
                actualizarVector();
                err4.setText(Double.toString(CONSTANTS.Y));
                err5.setText(Double.toString(CONSTANTS.X));
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });
        sp3.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {

                int level = WifiManager.calculateSignalLevel(CONSTANTS.wifis.get(sp1.getSelectedItemPosition()).level, CONSTANTS.LEVEL_OF_SIGNAL);
                System.out.println(level);
                System.out.println("test==========");
                err3.setText(Integer.toString(level));
                CONSTANTS.signal3=level;
                actualizarVector();
                err4.setText(Double.toString(CONSTANTS.Y));
                err5.setText(Double.toString(CONSTANTS.X));
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
        System.out.println("====================================");

        List<String> list = new ArrayList<String>();

        // Level of a Scan Result
        List<ScanResult> wifiList = wifiManager.getScanResults();
        for (ScanResult scanResult : wifiList) {
            int level = WifiManager.calculateSignalLevel(scanResult.level, 5);
            System.out.println("====================================");
            System.out.println("Level is " + level + " out of 5");
            list.add(scanResult.SSID);
        }
        CONSTANTS.wifis=wifiList;



        CONSTANTS.listAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        CONSTANTS.listAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);



    }

    public void actualizarVector(){
        int parm1 = CONSTANTS.LEVEL_OF_SIGNAL - CONSTANTS.signal1;
        int parm2 = CONSTANTS.LEVEL_OF_SIGNAL - CONSTANTS.signal2;
        int parm3 = CONSTANTS.LEVEL_OF_SIGNAL - CONSTANTS.signal3;

        CONSTANTS.X = calcularAlturaTriangulo(CONSTANTS.LEVEL_OF_SIGNAL,parm2,parm3);
        CONSTANTS.Y = calcularAlturaTriangulo(CONSTANTS.LEVEL_OF_SIGNAL,parm1,parm2);
    }



}
