package com.pas.pos_system.activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.pas.pos_system.R;
import com.pas.pos_system.viewModels.CreateLoginViewModel;
import com.pas.pos_system.viewModels.LoginViewModel;

public class CreateLoginActivity extends AppCompatActivity {

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, CreateLoginActivity.class);
        context.startActivity(intent);
    }

    private EditText editTextName, editTextUserName, editTextPassword, editTextVerificacionPassword;
    private Button button;

    private CreateLoginViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_login);

        this.editTextName = findViewById(R.id.inputCreateName);
        this.editTextUserName = findViewById(R.id.inputCreateUsername);
        this.editTextPassword = findViewById(R.id.inputCreatePassword);
        this.editTextVerificacionPassword = findViewById(R.id.inputCreatePasswordVerificacion);
        this.button = findViewById(R.id.btnCreateLogin);

    }

    public void createUser(View view) {

            verificacao();

    }

    public void verificacao() {

        String name = editTextName.getText().toString();
        String username = editTextUserName.getText().toString();
        String password = editTextPassword.getText().toString();
        String verificacion = editTextVerificacionPassword.getText().toString();

        if (name.equals("") || username.equals("") || password.equals("") || verificacion.equals("") ) {

            Toast.makeText(this, "Erro Falta Preencher Dados", Toast.LENGTH_LONG).show();

        }else if(!password.equals(verificacion)) {

            Toast.makeText(this, "Palavra passe não é igual", Toast.LENGTH_LONG).show();
        }
        else{
            this.mViewModel = new ViewModelProvider(this).get(CreateLoginViewModel.class);
            this.mViewModel.createLogin(this, username, password, name);
        }

    }

}