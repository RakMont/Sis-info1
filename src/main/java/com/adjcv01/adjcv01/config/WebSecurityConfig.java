package com.adjcv01.adjcv01.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.adjcv01.adjcv01.Service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	//Necesario para evitar que la seguridad se aplique a los resources
    //Como los css, imagenes y javascripts
    String[] resources = new String[]{
            "/include/**","/css/**","/icons/**","/img/**","/js/**","/layer/**"
    };
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
	        .antMatchers(resources).permitAll()
               // .antMatchers("/mascotas").anonymous()
                .antMatchers("/user/new").anonymous()
                .antMatchers("/","/index").permitAll()
	        .antMatchers("/admin*").access("hasRole('ADMIN')")
	        .antMatchers("/user*").access("hasRole('USER')")
                .antMatchers("/vacuna/new*").access("hasRole('ADMIN')")
                .antMatchers("/vacunas*").access("hasRole('ADMIN')")
                .antMatchers("/propietario/new*").access("hasRole('ADMIN')")
                .antMatchers("/propietarios*").access("hasRole('ADMIN')")
                .antMatchers("/producto/new*").access("hasRole('ADMIN')")
                //.antMatchers("/productos*").access("hasRole('ADMIN')")
                .antMatchers("/mascota/new*").access("hasRole('ADMIN')")
                .antMatchers("/mascotas*").access("hasRole('ADMIN')")

                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")
                .permitAll()
                .defaultSuccessUrl("/menu")
                .failureUrl("/login?error=true")
                .usernameParameter("username")
                .passwordParameter("password")
                .and()
            .logout()
                .permitAll()
                .logoutSuccessUrl("/login?logout");
    }
    BCryptPasswordEncoder bCryptPasswordEncoder;
    //Crea el encriptador de contraseñas	
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
		bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
//El numero 4 representa que tan fuerte quieres la encriptacion.
//Se puede en un rango entre 4 y 31. 
//Si no pones un numero el programa utilizara uno aleatoriamente cada vez
//que inicies la aplicacion, por lo cual tus contrasenas encriptadas no funcionaran bien
        return bCryptPasswordEncoder;
    }
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.POST, "/mascotas");
        web.ignoring().antMatchers(HttpMethod.POST, "/filterEspecie");
        web.ignoring().antMatchers(HttpMethod.POST, "/filterPropietario");
        web.ignoring().antMatchers(HttpMethod.POST, "/vacunaMascota");
        web.ignoring().antMatchers(HttpMethod.POST, "/MascotaActualizar");


        web.ignoring().antMatchers(HttpMethod.POST, "/propietarios");
        web.ignoring().antMatchers(HttpMethod.POST, "/PropietarioActualizar");

        web.ignoring().antMatchers(HttpMethod.POST, "/vacunas");
        web.ignoring().antMatchers(HttpMethod.POST, "/VacunaActualizar");

        web.ignoring().antMatchers(HttpMethod.POST, "/productos");
        web.ignoring().antMatchers(HttpMethod.POST, "/ProductoActualizar");

        web.ignoring().antMatchers(HttpMethod.POST, "/users");

    }
    @Autowired
    UserDetailsServiceImpl userDetailsService;
	
    //Registra el service para usuarios y el encriptador de contrasena
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception { 
 
        // Setting Service to find User in the database.
        // And Setting PassswordEncoder
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());     
    }
}