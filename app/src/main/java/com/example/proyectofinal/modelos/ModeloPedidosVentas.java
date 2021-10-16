package com.example.proyectofinal.modelos;

public class ModeloPedidosVentas {
    private int IDCLIENTE;
    private String NOMBRE;
    private String DIRECCION;
    private String TELEFONO;
    private int IDVENTA;
    private double IMPORTERECIBIDO;
    private int SELECCIONAR;
    private int idTienda;

    public int getIdTienda() {
        return idTienda;
    }

    public void setIdTienda(int idTienda) {
        this.idTienda = idTienda;
    }


    public int getIDCLIENTE() {
        return IDCLIENTE;
    }

    public void setIDCLIENTE(int IDCLIENTE) {
        this.IDCLIENTE = IDCLIENTE;
    }

    public String getNOMBRE() {
        return NOMBRE;
    }

    public void setNOMBRE(String NOMBRE) {
        this.NOMBRE = NOMBRE;
    }

    public String getDIRECCION() {
        return DIRECCION;
    }

    public void setDIRECCION(String DIRECCION) {
        this.DIRECCION = DIRECCION;
    }

    public String getTELEFONO() {
        return TELEFONO;
    }

    public void setTELEFONO(String TELEFONO) {
        this.TELEFONO = TELEFONO;
    }

    public int getIDVENTA() {
        return IDVENTA;
    }

    public void setIDVENTA(int IDVENTA) {
        this.IDVENTA = IDVENTA;
    }

    public double getIMPORTERECIBIDO() {
        return IMPORTERECIBIDO;
    }

    public void setIMPORTERECIBIDO(double IMPORTERECIBIDO) {
        this.IMPORTERECIBIDO = IMPORTERECIBIDO;
    }

    public int getSELECCIONAR() {
        return SELECCIONAR;
    }

    public void setSELECCIONAR(int SELECCIONAR) {
        this.SELECCIONAR = SELECCIONAR;
    }





}
