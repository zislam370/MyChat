package com.t2m.mychat;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Verify_code extends AppCompatActivity {
    EditText phone_num_varify;
    String phone_varify;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_code);
        phone_num_varify = (EditText)findViewById(R.id.phone_number);

    }


    public void verifyCode(View v) {

        phone_varify = phone_num_varify.getText().toString();

        String method = "login";
        Databaseconn databaseconn = new Databaseconn(this);
        databaseconn.execute(method, phone_varify);
        //finish();


            //Intent intent = new Intent(getApplicationContext(), profile.class);
            //startActivity(intent);



    }
}
