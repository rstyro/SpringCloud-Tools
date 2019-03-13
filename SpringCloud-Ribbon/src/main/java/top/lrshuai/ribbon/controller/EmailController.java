package top.lrshuai.ribbon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import top.lrshuai.common.dto.Result;

@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/send")
    public Result sendEmail(String toAddress){
        LinkedMultiValueMap<String, String> parameterMap = new LinkedMultiValueMap<String, String>();
        parameterMap.add("toAddress", toAddress);
        ResponseEntity<Result> responseEntity = restTemplate.postForEntity("http://email/mail/sendTemplate",parameterMap, Result.class);
        System.out.println("==responseEnitiy="+responseEntity);
        return responseEntity.getBody();
    }
}
