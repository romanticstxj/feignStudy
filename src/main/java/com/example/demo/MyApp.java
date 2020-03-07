package com.example.demo;

import feign.Feign;
import feign.Param;
import feign.RequestLine;
import feign.jackson.JacksonDecoder;

import java.util.List;

interface GitHub {
    @RequestLine("GET /repos/{owner}/{repo}/contributors")
    String contributors(@Param("owner") String owner, @Param("repo") String repo);

    @RequestLine("POST /repos/{owner}/{repo}/issues")
    void createIssue(Issue issue, @Param("owner") String owner, @Param("repo") String repo);

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
        GitHub github = Feign.builder()
//                .decoder(new JacksonDecoder())
                .target(GitHub.class, "https://api.github.com");

        // Fetch and print a list of the contributors to this library.
        String contributors = github.contributors("OpenFeign", "feign");
//        for (Contributor contributor : contributors) {
            System.out.println(contributors);
//        }
    }


}