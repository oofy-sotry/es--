package com.example.ElasticsearchItemDTO;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Getter
@Setter
@Document(indexName = "items")
public class Item {
    @Id
    private String id;
    private String title;
    private String body;
}
