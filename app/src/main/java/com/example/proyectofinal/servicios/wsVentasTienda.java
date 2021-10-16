package com.example.proyectofinal.servicios;

import android.os.AsyncTask;

import com.example.proyectofinal.clases.Globales;
import com.example.proyectofinal.modelos.ModeloListarTiendas;
import com.example.proyectofinal.modelos.ModeloPedidosVentas;
import com.example.proyectofinal.modelos.ModeloReporteVentas;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class wsVentasTienda {


    public List listarTiendas(){
        List lsTienda = null;
        try{
            lsTienda = new TaskListarTienda().execute().get();
        }catch (Exception ex){

        }

        return  lsTienda;
    }

    public List listarVentas(String fechaini,String fechafin,String tienda){
        List lsvent = null;
        try{
            System.out.println("Si llega");
            lsvent = new TaskVentasTienda().execute(fechaini,fechafin,tienda).get();
        }catch (Exception ex){

        }

        return  lsvent;
    }

    public List listarVentasPendiente(String idtienda){
        List lsvent = null;
        try{
            System.out.println("Si llega");
            lsvent = new TaskVentasPendiente().execute(idtienda).get();
        }catch (Exception ex){

        }

        return  lsvent;
    }

    public boolean actualizar(String idventa,String idusuario){
        boolean lsvent = true;
        try{
            System.out.println("Si llega");
            lsvent = new TaskActualizar().execute(idventa,idusuario).get();
        }catch (Exception ex){

        }
    return  true;

    }

}



class TaskListarTienda extends AsyncTask<String, Void, List> {
    public static final  String SOAP_ACTION = "http://"+Globales.IP+":"+Globales.Port+"/WebServices/Reportes/ListadoTiendas";
    public static final String METHOD = "ListadoTiendas";
    public static final String NAMESPACE = "http://servicios.org/";
    public static final String URL = "http://"+Globales.IP+":"+Globales.Port+"/WebServices/Reportes?wsdl";

    private  String username;
    private String password;
    static public   String resultado;


    @Override
    protected List doInBackground(String... strings) {
        ArrayList<ModeloListarTiendas> lsProdT = new ArrayList<>();
        SoapObject request = new SoapObject(NAMESPACE, METHOD);
        // request.addProperty("direccion",strings[0]);
        //request.addProperty("codigo", strings[1]);


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
                    ModeloListarTiendas obj = new ModeloListarTiendas();
                    obj.setDireccion(so.getPropertyAsString("direccion"));
                    obj.setIdTienda(so.getPropertyAsString("idTienda"));
                    obj.setNombre(so.getPropertyAsString("nombre"));
                    obj.setTelefono(so.getPropertyAsString("telefono"));

                    System.out.println( "\ndireccion " + obj.getDireccion());
                    System.out.println( "\nidTienda " + obj.getIdTienda());
                    System.out.println( "\nnombre " + obj.getNombre());
                    System.out.println( "\ntelefono " + obj.getTelefono());
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




class TaskVentasTienda extends AsyncTask<String, Void, List> {
    public static final  String SOAP_ACTION = "http://"+Globales.IP+":"+Globales.Port+"/WebServices/Reportes/ReporteVenta";
    public static final String METHOD = "ReporteVenta";
    public static final String NAMESPACE = "http://servicios.org/";
    public static final String URL = "http://"+Globales.IP+":"+Globales.Port+"/WebServices/Reportes?wsdl";
    private  String username;
    private String password;
    static public   String resultado;


    @Override
    protected List doInBackground(String... strings) {
        ArrayList<ModeloReporteVentas> lstVentas = new ArrayList<>();
        SoapObject request = new SoapObject(NAMESPACE, METHOD);
        request.addProperty("FechaInicio",strings[0]);
        request.addProperty("FechaFinal", strings[1]);
        request.addProperty("TiendaID", strings[2]);

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
                    ModeloReporteVentas obj = new ModeloReporteVentas();
                    obj.setCantidad_prod(so.getPropertyAsString("cantidad_prod"));
                    obj.setFecha_venta(so.getPropertyAsString("fecha_Venta"));
                    obj.setNit_tienda(so.getPropertyAsString("nit_Tienda"));
                    obj.setNombre_Empleado(so.getPropertyAsString("nombre_Empleado"));
                    obj.setNombre_Tienda(so.getPropertyAsString("nombre_Tienda"));
                    obj.setNumero_doc(so.getPropertyAsString("numero_Documento"));
                    obj.setTipo_doc(so.getPropertyAsString("tipo_Documento"));

                    obj.setUnidades_vendidas(so.getPropertyAsString("unidades_Vendidas"));
                    try{
                        obj.setTotal_venta(Double.parseDouble(so.getPropertyAsString("total_Venta").toString()));


                    }catch (Exception ex){

                    }
                    System.out.println(so.getPropertyAsString("nombre_Empleado"));

                    lstVentas.add(obj);

                }
            }

        } catch (IOException e){
            e.printStackTrace();
        } catch (XmlPullParserException e){
            e.printStackTrace();
        }


