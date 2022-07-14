package com.example.article11.api;

import com.example.article11.entity.ArticleEntity;
import com.example.article11.exception.ErrorCode;
import com.example.article11.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
public class RestApi {

    private final ArticleRepository articleRepository;

    public RestApi(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @GetMapping("/articleById/{id}")
    public Optional<ArticleEntity> getArticleById(@PathVariable Long id) throws Exception {
        var valueExpected = articleRepository.findById(id);
        if(valueExpected.isPresent()) {
            log.info("Pobieram artykuł o id =" + " " + id);
            return valueExpected;
        }else{
            log.info("W bazie danych nie znaleziono artykułu o id=" + id);
            throw new Exception(String.valueOf(ErrorCode.NO_SUCH_ARTICLE_FOUND));
        }
    }

    @GetMapping("/allArticleByLocalDateSortDesc")
    public List<ArticleEntity> getAllArticleSortByLocalDateDesc(){
        log.info("Pobieram wszystkie artykuły posortowane po dacie malejąco");
        return articleRepository.findByOrderByLocalDateTimeDesc();
    }

    @GetMapping("/allArticleKeyWord/{contents}")
    public List<ArticleEntity> getAllArticleLikeAFewLetters(@PathVariable String contents) throws Exception{
        if(contents.isEmpty()){
            throw new Exception(String.valueOf(ErrorCode.NO_SUCH_ARTICLE_FOUND));
        }else {
            log.info("Pobieram wszystkie artykuły które spełniają określony filtr, mają w treści podany ciąg liter");
            return articleRepository.findByContentsContaining(contents);
        }
    }

    @PostMapping("/add")
    public void saveArticle(@RequestBody ArticleEntity articleEntity){
        log.info("Zapisuje nowy rekord do bazy danych");
        articleRepository.save(articleEntity);
    }

    @PutMapping("/update/{id}")
    public ArticleEntity updateArticle(@RequestBody ArticleEntity articleEntity){
        log.info("Aktualizuje rekord o zadanym id");
        return articleRepository.save(articleEntity);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteArticle(@PathVariable Long id) {
        log.info("Usuwam rekord o podanym id =" + id);
        articleRepository.deleteById(id);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void fillDB() {
        ArticleEntity article = new ArticleEntity("Wypadek na trasie s8",  LocalDateTime.now(),"Onet.pl", "Maciej Okliński", Timestamp.valueOf(LocalDateTime.now()));
        save(article);
        ArticleEntity article1 = new ArticleEntity("Najtańsze domeny internetowe pl",  LocalDateTime.now(),"Domena.pl", "Krzysztof Kowalski", Timestamp.valueOf(LocalDateTime.now()));
        save(article1);
        ArticleEntity article2 = new ArticleEntity("Dlaczego IT, programowanie jest tak oblegane",  LocalDateTime.now(),"geegsforgeegs.org", "Bartosz Wróblewski", Timestamp.valueOf(LocalDateTime.now()));
        save(article2);
    }

    private ArticleEntity save(ArticleEntity articleEntity){
        return articleRepository.save(articleEntity);
    }
}
