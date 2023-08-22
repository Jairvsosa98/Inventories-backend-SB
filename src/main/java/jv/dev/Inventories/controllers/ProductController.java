package jv.dev.Inventories.controllers;

import jv.dev.Inventories.exceptions.RecourseNotFoundException;
import jv.dev.Inventories.models.Product;
import jv.dev.Inventories.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
//http://localhost:9090/inventario-app/v1
@RequestMapping("inventory-app/v1")
@CrossOrigin("${client.url}")
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    //http://localhost:9090/inventario-app/products
    @GetMapping("/products")

    public List<Product> getProducts() {
        List<Product> products = this.productService.listProducts();
        logger.info("Productos Obtenidos:");
        products.forEach((product -> logger.info(product.toString())));
        return products;
    }

    @PostMapping("/products")

    public Product addProduct(@RequestBody Product product) {
        logger.info("Producto a agregar: " + product);
        return this.productService.saveProduct(product);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id) {
        Product product = this.productService.searchProductById(id);
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            throw new RecourseNotFoundException("No se encontró el id:" + id);
        }
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable int id, @RequestBody Product productResponse) {
        Product product = this.productService.searchProductById(id);
        if (product == null) {
            throw new RecourseNotFoundException("No se encontró el id: " + id);
        }
        product.setDescription(productResponse.getDescription());
        product.setPrice(productResponse.getPrice());
        product.setStock(productResponse.getStock());
        this.productService.saveProduct(product);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteProduct(@PathVariable int id) {
        Product product = productService.searchProductById(id);
        if (product == null) {
            throw new RecourseNotFoundException("No se encontró el id:" + id);
        }
        this.productService.deleteProductById(product.getIdProduct());
        Map<String, Boolean> response = new HashMap<>();
        response.put("success", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }


}
