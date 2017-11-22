package dicka.dao;

import dicka.model.Product;
import dicka.services.ServiceProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DAOImplProduct implements DAOProduct{

    private ServiceProduct serviceProduct;

    @Autowired
    public DAOImplProduct(ServiceProduct serviceProduct){
        this.serviceProduct = serviceProduct;
    }

    @Override
    public boolean insertProduct(Product product) {
        if(serviceProduct.ifExisProductKode(product.getKodeproduct())){
            return false;
        }else{
            serviceProduct.insertProduct(product);
            return true;
        }
    }

    @Override
    public void updateProduct(Product product) {
        serviceProduct.updateProduct(product);
    }

    @Override
    public void deleteProduct(Product product) {
        serviceProduct.deleteProduct(product);
    }

    @Override
    public Product findOneProduct(int idproduct) {
        return serviceProduct.findOneProduct(idproduct);
    }

    @Override
    public List<Product> findAllProducts() {
        return serviceProduct.findAllProduct();
    }
}
