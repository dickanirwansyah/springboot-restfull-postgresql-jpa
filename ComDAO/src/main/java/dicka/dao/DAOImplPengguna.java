package dicka.dao;

import dicka.model.Pengguna;
import dicka.services.ServicePengguna;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DAOImplPengguna implements DAOPengguna{

    private ServicePengguna servicePengguna;


    @Autowired
    public DAOImplPengguna(ServicePengguna servicePengguna){
        this.servicePengguna = servicePengguna;
    }


    @Override
    public boolean insertPengguna(Pengguna pengguna) {
        if(servicePengguna.ifUsernamePenggunaIsExist(pengguna.getUsername())){
            return false;
        }else{
            servicePengguna.insertPengguna(pengguna);
            return true;
        }
    }

    @Override
    public void updatePengguna(Pengguna pengguna) {

    }

    @Override
    public void deletePengguna(Pengguna pengguna) {

    }

    @Override
    public Pengguna findOnePenggunaById(int idpengguna) {
        return servicePengguna.findOnePenggunaById(idpengguna);
    }

    @Override
    public List<Pengguna> findAllPengguna() {
        return servicePengguna.findAllPengguna();
    }

    @Override
    public Pengguna findByUsername(String username) {
        return servicePengguna.CariBerdasarkanUsername(username);
    }
}
