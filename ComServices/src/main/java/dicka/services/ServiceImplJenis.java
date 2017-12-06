package dicka.services;

import dicka.model.Jenis;
import dicka.repository.RepositoryJenis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Transactional
@Repository
public class ServiceImplJenis implements ServiceJenis{


    private RepositoryJenis repositoryJenis;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public ServiceImplJenis(RepositoryJenis repositoryJenis){
        this.repositoryJenis = repositoryJenis;
    }

    @Override
    public void insertJenis(Jenis jenis) {
        entityManager.persist(jenis);
    }

    @Override
    public Jenis updateJenis(Jenis jenis) {
        if(!entityManager.contains(jenis))
            jenis = entityManager.merge(jenis);
        return jenis;
    }

    @Override
    public void deleteJenis(Jenis jenis) {
        entityManager.remove(jenis);
    }

    @Override
    public Jenis findByJenisById(int idjenis) {
        return repositoryJenis.findOne(idjenis);
    }

    @Override
    public List<Jenis> findAllJenis() {
       return repositoryJenis.findAll();
    }

    @Override
    public boolean ifExistJenis(String kodejenis) {
        String hql="from Jenis as jen where jen.kodejenis = ?";
        int counted = entityManager.createQuery(hql).setParameter(1, kodejenis)
                .getResultList().size();
        return counted > 0 ? true : false;
    }
}
