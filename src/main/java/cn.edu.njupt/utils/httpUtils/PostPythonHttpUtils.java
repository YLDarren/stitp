package cn.edu.njupt.utils.httpUtils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

/**
 * 向python识别服务发送post请求
 */
public class PostPythonHttpUtils {

    public static void postPython(String request){
        RestTemplate restTemplate = new RestTemplate();

        //获取请求头
        HttpEntity<String> header = header(request);

        ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://39.108.188.185:8080/stitp/upload/", header , String.class);

        int code = responseEntity.getStatusCodeValue();

        String body = responseEntity.getBody();

        JsonObject json = new JsonParser().parse(body).getAsJsonObject();

        System.out.println(json.get("status"));

        System.out.println(code);

        System.out.println(body);
    }

    /**
     * 根据请求参数封装请求头
     * @param request
     * @return
     */
    private static HttpEntity<String> header(String request){
        HttpHeaders headers = new HttpHeaders();
        //设置发送数据类型
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        //设置返回数据类型
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());

        HttpEntity<String> formEntity = new HttpEntity<String>(request, headers);

        return formEntity;
    }

    public static void main(String[] args) {
        postPython("");
    }
}
