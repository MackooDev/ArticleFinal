# ArticleFinal

REST API for article press.

Technology: Java 11, SpringBoot, Maven, Database H2.

Endpoints:

method HTTPL GET
*get all article sort by local date descending: localhost:8080/allArticleByLocalDateSortDesc

method HTTP: GET
*get article by id: localhost:8080/articleById/2

method HTTP: GET
*get all article like a few letters: localhost:8080/allArticleKeyWord/a

method HTTTP: POST
*save article: localhost:8080/add ->     {

        "id": 4,
        "contents": "Najlepsze oferty pracy IT",
        "localDateTime": "2022-07-11T10:19:55.426915",
        "name": "NoFluffJobs",
        "author": "Jan Kowalski",
        "timestamp": "2022-07-11T10:19:55.426+00:00"
    }
    
method HTTP: PUT
*update article: localhost:8080/update/4 ->{

        "id": 4,
        "contents": "Najlepsze oferty pracy IT",
        "localDateTime": "2022-07-11T10:19:55.426915",
        "name": "NoFluffJobs",
        "author": "Gerard Kowalski",
        "timestamp": "2022-07-11T10:19:55.426+00:00"
    }

method HTTP: DELETE
*delete article: localhost:8080/delete/4
