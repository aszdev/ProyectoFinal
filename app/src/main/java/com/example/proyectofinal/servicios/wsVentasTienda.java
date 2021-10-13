package com.example.proyectofinal.servicios;

import android.os.AsyncTask;

import com.example.proyectofinal.modelos.ModeloProductosTienda;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class wsVentasTienda {
    public  List listarTiendas(){
        List lsProdT = null;


        return  lsProdT;
    }

    public List ventasTienda(String fechaini,String fechafin){
        List lsProdT = null;
        try{
            //lsProdT = new TaskProductosTienda().execute("","").get();
        }catch (Exception ex){

        }

        return  lsProdT;
    }

}



class TaskVentasTienda extends AsyncTask<String, Void, List> {
    public static final  String SOAP_ACTION = "http://192.168.1.2:8012/WebServices/Reportes/ProductosTienda";
    public static final String METHOD = "ProductosTienda";
    public static final String NAMESPACE = "http://servicios.org/";
    public static final String URL = "http://192.168.1.2:8012/WebServices/Reportes?wsdl";
    private  String username;
    private String password;
    static public   String resultado;


    @Override
    protected List doInBackground(String... strings) {
        ArrayList<ModeloProductosTienda> lsProdT = new ArrayList<>();
        SoapObject request = new SoapObject(NAMESPACE, METHOD);
        request.addProperty("tienda",strings[0]);
        request.addProperty("codigo", strings[1]);


        // System.out.println("Email = " + getUsername());
        //System.out.println("Pass = " + getPassword());

        SoapSerializationEnvelope envelope =
                new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = false;
        envelope.setOutputSoapObject(request);

        HttpTransportSE tranport = new HttpTransportSE(URL);
        try {
            tranport.call(SOAP_ACTION, envelope);
            SoapObject soapObject = (SoapObject)envelope.bodyIn;
            //  SoapPrimitive result =
            //        (SoapPrimitive)soapObject.getProperty(0);
            //System.out.println("Impresion: " +result);

            if (soapObject != null && soapObject.getPropertyCount() > 0) {
                for (int i = 0; i < soapObject.getPropertyCount(); i++) {
                    SoapObject so = (SoapObject) soapObject.getProperty(i);
                    ModeloProductosTienda obj = new ModeloProductosTienda();
                    obj.setNit(so.getPropertyAsString("nit"));
                    obj.setNomTienda(so.getPropertyAsString("nombreTienda"));
                    System.out.println( "\nNIT " + obj.getNit());
                    System.out.println( "\nTienda " + obj.getNomTienda());
                    lsProdT.add(obj);
                }
            }



            //SoapPrimitive result =
            //(SoapPrimitive)response;
/*
            SoapPrimitive result =
                    (SoapPrimitive)response.getProperty("return");
            res = result.toString();

            */
        } catch (IOException e){
            e.printStackTrace();
        } catch (XmlPullParserException e){
            e.printStackTrace();
        }



        return  lsProdT;

    }
}




class TaskListarTienda extends AsyncTask<String, Void, List> {
    public static final  String SOAP_ACTION = "http://192.168.1.2:8012/WebServices/Reportes/ProductosTienda";
    public static final String METHOD = "ProductosTienda";
    public static final String NAMESPACE = "http://servicios.org/";
    public static final String URL = "http://192.168.1.2:8012/WebServices/Reportes?wsdl";
    private  String username;
    private String password;
    static public   String resultado;


    @Override
    protected List doInBackground(String... strings) {
        ArrayList<ModeloProductosTienda> lsProdT = new ArrayList<>();
        SoapObject request = new SoapObject(NAMESPACE, METHOD);
        request.addProperty("tienda",strings[0]);
        request.addProperty("codigo", strings[1]);


        // System.out.println("Email = " + getUsername());
        //System.out.println("Pass = " + getPassword());

        SoapSerializationEnvelope envelope =
                new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = false;
        envelope.setOutputSoapObject(request);

        HttpTransportSE tranport = new HttpTransportSE(URL);
        try {
            tranport.call(SOAP_ACTION, envelope);
            SoapObject soapObject = (SoapObject)envelope.bodyIn;
            //  SoapPrimitive result =
            //        (SoapPrimitive)soapObject.getProperty(0);
            //System.out.println("Impresion: " +result);

            if (soapObject != null && soapObject.getPropertyCount() > 0) {
                for (int i = 0; i < soapObject.getPropertyCount(); i++) {
                    SoapObject so = (SoapObject) soapObject.getProperty(i);
                    ModeloProductosTienda obj = new ModeloProductosTienda();
                    obj.setNit(so.getPropertyAsString("nit"));
                    obj.setNomTienda(so.getPropertyAsString("nombreTienda"));
                    System.out.println( "\nNIT " + obj.getNit());
                    System.out.println( "\nTienda " + obj.getNomTienda());
                    lsProdT.add(obj);
                }
            }



            //SoapPrimitive result =
            //(SoapPrimitive)response;
/*
            SoapPrimitive result =
                    (SoapPrimitive)response.getProperty("return");
            res = result.toString();

            */
        } catch (IOException e){
            e.printStackTrace();
        } catch (XmlPullParserException e){
            e.printStackTrace();
        }



        return  lsProdT;

    }
}