package dicka.services;

import dicka.model.Kategori;
import dicka.repository.RepositoryKategori;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Transactional
@Validated
@Repository
public class ServiceImplKategori implements ServiceKategori{

    private static final Logger LOGGER =
            LoggerFactory.getLogger(ServiceImplKategori.class);

    private RepositoryKategori repositoryKategori;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public ServiceImplKategori(RepositoryKategori repositoryKategori){
        this.repositoryKategori = repositoryKategori;
    }

    @Override
    public void insertKategori(Kategori kategori) {
        LOGGER.debug("insert kategori");
        entityManager.persist(kategori);
    }

    @Override
    public Kategori updateKategori(Kategori kategori) {
        LOGGER.debug("update kategori");
        if(!entityManager.contains(kategori))
            kategori = entityManager.merge(kategori);
        return kategori;
    }

    @Override
    public void deleteKategori(Kategori kategori) {
        LOGGER.debug("delete kategori");
        repositoryKategori.delete(kategori);
    }

    @Override
    public Kategori findOneKategori(int idkategori) {
        LOGGER.debug("find one kategori");
        return repositoryKategori.findOne(idkategori);
    }

    @Override
    public List<Kategori> findAllKategori() {
        LOGGER.debug("list kategori");
        return repositoryKategori.findAll();
    }

    @Override
    public boolean ifExistIdkategori(Kategori kategori) {
        LOGGER.debug("kode kategori sudah ada");
        return findOneKategori(kategori.getIdkategori()) !=null;
    }

    @Override
    public boolean ifExistNamaKategori(String nama) {
        LOGGER.debug("nama kategori sudah ada");
        String hql = "from Kategori as cat where cat.nama = ?";
        int count = entityManager.createQuery(hql).setParameter(1, nama)
                .getResultList().size();
        return count > 0 ? true : false;
    }
}
