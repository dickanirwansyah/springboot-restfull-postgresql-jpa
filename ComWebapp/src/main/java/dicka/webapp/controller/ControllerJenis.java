package dicka.webapp.controller;

import dicka.dao.DAOJenis;
import dicka.model.Jenis;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class ControllerJenis {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(ControllerJenis.class);

    private DAOJenis daoJenis;

    @Autowired
    public ControllerJenis(DAOJenis daoJenis){
        this.daoJenis = daoJenis;
    }

    @GetMapping(value = "/jenis")
    public ResponseEntity<List<Jenis>> findAllJenis(){
       LOGGER.debug("menampilkan data");
       List<Jenis> listjenis = daoJenis.findAllJenis();
       if(listjenis.isEmpty()){
           return new ResponseEntity(HttpStatus.BAD_REQUEST);
       }
       return new ResponseEntity<List<Jenis>>(listjenis, HttpStatus.OK);
    }

    @GetMapping(value = "/jenis/{idjenis}")
    public ResponseEntity<Jenis>findOneJenis(@PathVariable String idjenis){
        LOGGER.debug("menampilkan data idjenis");
        Jenis jenis = daoJenis.findOneJenisById(Integer.parseInt(idjenis));
        if(jenis == null){
            return new ResponseEntity<Jenis>(HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<Jenis>(jenis, HttpStatus.OK);
        }
    }

    @PostMapping(value = "/deleteJenis/{idjenis}")
    public ResponseEntity<String> deleteJenis(@PathVariable String idjenis){
        LOGGER.debug("delete data jenis");
        Jenis jenis = daoJenis.findOneJenisById(Integer.parseInt(idjenis));
        if(jenis == null){
            LOGGER.debug("data kosong");
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
        daoJenis.deleteJenis(jenis);
        return new ResponseEntity<String>(HttpStatus.ACCEPTED);
    }

    @PostMapping(value = "/updateJenis/{idjenis}")
    public ResponseEntity<String>updateJenis(@PathVariable String idjenis,
                                             @RequestBody Jenis jenis,
                                             UriComponentsBuilder builder){
        LOGGER.debug("update data jenis");
        Jenis currentJenis = daoJenis.findOneJenisById(Integer.parseInt(idjenis));
        if(jenis == null){
            LOGGER.debug("data kosong");
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/updateJenis/{idjenis}")
                .buildAndExpand(jenis.getIdjenis()).toUri());

        currentJenis.setKodejenis(jenis.getKodejenis());
        currentJenis.setDeskripsi(jenis.getDeskripsi());
        daoJenis.updateJenis(currentJenis);

        return new ResponseEntity<String>(headers, HttpStatus.ACCEPTED);
    }

    @PostMapping(value = "/insertJenis")
    public ResponseEntity<String> insertJenis(@RequestBody final Jenis jenis,
                                              UriComponentsBuilder builder){
        boolean validated = daoJenis.insertJenis(jenis);
        if(validated == false){
            LOGGER.debug("data konflik");
            return new ResponseEntity<String>(HttpStatus.CONFLICT);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/insertJenis/{idjenis}")
                .buildAndExpand(jenis.getIdjenis()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }
}
