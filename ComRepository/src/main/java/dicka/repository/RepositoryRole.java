package dicka.repository;

import dicka.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface RepositoryRole extends JpaRepository<Role, Integer>{

}
