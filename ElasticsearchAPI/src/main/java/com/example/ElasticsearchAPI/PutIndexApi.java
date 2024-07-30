package com.example.ElasticsearchAPI;

import com.example.ElasticsearchItemDTO.Item;
import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class PutIndexApi {

    // RestHighLevelClient 초기화
    private final RestHighLevelClient client;

    public PutIndexApi() {
        // RestClient 초기화
        RestClient restClient = RestClient.builder(new HttpHost("localhost", 9200)).build();
        // RestHighLevelClient 초기화
        this.client = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200)));
    }

    @GetMapping(value = "/getDocument/")
    public ResponseEntity<String> getDocument(@RequestParam(name = "indexname") String indexname) {
        try {
            GetIndexRequest request = new GetIndexRequest(indexname);
            boolean exists = client.indices().exists(request, RequestOptions.DEFAULT);

            if (exists) {
                // 인덱스가 존재할 경우
                return new ResponseEntity<>("인덱스가 존재합니다.", HttpStatus.OK);
            } else {
                // 인덱스가 존재하지 않을 경우
                return new ResponseEntity<>("인덱스가 존재하지 않습니다.", HttpStatus.NOT_FOUND);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("인덱스 조회 중 오류가 발생했습니다: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/put_{indexname}/_doc/{id}")
    public ResponseEntity<String> putDocument(@PathVariable(name = "indexname") String indexname, @PathVariable(name = "id") String id, @RequestBody Item item) {
        try {
            // IndexRequest 생성
            IndexRequest request = new IndexRequest(indexname)
                .id(id)
                .source("title", item.getTitle(), "body", item.getBody());

            // 문서 추가 요청
            IndexResponse response = client.index(request, RequestOptions.DEFAULT);

            // 성공적으로 생성된 경우
            if (response.getResult() == IndexResponse.Result.CREATED) {
                return new ResponseEntity<>("문서가 정상적으로 생성되었습니다.", HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>("문서 생성 중 문제가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("문서 생성 중 오류가 발생했습니다: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
