package jv.dev.Inventories.service;

import jv.dev.Inventories.models.Product;

import java.util.List;

public interface IProductService {
    List<Product> listProducts();

    Product searchProductById(Integer idProduct);

    Product saveProduct(Product product);

    void deleteProductById(Integer idProduct);
}
