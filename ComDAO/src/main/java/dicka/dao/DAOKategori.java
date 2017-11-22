package dicka.dao;

import dicka.model.Kategori;

import java.util.List;

public interface DAOKategori {

    boolean insertKategori(Kategori kategori);

    void updateKategori(Kategori kategori);

    void deleteKategori(Kategori kategori);

    List<Kategori> findAllKategoris();

    Kategori findOneKategori(int idkategori);
}
