# refer:
https://www.javainuse.com/spring/spring-security-interview-questions

https://www.interviewbit.com/spring-security-interview-questions/

# Spring-Security
**Authentication and authorization**: Authentication and authorization are two vital information security processes that administrators use to protect systems and information
- **Authentication** verifies the identity of a user or service( verifies that someone or something is who they say they are )
- **authorization** determines their access rights ( determines a user or service's level of access ). We use authorization to give users or services permission to access some data or perform a particular action

Spring makes use of the DelegatingFilterProxy for implementing security mechanisms
Proxy for standard Servlet Filter, delegating to a Spring-managed bean that implements the Filter interface. Its the starting point in the springSecurityFilterChain which instantiates the Spring Security filters according to the Spring configuration

OAuth2 Authorization code grant type: OAuth (Open Authorization) is a simple way to publish and interact with protected data. It is an open standard for token-based authentication and authorization on the Internet. It allows an end user's account information to be used by third-party services, such as Facebook, without exposing the user's password.
- Authorization code grant
- Implicit grant
- Resource owner credentials grant
- Client credentials grant
- Refresh token grant


how to refresh expired JSON Web Token <br>
![](https://www.javainuse.com/series-7-1-min.JPG)

JWT ? How to implement it using Spring Boot Security? <br>
![](https://www.javainuse.com/62-12-min.JPG)

What is OAuth2 Client Credentials Grant?
- The **Client Credentials Grant** involves machine to machine authentication. This type of Authentication does not involve any end-user. The machine it self authenticates itself to access a protected resource. 
- **Authorization Grant**: the end user had to authenticate himself using Authorization Server like Gmail

hat is OAuth2 Password Grant?
- Password grant type: the user triggers the client to get some resource. 
- it passes the username and password to the client
- The client then communicates with the authorization server using the provided username, password and also its own clientId and clientSecret to get the access token
- Using this access token it then gets the required resource from the resource server.

Form Login Authentication
```
@Override
protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .antMatchers("/").permitAll().antMatchers("/welcome")
        .hasAnyRole("USER", "ADMIN").antMatchers("/getEmployees").hasAnyRole("USER", "ADMIN")
        .antMatchers("/addNewEmployee").hasAnyRole("ADMIN").anyRequest().authenticated()
        
        .and()
            .formLogin()
            .permitAll()
            
        .and()
            .logout().permitAll();

    http.csrf().disable();
}
```

Custom Login Page
```
@Override
protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
            .antMatchers("/").permitAll().antMatchers("/welcome").hasAnyRole("USER", "ADMIN")
            .antMatchers("/getEmployees").hasAnyRole("USER", "ADMIN").antMatchers("/addNewEmployee")
            .hasAnyRole("ADMIN").anyRequest().authenticated()

            .and()
                .formLogin()
                .loginPage("/login")            // Custom Login Page
                .permitAll()

            .and()
                .logout().permitAll();

    http.csrf().disable();
}
```

do authentication against database tables
- In Memory Configuration 
```
@Autowired
public void configureGlobal(AuthenticationManagerBuilder authenticationMgr) throws Exception {
    authenticationMgr
        .inMemoryAuthentication()
            .withUser("employee").password("employee")
            .authorities("ROLE_USER")
        
        .and()
            .withUser("javainuse").password("javainuse")
            .authorities("ROLE_USER", "ROLE_ADMIN");
}
```
- Database Authentication
```
@Autowired
public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
    auth.jdbcAuthentication()
            .dataSource(dataSource);
}
```

configure Spring Security with in-memory configuration?
```
@Autowired
public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth
        .inMemoryAuthentication()
        .withUser("user").password("password").roles("USER")

        .and()
        .withUser("admin").password("password").roles("USER", "ADMIN");
}
```


use of Spring Boot Security AuthenticationHandler class<br>
Using `successHandler` to realize:
  - users with role USER to be redirected to the welcome page
  - users with role ADMIN to be redirected to the add employee page.
```
@Override
protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()

            .antMatchers("/").permitAll().antMatchers("/welcome").hasAnyRole("USER", "ADMIN")
            .antMatchers("/getEmployees").hasAnyRole("USER", "ADMIN")
            .antMatchers("/addNewEmployee").hasAnyRole("ADMIN")
            .anyRequest().authenticated()

            .and()
                .formLogin().successHandler(successHandler)     // successHandler
                .loginPage("/login").permitAll()
                
            .and()
                .logout().permitAll();

    http.csrf().disable();
}
```

the difference between ROLE_USER and ROLE_ANONYMOUS in a Spring intercept url configuration
- ROLE_ANONYMOUS: default role; assigned to an unauthenticated (anonymous) user when a configuration uses Spring Security's "anonymous authentication" filter
- ROLE_USER: has no meaning; assign this role to your users when they are authenticated (you are in charge of loading the roles (authorities) for an authenticated user).

DelegatingFilterProxy: delegating proxy to automatically intercept a URL with a particular pattern to apply spring security.
```
<filter>
    <filter-name>springSecurityFilterChain</filter-name>
    <filter-class>org.springframework.web.filter.DelegatingFilterProxy</filter-class>
</filter>

<filter-mapping>
    <filter-name>springSecurityFilterChain</filter-name>
    <url-pattern>/*</url-pattern>
</filter-mapping>
```


The difference between @Secured and @PreAuthorize
- @PreAuthorize can work with Spring Expression Language (SpEL)
  - Access methods and properties of SecurityExpressionRoot.
  - Access method arguments (requires compilation with debug info or custom ParameterNameDiscoverer)
    ```
    @Secured("ROLE_USER")
    public void create(Contact contact)

    @PreAuthorize("#contact.name == principal.name")
    public void doSomething(Contact contact)
    ```
- access the method only if the user has Role1 **and** Role2: need to use `@PreAuthorize`
    ```
    // role1 and role2
    @PreAuthorize("hasRole('ROLE_role1') and hasRole('ROLE_role2')") 

    // is treated as an OR
    @Secured({"role1", "role2"}) 
    ```


