package dicka.dao;

import dicka.model.Barang;

import java.util.List;

public interface DAOBarang {

    boolean insertBarang(Barang barang);

    void updateBarang(Barang barang);

    void deleteBarang(Barang barang);

    Barang findOneByIdbarang(int idbarang);

    Barang findOneByNama(String nama);

    List<Barang> findAllBarangs();
}
