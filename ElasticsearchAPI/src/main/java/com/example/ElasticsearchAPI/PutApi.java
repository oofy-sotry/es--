package com.example.ElasticsearchAPI;

import com.example.ElasticsearchItemDTO.Item;
import org.apache.http.HttpHost;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class PutApi {
    // Elasticsearch와 연결 설정
    RestClient restClient = RestClient.builder(new HttpHost("localhost", 9200)).build();

    @PutMapping(value = "/put_item")
    public @ResponseBody ResponseEntity<String> putDocument(@RequestParam(name = "id") String id, @RequestBody Item item) throws IOException, JSONException {

    	// JSON 객체 생성
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", item.getId());
        jsonObject.put("title", item.getTitle());
        jsonObject.put("body", item.getBody());

        // PUT 요청위한 request 객체 생성
        Request request = new Request("PUT", "/items/_doc/" + id);
        request.setJsonEntity(jsonObject.toString());

        // 실행 및 응답
        Response response = restClient.performRequest(request);

        // 응답을 JSON 객체로 변환
        JSONObject responseObject = new JSONObject(response.getEntity().getContent().readAllBytes());

        // html로 데이터 전송
        return new ResponseEntity<>(responseObject.toString(), HttpStatus.CREATED);
    }
}
