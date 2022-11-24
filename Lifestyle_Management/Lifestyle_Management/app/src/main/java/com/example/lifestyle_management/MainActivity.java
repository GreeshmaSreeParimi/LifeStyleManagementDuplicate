package com.example.lifestyle_management;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText loginUsername , loginPassword;
    private DatabaseHelper myDb;
    private Button login;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginUsername = findViewById(R.id.username);
        loginPassword = findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);

        sp = getSharedPreferences("login",MODE_PRIVATE);
        if(sp.getBoolean("logged",true)){
            startActivity(new Intent(MainActivity.this , LandingPage.class));
        }
        else
        {
            myDb = new DatabaseHelper(this);
            loginUser();
        }


        TextView createAcc = (TextView) findViewById(R.id.createaccount);

        createAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerScreen = new Intent(MainActivity.this, CreateAccount.class);
                startActivity(registerScreen);
            }
        });

        configureSkipAcc();

    }

    private void loginUser(){
        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (loginUsername.getText().toString().trim().length() == 0) {
                    loginUsername.setError("Please enter the email");

                }
                else if (loginPassword.getText().toString().trim().length() == 0) {
                    loginPassword.setError("Please enter the password");
                }
                else{
                    boolean var = myDb.checkUser(loginUsername.getText().toString() , loginPassword.getText().toString());
                    if (var){
                        sp.edit().putBoolean("logged",true).apply();
                        Toast.makeText(MainActivity.this, "Login SuccessfulL", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this , LandingPage.class));
                        finish();
                    }else{
                        Toast.makeText(MainActivity.this, "Login Failed !!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
    private void configureSkipAcc()
    {
        TextView skip = (TextView) findViewById(R.id.skip);
        skip.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, LandingPage.class));
            }
        }));
    }

}
