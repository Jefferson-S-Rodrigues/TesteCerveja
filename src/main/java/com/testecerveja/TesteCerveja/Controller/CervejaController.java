package com.testecerveja.TesteCerveja.Controller;

import com.testecerveja.TesteCerveja.Entity.Cerveja;
import com.testecerveja.TesteCerveja.Repository.CervejaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(path = "/api")
public class CervejaController {

    @Autowired
    private CervejaRepository cervejaRepository;

    @PostMapping(path = "/cerveja")
    public @ResponseBody
    String addCerveja(
            @RequestParam String estilo,
            @RequestParam int tempL,
            @RequestParam int tempH) {

        Cerveja _cerveja = new Cerveja();
        _cerveja.setEstilo(estilo);
        if (tempH < tempL) {
            int _temp = tempH;
            tempH = tempL;
            tempL = _temp;
        }
        _cerveja.setTempH(tempH);
        _cerveja.setTempL(tempL);
        cervejaRepository.save(_cerveja);
        return "Saved";
    }

    @GetMapping(path = "/cerveja")
    public @ResponseBody
    Iterable<Cerveja> getAllCervejas() {
        return cervejaRepository.findAll();
    }

    @GetMapping(path = "/cerveja/{id}")
    public @ResponseBody
    ResponseEntity getCervejaById(@PathVariable int id) {
        return cervejaRepository.findById(id)
                .map(cerv -> ResponseEntity.ok().body(cerv))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(path = "/cerveja/estilo")
    public @ResponseBody
    Iterable<Cerveja> getCervejaByEstilo(@RequestParam String estilo) {
        return cervejaRepository.findCervejaByEstilo(estilo);
    }

    @GetMapping(path = "/cerveja/temp")
    public @ResponseBody
    Iterable<Cerveja> getCervejaByTemp(@RequestParam int temp) {
        return cervejaRepository.findCervejaByTemp(temp);
    }

    @PutMapping("/cerveja/{id}")
    public ResponseEntity<Cerveja> updateCerveja(
            @PathVariable("id") int id,
            @RequestParam(required = false) String estilo,
            @RequestParam(required = false) Integer tempL,
            @RequestParam(required = false) Integer tempH) {
        Optional<Cerveja> cervejaData = cervejaRepository.findById(id);

        if (cervejaData.isPresent()) {
            Cerveja _cerveja = cervejaData.get();
            if (estilo != null) _cerveja.setEstilo(estilo);
            if (tempL != null) _cerveja.setTempL(tempL);
            if (tempH != null) _cerveja.setTempH(tempH);
            return new ResponseEntity<>(cervejaRepository.save(_cerveja), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/cerveja/{id}")
    public ResponseEntity<HttpStatus> deleteCerveja(@PathVariable("id") int id) {
        try {
            cervejaRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
