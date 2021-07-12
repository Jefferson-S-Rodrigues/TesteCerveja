package com.testecerveja.TesteCerveja.Spotify;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.json.JSONObject;

public final class AcessoSpotify {

    private static String token;

    public static String getPlaylists(String cerveja) throws IOException {

        String _url = getPlaylistURL(cerveja);

        if (!_url.isEmpty()) {
            String playlist = getPlaylist(_url);
            return playlist;
        }

        return "";
    }

    private static String getPlaylistURL(String tipo_cerveja) throws IOException {

        String _url = "https://api.spotify.com/v1/search?q=" + tipo_cerveja + "&limit=1&type=playlist";
        URL url = new URL(_url);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Accept", "application/json");
        con.setRequestProperty("Content-Type", "application/json");
        if (AcessoSpotify.token == null) setToken();
        con.setRequestProperty("Authorization", "Bearer ".concat(AcessoSpotify.token));

        int responseCode = con.getResponseCode();
        JSONObject jobj = null;
        String playlist_url = "";

        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            jobj = new JSONObject(response.toString());
            playlist_url = jobj.getJSONObject("playlists").getJSONArray("items").getJSONObject(0).get("href").toString();
        } else {
            setToken();
        }
        return playlist_url;
    }

    private static String getPlaylist(String _url) throws IOException {

        _url += "?fields=name,tracks.items(track(name,artist,link))";
        URL url = new URL(_url);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Accept", "application/json");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Authorization", "Bearer ".concat(AcessoSpotify.token));

        int responseCode = con.getResponseCode();
        String json_resposta = "";

        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String rl;
            StringBuffer resposta = new StringBuffer();

            while ((rl = br.readLine()) != null) {
                resposta.append(rl);
            }
            br.close();

            json_resposta = resposta.toString();
        }
        return json_resposta;
    }

    private static String getBASE64() {

        String id_secret = String.join(":", Credenciais.CLIENT_ID.toString(), Credenciais.SECRET.toString());
        return Base64.getEncoder().encodeToString(id_secret.getBytes());
    }

    public static void setToken() throws IOException {
        String _url = "https://accounts.spotify.com/api/token";
        URL url = new URL(_url);

        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        con = (HttpURLConnection) url.openConnection();
        con.setDoOutput(true);
        con.setDoInput(true);

        con.setRequestMethod("POST");
        con.setRequestProperty("Authorization", String.format("Basic %s", getBASE64()));
        con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

        String body = "grant_type=client_credentials";

        byte[] out = body.getBytes(StandardCharsets.UTF_8);
        int length = out.length;
        con.setFixedLengthStreamingMode(length);

        con.connect();

        try (OutputStream os = con.getOutputStream()) {
            os.write(out);
        }

        InputStream result = con.getInputStream();
        String s = new String(result.readAllBytes());
        JSONObject obj = new JSONObject(s);
        AcessoSpotify.token = obj.get("access_token").toString();

        if (AcessoSpotify.token == null || AcessoSpotify.token.isEmpty()) {
            throw new IOException(String.format("Erro %d: Problemas com as credenciais de acesso à API do Spotify", con.getResponseCode()));
        }
    }
}

