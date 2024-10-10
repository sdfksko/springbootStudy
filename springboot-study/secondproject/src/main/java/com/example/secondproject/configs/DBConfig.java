package com.example.secondproject.configs;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = {"com.example.secondproject.mybatis"})
public class DBConfig {
    @Bean   // Spring에서 관리하는 저장소인 Bean에 등록(jpa)
    public DataSource dataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName("com.mysql.cj.jdbc.Driver");
        hikariConfig.setJdbcUrl("jdbc:mysql://localhost:3306/search_region?useSSL=false&useUnicode=true&serverTimezone=Asia/Seoul");
        hikariConfig.setUsername("root");
        hikariConfig.setPassword("1234");

        hikariConfig.setPoolName("wish-pool");
        hikariConfig.setMaximumPoolSize(3);

        return new HikariDataSource(hikariConfig);
    }

    // Mybatis사용 환경 설정 세팅
    // classpath = resource
    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource());
        sqlSessionFactory.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/**/*.xml"));
        return (SqlSessionFactory) sqlSessionFactory.getObject();
    }
}
