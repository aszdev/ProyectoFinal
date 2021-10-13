package com.example.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectofinal.clases.funciones;
import com.example.proyectofinal.servicios.wsLogin;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class LoginActivity extends AppCompatActivity {
    private TextInputLayout et1;
    private TextInputLayout et2;
    private MaterialButton btn1;
    private TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        //TextInputLayout vemail = (TextInputLayout)findViewById(R.id.usuarioTextField);
        //TextInputLayout vpass = (TextInputLayout)findViewById(R.id.contrasenaTextField);
        //String txt_email = vemail.getEditText().getText().toString().trim();
       // String txt_pass = vpass.getEditText().getText().toString().trim();

        et1 = findViewById(R.id.usuarioTextField);
        et2 = findViewById(R.id.contrasenaTextField);

        Button vbtnLog = (Button)findViewById(R.id.inicioSesion);
        vbtnLog.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String username =et1.getEditText().getText().toString();
                String password =et2.getEditText().getText().toString();

                boolean flagh = validarUsuario(username,password);
            }
        });
    }

    private boolean validarUsuario(String pmail, String ppass){

     if(TextUtils.isEmpty(pmail)){
            mostrarAlerta("Error! debe ingresar el Email");
            return  false;
        }
        if(TextUtils.isEmpty(ppass)){
            mostrarAlerta("Error! debe ingresar la Contraseña");
            return  false;
        }

////--------------aqui va tu funcion de llamada al web service
        int idusuario = existeUsuariows(pmail,ppass);

        if(idusuario>0){
            mostrarAlerta("Bienvenido!!!");
            Intent intent = new Intent(LoginActivity.this, MainActivityMenu.class);
            startActivity(intent);
            finish();
            return true;
        }else{
            mostrarAlerta("Error Usuario o Contraseña incorrectos");
            return false;
        }

    }

    /// esta funcion debe regrar el valor del web service y el valor se lo asignas
    /// a la variable vretorna
    private int existeUsuariows(String pmail, String ppass){
        int vretorna =1;
        funciones fn = new funciones();
        wsLogin vlog = new wsLogin();
        String encrypass =ppass;// fn.toSHA256(ppass);
        vretorna = vlog.wsValid(pmail,encrypass);

        return vretorna;
    }

    private void mostrarAlerta(String mensaje){
        Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_SHORT).show();
    }



}

