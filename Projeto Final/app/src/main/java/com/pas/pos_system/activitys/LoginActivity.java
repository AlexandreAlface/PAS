package com.pas.pos_system.activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.pas.pos_system.R;
import com.pas.pos_system.viewModels.LoginViewModel;

public class LoginActivity extends AppCompatActivity {

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    private EditText editTextUsername, editTextPassword;
    private LoginViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.editTextUsername = findViewById(R.id.inputName);
        this.editTextPassword = findViewById(R.id.inputPassword);
    }

    public void login(View view) {

        validateLogin();

    }

    public void validateLogin(){

        String username = this.editTextUsername.getText().toString();
        String password = this.editTextPassword.getText().toString();

        if (username.equals("") || password.equals("")) {

            Toast.makeText(this, "Erro Falta Preencher Dados", Toast.LENGTH_SHORT).show();

        }
        else{

            this.mViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
            this.mViewModel.login(this,username,password);
        }

    }

    public void CreateAccount(View view) {

        Intent intent = new Intent(this, CreateLoginActivity.class);
        startActivity(intent);

    }
}