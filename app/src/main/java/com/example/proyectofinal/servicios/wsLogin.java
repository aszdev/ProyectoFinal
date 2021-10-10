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
        return 1;
    }
}


private class AuthTask extends AsyncTask<Void, Void, String> {
    public static final  String SOAP_ACTION = "http://192.168.1.10:8080/WSLogin/WebServiceLogin/iniciarSesion";
    public static final String METHOD = "iniciarSesion";
    public static final String NAMESPACE = "http://webservice/";
    public static final String URL = "http://192.168.1.10:8080/WSLogin/WebServiceLogin?wsdl";


    @Override
    protected String doInBackground(Void... params) {
        SoapObject request = new SoapObject(NAMESPACE, METHOD);
        request.addProperty("Correo", username);
        request.addProperty("Clave", password);
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
        SoapPrimitive result =
                (SoapPrimitive)response.getProperty("vUsuario");


        return result.toString();
    }


    @Override
    protected void onPostExecute(String aBoolean) {
        super.onPostExecute(aBoolean);
        tv1.setText("Resultado " + aBoolean);
    }
}



