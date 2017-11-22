package dicka.services;

import dicka.model.Product;
import dicka.repository.RepositoryProduct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
@Validated
public class ServiceImplProduct implements ServiceProduct{

    @PersistenceContext
    private EntityManager entityManager;

    private RepositoryProduct repositoryProduct;

    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceImplProduct.class);

    @Autowired
    public ServiceImplProduct(RepositoryProduct repositoryProduct){
        this.repositoryProduct = repositoryProduct;
    }

    @Override
    public void insertProduct(Product product) {
        LOGGER.debug("insert product");
        entityManager.persist(product);
    }

    @Override
    public Product updateProduct(Product product) {
       LOGGER.info("update product");
       if(!entityManager.contains(product))
           product = entityManager.merge(product);
       return product;
    }

    @Override
    public void deleteProduct(Product product) {
        LOGGER.info("delete product");
        entityManager.remove(product);
    }

    @Override
    public Product findOneProduct(int idproduct) {
        LOGGER.info("find one product");
        return repositoryProduct.findOne(idproduct);
    }

    @Override
    public List<Product> findAllProduct() {
        LOGGER.info("find all product");
        return repositoryProduct.findAll();
    }

    @Override
    public boolean ifExisProductKode(String kode) {
        LOGGER.debug("product is exist");
        String hql="from Product as produk where produk.kodeproduct = ?";
        int counted = entityManager.createQuery(hql).setParameter(1, kode)
                .getResultList().size();
        return counted > 0 ? true : false;
    }
}
