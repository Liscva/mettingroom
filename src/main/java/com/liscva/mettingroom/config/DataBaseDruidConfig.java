package com.liscva.mettingroom.config;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * druid配置
 */
@Configuration
@Slf4j(topic = "数据源配置类")
@EnableTransactionManagement
public class DataBaseDruidConfig {

    @Autowired
    private ConfigurableApplicationContext applicationContext;

    @Value("${spring.datasource.initialSize}")
    private int initialSize;

    @Value("${spring.datasource.minIdle}")
    private int minIdle;

    @Value("${spring.datasource.maxActive}")
    private int maxActive;

    @Value("${spring.datasource.maxWait}")
    private int maxWait;

    @Value("${spring.datasource.timeBetweenEvictionRunsMillis}")
    private int timeBetweenEvictionRunsMillis;

    @Value("${spring.datasource.minEvictableIdleTimeMillis}")
    private int minEvictableIdleTimeMillis;

    @Value("${spring.datasource.validationQuery}")
    private String validationQuery;

    @Value("${spring.datasource.testWhileIdle}")
    private boolean testWhileIdle;

    @Value("${spring.datasource.testOnBorrow}")
    private boolean testOnBorrow;

    @Value("${spring.datasource.testOnReturn}")
    private boolean testOnReturn;


    /**
     * 业务库数据源
     *
     * @return DataSource
     * @throws SQLException sql异常
     */
    @Bean(name = "dataSource")
    public DataSource dataSourceFacade() throws SQLException {
        return dataSource();
    }


    /**
     * 业务库数据源
     *
     * @return DataSource
     * @throws SQLException sql异常
     */
    public DataSource dataSource() throws SQLException {
        DruidDataSource datasource = DruidDataSourceBuilder.create().build();
        try {
            //new DruidDataSourceFactory().createDataSource(properties)
            String url = applicationContext.getEnvironment().getProperty("spring.datasource.url");
            String username = applicationContext.getEnvironment().getProperty("spring.datasource.username");
            String password = applicationContext.getEnvironment().getProperty("spring.datasource.password");
            String driverClassName = applicationContext.getEnvironment().getProperty("spring.datasource.driverClassName");
            datasource.setUrl(url);
            datasource.setUsername(username);
            datasource.setPassword(password);
            datasource.setDriverClassName(driverClassName);
            datasource.setQueryTimeout(3000);

            commonConfigDataSource(datasource);
            datasource.setFilters("stat,wall");
            final List<Filter> filterList = new ArrayList<Filter>();
            datasource.setProxyFilters(filterList);
            datasource.init();
            log.info(url + "库加载成功！");
        } catch (Exception e) {
            log.error("数据库加载失败！");
            throw e;
        }
        return datasource;
    }

    /**
     * 默认核心配置
     *
     * @param datasource 数据源
     */
    private void commonConfigDataSource(DruidDataSource datasource) {
        datasource.setInitialSize(initialSize);
        datasource.setMinIdle(minIdle);
        datasource.setMaxActive(maxActive);
        datasource.setMaxWait(maxWait);
        datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        datasource.setValidationQuery(validationQuery);
        datasource.setTestWhileIdle(testWhileIdle);
        datasource.setTestOnBorrow(testOnBorrow);
        datasource.setTestOnReturn(testOnReturn);
    }

}

