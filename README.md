# Desafio Cervejaria :beers:

O objetivo desse projeto é escolher qual a melhor cerveja para uma dada temperatura. :beer:


### Inserir e listar as cervejas

```console
curl --request POST --data 'estilo=Weissbier&tempL=-1&tempH=3' http://localhost:8080/api/add
curl --request POST --data 'estilo=Pilsens&tempL=-2&tempH=4' http://localhost:8080/api/add
curl --request POST --data 'estilo=Weizenbier&tempL=-4&tempH=6' http://localhost:8080/api/add
curl --request POST --data 'estilo=Red ale&tempL=-5&tempH=5' http://localhost:8080/api/add
curl --request POST --data 'estilo=India pale ale&tempL=-6&tempH=7' http://localhost:8080/api/add
curl --request POST --data 'estilo=IPA&tempL=-7&tempH=10' http://localhost:8080/api/add
curl --request POST --data 'estilo=Dunkel&tempL=-8&tempH=2' http://localhost:8080/api/add
curl --request POST --data 'estilo=Imperial Stouts&tempL=-10&tempH=13' http://localhost:8080/api/add
curl --request POST --data 'estilo=Brown ale&tempL=0&tempH=14' http://localhost:8080/api/add

curl http://localhost:8080/api/all
```

### Executar a aplicação

```console
./gradlew bootRun
```

## Credenciais do Spotify Web API

Para gerar as credenciais do Spotify Web API, acesse o site e siga o tutorial:

<https://developer.spotify.com/documentation/general/guides/authorization-guide/>

Faça uma cópia do arquivo:

> src/main/java/com/testecerveja/TesteCerveja/Spotify/Credenciais_template.java

com o nome (mantenha na mesma pasta):

> Credenciais.java

substitua os campos conforme dados obtidos pelo Spotify.
