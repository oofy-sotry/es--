package com.example.ElasticsearchAPI;

import org.apache.http.HttpHost;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class DeleteApi {
    // Elasticsearch와 연결 설정
    RestClient restClient = RestClient.builder(new HttpHost("localhost", 9200)).build();

    @DeleteMapping(value = "/delete_item")
    public ResponseEntity<String> deleteDocument(@RequestParam(name = "id") String id) throws IOException {
        // DELETE 요청위한 request 객체 생성
        Request request = new Request("DELETE", "/items/_doc/" + id);

        // 실행 및 응답
        Response response = restClient.performRequest(request);

        // 응답을 JSON 객체로 변환
        JSONObject responseObject = new JSONObject(new String(response.getEntity().getContent().readAllBytes()));

        // html로 데이터 전송
        return new ResponseEntity<>(responseObject.toString(), HttpStatus.OK);
    }
}
