package br.com.pim.projetoPim.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import br.com.pim.projetoPim.repository.UsuarioRepository;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebMvc
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {

	@Autowired
	private AutenticacaoService autenticacaoService;

	@Autowired
	private TokenService tokenService;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	// configuracoes de autenticacao

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(autenticacaoService).passwordEncoder(new BCryptPasswordEncoder());
	}

	// configuracoes de autorizacoes, perfil de acesso
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()

				// .antMatchers(HttpMethod.GET, "/categorias/embedmodulos").permitAll()
				// .antMatchers(HttpMethod.GET, "/categorias").permitAll()
				// .antMatchers(HttpMethod.GET, "/videos").permitAll()
				// .antMatchers(HttpMethod.GET, "/modulos/**").permitAll()
				// .antMatchers(HttpMethod.GET, "/modulos/embedvideos").permitAll()
				// .antMatchers(HttpMethod.POST, "/modulos").permitAll()
				// .antMatchers(HttpMethod.POST, "/categorias").permitAll()
				// .antMatchers(HttpMethod.POST, "/videos").permitAll()
				// .antMatchers(HttpMethod.PUT, "/usuarios").permitAll()
				// .antMatchers(HttpMethod.DELETE, "/usuarios").permitAll()
				.antMatchers(HttpMethod.POST, "/auth").permitAll().antMatchers(HttpMethod.POST, "/usuarios").permitAll()
				.antMatchers(HttpMethod.GET, "/usuarios").permitAll().anyRequest().authenticated().and().cors().and()
				.httpBasic().and().csrf().disable().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.addFilterBefore(new AutenticacaoViaTokenFilter(tokenService, usuarioRepository),
						UsernamePasswordAuthenticationFilter.class);

	}

	// configuracoes de recursos estaticos(js, css e imagens)
	@Override
	public void configure(WebSecurity web) throws Exception {

	}

}