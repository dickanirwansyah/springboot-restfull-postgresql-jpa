package dicka.dao;

import dicka.model.Pengguna;

import java.util.List;

public interface DAOPengguna {

    boolean insertPengguna(Pengguna pengguna);

    void updatePengguna(Pengguna pengguna);

    void deletePengguna(Pengguna pengguna);

    Pengguna findOnePenggunaById(int idpengguna);

    List<Pengguna> findAllPengguna();

    Pengguna findByUsername(String username);
}
