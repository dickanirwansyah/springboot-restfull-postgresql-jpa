package dicka.dao;

import dicka.model.Product;

import java.util.List;

public interface DAOProduct {

    boolean insertProduct(Product product);

    void updateProduct(Product product);

    void deleteProduct(Product product);

    Product findOneProduct(int idproduct);

    List<Product> findAllProducts();
}
