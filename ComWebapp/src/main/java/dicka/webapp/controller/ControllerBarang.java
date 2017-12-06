package dicka.webapp.controller;

import dicka.dao.DAOBarang;
import dicka.model.Barang;
import dicka.model.BarangMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api")
public class ControllerBarang {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(ControllerBarang.class);

    private DAOBarang daoBarang;

    @Autowired
    public ControllerBarang(DAOBarang daoBarang){
        this.daoBarang = daoBarang;
    }

    @PostMapping(value = "/barangs")
    public List<Barang> getListBarangs(){
        LOGGER.debug("access list barang");
        if(getBarangs() == null){
            LOGGER.debug("data kosong");
        }
        return getBarangs();
    }

    @PostMapping(value = "/insertBarang")
    public ResponseEntity<String> insertBarang(@RequestBody final Barang barang,
                                               UriComponentsBuilder builder){
        boolean validated = daoBarang.insertBarang(barang);
        if(validated == false){
            LOGGER.debug("data konflik");
            return new ResponseEntity<String>(HttpStatus.CONFLICT);
        }
        LOGGER.debug("data insert");
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/insertBarang/{idbarang}")
                .buildAndExpand(barang.getIdbarang()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    @PostMapping(value = "/updateBarang/{idbarang}")
    public ResponseEntity<String> updateBarang(@PathVariable String idbarang,
                                               @RequestBody Barang barang){
        LOGGER.debug("update barang");
        Barang data_sekarang = daoBarang.findOneByIdbarang(Integer.parseInt(idbarang));
        if(data_sekarang == null){
            LOGGER.debug("data kosong");
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }

        data_sekarang.setJenis(barang.getJenis());
        data_sekarang.setKodebarang(barang.getKodebarang());
        data_sekarang.setNama(barang.getNama());
        data_sekarang.setPrice(barang.getPrice());
        data_sekarang.setQuantity(barang.getQuantity());
        daoBarang.updateBarang(data_sekarang);

        return new ResponseEntity<String>(HttpStatus.CREATED);
    }

    @PostMapping(value = "/barangByNama/{nama}")
    public ResponseEntity<Barang> findBarangByName(@PathVariable String nama){
        LOGGER.debug("access nama");
        Barang databarang = daoBarang.findOneByNama(nama);
        if(databarang == null){
            return new ResponseEntity<Barang>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Barang>(databarang, HttpStatus.ACCEPTED);
    }

    @PostMapping(value = "/deleteBarang/{idbarang}")
    public ResponseEntity<String> deleteBarang(@PathVariable String idbarang){

        LOGGER.debug("delete barang");
        Barang barang = daoBarang.findOneByIdbarang(Integer.parseInt(idbarang));
        if(barang == null){
            LOGGER.debug("data gagal dihapus");
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
        daoBarang.deleteBarang(barang);
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    //methode list
    private List<Barang> getBarangs(){
        return daoBarang.findAllBarangs();
    }

}
