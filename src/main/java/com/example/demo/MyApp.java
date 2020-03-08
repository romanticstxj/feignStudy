package com.example.demo;

import com.google.common.collect.Maps;
import feign.*;
import feign.jackson.JacksonDecoder;
import feign.jaxrs.JAXRSContract;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

interface GitHub {
    @RequestLine("GET /repos/{owner}/{repo}/contributors")
    String contributors(@Param("owner") String owner, @Param("repo") String repo);

    @RequestLine("POST /repos/{owner}/{repo}/issues")
    void createIssue(Issue issue, @Param("owner") String owner, @Param("repo") String repo);




}

interface MyClient{
//    @RequestLine("GET /test")
//    @Headers("token: {token}")
//    String testHeader(@Param("token") String token);

//    @RequestLine("GET /test")
//    @GetMapping("/test")
//    String testHeaderMap(@QueryMap Map<String, Object> headerMap);

//    @RequestLine("GET /find")
    @GetMapping("/find")
    String find(@SpringQueryMap Map<String, Object> queryMap);
}

class Contributor {
  String login;
  int contributions;
}

  class Issue {
    String title;
    String body;
    List<String> assignees;
    int milestone;
    List<String> labels;
}

public class MyApp {
    public static void main(String... args) {
        MyClient myClient = Feign.builder()
                .logLevel(Logger.Level.FULL)
                .contract(new JAXRSContract())
//                .decoder(new JacksonDecoder())
//                .target(GitHub.class, "https://api.github.com");
                .target(MyClient.class, "http://localhost:8089");
//        // Fetch and print a list of the contributors to this library.
//        String contributors = github.contributors("OpenFeign", "feign");
////        for (Contributor contributor : contributors) {
//            System.out.println(contributors);
////        }

//        String result = myClient.testHeader("token");
//        System.out.println(result);
//        testQueryMap(myClient);
        testQueryMap(myClient);
    }

//    public static void testHeaderMap(MyClient myClient){
//        Map<String, Object> map = Maps.newHashMap();
//        map.put("token", "token1");
//        myClient.testHeaderMap(map);
//    }

    public static void testQueryMap(MyClient myClient){
        Map<String, Object> map = Maps.newHashMap();
        map.put("var", "var1");
        System.out.println(myClient.find(map));
    }


}