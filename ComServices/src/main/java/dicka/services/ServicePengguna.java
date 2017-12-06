package dicka.services;

import dicka.model.Pengguna;

import java.util.List;

public interface ServicePengguna {

    void insertPengguna(Pengguna pengguna);

    Pengguna updatePengguna(Pengguna pengguna);

    void deletePengguna(Pengguna pengguna);

    List<Pengguna> findAllPengguna();

    Pengguna findOnePenggunaById(int idpengguna);

    boolean ifUsernamePenggunaIsExist(String username);

    Pengguna CariBerdasarkanUsername(String usernam);
}
