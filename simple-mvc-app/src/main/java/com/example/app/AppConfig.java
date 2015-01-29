package com.example.app;

import org.h2.tools.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.sql.SQLException;

/**
 * Created by naga on 2015/01/22.
 */
@Configuration
public class AppConfig {

    // H2コンソールを使用可能にする。
    // http://localhost:8082/
    @Bean(name = "h2Server", initMethod = "start", destroyMethod = "stop")
    @DependsOn(value = "h2WebServer")
    Server createTcpServer() throws SQLException {
        return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9092");
    }
    @Bean(name = "h2WebServer", initMethod = "start", destroyMethod = "stop")
    Server createWebServer() throws SQLException {
        return Server.createWebServer("-web", "-webAllowOthers", "-webPort", "8082");
    }


}
