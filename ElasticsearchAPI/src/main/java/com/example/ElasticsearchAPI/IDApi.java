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
public class IDApi {
    RestClient restClient = RestClient.builder(new HttpHost("localhost", 9200)).build();
    ElasticsearchTransport transport = new RestClientTransport(restClient, new JacksonJsonpMapper());
    ElasticsearchClient client = new ElasticsearchClient(transport);

    @GetMapping(value = "/id_item")
    public @ResponseBody ResponseEntity<String> search(@RequestParam(name = "id") String id) throws IOException, JSONException {
        String title = "";
        String body = "";

// GET이 아닌 POST를 사용, elasticsearch나 Kibaba에서는 기본적으로 GET으로 데이터 검색 POST는 수정
// 하지만 java 라이브러리에서 elasticsearch는 기본적으로 검색 요청을 HTTP POST 메서드 사용
// 만약 HTTP GET 요청을 하려면 RestClient를 사용하여 직접 코딩 해야함
        
        SearchResponse<Item> search = client.search(s -> s
                        .index("items")
                        .query(q -> q
                                .term(t -> t
                                        .field("_id")
                                        .value(v -> v.stringValue(id))
                                )),
                Item.class);

        for (Hit<Item> hit: search.hits().hits()) {
            title = hit.source().getTitle();
            body = hit.source().getBody();
        }

// GET을 사용하는 방법
 /*
        Request request = new Request("GET", "/items/_search");
        String jsonString = "{ \"query\": { \"term\": { \"_id\": \"" + id + "\" } } }";
        request.addParameter("source", jsonString);
        request.addParameter("source_content_type", "application/json");

        Response response = restClient.performRequest(request);

        JSONObject responseObject = new JSONObject(response.getEntity().getContent().readAllBytes());
        JSONObject hitsObject = responseObject.getJSONObject("hits");
        if (hitsObject.getJSONArray("hits").length() > 0) {
            JSONObject hit = hitsObject.getJSONArray("hits").getJSONObject(0).getJSONObject("_source");
            title = hit.getString("title");
            body = hit.getString("body");
        }
*/


        JSONObject msg = new JSONObject();
        msg.put("title", title);
        msg.put("body", body);

        return new ResponseEntity<>(msg.toString(), HttpStatus.OK);
    }
}

