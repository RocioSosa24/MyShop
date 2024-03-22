package com.myshop.MyShop.Model.Entity.Service;

import com.myshop.MyShop.Model.Entity.Producto;
import com.myshop.MyShop.Model.Entity.Repository.ProductoRepository;
import com.myshop.MyShop.Model.Entity.Repository.TipoRepository;
import com.myshop.MyShop.Model.Entity.TipoProducto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductoService {

    private final TipoRepository tipoRepository;
    private final ProductoRepository productoRepository;

    @Autowired
    public ProductoService(TipoRepository tipoRepository, ProductoRepository productoRepository) {
        this.tipoRepository = tipoRepository;
        this.productoRepository = productoRepository;
    }

    public List<Producto> obtenerTodosLosProductos() {
        return productoRepository.findAll();
    }

    public Optional<Producto> obtenerProductoPorId(Integer id) {
        return productoRepository.findById(id);
    }

    public List<Producto> filterByPriceRange(double minPrice, double maxPrice) {
        return productoRepository.findByPrecioBetween(minPrice, maxPrice);
    }

    public List<Producto> filterByProductType(List<TipoProducto> tipoProductos) {
        List<Long> tipoProductoIds = tipoProductos.stream().map(TipoProducto::getId).collect(Collectors.toList());
        return productoRepository.findByTipoProductoIdIn(tipoProductoIds);
    }

    public List<Producto> search(String busqueda) {
        return productoRepository.findByNombreContainingIgnoreCase(busqueda);
    }

    public List<Producto> filterBySizes(List<String> talles) {
        return productoRepository.findByTalleIn(talles);
    }

    public List<Producto> obtenerUltimos4Productos() {
        return productoRepository.findTop4ByOrderByCreacionDesc();
    }

    public List<Producto> filterByPriceRangeAndType(double minPrice, double maxPrice, List<TipoProducto> tipoProductos) {
        List<Long> tipoProductoIds = tipoProductos.stream().map(TipoProducto::getId).collect(Collectors.toList());
        return productoRepository.findByPrecioBetweenAndTipoProductoIdIn(minPrice, maxPrice, tipoProductoIds);
    }

    public List<Producto> filterByPriceRangeAndSizes(Double minPrice, Double maxPrice, List<String> talles) {
        return productoRepository.findByPrecioBetweenAndTalleIn(minPrice, maxPrice, talles);
    }

    public List<Producto> filterByTypeAndSizes(List<TipoProducto> tipoProductos, List<String> talles) {
        List<Long> tipoProductoIds = tipoProductos.stream().map(TipoProducto::getId).collect(Collectors.toList());
        return productoRepository.findByTipoProductoIdInAndTalleIn(tipoProductoIds, talles);
    }

    public List<Producto> filterByPriceTypeAndSizes(Double minPrice, Double maxPrice, List<TipoProducto> tipoProductos, List<String> talles) {
        List<Long> tipoProductoIds = tipoProductos.stream().map(TipoProducto::getId).collect(Collectors.toList());
        return productoRepository.findByPrecioBetweenAndTipoProductoIdInAndTalleIn(minPrice, maxPrice, tipoProductoIds, talles);
    }
}
