package com.example.proyectofinal.modelos;

public class ModeloReporteVentas {

    private String cantidad_prod;
    private String fecha_venta;
    private String nit_tienda;
    private String nombre_Empleado;
    private String nombre_Tienda;
    private String numero_doc;
    private String tipo_doc;
    private double total_venta;
    private String unidades_vendidas;


    public String getCantidad_prod() {
        return cantidad_prod;
    }

    public void setCantidad_prod(String cantidad_prod) {
        this.cantidad_prod = cantidad_prod;
    }

    public String getFecha_venta() {
        return fecha_venta;
    }

    public void setFecha_venta(String fecha_venta) {
        this.fecha_venta = fecha_venta;
    }

    public String getNit_tienda() {
        return nit_tienda;
    }

    public void setNit_tienda(String nit_tienda) {
        this.nit_tienda = nit_tienda;
    }

    public String getNombre_Empleado() {
        return nombre_Empleado;
    }

    public void setNombre_Empleado(String nombre_Empleado) {
        this.nombre_Empleado = nombre_Empleado;
    }

    public String getNombre_Tienda() {
        return nombre_Tienda;
    }

    public void setNombre_Tienda(String nombre_Tienda) {
        this.nombre_Tienda = nombre_Tienda;
    }

    public String getNumero_doc() {
        return numero_doc;
    }

    public void setNumero_doc(String numero_doc) {
        this.numero_doc = numero_doc;
    }

    public String getTipo_doc() {
        return tipo_doc;
    }

    public void setTipo_doc(String tipo_doc) {
        this.tipo_doc = tipo_doc;
    }

    public double getTotal_venta() {
        return total_venta;
    }

    public void setTotal_venta(double total_venta) {
        this.total_venta = total_venta;
    }

    public String getUnidades_vendidas() {
        return unidades_vendidas;
    }

    public void setUnidades_vendidas(String unidades_vendidas) {
        this.unidades_vendidas = unidades_vendidas;
    }
}
