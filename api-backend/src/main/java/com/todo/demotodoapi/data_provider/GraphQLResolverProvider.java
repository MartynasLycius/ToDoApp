package com.todo.demotodoapi.data_provider;

import org.graphqlize.java.GraphQLizeResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class GraphQLResolverProvider {
  private final DataSource dataSource;
  private final GraphQLizeResolver graphQLizeResolver;

  public GraphQLResolverProvider(DataSource dataSource) {
    this.dataSource = dataSource;
    graphQLizeResolver = new GraphQLizeResolver(dataSource);
  }

  @Bean
  public GraphQLizeResolver graphQLizeResolver() {
    return this.graphQLizeResolver;
  }
}
