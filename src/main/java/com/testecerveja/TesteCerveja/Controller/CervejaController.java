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
            @RequestParam int tempH,
            @RequestParam int tempL) {

        Cerveja n = new Cerveja();
        n.setEstilo(estilo);
        n.setTempH(tempH);
        n.setTempL(tempL);
        cervejaRepository.save(n);
        return "Saved";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Cerveja> getAllUsers() {
        // This returns a JSON or XML with the users
        return cervejaRepository.findAll();
    }


}
