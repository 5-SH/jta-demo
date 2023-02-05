package com.seungho.jtademo.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class RoutingDatasource extends AbstractRoutingDataSource {
  @Override
  protected Object determineCurrentLookupKey() {
    return ContextHolder.get();
  }
}
