package com.example.proyectofinal.servicios;

import android.accessibilityservice.GestureDescription;
import android.os.AsyncTask;

import com.example.proyectofinal.modelos.ModeloProductosTienda;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class wsProductosTienda {
public  List produtosTienda(String tienda,String prod){
    List lsProdT = null;
    try{
        lsProdT = new TaskProductosTienda().execute(tienda,"").get();
    }catch (Exception ex){

    }

    return  lsProdT;
}
}

class TaskProductosTienda extends AsyncTask<String, Void, List> {
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
                    obj.setNomTienda(so.getPropertyAsString("nombretienda"));
                    obj.setDirTienda(so.getPropertyAsString("direccion"));
                    obj.setCodprod(so.getPropertyAsString("codigoprod"));
                    obj.setNomProd(so.getPropertyAsString("nombreProducto"));
                    obj.setDescripcion(so.getPropertyAsString("descripcion"));
                    try{
                        obj.setStock(Integer.parseInt(so.getPropertyAsString("stock").toString()));
                        obj.setPrecioCompra(Double.parseDouble(so.getPropertyAsString("precioCompra").toString()));
                        obj.setPrecioVenta(Double.parseDouble(so.getPropertyAsString("precioVenta").toString()));

                    }catch (Exception ex){

                    }

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