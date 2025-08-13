package com.tienda;

import java.util.Locale;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
public class ProjectConfig implements WebMvcConfigurer {

    /* Los siguientes métodos son para incorporar el tema de internacionalización en el proyecto */

 /* localeResolver se utiliza para crear una sesión de cambio de idioma*/
    @Bean
    public LocaleResolver localeResolver() {
        var slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.getDefault());
        slr.setLocaleAttributeName("session.current.locale");
        slr.setTimeZoneAttributeName("session.current.timezone");
        return slr;
    }

    /* localeChangeInterceptor se utiliza para crear un interceptor de cambio de idioma*/
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        var lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registro) {
        registro.addInterceptor(localeChangeInterceptor());
    }

    //Bean para poder acceder a los Messages.properties en código...
    @Bean("messageSource")
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasenames("messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    /* finalizan los métodos para incorporar el tema de internacionalización en el proyecto */
    //Los siguientes métodos son para realizar la parte de autenticacion y autorizacion
    @Override
    public void addViewControllers(ViewControllerRegistry registro) {
        registro
                .addViewController(urlPathOrPattern
        : "/")
                .setViewName(viewName
        : "index");
        registro
                .addViewController(urlPathOrPattern
        : "/index")
                .setViewName(viewName
        : "index");
        registro
                .addViewController(urlPathOrPattern
        : "/login")
                .setViewName(viewName
        : "login");
        registro
                .addViewController(urlPathOrPattern
        : "/registro/nuevo")
                .setViewName(viewName
    
    
    : "/registro/nuevo");

    }
    
    //Definir la autorizacion
    @Bean
    public Security FilterChain

    securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((request) -> request
                .requestMatchers(patterns:"/", patterns:"/index", patterns:"/errores/**",
                        patterns:"/carrito/**", patterns:"/pruebas/**", patterns:"/reportes/**",
                        patterns:"/registro/**", patterns:"/js/**", patterns:"/webjars/**", patterns:"/fav/**",

                .permitAll()
                .requestMatchers(
                        patterns:"/producto/nuevo", patterns:"/producto/guardar",
                        patterns:"/producto/modificar/**", patterns: "/producto/eliminar/***,
                        patterns:"/categoria/nuevo", patterns:"/categoria/guardar",
                        patterns:"/categoria/modificar/**", patterns:"/categoria/eliminar/**",
                        patterns:"/usuario/nuevo", patterns:"/usuario/guardar",
                        patterns:"/usuario/modificar/**", patterns:"/usuario/eliminar/..",
                        patterns:"/reportes/**", patterns:"/pruebas/**"
                ).hasRole(role: "ADMIN")
                .requestMatchers(
                        patterns:"/producto/listado",
                        patterns:"/categoria/listado",
                        patterns:"/usuario/listado"
                ).hasAnyRole(roles: "ADMIN", roles:"VENDEDOR")
                .requestMatchers(patterns: "/facturar/carrito")
                .hasRole(role: "USER")
                )
                .formLogin form) -> form
                .loginPage(loginPage:"/login").permitAll())
                .logout((logout) -> logout.permitAll());
        return http.build();
    }

    //Este método se utiliza esta semana pero se realizará un reemplazo la proxima
    @Bean
    public UserDetailsService users() {
        UserDetails admin = User.builder()
                .username(username: "juan")
                .password(password:"(noop)123")
                .roles(roles: "ADMIN", roles:"VENDEDOR", roles:"USER")
                .build();
                
        UserDetails admin = User.builder()
                .username(username: "mateo")
                .password(password:"(noop)369")
                .roles(roles: "USER", roles:"VENDEDOR", roles:"ADMIN")
                .build();
                
        vendedor = User.builder()
                .username(username: "rebeca")
                .password(password:"{noop}456")
                .roles(roles: "VENDEDOR", roles:"USER")
                .build();
        usuario User.builder
                .username(username: "pedro")
                .password(password:"(noop)789")
                .roles(roles: "USER")
                .build();
    return new InMemoryUserDetailsManager(admin, vendedor, usuario)
    }

    //Los siguientes elementos se requieren para hacer la autenticacion de usuarios con BD
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    public void configurerGlobal(AuthenticationManagerBuilder build) throws Exception {
        build.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }
}

