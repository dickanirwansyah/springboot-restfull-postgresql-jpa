package dicka.repository;

import dicka.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface RepositoryProduct extends JpaRepository<Product, Integer>{
}
