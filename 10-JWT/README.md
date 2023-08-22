# refer
https://www.javainuse.com/misc/jwt-interview-questions

# JWT

JWT: JSON Web Token (JWT) is an open standard (RFC 7519) that specifies a compact and self-contained way of transmitting information securely as a JSON object between parties

Advantages of JWT Authorization
- It is digitally signed so if any one modifies it the server will know about it
- It is most suitable for Microservices Architecture
- specifying the expiration time: normally 1200 seconds or 20 minutes
  ```
    private String doGenerateToken(Map<String, Object> claims, String subject) {

		return Jwts.builder()
            .setClaims(claims)
            .setSubject(subject)
            .setIssuedAt(new Date(System.currentTimeMillis()))
			.setExpiration(new Date(System.currentTimeMillis() + jwtExpirationInMs))
            .signWith(SignatureAlgorithm.HS512, secret)
            .compact();
	}

  ```

structure of JWT：
header.payload.signature

https://www.techgeeknext.com/spring-boot-security/jwt-interview-questions

Workflow of JWT
![](https://www.techgeeknext.com/jwt/jwt-workflow.JPG)

Why JWT is a stateless authentication?
- because the authorizing server doesn't need to keep track of anything, the token is all that's required to verify a token bearer's authorization. In stateless authentication, no need to store user information in the session.

Does JWT token contain password?
- **yes**, it is OK to pass/receive sensitive data in JWT if you encrypt the data before placing into JWT's payload and decrypt it after the JWT validation to use it.
- In a general case you would not need to keep user credentials in the JWT because the JWT is by itself a dynamically generated credential that represents the login / password (or the other means of authentication) provided at the JWT's first generation time.

AbstractSecurityInterceptor
- handles the **initial authorization** of an incoming request
  - FilterSecurityInterceptor: All authenticated user requests will be authorised by it.
  - MethodSecurityInterceptor: for method level security to be implemented. It enables us to apply security to our programme at the method level.

What is difference of using @PreAuthorize and @Secured in Spring Security?
- **@PreAuthorize** annotation is used to check for authorization before executing the method.
- **alternatively** use the **@Secured** annotation in spring to handle method-level security, however it has several limitations:
  - cannot have several conditions with the @Secured annotation.` i.e. the roles cannot be coupled with an AND/OR condition`
  - Spring expression language is not supported by the @Secured annotation


difference between hasRole() and hasAuthority()?
- hasRole() need not specify the ROLE prefix 
- hasAuthority() needs the complete string to be explicitly specified.
- hasRole(): Spring roles are authorities with the ROLE_prefix / the authority name in the claim must begin with `ROLE_`.
  - `hasRole('ADMIN')`  ---- `ROLE_ADMIN`   ==  `hasAuthority("ROLE_ADMIN")`
  - hasAuthority("ROLE_ADMIN") and hasRole("ADMIN") perform the same task

```
@PreAuthorize("hasAuthority('Admin')")
@RequestMapping("/fetch-users")
@ResponseBody
public String protectedUserPage() {
    return "TechGeekNext User";
}

------------------------------------------

@PreAuthorize("hasRole('admin')")
@RequestMapping("/fetch-users")
public String protectedUserPage() {
    return "TechGeekNext User";
}
```

difference between Spring Security's @PreAuthorize and HttpSecurity:  HttpSecurity is done before @PreAuthorize.
- **HttpSecurity**: HttpSecurity method is associated with URL endpoints. It rejects the request in a web request filter, before controller mapping has occurred. Earlier
- **@PreAuthorize**: @PreAuthorize assessment happens before the execution of the controller method; later

enable Method-level Security for Spring?
- `@PreAuthorize` annotation is enabled by the `@EnableGlobalMethodSecurity(prePostEnabled = true)` annotation
```
@Component
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    ...
}
```
use Authorization Based On OAuth 2.0 with PreAuthorize?
```
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    protected void configure(final HttpSecurity http) throws Exception {
        http.antMatcher("/**")
            .authorizeRequests()
            .antMatchers("/").permitAll()
            .anyRequest().authenticated()
            //.and().formLogin();   // <-- Without OAUTH 
            .and().oauth2Login();  // <-- With OAUTH
    }
}
```

Which open source tools are available for Oauth 2.0 /SAML/ OpenID Connect based SSO?
- It works with a variety of protocols, including **Oauth 2.0**, **SAML 2.0** and **OpenID Connect**. User credentials can also be stored locally or via an LDAP or Kerberos backend.
  - A login credential is a set of unique identifiers–such as a username and password
- When a user logs in successfully with **Keycloak**, they are given a **token**, which is **saved as a cookie** in the browser, and they are automatically sent back to the service they were trying to access. This token usually includes a username as well as information about the user's permissions













