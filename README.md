# Desafio Cervejaria :beers:

O objetivo desse projeto é escolher uma _Playlist_ :notes: no _Spotify_ :sound: de acordo com a escolha de uma cerveja para uma dada temperatura. :beer:

## Comandos

Método | URL | Ação
-------|-----|-------
POST | /api/cerveja | Cria uma nova cerveja
GET | /api/cerveja | Lista todas as cervejas
GET | /api/cerveja/`:id` | Lista uma cerveja pelo `:id`
GET | /api/cerveja/estilo?estilo=`:estilo` | Lista uma cerveja pelo `:estilo`
PUT | /api/cerveja/`:id` | Edita uma cerveja pelo `:id`
DELETE | /api/cerveja/`:id` | Deleta uma cerveja pelo `:id`
__GET__ | __/api/playlist?temp=`:temp`__ | __Busca uma _Playlist_ no _Spotify_ a partir de uma temperatura `:temp` de uma cerveja__

### Exemplos (comando Curl)

#### Inserindo as cervejas
```console
curl --request POST --data 'estilo=Weissbier&tempL=-1&tempH=3' http://localhost:8080/api/cerveja
curl --request POST --data 'estilo=Pilsens&tempL=-2&tempH=4' http://localhost:8080/api/cerveja
curl --request POST --data 'estilo=Weizenbier&tempL=-4&tempH=6' http://localhost:8080/api/cerveja
curl --request POST --data 'estilo=Red ale&tempL=-5&tempH=5' http://localhost:8080/api/cerveja
curl --request POST --data 'estilo=India pale ale&tempL=-6&tempH=7' http://localhost:8080/api/cerveja
curl --request POST --data 'estilo=IPA&tempL=-7&tempH=10' http://localhost:8080/api/cerveja
curl --request POST --data 'estilo=Dunkel&tempL=-8&tempH=2' http://localhost:8080/api/cerveja
curl --request POST --data 'estilo=Imperial Stouts&tempL=-10&tempH=13' http://localhost:8080/api/cerveja
curl --request POST --data 'estilo=Brown ale&tempL=0&tempH=14' http://localhost:8080/api/cerveja
```
#### Listando todas as cervejas
```console
curl http://localhost:8080/api/cerveja
```

## Executar a aplicação

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
