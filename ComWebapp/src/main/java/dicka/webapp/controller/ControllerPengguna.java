package dicka.webapp.controller;

import dicka.dao.DAOPengguna;
import dicka.model.Pengguna;
import dicka.model.Role;
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
public class ControllerPengguna {


    private DAOPengguna daoPengguna;

    private static final Logger LOGGER =
            LoggerFactory.getLogger(ControllerPengguna.class);

    @Autowired
    public ControllerPengguna(DAOPengguna daoPengguna){
        this.daoPengguna = daoPengguna;
    }

    @GetMapping(value = "/pengguna")
    public ResponseEntity<List<Pengguna>>getListPengguna(){
        LOGGER.debug("access data pengguna");
        List<Pengguna> listpengguna = daoPengguna.findAllPengguna();
        if(listpengguna.isEmpty()){
            LOGGER.debug("data is empty");
            return new ResponseEntity<List<Pengguna>>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<List<Pengguna>>(listpengguna, HttpStatus.OK);
    }

    @GetMapping(value = "/pengguna/{idpengguna}")
    public ResponseEntity<Pengguna>getPenggunaById(@PathVariable String idpengguna){
        LOGGER.debug("access data pengguna by id");
        Pengguna pengguna = daoPengguna.findOnePenggunaById(Integer.parseInt(idpengguna));
        if(pengguna == null){
            LOGGER.debug("data is failed");
            return new ResponseEntity<Pengguna>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Pengguna>(pengguna, HttpStatus.ACCEPTED);
    }

    @PostMapping(value = "/insertPengguna")
    public ResponseEntity<Pengguna>insertPengguna(@RequestBody Pengguna pengguna,
                                                  UriComponentsBuilder builder){
        LOGGER.debug("try to access insert data");
        boolean validated = daoPengguna.insertPengguna(pengguna);
        if(validated == false){
            LOGGER.debug("data conflict");
            return new ResponseEntity<Pengguna>(HttpStatus.CONFLICT);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/insertPengguna/{idpengguna}")
                .buildAndExpand(pengguna.getIdpengguna()).toUri());
        return new ResponseEntity<Pengguna>(headers, HttpStatus.CREATED);
    }
}
