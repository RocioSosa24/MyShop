package com.myshop.MyShop.Model.Entity;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Carrito {
    private List<ItemCarrito> items = new ArrayList<>();

    public Carrito() {
    }

    public void addItem(Producto producto) {
        for (ItemCarrito item : items) {
            if (item.getProducto().getId().equals(producto.getId())) {
                item.incrementCantidad();
                return;
            }
        }
        items.add(new ItemCarrito(producto));
    }

    public void removeItem(Producto producto) {
        items.removeIf(item -> item.getProducto().getId().equals(producto.getId()));
    }

    public List<ItemCarrito> getItems() {
        return items;
    }

    public double getTotal() {
        return items.stream()
                .mapToDouble(ItemCarrito::getSubtotal)
                .sum();
    }

    public void actualizarCantidad(Producto producto, int nuevaCantidad) {
        for (ItemCarrito item : items) {
            if (item.getProducto().getId().equals(producto.getId())) {
                item.setCantidad(nuevaCantidad);
                return;
            }
        }
    }
    public void incrementQuantity(Producto producto) {
        for (ItemCarrito item : items) {
            if (item.getProducto().getId().equals(producto.getId())) {
                item.incrementCantidad();
                return;
            }
        }
    }

    public void decrementQuantity(Producto producto) {
        for (ItemCarrito item : items) {
            if (item.getProducto().getId().equals(producto.getId())) {
                if (item.getCantidad() > 1) {
                    item.decrementCantidad();
                }
                return;
            }
        }
    }

}
