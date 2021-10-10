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

import com.example.proyectofinal.servicios.wsLogin;
import com.google.android.material.textfield.TextInputLayout;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class LoginActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        TextInputLayout vemail = (TextInputLayout)findViewById(R.id.usuarioTextField);
        TextInputLayout vpass = (TextInputLayout)findViewById(R.id.contrasenaTextField);
        String txt_emal = vemail.getEditText().getText().toString();
        String txt_pass = vpass.getEditText().getText().toString();

        Button vbtnLog = (Button)findViewById(R.id.inicioSesion);
        vbtnLog.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                boolean flagh = validarUsuario(txt_emal,txt_pass);
            }
        });
    }

    private boolean validarUsuario(String pmail, String ppass){

     /*   if(TextUtils.isEmpty(pmail)){
            mostrarAlerta("Error! debe ingresar el Email");
            return  false;
        }
        if(TextUtils.isEmpty(ppass)){
            mostrarAlerta("Error! debe ingresar la Contraseña");
            return  false;
        }*/

////--------------aqui va tu funcion de llamada al web service
        int idusuario = existeUsuariows(pmail,ppass);

        if(idusuario>0){
            mostrarAlerta("Bienvenido!!!");
            Intent intent = new Intent(LoginActivity.this, DashBoard.class);
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

        wsLogin vlog = new wsLogin();
        vretorna = vlog.wsValid(pmail,ppass);

        return vretorna;
    }

    private void mostrarAlerta(String mensaje){
        Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_SHORT).show();
    }



}

