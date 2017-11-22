package dicka.dao;

import dicka.model.Kategori;
import dicka.services.ServiceKategori;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DAOImplKategori implements DAOKategori{

    private ServiceKategori serviceKategori;

    @Autowired
    public DAOImplKategori(ServiceKategori serviceKategori){
        this.serviceKategori = serviceKategori;
    }

    @Override
    public boolean insertKategori(Kategori kategori) {
        if (serviceKategori.ifExistNamaKategori(kategori.getNama())) {
            return false;
        }else{
            serviceKategori.insertKategori(kategori);
            return true;
        }
    }

    @Override
    public void updateKategori(Kategori kategori) {
        serviceKategori.updateKategori(kategori);
    }

    @Override
    public void deleteKategori(Kategori kategori) {
        serviceKategori.deleteKategori(kategori);
    }

    @Override
    public List<Kategori> findAllKategoris() {
        return serviceKategori.findAllKategori();
    }

    @Override
    public Kategori findOneKategori(int idkategori) {
        return serviceKategori.findOneKategori(idkategori);
    }
}
