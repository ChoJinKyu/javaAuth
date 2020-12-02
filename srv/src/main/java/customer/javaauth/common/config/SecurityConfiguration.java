package customer.javaauth.common.config;

import com.sap.cloud.security.xsuaa.XsuaaServiceConfiguration;
import com.sap.cloud.security.xsuaa.token.TokenAuthenticationConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.Jwt;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    XsuaaServiceConfiguration xsuaaServiceConfiguration;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
                .anyRequest().permitAll().and().oauth2ResourceServer().jwt()
                .jwtAuthenticationConverter(getJwtAuthenticationConverter());

    }

    Converter<Jwt, AbstractAuthenticationToken> getJwtAuthenticationConverter() {

        TokenAuthenticationConverter converter = new TokenAuthenticationConverter(xsuaaServiceConfiguration);
        converter.setLocalScopeAsAuthorities(true);

		return converter;
    }


    /*
	Converter<Jwt, AbstractAuthenticationToken> getJwtAuthenticationConverter() {        
        TokenAuthenticationConverter converter = new TokenAuthenticationConverter(xsuaaServiceConfiguration);
        converter.setLocalScopeAsAuthorities(true);
		return converter;
    }
    */


/*
	Converter<Jwt, AbstractAuthenticationToken> getJwtAuthenticationConverter() {       
        TokenAuthenticationConverter converter = new TokenAuthenticationConverter( getXsuaaServiceConfiguration());
        converter.setLocalScopeAsAuthorities(true);
		return converter;
    }

	@Bean
	XsuaaServiceConfiguration getXsuaaServiceConfiguration() {
        return new MySecurityConfiguration();
    }

	private class MySecurityConfiguration extends ExtXsuaaServiceConfiguration {

		@Override
		public String getClientSecret() {
			return "mysecret-basic";
		}

	}
*/

    /*
     @Override
     protected void configure(AuthenticationManagerBuilder auth) throws Exception {
          auth.userDetailsService(userService);
     }
     */


/*    
	Converter<Jwt, AbstractAuthenticationToken> getJwtAuthenticationConverter() {       
        Token jwtToken = SpringSecurityContext.getToken();

        ExtUserInfo extUser = new ExtUserInfo();
        Map<String, String> additionalAttributes = new HashMap<String, String>();
        additionalAttributes.put("testName", "testName");
        try{

            OAuth2TokenResponse userToken = xsuaaTokenFlows.userTokenFlow()
                            .token(jwtToken.toString())
                            .subdomain(jwtToken.getSubdomain())     
                            .disableCache(true)                 // optionally disables token cache for request
                            .attributes(additionalAttributes)   // this is optional
                            .execute();

        } catch (TokenFlowException e) {
            e.printStackTrace();
        }
        
        
        TokenAuthenticationConverter converter = new TokenAuthenticationConverter(xsuaaServiceConfiguration);
        converter.setLocalScopeAsAuthorities(true);
		return converter;
    }
*/    

}
