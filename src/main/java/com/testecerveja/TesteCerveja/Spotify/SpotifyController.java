package com.testecerveja.TesteCerveja.Spotify;

import com.testecerveja.TesteCerveja.Entity.Cerveja;
import com.testecerveja.TesteCerveja.Repository.CervejaRepository;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

public final class SpotifyController {

    public static JSONObject getPlaylist(int temp, CervejaRepository cervejaRepository) throws Exception {

        List<Cerveja> _cervejas = cervejaRepository.findCervejaByTemp(temp);

        if (!_cervejas.isEmpty()) {
            Cerveja _cerveja = _cervejas.get(0);
            String _playlist = "";

            try {
                _playlist = AcessoSpotify.getPlaylists(_cerveja.getEstilo());
                if (_playlist.isEmpty()) {
                    _playlist = AcessoSpotify.getPlaylists(_cerveja.getEstilo());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (!_playlist.isEmpty()) {
                JSONObject jobj = new JSONObject();
                jobj.put("beerStyle", _cerveja.getEstilo());
                jobj.put("playlist", _playlist);

                return jobj;
            }
        }

        throw new Exception("Playlist não encontrada");
    }
}
