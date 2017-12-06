package dicka.services;

import dicka.model.Barang;

import java.util.List;

public interface ServiceBarang {

    void insertBarang(Barang barang);

    Barang updateBarang(Barang barang);

    void deleteBarang(Barang barang);

    Barang findOneBarangById(int idbarang);

    Barang findOneBarangByNama(String nama);

    List<Barang>findAllBarangs();

    boolean ifBarangIsExist(String kodebarang);
}
