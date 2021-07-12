package com.testecerveja.TesteCerveja.Controller;

import com.testecerveja.TesteCerveja.Entity.Cerveja;
import com.testecerveja.TesteCerveja.Repository.CervejaRepository;
import com.testecerveja.TesteCerveja.SpotifyDAO.Musica;
import com.testecerveja.TesteCerveja.SpotifyDAO.Playlist;
import com.testecerveja.TesteCerveja.SpotifyDAO.PlaylistDAO;
import com.testecerveja.TesteCerveja.Spotify.SpotifyController;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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

    @GetMapping(path = "/playlist")
    public @ResponseBody
    ResponseEntity getPlaylist(@RequestParam int temp) {

        PlaylistDAO playlist = new PlaylistDAO();
        SpotifyController sc = new SpotifyController();
        try {
            JSONObject dados = SpotifyController.getPlaylist(temp, cervejaRepository);

            playlist.setBeerStyle(dados.getString("beerStyle"));
            Playlist _pl = new Playlist();
            JSONObject jpl = new JSONObject(dados.get("playlist").toString());
            _pl.setName(jpl.getString("name"));
            JSONArray musicas = jpl.getJSONObject("tracks").getJSONArray("items");

            musicas.toList().forEach(musica -> {
                Musica m = new Musica();


                if (((HashMap) ((HashMap) musica).get("track")).containsKey("name")) {
                    m.setName(((HashMap) ((HashMap) musica).get("track")).get("name").toString());
                }
                if (((HashMap) ((HashMap) musica).get("track")).containsKey("artist")) {
                    m.setArtist(((HashMap) ((HashMap) musica).get("track")).get("artist").toString());
                }
                if (((HashMap) ((HashMap) musica).get("track")).containsKey("link")) {
                    m.setLink(((HashMap) ((HashMap) musica).get("track")).get("link").toString());
                }

                _pl.addMusica(m);
            });
            playlist.setPlaylist(_pl);

            return new ResponseEntity<PlaylistDAO>(playlist, HttpStatus.OK);
        } catch (JSONException e) {

            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

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
