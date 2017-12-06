package dicka.webapp.controller;

import dicka.dao.DAOProduct;
import dicka.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class ControllerProduct {

    private DAOProduct daoProduct;

    private static final Logger LOGGER =
            LoggerFactory.getLogger(ControllerProduct.class);

    @Autowired
    public ControllerProduct(DAOProduct daoProduct){
        this.daoProduct = daoProduct;
    }

    @GetMapping(value = "/products")
    public ResponseEntity<List<Product>>findAllProduct(){
        LOGGER.debug("access products");
        List<Product> productList = daoProduct.findAllProducts();
        if(productList.isEmpty()){
            LOGGER.debug("data is empty");
            return new ResponseEntity<List<Product>>(HttpStatus.BAD_REQUEST);
        }
        System.out.println("access product oke");
        return new ResponseEntity<List<Product>>(productList, HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteProduct/{idproduct}")
    public ResponseEntity<Void>deleteProduct(@PathVariable String idproduct){
        LOGGER.info("access delete prodct");
        Product product = daoProduct.findOneProduct(Integer.parseInt(idproduct));
        if(product == null){
            LOGGER.info("data is empty");
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }

        daoProduct.deleteProduct(product);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PostMapping(value = "/updateProduct/{idproduct}")
    public ResponseEntity<Product>updateProduct(@PathVariable String idproduct,
                                                @RequestBody Product product){
        LOGGER.info("access update product");
        Product currentproduct = daoProduct.findOneProduct(Integer.parseInt(idproduct));
        if(currentproduct == null){
            LOGGER.info("data is empty");
            System.out.println("data null");
        }

        currentproduct.setActives(product.getActives());
        currentproduct.setDeskripsi(product.getDeskripsi());
        currentproduct.setKodeproduct(product.getKodeproduct());
        currentproduct.setKategori(product.getKategori());
        currentproduct.setNama(product.getNama());
        currentproduct.setTanggal(product.getTanggal());

        daoProduct.updateProduct(currentproduct);
        System.out.print("success update !");
        return new ResponseEntity<Product>(HttpStatus.ACCEPTED);
    }

    @PostMapping(value = "/insertProduct")
    public ResponseEntity<Product>insertProduct(@RequestBody Product product, UriComponentsBuilder builder){
        LOGGER.debug("access insert product");
        boolean valid = daoProduct.insertProduct(product);
        if(valid == false){
            LOGGER.debug("data is exist !");
            return new ResponseEntity<Product>(HttpStatus.CONFLICT);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/insertProduct/{idproduct}")
                .buildAndExpand(product.getIdproduct()).toUri());
        System.out.println("insert oke");
        return new ResponseEntity<Product>(headers, HttpStatus.CREATED);
    }

    @GetMapping(value = "/products/{idproduct}")
    public ResponseEntity<Product>findOneProduct(@PathVariable String idproduct){
        LOGGER.debug("access idproduct");
        Product product = daoProduct.findOneProduct(Integer.parseInt(idproduct));
        if(product == null){
            LOGGER.debug("data is empty");
            return new ResponseEntity<Product>(HttpStatus.BAD_REQUEST);
        }
        System.out.println("findone product ok");
        return new ResponseEntity<Product>(product, HttpStatus.OK);
    }
}
