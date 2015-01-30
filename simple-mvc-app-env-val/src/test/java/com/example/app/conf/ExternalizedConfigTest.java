package com.example.app.conf;

import com.example.app.App;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
public class ExternalizedConfigTest {

    @Autowired
    ExternalizedConfig externalizedConfig;

    @Test
    public void 環境変数などをテスト時に用意する() {

        String expected = "ローカル開発環境";
        String acctual = externalizedConfig.getName();

        assertThat(acctual, is(expected));
    }
}