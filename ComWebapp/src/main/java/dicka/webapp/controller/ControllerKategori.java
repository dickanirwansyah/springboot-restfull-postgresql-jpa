package dicka.webapp.controller;

import dicka.dao.DAOKategori;
import dicka.model.Kategori;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
@Service
public class ControllerKategori {

    private DAOKategori daoKategori;

    private static final Logger
            LOGGER = LoggerFactory.getLogger(ControllerKategori.class);

    @Autowired
    public ControllerKategori(DAOKategori daoKategori){
        this.daoKategori = daoKategori;
    }

    @GetMapping(value = "/kategoris")
    public ResponseEntity<List<Kategori>>findAllKategoris(){
        LOGGER.debug("access list kategori");
        List<Kategori> listkategoris = daoKategori.findAllKategoris();
        if(listkategoris.isEmpty()){
            LOGGER.debug("data kosong");
            return new ResponseEntity<List<Kategori>>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<List<Kategori>>(listkategoris, HttpStatus.OK);
    }

    @PostMapping(value = "/insertKategori")
    public ResponseEntity<Kategori>insertKategori(@RequestBody Kategori kategori,
                                                  UriComponentsBuilder builder){
        LOGGER.debug("access insert kategori");
        boolean validated = daoKategori.insertKategori(kategori);
        if(validated == false){
            LOGGER.debug("nama kategori sudah ada");
            return new ResponseEntity<Kategori>(HttpStatus.CONFLICT);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/insertKategori/{idkategori}")
                .buildAndExpand(kategori.getIdkategori()).toUri());
        return new ResponseEntity<Kategori>(headers, HttpStatus.CREATED);
    }

    @PostMapping(value = "/updateKategori/{idkategori}")
    public ResponseEntity<Kategori>updateKategori(@PathVariable String idkategori,
                                                  @RequestBody Kategori kategori){

        LOGGER.debug("access update kategori");
        Kategori datakategori = daoKategori.findOneKategori(Integer.parseInt(idkategori));
        if(datakategori == null){
            LOGGER.debug("data not found !");
            return new ResponseEntity<Kategori>(HttpStatus.BAD_REQUEST);
        }

        datakategori.setDeskripsi(kategori.getDeskripsi());
        datakategori.setNama(kategori.getNama());
        daoKategori.updateKategori(datakategori);

        return new ResponseEntity<Kategori>(datakategori, HttpStatus.CREATED);
    }

    @GetMapping(value = "/kategoris/{idkategori}")
    public ResponseEntity<Kategori>findByIdkategori(@PathVariable String idkategori, UriComponentsBuilder builder){
        LOGGER.debug("access find by idkategori");
        Kategori kategori = daoKategori.findOneKategori(Integer.parseInt(idkategori));
        if(kategori == null){
            LOGGER.debug("data kosong");
            return new ResponseEntity<Kategori>(HttpStatus.BAD_REQUEST);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/kategoris/{idkategori}")
                .buildAndExpand(kategori.getIdkategori()).toUri());
        return new ResponseEntity<Kategori>(kategori, headers, HttpStatus.OK);
    }
}
