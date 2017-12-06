package dicka.services;

import dicka.model.Jenis;

import java.util.List;

public interface ServiceJenis {

    void insertJenis(Jenis jenis);

    Jenis updateJenis(Jenis jenis);

    void deleteJenis(Jenis jenis);

    Jenis findByJenisById(int idjenis);

    List<Jenis>findAllJenis();

    boolean ifExistJenis(String kodejenis);
}