        return  lstVentas;

    }
}


class TaskVentasPendiente extends AsyncTask<String, Void, List> {
    public static final  String SOAP_ACTION = "http://"+Globales.IP+":"+Globales.Port+"/WebServices/Reportes/ListarPedidoVentas";
    public static final String METHOD = "ListarPedidoVentas";
    public static final String NAMESPACE = "http://servicios.org/";
    public static final String URL = "http://"+Globales.IP+":"+Globales.Port+"/WebServices/Reportes?wsdl";
    private  String username;
    private String password;
    static public   String resultado;


    @Override
    protected List doInBackground(String... strings) {
        ArrayList<ModeloPedidosVentas> lstVentas = new ArrayList<>();
        SoapObject request = new SoapObject(NAMESPACE, METHOD);
        request.addProperty("IdTienda",strings[0]);


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
                    ModeloPedidosVentas obj = new ModeloPedidosVentas();

                    obj.setDIRECCION(so.getPropertyAsString("DIRECCION"));


                    obj.setNOMBRE(so.getPropertyAsString("NOMBRE"));
                    obj.setTELEFONO(so.getPropertyAsString("TELEFONO"));

                    try{
                        obj.setIDVENTA(Integer.parseInt(so.getPropertyAsString("IDVENTA")));
                        obj.setIDCLIENTE(Integer.parseInt(so.getPropertyAsString("IDCLIENTE")));
                        obj.setIMPORTERECIBIDO(Double.parseDouble(so.getPropertyAsString("IMPORTERECIBIDO").toString()));


                    }catch (Exception ex){

                    }

                    lstVentas.add(obj);

                }
            }

        } catch (IOException e){
            e.printStackTrace();
        } catch (XmlPullParserException e){
            e.printStackTrace();
        }


        return  lstVentas;

    }
}



class TaskActualizar extends AsyncTask<String, Void, Boolean> {
    public static final  String SOAP_ACTION = "http://"+Globales.IP+":"+Globales.Port+"/WebServices/Reportes/Actualizar";
    public static final String METHOD = "Actualizar";
    public static final String NAMESPACE = "http://servicios.org/";
    public static final String URL = "http://"+Globales.IP+":"+Globales.Port+"/WebServices/Reportes?wsdl";
    private  String username;
    private String password;
    static public   String resultado;


    @Override
    protected Boolean doInBackground(String... strings) {
        ArrayList<ModeloPedidosVentas> lstVentas = new ArrayList<>();
        SoapObject request = new SoapObject(NAMESPACE, METHOD);
        request.addProperty("ID_VENTA",strings[0]);
        request.addProperty("ID_MENSAJERO",strings[1]);


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
              SoapPrimitive result =
                  (SoapPrimitive)soapObject.getProperty("return");
            //System.out.println("Impresion: " +result);



        } catch (IOException e){
            e.printStackTrace();
        } catch (XmlPullParserException e){
            e.printStackTrace();
        }


        return  true;

    }
}