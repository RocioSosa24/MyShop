package com.myshop.MyShop.Model.Entity.Repository;

import com.myshop.MyShop.Model.Entity.Producto;
import com.myshop.MyShop.Model.Entity.TipoProducto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    // Búsqueda por precio
    List<Producto> findByPrecioBetween(double minPrice, double maxPrice);

    // Búsqueda de los últimos 4 productos por fecha de creación
    List<Producto> findTop4ByOrderByCreacionDesc();

    // Búsqueda por tipo de producto
    List<Producto> findByTipoProductoIdIn(List<Long> tipoProductoIds);
    List<Producto> findByTipoProductoIdInAndTalleIn(List<Long> tipoProductoIds, List<String> talles);

    // Búsqueda por precio y tipo de producto
    List<Producto> findByPrecioBetweenAndTipoProductoIdIn(double minPrice, double maxPrice, List<Long> tipoProductoIds);
    List<Producto> findByPrecioBetweenAndTipoProductoIdInAndTalleIn(double minPrice, double maxPrice, List<Long> tipoProductoIds, List<String> talles);

    // Búsqueda por talle
    List<Producto> findByTalleIn(List<String> talles);
    List<Producto> findByPrecioBetweenAndTalleIn(Double minPrice, Double maxPrice, List<String> talles);

    // Búsqueda por nombre (ignorando mayúsculas y minúsculas)
    List<Producto> findByNombreContainingIgnoreCase(String busqueda);
}
