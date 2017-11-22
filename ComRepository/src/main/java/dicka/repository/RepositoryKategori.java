package dicka.repository;

import dicka.model.Kategori;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface RepositoryKategori extends JpaRepository<Kategori, Integer>{

}
