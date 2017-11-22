package dicka.services;

import dicka.model.Kategori;

import java.util.List;

public interface ServiceKategori {


    void insertKategori(Kategori kategori);

    Kategori updateKategori(Kategori kategori);

    void deleteKategori(Kategori kategori);

    Kategori findOneKategori(int idkategori);

    List<Kategori> findAllKategori();

    boolean ifExistIdkategori(Kategori kategori);

    boolean ifExistNamaKategori(String nama);
}
