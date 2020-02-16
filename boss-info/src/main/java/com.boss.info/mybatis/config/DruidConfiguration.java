package com.boss.info.mybatis.config;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 描述: 数据源实现
 *
 * @outhor hants
 * @create 2019-04-01 16:54
 */
@Configuration
@Slf4j
public class DruidConfiguration implements EnvironmentAware {

    //private static Logger logger = Logger.getLogger(DruidConfiguration.class);

    public static final String PARAS_CONFIG_FILENAME = "application.properties";

    private String driverUrl;
    private String driverUsername;
    private String driverPassword;
    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    @Value("${spring.datasource.druid.initial-size}")
    private Integer initialSize;

    @Value("${spring.datasource.druid.min-idle}")
    private Integer minIdle;

    @Value("${spring.datasource.druid.max-active}")
    private Integer maxActive;

    @Value("${spring.datasource.druid.max-wait}")
    private Long maxWait;

    @Value("${spring.datasource.druid.time-between-eviction-runs-millis}")
    private Long timeBetweenEvictionRunsMillis;

    @Value("${spring.datasource.druid.min-evictable-idle-time-millis}")
    private Long minEvictableIdleTimeMillis;

    @Value("${spring.datasource.druid.validation-query}")
    private String validationQuery;

    @Value("${spring.datasource.druid.test-while-idle}")
    private Boolean testWhileIdle;

    @Value("${spring.datasource.druid.test-on-borrow}")
    private Boolean testOnBorrow;

    @Value("${spring.datasource.druid.test-on-return}")
    private Boolean testOnReturn;

    @Value("${spring.datasource.druid.pool-prepared-statements}")
    private Boolean poolPreparedStatements;

    @Value("${spring.datasource.druid.max-pool-prepared-statement-per-connection-size}")
    private Integer maxPoolPreparedStatementPerConnectionSize;

    @Value("${spring.datasource.druid.filters}")
    private String filters;

    @Override
    public void setEnvironment(Environment environment) {
        InputStream in = null;
        try {
            driverUrl = environment.getProperty("spring.datasource.druid.url");
            driverUsername = environment.getProperty("spring.datasource.druid.username");
            driverPassword = environment.getProperty("spring.datasource.druid.password");

            if (StringUtils.isBlank(driverUrl) ||
                    StringUtils.isBlank(driverUsername) ||
                    StringUtils.isBlank(driverPassword)) {
                log.warn("loading datasource properties from application.properties, environmental not setting");
                Properties properties = new Properties();
                in = this.getClass().getClassLoader().getResourceAsStream(PARAS_CONFIG_FILENAME);
                if (in != null) {
                    properties.load(in);
                }
                this.driverUrl = properties.getProperty("spring.datasource.druid.url");
                this.driverUsername = properties.getProperty("spring.datasource.druid.username");
                this.driverPassword = properties.getProperty("spring.datasource.druid.password");
            }
        } catch (IOException e) {
            log.error("DataSourceConfiguration init error {} ", e);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    log.error("DataSourceConfiguration setEnvironment in finally error:", e);
                }
            }
        }
    }

    @Bean(initMethod = "init", destroyMethod = "close")
    @Primary
    public DruidDataSource dataSource() throws Exception {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(this.driverUrl);
        dataSource.setUsername(this.driverUsername);
        dataSource.setPassword(this.driverPassword);
        dataSource.setInitialSize(initialSize);
        dataSource.setMinIdle(minIdle);
        dataSource.setMaxActive(maxActive);
        dataSource.setMaxWait(maxWait);
        dataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        dataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        dataSource.setValidationQuery(validationQuery);
        dataSource.setTestWhileIdle(testWhileIdle);
        dataSource.setTestOnBorrow(testOnBorrow);
        dataSource.setTestOnReturn(testOnReturn);
        dataSource.setPoolPreparedStatements(poolPreparedStatements);
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
        dataSource.setFilters(filters);
        return dataSource;
    }

}