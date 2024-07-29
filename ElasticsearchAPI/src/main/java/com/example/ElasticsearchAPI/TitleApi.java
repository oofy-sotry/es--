package com.example.ElasticsearchAPI;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.ElasticsearchItemDTO.Item;
import org.json.JSONObject;
import java.io.IOException;

@RestController
public class TitleApi {
    RestClient restClient = RestClient.builder(new HttpHost("localhost", 9200)).build();
    ElasticsearchTransport transport = new RestClientTransport(restClient, new JacksonJsonpMapper());
    ElasticsearchClient client = new ElasticsearchClient(transport);

    @GetMapping(value = "/title_item")
    public @ResponseBody ResponseEntity<String> search(@RequestParam(name = "query") String query) throws IOException, JSONException {
        String title = "";
        String body = "";

        SearchResponse<Item> search = client.search(s -> s
                        .index("items")
                        .query(q -> q
                                .match(t -> t
                                        .field("title")
                                        .query(query)
                                )),
                Item.class);

        for (Hit<Item> hit: search.hits().hits()) {
            title = hit.source().getTitle();
            body = hit.source().getBody();
        }

        JSONObject msg = new JSONObject();
        msg.put("title", title);
        msg.put("body", body);

        return new ResponseEntity<>(msg.toString(), HttpStatus.OK);
    }
}
