package com.t2m.mychat;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.text.format.Formatter;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Date;
import java.util.Random;

import android.provider.Settings.Secure;

public class Phonenumber extends AppCompatActivity {
    EditText phone_num;

    String phone;

    String device_id;

    String user_ip;

    String active_date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_phonenumber);

      TextView text = (TextView)findViewById(R.id.Country_info);
        phone_num = (EditText)findViewById(R.id.user_phone);

      


        TelephonyManager  tm=(TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);

        //Calling the methods of TelephonyManager the returns the information
        String SIMCountryISO=tm.getNetworkCountryIso().toUpperCase();

        text.setText(SIMCountryISO);

        if (SIMCountryISO.equals("BD")) {
            //Toast.makeText(getApplicationContext(), "iso code test!!! =)",
                    //Toast.LENGTH_LONG).show();
            text.setText("Bangladesh(+880)");

        } else {

            text.setText(SIMCountryISO);
        }

      /*  if (SIMCountryISO.equals(null)) {
            Toast.makeText(getApplicationContext(), "connect to the network!!! =)",
                    Toast.LENGTH_LONG).show();

            phone_num.setFocusable(false);
            phone_num.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    Toast.makeText(getApplicationContext(), "connect to the network!!! =)",
                            Toast.LENGTH_LONG).show();


                }
            });
        }*/



        //// internet check //////




        /////// internet check end /////



        //// device id code ////

         device_id = Secure.getString(this.getContentResolver(),
                Secure.ANDROID_ID);

        //Toast.makeText(getApplicationContext(),device_id,
                //Toast.LENGTH_LONG).show();


        /// find ip of user device ///

        WifiManager wm = (WifiManager) getSystemService(WIFI_SERVICE);
        user_ip = Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress());
        //Toast.makeText(getApplicationContext(),ip,
        //Toast.LENGTH_LONG).show();


        //// find current time and date /////

        active_date = DateFormat.getDateTimeInstance().format(new Date());

        //Toast.makeText(getApplicationContext(),active_date,
        //Toast.LENGTH_LONG).show();


        //// find phone number///

        TelephonyManager tMgr = (TelephonyManager)this.getSystemService(Context.TELEPHONY_SERVICE);
        String mPhoneNumber = tMgr.getLine1Number();

        ///// find operator name////

        TelephonyManager tManager = (TelephonyManager) getBaseContext()
                .getSystemService(Context.TELEPHONY_SERVICE);

        // Get carrier name (Network Operator Name)
        String carrierName = tManager.getNetworkOperatorName();


        Toast.makeText(getApplicationContext(),mPhoneNumber,
        Toast.LENGTH_LONG).show();

        Toast.makeText(getApplicationContext(),carrierName,
                Toast.LENGTH_LONG).show();


    }

    ///// net check ////


   ////// end ///

    public void userReg(View v)
    {


         phone = phone_num.getText().toString();
         String code_gen = Integer.toString(n);


        String method = "register";
        Databaseconn databaseconn = new Databaseconn(this);
        databaseconn.execute(method,phone,code_gen,device_id,user_ip,active_date);
        finish();
        Intent intent = new Intent(getApplicationContext(), Verify_code.class);
        startActivity(intent);

        Toast.makeText(getApplicationContext(),code_gen,
            Toast.LENGTH_LONG).show();

    }

    private int nDigitRandomNo(int digits){
        int max = (int) Math.pow(10,(digits)) - 1; //for digits =7, max will be 9999999
        int min = (int) Math.pow(10, digits-1); //for digits = 7, min will be 1000000
        int range = max-min; //This is 8999999
        Random r = new Random();
        int x = r.nextInt(range);// This will generate random integers in range 0 - 8999999
        int nDigitRandomNo = x+min; //Our random rumber will be any random number x + min
        return nDigitRandomNo;
    }
    int digits = 6;
    int n = nDigitRandomNo(digits);



}
