package dicka.repository;

import dicka.model.Pengguna;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface RepositoryPengguna extends JpaRepository<Pengguna, Integer>{

}
