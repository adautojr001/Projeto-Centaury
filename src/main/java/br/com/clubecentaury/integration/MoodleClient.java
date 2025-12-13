package br.com.clubecentaury.integration;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

public class MoodleClient {
    private final String baseUrl;
    private final String token;
    private final RestTemplate rest = new RestTemplate();

    public MoodleClient(String baseUrl, String token) {
        this.baseUrl = baseUrl;
        this.token = token;
    }

    public Map[] getCourses() {
        UriComponentsBuilder uri = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .queryParam("wstoken", token)
                .queryParam("wsfunction", "core_course_get_courses")
                .queryParam("moodlewsrestformat", "json");
        return rest.getForObject(uri.toUriString(), Map[].class);
    }

    public Map<String,Object> enrollUser(int courseId, int userId) {
        UriComponentsBuilder uri = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .queryParam("wstoken", token)
                .queryParam("wsfunction", "enrol_manual_enrol_users")
                .queryParam("moodlewsrestformat", "json");
        MultiValueMap<String, Object> form = new LinkedMultiValueMap<>();
        // Moodle expects arrays like enrolments[0][roleid], enrolments[0][userid], enrolments[0][courseid]
        form.add("enrolments[0][roleid]", "5"); // student role usually 5
        form.add("enrolments[0][userid]", String.valueOf(userId));
        form.add("enrolments[0][courseid]", String.valueOf(courseId));
        // Post as form data
        var response = rest.postForObject(uri.toUriString(), form, Map.class);
        return response;
    }
}
