package dicka.dao;

import dicka.model.Barang;
import dicka.services.ServiceBarang;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DAOImplBarang implements DAOBarang {

    private ServiceBarang serviceBarang;

    @Autowired
    public DAOImplBarang(ServiceBarang serviceBarang){
        this.serviceBarang = serviceBarang;
    }

    @Override
    public boolean insertBarang(Barang barang) {
        if(serviceBarang.ifBarangIsExist(barang.getKodebarang())){
            System.out.println("data gagal diinsert kode barang sudah ada");
            return false;
        }else{
            serviceBarang.insertBarang(barang);
            return true;
        }
    }

    @Override
    public void updateBarang(Barang barang) {
        serviceBarang.updateBarang(barang);
    }

    @Override
    public void deleteBarang(Barang barang) {
        serviceBarang.deleteBarang(barang);
    }

    @Override
    public Barang findOneByIdbarang(int idbarang) {
        return serviceBarang.findOneBarangById(idbarang);
    }

    @Override
    public Barang findOneByNama(String nama) {
        return serviceBarang.findOneBarangByNama(nama);
    }

    @Override
    public List<Barang> findAllBarangs() {
        return serviceBarang.findAllBarangs();
    }
}
