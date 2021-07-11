package com.testecerveja.TesteCerveja;

import com.testecerveja.TesteCerveja.Spotify.AcessoSpotify;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.net.HttpURLConnection;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class TesteCervejaApplicationTests {


	@Test
	void RecuperarTokenTeste() {

		assertDoesNotThrow(() -> AcessoSpotify.setToken());
	}

	@Test
	void RecoperarExemploTeste() {
		String cerveja = "IPA";
		int resposta = 400;
		String resultado = null;

		try {
			resultado = AcessoSpotify.getPlaylists(cerveja);
			if (resultado != null && !resultado.isEmpty()) {
				resposta = 200;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		assertEquals(HttpURLConnection.HTTP_OK, resposta);
	}

}
