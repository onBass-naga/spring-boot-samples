package com.example.app.comment;

import com.example.app.App;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


/**
 * Created by naga on 2015/03/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
@WebAppConfiguration
@IntegrationTest({"server.port:0",
        "spring.datasource.url:jdbc:h2:mem:testDB;DB_CLOSE_ON_EXIT=FALSE"})
public class CommentControllerTest {

    @Autowired
    CommentRepository commentRepository;

    @Value("${local.server.port}")
    int port;

    RestTemplate restTemplate = new TestRestTemplate();

    String uri;
    Comment comment1;
    Comment comment2;


    @Before
    public void setUp() {
        commentRepository.deleteAll();

        comment1 = new Comment();
        comment1.setAuthor("Taro");
        comment1.setText("Yamada");
        comment2 = new Comment();
        comment2.setAuthor("Ichiro");
        comment2.setText("Suzuki");
        commentRepository.save(Arrays.asList(comment1, comment2));

        uri = String.format("http://localhost:%s/comments", port);
    }

    @After
    public void tearDown() throws Exception {
        commentRepository.deleteAll();
    }

    @Test
    public void testGetComments() throws Exception {
        ResponseEntity<String> response = restTemplate.getForEntity(
                "http://localhost:" + port + "/comments", String.class);

        assertThat(response.getStatusCode(), is(HttpStatus.OK));
    }

    @Test
    public void testPostComments() throws Exception {
        Comment comment3 = new Comment();
        comment3.setAuthor("Nobita");
        comment3.setText("Nobi");

        ResponseEntity<String> response = restTemplate.postForEntity(
                "http://localhost:" + port + "/comments", comment3, String.class);

        assertThat(response.getStatusCode(), is(HttpStatus.CREATED));
        assertThat(response.getBody(), is("{\"id\":3,\"author\":\"Nobita\",\"text\":\"Nobi\"}"));
    }

}
