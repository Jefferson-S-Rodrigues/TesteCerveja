package com.testecerveja.TesteCerveja.Controller;

import com.testecerveja.TesteCerveja.Entity.Cerveja;
import com.testecerveja.TesteCerveja.Repository.CervejaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/api")
public class CervejaController {

    @Autowired
    private CervejaRepository cervejaRepository;

    @PostMapping(path="/add")
    public @ResponseBody String addCerveja (
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

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Cerveja> getAllCervejas() {
        return cervejaRepository.findAll();
    }

}
