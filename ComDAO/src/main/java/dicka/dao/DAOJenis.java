package dicka.dao;

import dicka.model.Jenis;

import java.util.List;

public interface DAOJenis {

    boolean insertJenis(Jenis jenis);

    void updateJenis(Jenis jenis);

    void deleteJenis(Jenis jenis);

    Jenis findOneJenisById(int idjenis);

    List<Jenis> findAllJenis();
}
