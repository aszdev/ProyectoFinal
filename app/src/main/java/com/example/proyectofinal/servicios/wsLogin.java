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

    //  new  AuthTask().execute();
        AuthTask at = new AuthTask();
        at.setUsername(pmail);
        at.setPassword(ppass);
        at.execute();
        int ret =0;
        try{
             String res= at.resultado;
            System.out.println("Resultado final: " +res);
             ret = Integer.valueOf(res);
        }catch (Exception ex){
            System.out.println("Error al convertir el numero: " + ex.getMessage());
        }
     return 1;
    }
}


class AuthTask extends AsyncTask<Void, Void, String> {
    public static final  String SOAP_ACTION = "http://192.168.1.2:8012/Usuario/IniciarSesionRequest";
    public static final String METHOD = "IniciarSesion";
    public static final String NAMESPACE = "http://servicios.org/";
    public static final String URL = "http://192.168.1.2:8012/WebServices/Usuario?wsdl";
    private  String username;
    private String password;
   static public   String resultado;

    @Override
    protected String doInBackground(Void... params) {
        SoapObject request = new SoapObject(NAMESPACE, METHOD);
        request.addProperty("Correo",getUsername());
        request.addProperty("Clave", getPassword());
        String res="0";
       // System.out.println("Email = " + getUsername());
        //System.out.println("Pass = " + getPassword());

        SoapSerializationEnvelope envelope =
                new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = false;
        envelope.setOutputSoapObject(request);

        HttpTransportSE tranport = new HttpTransportSE(URL);
        try {
            tranport.call(SOAP_ACTION, envelope);
            SoapObject response = (SoapObject)envelope.bodyIn;
            //SoapPrimitive result =
            //(SoapPrimitive)response;

            SoapPrimitive result =
                    (SoapPrimitive)response.getProperty("return");
            res = result.toString();
        } catch (IOException e){
            e.printStackTrace();
        } catch (XmlPullParserException e){
            e.printStackTrace();
        }

       // String result="1";
        System.out.println("resultado: " + res);
        this.resultado = res;
        return res;
    }


    @Override
    protected void onPostExecute(String aBoolean) {
        super.onPostExecute(aBoolean);
        //tv1.setText("Resultado " + aBoolean);
        this.resultado = aBoolean;
       //setResultado(aBoolean);
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

}



