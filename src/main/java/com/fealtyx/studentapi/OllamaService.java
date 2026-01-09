package com.fealtyx.studentapi;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class OllamaService {

    private static final String OLLAMA_URL = "http://localhost:11434/api/generate";

    private final RestTemplate restTemplate = new RestTemplate();

    public String generateStudentSummary(Student student) {

        String prompt = buildPrompt(student);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", "llama3");
        requestBody.put("prompt", prompt);
        requestBody.put("stream", false);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> request =
                new HttpEntity<>(requestBody, headers);

        ResponseEntity<Map> response =
                restTemplate.postForEntity(OLLAMA_URL, request, Map.class);

        return response.getBody().get("response").toString();
    }

    private String buildPrompt(Student student) {
        return String.format(
                "Generate a short professional student summary:\n" +
                "Name: %s\n" +
                "Email: %s\n" +
                "GPA: %.2f",
                student.getName(),
                student.getEmail(),
                student.getGpa()
        );
    }
}
