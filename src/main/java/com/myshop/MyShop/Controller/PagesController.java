package com.myshop.MyShop.Controller;

import com.myshop.MyShop.Model.Entity.Producto;
import com.myshop.MyShop.Model.Entity.Repository.TipoRepository;
import com.myshop.MyShop.Model.Entity.Service.ProductoService;
import com.myshop.MyShop.Model.Entity.TipoProducto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class PagesController {
    private final ProductoService productoService;
    private final TipoRepository tipoRepository;

    @Autowired
    public PagesController(ProductoService productoService, TipoRepository tipoRepository) {
        this.productoService = productoService;
        this.tipoRepository = tipoRepository;
    }

    @GetMapping("/index")
    public String index(Model model) {

        List<Producto> productos = productoService.obtenerTodosLosProductos();
        model.addAttribute("productos", productos);

        List<Producto> ultimosProductos = productoService.obtenerUltimos4Productos();
        model.addAttribute("ultimosProductos", ultimosProductos);

        return "index";
    }

    @GetMapping("/shop")
    public String filterProducts(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "price-max", required = false) Double maxPrice,
            @RequestParam(value = "size", required = false) List<String> talles,
            @RequestParam(value = "search", required = false) String busqueda,
            @RequestParam(value = "productType", required = false) List<Long> idTipoProductoIds,
            Model model) {

        List<TipoProducto> idTipoProducto = null;
        if (idTipoProductoIds != null) {
            List<Integer> idTipoProductoIntegers = idTipoProductoIds.stream()
                    .map(Long::intValue)
                    .collect(Collectors.toList());

            idTipoProducto = tipoRepository.findAllById(idTipoProductoIntegers);
        }
        int pageSize = 4;
        int startIndex = (page - 1) * pageSize;

        List<Producto> filteredProducts;

        Double minPrice = 0.0;

        if (maxPrice != null && idTipoProducto != null && !idTipoProducto.isEmpty() && talles != null && !talles.isEmpty()) {
            // Filtrado por precio, tipo de producto y talles
            filteredProducts = productoService.filterByPriceTypeAndSizes(minPrice, maxPrice, idTipoProducto, talles);
        } else if (maxPrice != null && idTipoProducto != null && !idTipoProducto.isEmpty()) {
            // Filtrado por precio y tipo de producto
            filteredProducts = productoService.filterByPriceRangeAndType(minPrice, maxPrice, idTipoProducto);
        } else if (maxPrice != null && talles != null && !talles.isEmpty()) {
            // Filtrado por precio y talles
            filteredProducts = productoService.filterByPriceRangeAndSizes(minPrice, maxPrice, talles);
        } else if (idTipoProducto != null && !idTipoProducto.isEmpty() && talles != null && !talles.isEmpty()) {
            // Filtrado por tipo de producto y talles
            filteredProducts = productoService.filterByTypeAndSizes(idTipoProducto, talles);
        } else if (maxPrice != null) {
            // Filtrado solo por precio
            filteredProducts = productoService.filterByPriceRange(minPrice, maxPrice);
        } else if (idTipoProducto != null && !idTipoProducto.isEmpty()) {
            // Filtrado solo por tipo de producto
            filteredProducts = productoService.filterByProductType(idTipoProducto);
        } else if (talles != null && !talles.isEmpty()) {
            // Filtrado solo por talles
            filteredProducts = productoService.filterBySizes(talles);
        } else if (busqueda != null && !busqueda.isEmpty()) {
            filteredProducts = productoService.search(busqueda);
        } else {
            filteredProducts = productoService.obtenerTodosLosProductos();
        }

        // Ver si no se encontraron productos
        if (filteredProducts.isEmpty()) {
            model.addAttribute("noProductos", true);
            model.addAttribute("productos", Collections.emptyList());
            model.addAttribute("totalPaginas", 0);
            model.addAttribute("paginaActual", 0);
            model.addAttribute("maxPrice", maxPrice);
            model.addAttribute("talles", talles);
            model.addAttribute("busqueda", busqueda);
            model.addAttribute("idTipoProducto", idTipoProductoIds);
            return "shop";
        } else {
            // Paginación
            int totalProductos = filteredProducts.size();
            int totalPages = (int) Math.ceil((double) totalProductos / pageSize);

            if (startIndex >= totalProductos) {
                page = totalPages;
                startIndex = (page - 1) * pageSize;
            }

            int endIndex = Math.min(startIndex + pageSize, totalProductos);
            filteredProducts = filteredProducts.subList(startIndex, endIndex);

            model.addAttribute("productos", filteredProducts);
            model.addAttribute("totalPaginas", totalPages);
            model.addAttribute("paginaActual", page);

            model.addAttribute("maxPrice", maxPrice);
            model.addAttribute("talles", talles);
            model.addAttribute("busqueda", busqueda);
            model.addAttribute("idTipoProducto", idTipoProductoIds);
            return "shop";
        }
    }


    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }

    @GetMapping("/detail/{id}")
    public String detailId(@PathVariable Integer id, Model model) {
        Optional<Producto> productoOptional = productoService.obtenerProductoPorId(id);

        if (productoOptional.isPresent()) {
            Producto producto = productoOptional.get();
            model.addAttribute("producto", producto);
            return "detail";
        } else {

            return "producto_no_encontrado";
        }

    }
}
