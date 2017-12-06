package dicka.services;

import dicka.model.Pengguna;
import dicka.repository.RepositoryPengguna;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Transactional
@Repository
public class ServiceImplPengguna implements ServicePengguna {

    private RepositoryPengguna repositoryPengguna;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public ServiceImplPengguna(RepositoryPengguna repositoryPengguna){
        this.repositoryPengguna = repositoryPengguna;
    }

    @Override
    public void insertPengguna(Pengguna pengguna) {
        entityManager.persist(pengguna);
    }

    @Override
    public Pengguna updatePengguna(Pengguna pengguna) {
        if(!entityManager.contains(pengguna))
            pengguna = entityManager.merge(pengguna);
        return pengguna;
    }

    @Override
    public void deletePengguna(Pengguna pengguna) {
        entityManager.remove(pengguna);
    }

    @Override
    public List<Pengguna> findAllPengguna() {
        String hql = "from Pengguna";
        return entityManager.createQuery(hql).getResultList();
    }

    @Override
    public Pengguna findOnePenggunaById(int idpengguna) {
        String hql = "from Pengguna as p where p.idpengguna=?";
        return (Pengguna) entityManager.createQuery(hql).setParameter(1, idpengguna)
                .getSingleResult();
    }

    @Override
    public boolean ifUsernamePenggunaIsExist(String username) {
        String hql = "from Pengguna as p where p.username = ?";
        int counted = entityManager.createQuery(hql).setParameter(1, username)
                .getResultList().size();
        return counted > 0 ? true : false;
    }

    @Override
    public Pengguna CariBerdasarkanUsername(String username) {
        String hql = "from Pengguna as p where p.username = ?";
        return (Pengguna) entityManager.createQuery(hql).setParameter
                (1, username).getSingleResult();
    }
}
