package com.testecerveja.TesteCerveja.Spotify;

public enum Credenciais_template {
    CLIENT_ID {
        public String toString() {
            return "<Spotify Client ID>";
        }},
    SECRET {
        public String toString() {
            return "<Spotify Client Secret>";
        }
    },
    REFRESH_TOKEN {
        public String toString() {
            return "<Token gerado com o código de autenticação para atualização do token>";
        }
    }
}
