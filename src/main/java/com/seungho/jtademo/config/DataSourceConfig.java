package com.seungho.jtademo.config;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.transaction.managed.ManagedTransactionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
public class DataSourceConfig {

  @Bean
  public DataSource routingDataSource() {
    AbstractRoutingDataSource routingDataSource = new RoutingDatasource();
    routingDataSource.setDefaultTargetDataSource(createMySqlDataSource("jdbc:mysql://localhost:3306/spring"));

    Map<Object, Object> targetDataSources = new HashMap<>();
    targetDataSources.put(0, createPostgresqlDataSource("jdbc:postgresql://localhost:5433/postgres"));
    targetDataSources.put(1, createMariaDataSource("jdbc:mariadb://localhost:3305/maria"));
//    targetDataSources.put(2, createOracleDataSource("jdbc:oracle:thin:@localhost:1521/xe"));
//    targetDataSources.put(3, createSqlServerDataSource("jdbc:sqlserver://localhost:1433;DatabaseName=mssql"));

    routingDataSource.setTargetDataSources(targetDataSources);

    return routingDataSource;
  }

  private DataSource createMySqlDataSource(String url) {
    AtomikosDataSourceBean dataSource = new AtomikosDataSourceBean();

    Properties properties = new Properties();
    properties.setProperty("user", "root");
    properties.setProperty("password", "root");
    properties.setProperty("url", url);

    dataSource.setXaDataSourceClassName("com.mysql.jdbc.jdbc2.optional.MysqlXADataSource");
    dataSource.setUniqueResourceName(url.substring(0, url.length() > 45 ? 44 : url.length()));
    dataSource.setXaProperties(properties);

    return dataSource;
  }

  private DataSource createMariaDataSource(String url) {
    AtomikosDataSourceBean dataSource = new AtomikosDataSourceBean();

    Properties properties = new Properties();
    properties.setProperty("user", "root");
    properties.setProperty("password", "root");
    properties.setProperty("url", url);

    dataSource.setXaDataSourceClassName("org.mariadb.jdbc.MariaDbDataSource");
    dataSource.setUniqueResourceName(url.substring(0, url.length() > 45 ? 44 : url.length()));
    dataSource.setXaProperties(properties);

    return dataSource;
  }

  private DataSource createPostgresqlDataSource(String url) {
    AtomikosDataSourceBean dataSource = new AtomikosDataSourceBean();

    Properties properties = new Properties();
    properties.setProperty("user", "postgres");
    properties.setProperty("password", "root");
    properties.setProperty("url", url);

    dataSource.setXaDataSourceClassName("org.postgresql.xa.PGXADataSource");
    dataSource.setUniqueResourceName(url.substring(0, url.length() > 45 ? 44 : url.length()));
    dataSource.setXaProperties(properties);

    return dataSource;
  }

  private DataSource createOracleDataSource(String url) {
    AtomikosDataSourceBean dataSource = new AtomikosDataSourceBean();

    Properties properties = new Properties();
    properties.setProperty("user", "root");
    properties.setProperty("password", "root");
    properties.setProperty("URL", url);

    dataSource.setXaDataSourceClassName("oracle.jdbc.xa.client.OracleXADataSource");
    dataSource.setUniqueResourceName(url.substring(0, url.length() > 45 ? 44 : url.length()));
    dataSource.setXaProperties(properties);

    return dataSource;
  }

  private DataSource createSqlServerDataSource(String url) {
    AtomikosDataSourceBean dataSource = new AtomikosDataSourceBean();

    Properties properties = new Properties();
    properties.setProperty("user", "root");
    properties.setProperty("password", "root");
    properties.setProperty("URL", url);

    dataSource.setXaDataSourceClassName("com.microsoft.sqlserver.jdbc.SQLServerXADataSource");
    dataSource.setUniqueResourceName(url.substring(0, url.length() > 45 ? 44 : url.length()));
    dataSource.setXaProperties(properties);

    return dataSource;
  }

  @Bean
  public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
    SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
    factory.setDataSource(dataSource);
    factory.setTransactionFactory(new ManagedTransactionFactory());
    return factory.getObject();
  }
}
