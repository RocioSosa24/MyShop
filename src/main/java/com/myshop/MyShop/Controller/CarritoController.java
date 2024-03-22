package com.myshop.MyShop.Controller;

import com.myshop.MyShop.Model.Entity.Carrito;
import com.myshop.MyShop.Model.Entity.Producto;
import com.myshop.MyShop.Model.Entity.Service.ProductoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CarritoController {
    private final Carrito carrito;
    private final ProductoService productoService;

    public CarritoController(Carrito carrito, ProductoService productoService) {
        this.carrito = carrito;
        this.productoService = productoService;
    }

    @GetMapping("/cart")
    public String viewCart(Model model) {
        model.addAttribute("cart", carrito);
        model.addAttribute("items", carrito.getItems());
        return "cart";
    }

    @PostMapping("/add")
    public String addToCart(@RequestParam("id") Integer productId) {
        Producto productoFromDB = getProductById(productId);
        if (productoFromDB != null) {
            carrito.addItem(productoFromDB);
        }
        return "redirect:/cart";
    }

    @PostMapping("/updateQuantity/{id}")
    public String updateQuantity(@PathVariable("id") Integer id, @RequestParam("cantidad") int cantidad, @RequestParam("operation") String operation) {
        Producto producto = getProductById(id);
        if (producto != null) {
            if ("increment".equals(operation)) {
                carrito.incrementQuantity(producto);
            } else if ("decrement".equals(operation)) {
                carrito.decrementQuantity(producto);
            }
        }
        return "redirect:/cart";
    }

    @GetMapping("/remove/{id}")
    public String removeFromCart(@PathVariable("id") Integer id) {
        Producto producto = getProductById(id);
        if (producto != null) {
            carrito.removeItem(producto);
        }
        return "redirect:/cart";
    }

    private Producto getProductById(Integer id) {
        return productoService.obtenerProductoPorId(id).orElse(null);
    }
}
