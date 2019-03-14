package com.urban.billingapi.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

public class SecurityConfig
{
    @Configuration
    @EnableWebSecurity
    public static class WebSecurityConfig extends WebSecurityConfigurerAdapter
    {

        @Override
        @Bean
        public AuthenticationManager authenticationManagerBean() throws Exception
        {
            return super.authenticationManagerBean();
        }

        @Bean
        public UserDetailsService userDetailsService()
        {
            return new InMemoryUserDetailsManager(
                User.withDefaultPasswordEncoder()
                    .username( "user" )
                    .password( "123" )
                    .roles( "USER" )
                    .build() );
        }

        @Override
        protected void configure( HttpSecurity http ) throws Exception
        {

            http
                .authorizeRequests()
                .antMatchers( "/", "/swagger-ui.html", "/v2/api-docs", "/configuration/**", "/swagger*/**", "/webjars/**", "/authorize/**", "/oauth/token**" )
                .permitAll()
                .anyRequest()
                .authenticated();
        }
    }

    @Configuration
    @EnableAuthorizationServer
    public static class Oauth2WebSecurity extends AuthorizationServerConfigurerAdapter
    {
        @Autowired
        private AuthenticationManager authenticationManager;

        @Bean
        public TokenStore tokenStore()
        {
            return new InMemoryTokenStore();
        }

        @Override
        public void configure( AuthorizationServerSecurityConfigurer oauthServer ) throws Exception
        {
            oauthServer.checkTokenAccess( "permitAll()" );
        }

        @Override
        public void configure( ClientDetailsServiceConfigurer clients ) throws Exception
        {

            clients
                .inMemory()
                .withClient( "client" )
                .secret( "{noop}secret" )
                .scopes( "admin" )
                .authorizedGrantTypes( "implicit", "refresh_token", "password",
                    "authorization_code", "client_credentials" );
        }

        @Override
        public void configure( AuthorizationServerEndpointsConfigurer endpoints )
        {
            endpoints.authenticationManager( authenticationManager )
                     .tokenStore( tokenStore() );
        }

    }

    @Configuration
    @EnableResourceServer
    public static class ResourceServerConfig extends ResourceServerConfigurerAdapter
    {

        @Override
        public void configure( HttpSecurity http ) throws Exception
        {
            http
                .authorizeRequests()
                .antMatchers( "/api**" )
                .authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy( SessionCreationPolicy.STATELESS );
        }

    }

}
