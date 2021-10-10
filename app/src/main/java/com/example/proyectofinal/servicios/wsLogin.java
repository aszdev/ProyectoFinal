package com.example.proyectofinal.servicios;

import android.os.AsyncTask;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class wsLogin {
    public int wsValid(String pmail, String ppass){

      new  AuthTask().execute();
     //at.setUsername("ddasdsasa");
     //at.setPassword("dsadsdasasaddssd");
     //at.execute();
     //String res= at.getResultado();
     return 1;
    }
}


class AuthTask extends AsyncTask<Void, Void, String> {
    public static final  String SOAP_ACTION = "http://localhost:8012/WebServices/Usuario/IniciarSesion";
    public static final String METHOD = "IniciarSesion";
    public static final String NAMESPACE = "http://webservice/";
    public static final String URL = "http://localhost:8012/WebServices/Usuario?wsdl";
    private  String username;
    private String password;
    private  String resultado;

    @Override
    protected String doInBackground(Void... params) {
        SoapObject request = new SoapObject(NAMESPACE, METHOD);
        request.addProperty("Correo","ddddd");
        request.addProperty("Clave", "ddsadsad");

       // System.out.println("Email = " + getUsername());
        //System.out.println("Pass = " + getPassword());

        SoapSerializationEnvelope envelope =
                new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = false;
        envelope.setOutputSoapObject(request);

        HttpTransportSE tranport = new HttpTransportSE(URL);
        try {
            tranport.call(SOAP_ACTION, envelope);
        } catch (IOException e){
            e.printStackTrace();
        } catch (XmlPullParserException e){
            e.printStackTrace();
        }
        SoapObject response = (SoapObject)envelope.bodyIn;
       // SoapPrimitive result =
            //    (SoapPrimitive)response.;

       SoapPrimitive result =
              (SoapPrimitive)response.getProperty("vUsuario");
      //  String result="1";

        return result.toString();
    }


    @Override
    protected void onPostExecute(String aBoolean) {
        super.onPostExecute(aBoolean);
        //tv1.setText("Resultado " + aBoolean);
        setResultado(aBoolean);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
}



