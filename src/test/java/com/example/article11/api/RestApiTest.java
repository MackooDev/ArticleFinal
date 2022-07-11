package com.example.article11.api;

import com.example.article11.entity.ArticleEntity;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.nio.charset.StandardCharsets;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
public class RestApiTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @DisplayName("GET /allArticleByLocalDateSortDesc -> HTTP200")
    void whenGetArticleSortDesc_ThenReturnHTTP200() throws Exception {
        var endpointURL = "/allArticleByLocalDateSortDesc";


            MvcResult mvcResult = (MvcResult) mockMvc
                     .perform(
                             MockMvcRequestBuilders.get(endpointURL)
                     ).andExpect(status().isOk())
                     .andReturn();

            String ArticleASJSON = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
            List<ArticleEntity> articleEntityList = objectMapper.readValue(ArticleASJSON, new TypeReference<>() {});
        assertAll("zwraca wszystkie artykuły spełniające filtr",
                ()-> assertEquals(3, articleEntityList.size())

        );
    }

    @Test
    @DisplayName("POST /ArticleAdd -> HTTP200")
    void whenPostArticleAdd_ThenReturnHTTP200(){
        var endpointURL = "/add";
        try {
            mockMvc
                    .perform(
                            MockMvcRequestBuilders.post(endpointURL)
                    ).andExpect(status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
