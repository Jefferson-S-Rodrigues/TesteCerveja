package com.testecerveja.TesteCerveja.SpotifyDAO;

import java.util.ArrayList;
import java.util.List;

public class Playlist {

    String name;

    List<Musica> tracks;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Musica> getTracks() {
        return tracks;
    }

    public void setTracks(List<Musica> tracks) {
        this.tracks = tracks;
    }

    public void addMusica(Musica musica) {
        if (tracks == null) tracks = new ArrayList<>();
        tracks.add(musica);
    }
}
