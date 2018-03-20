package com.anonimouse.companyproject1.Activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.anonimouse.companyproject1.Helper.DBHelper;
import com.anonimouse.companyproject1.R;

public class Login extends AppCompatActivity {

    Context context = this;
    DBHelper dbHelper;

    Button mbtnlogin, mbtnregistration;
    EditText input_username, input_password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);
        InitializeVariables();

        dbHelper = new DBHelper(context);

        mbtnregistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(getApplicationContext(), Registration.class));
            }
        });

        mbtnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = input_username.getText().toString();
                String password = input_password.getText().toString();

                if(username.toString().isEmpty())
                    Toast.makeText(context, "Please input username", Toast.LENGTH_SHORT).show();
                else if(password.toString().isEmpty())
                    Toast.makeText(context, "Please input password", Toast.LENGTH_SHORT).show();
                else{
                    dbHelper.Userlogin(username,password,context);
                }


            }
        });
    }

    private void InitializeVariables(){
        mbtnlogin = (Button)findViewById(R.id.btn_login);
        mbtnregistration = (Button)findViewById(R.id.btn_registration);
        input_username = (EditText)findViewById(R.id.input_username);
        input_password = (EditText)findViewById(R.id.input_password);
    }
}
