package dicka.repository;

import dicka.model.Jenis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface RepositoryJenis extends JpaRepository<Jenis, Integer>{

}
