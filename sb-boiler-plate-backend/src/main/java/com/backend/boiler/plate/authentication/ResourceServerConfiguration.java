package com.backend.boiler.plate.authentication;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import com.backend.boiler.plate.Utils.Constants;

@Configuration
@EnableResourceServer 
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

	private static final String RESOURCE_ID = "my_rest_api";
	private static InMemoryTokenStore tokenStore = new InMemoryTokenStore();
	
	@Autowired
    JsonToUrlEncodedAuthenticationFilter jsonFilter;
	
	@Autowired
    private DataSource dataSource;
	
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) {
		resources.tokenStore(tokenStore()).resourceId(RESOURCE_ID);
	}
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.cors();
		http.csrf().disable()
			.authorizeRequests()
			.antMatchers("/api/**")
             .hasAnyRole(Constants.ROLE_ADMIN, Constants.ROLE_USER)
             .antMatchers("/images/**","/public/**")
             .permitAll().anyRequest().authenticated()
             .and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
	}
	
    
    @Bean
    public TokenStore tokenStore() {
        String insertAccessTokenSql = "insert into oauth_access_token (token_id, token, authentication_id, user_name, client_id, authentication, refresh_token) values (?, ?, ?, ?, ?, ?, ?)";
        String selectAccessTokensFromUserNameAndClientIdSql = "select token_id, token from oauth_access_token where user_name = ? and client_id = ?";
        String selectAccessTokensFromUserNameSql = "select token_id, token from oauth_access_token where user_name = ?";
        String selectAccessTokensFromClientIdSql = "select token_id, token from oauth_access_token where client_id = ?";
        String insertRefreshTokenSql = "insert into oauth_refresh_token (token_id, token, authentication) values (?, ?, ?)";

        JdbcTokenStore jdbcTokenStore = new JdbcTokenStore(dataSource);
        jdbcTokenStore.setInsertAccessTokenSql(insertAccessTokenSql);
        jdbcTokenStore.setSelectAccessTokensFromUserNameAndClientIdSql(selectAccessTokensFromUserNameAndClientIdSql);
        jdbcTokenStore.setSelectAccessTokensFromUserNameSql(selectAccessTokensFromUserNameSql);
        jdbcTokenStore.setSelectAccessTokensFromClientIdSql(selectAccessTokensFromClientIdSql);
        jdbcTokenStore.setInsertRefreshTokenSql(insertRefreshTokenSql);


        return jdbcTokenStore;
    }
	
}

