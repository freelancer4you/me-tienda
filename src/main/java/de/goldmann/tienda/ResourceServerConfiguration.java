package de.goldmann.tienda;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    @Value("${security.oauth2.resource.id}")
    String     resourceId;

    @Autowired
    TokenStore tokenStore;

    @Override
    public void configure(final HttpSecurity http) throws Exception {
        // @formatter:off
        http.csrf().disable()
        .authorizeRequests()
        .antMatchers(HttpMethod.OPTIONS, Constants.PATH_PROTECTED + "**").permitAll()// CORS requests
        .antMatchers(Constants.PATH_PROTECTED + "**").authenticated()
        .antMatchers(HttpMethod.OPTIONS, "/api/resources/orders").permitAll()// CORS requests
        .antMatchers("/api/resources/orders").authenticated()
        .antMatchers("/**").anonymous();
        // @formatter:on
    }

    @Override
    public void configure(final ResourceServerSecurityConfigurer serverSecurityConfigurer) throws Exception {
        serverSecurityConfigurer.resourceId(resourceId).tokenStore(tokenStore);
    }

}
