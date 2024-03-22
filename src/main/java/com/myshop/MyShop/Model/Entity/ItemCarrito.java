package com.myshop.MyShop.Model.Entity;

public class ItemCarrito {
    private Producto producto;
    private int cantidad;

    public ItemCarrito(Producto producto) {
        this.producto = producto;
        this.cantidad = 1;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void incrementCantidad() {
        cantidad++;
    }

    public void decrementCantidad() {
        cantidad--;
    }

    public double getSubtotal() {
        return producto.getPrecio() * cantidad;
    }

}
