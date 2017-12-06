package dicka.dao;

import dicka.model.Jenis;
import dicka.services.ServiceJenis;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DAOImplJenis implements DAOJenis{

    private ServiceJenis serviceJenis;


    private static final Logger LOGGER =
            LoggerFactory.getLogger(DAOImplJenis.class);

    @Autowired
    public DAOImplJenis(ServiceJenis serviceJenis){
        this.serviceJenis = serviceJenis;
    }

    @Override
    public boolean insertJenis(Jenis jenis) {
       if(serviceJenis.ifExistJenis(jenis.getKodejenis())){
           return false;
       }else{
           serviceJenis.insertJenis(jenis);
           return true;
       }
    }

    @Override
    public void updateJenis(Jenis jenis) {
        serviceJenis.updateJenis(jenis);
    }

    @Override
    public void deleteJenis(Jenis jenis) {
        serviceJenis.deleteJenis(jenis);
    }

    @Override
    public Jenis findOneJenisById(int idjenis) {
        return serviceJenis.findByJenisById(idjenis);
    }

    @Override
    public List<Jenis> findAllJenis() {
        return serviceJenis.findAllJenis();
    }
}
