package com.example.article11.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "ARTICLE_TABLE")
public class ArticleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LP")
    private Long id;
    @Column(name = "PUBLIKACJA", nullable = false)
    private String contents;
    @Column(name = "DATA_I_GODZINA_PUBLIKACJI", nullable = false)
    private LocalDateTime localDateTime;
    @Column(name = "NAZWA_WYDAWCY_CZASOPISMA", nullable = false)
    private String name;
    @Column(name = "IMIĘ_NAZWISKO_AUTORA_PUBLIKACJI", nullable = false)
    private String author;
    @Column(name = "DATA_DODANIA_PUBLIKACJI", nullable = false)
    private Timestamp timestamp;

    public ArticleEntity(String contents, LocalDateTime localDateTime, String name, String author, Timestamp timestamp) {

        this.contents = contents;
        this.localDateTime = localDateTime;
        this.name = name;
        this.author = author;
        this.timestamp = timestamp;
    }

    public ArticleEntity() {
    }
}

