package top.lrshuai.ribbon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import top.lrshuai.common.dto.Result;

@RestController
@RequestMapping("/upload")
public class UploadImageController {

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/image")
    public Result base64Image(String base64){
        LinkedMultiValueMap<String, String> parameterMap = new LinkedMultiValueMap<String, String>();
        parameterMap.add("base64", base64);
        ResponseEntity<Result> responseEntity = restTemplate.postForEntity("http://upload-imgage-service/upload/base64ToLocalhostImage",parameterMap, Result.class);
        System.out.println("==responseEnitiy="+responseEntity);
        return responseEntity.getBody();
    }

}
