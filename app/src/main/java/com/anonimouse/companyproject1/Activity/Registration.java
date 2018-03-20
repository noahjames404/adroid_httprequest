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

public class Registration extends AppCompatActivity {

    Context context = this;
    DBHelper dbHelper;

    EditText input_firstname, input_lastname, input_age, input_address, input_username, input_password;

    Button mBtnRegister, mBtnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_registration);
        InitializeVariables();

        dbHelper = new DBHelper(context);

        mBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(input_firstname.getText().toString().isEmpty())
                    Toast.makeText(context, "Please enter your first name", Toast.LENGTH_SHORT).show();
                else if(input_lastname.getText().toString().isEmpty())
                    Toast.makeText(context, "Please enter your last name", Toast.LENGTH_SHORT).show();
                else if(input_age.getText().toString().isEmpty())
                    Toast.makeText(context, "Please enter your age", Toast.LENGTH_SHORT).show();
                else if (input_address.getText().toString().isEmpty())
                    Toast.makeText(context, "Please enter your address", Toast.LENGTH_SHORT).show();
                else if (input_username.getText().toString().isEmpty())
                    Toast.makeText(context, "Please enter your username", Toast.LENGTH_SHORT).show();
                else if(input_password.getText().toString().isEmpty())
                    Toast.makeText(context, "Please enter your password", Toast.LENGTH_SHORT).show();
                else{
                    String firstname = input_firstname.getText().toString();
                    String lastname = input_lastname.getText().toString();
                    String age = input_age.getText().toString();
                    String address = input_address.getText().toString();
                    String username = input_username.getText().toString();
                    String password = input_password.getText().toString();
                    dbHelper.SaveUsername(firstname,lastname,age,address,username,password,context);
                }

            }
        });

        mBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(getApplicationContext(), Login.class));
            }
        });


    }

    private void InitializeVariables(){
        input_firstname = (EditText)findViewById(R.id.input_firstname);
        input_lastname = (EditText)findViewById(R.id.input_lastname);
        input_age = (EditText)findViewById(R.id.input_age);
        input_address = (EditText)findViewById(R.id.input_address);
        input_username = (EditText)findViewById(R.id.input_username);
        input_password = (EditText)findViewById(R.id.input_password);
        mBtnBack = (Button)findViewById(R.id.btn_back);
        mBtnRegister = (Button)findViewById(R.id.btn_register);

    }
}
