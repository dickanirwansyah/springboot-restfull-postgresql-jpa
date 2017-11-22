package dicka.services;

import dicka.model.Product;

import java.util.List;

public interface ServiceProduct {

    void insertProduct(Product product);

    Product updateProduct(Product product);

    void deleteProduct(Product product);

    Product findOneProduct(int idproduct);

    List<Product> findAllProduct();

    boolean ifExisProductKode(String kode);
}
