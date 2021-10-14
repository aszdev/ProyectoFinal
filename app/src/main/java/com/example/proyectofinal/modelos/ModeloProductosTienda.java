package com.example.proyectofinal.modelos;

public class ModeloProductosTienda {
private String Nit;
private String NomTienda;
private String DirTienda;
private String codprod;
private String NomProd;
private String Descripcion;
private int Stock;
private double PrecioCompra;
private double PrecioVenta;


    public String getNit() {
        return Nit;
    }

    public void setNit(String nit) {
        Nit = nit;
    }

    public String getNomTienda() {
        return NomTienda;
    }

    public void setNomTienda(String nomTienda) {
        NomTienda = nomTienda;
    }

    public String getDirTienda() {
        return DirTienda;
    }

    public void setDirTienda(String dirTienda) {
        DirTienda = dirTienda;
    }

    public String getCodprod() {
        return codprod;
    }

    public void setCodprod(String codprod) {
        this.codprod = codprod;
    }

    public String getNomProd() {
        return NomProd;
    }

    public void setNomProd(String nomProd) {
        NomProd = nomProd;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public int getStock() {
        return Stock;
    }

    public void setStock(int stock) {
        Stock = stock;
    }

    public double getPrecioCompra() {
        return PrecioCompra;
    }

    public void setPrecioCompra(double precioCompra) {
        PrecioCompra = precioCompra;
    }

    public double getPrecioVenta() {
        return PrecioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        PrecioVenta = precioVenta;
    }
}
