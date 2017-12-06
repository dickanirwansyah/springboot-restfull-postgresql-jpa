package dicka.repository;

import dicka.model.Barang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface RepositoryBarang extends JpaRepository<Barang, Integer>{
}
