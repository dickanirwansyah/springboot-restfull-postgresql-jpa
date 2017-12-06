package dicka.services;

import dicka.model.Barang;
import dicka.repository.RepositoryBarang;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Transactional
@Repository
public class ServiceImplBarang implements ServiceBarang{

    @PersistenceContext
    private EntityManager entityManager;

    private RepositoryBarang repositoryBarang;

    @Autowired
    public ServiceImplBarang(RepositoryBarang repositoryBarang){
        this.repositoryBarang = repositoryBarang;
    }

    @Override
    public void insertBarang(Barang barang) {
        entityManager.persist(barang);
    }

    @Override
    public Barang updateBarang(Barang barang) {
        if(!entityManager.contains(barang))
            barang = entityManager.merge(barang);
        return barang;
    }

    @Override
    public void deleteBarang(Barang barang) {
        entityManager.remove(barang);
    }

    @Override
    public Barang findOneBarangById(int idbarang) {
        String hql = "from Barang as bar where bar.idbarang = ?";
        return (Barang) entityManager.createQuery(hql).setParameter(1, idbarang)
                .getSingleResult();
    }

    @Override
    public Barang findOneBarangByNama(String nama) {
        String hql = "from Barang as bar where bar.nama = ?";
        return (Barang) entityManager.createQuery(hql).setParameter(1, nama)
                .getSingleResult();
    }

    @Override
    public List<Barang> findAllBarangs() {
        return repositoryBarang.findAll();
    }

    @Override
    public boolean ifBarangIsExist(String kodebarang) {
        String hql = "from Barang as bar where bar.kodebarang = ?";
        int counted = entityManager.createQuery(hql).setParameter(1, kodebarang)
                .getResultList().size();
        return counted > 0 ? true : false;
    }
}
