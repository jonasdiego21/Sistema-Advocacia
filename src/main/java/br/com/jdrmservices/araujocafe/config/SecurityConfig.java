package br.com.jdrmservices.araujocafe.config;
	
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
	
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		
		web.ignoring()
			.antMatchers("/css/**")
			.antMatchers("/js/**")
			.antMatchers("/uikit-2.27.4/**")
			.antMatchers("/images/**");
		
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {	
		http
			.authorizeRequests()
				.antMatchers("/processos").hasRole("VISUALIZACAO_PROCESSO")
				.antMatchers("/contasPagar").hasRole("VISUALIZACAO_CONTA_PAGAR")
				.antMatchers("/contasPagarLancamento").hasRole("VISUALIZACAO_CONTA_PAGAR")
				.antMatchers("/processos").hasRole("VISUALIZACAO_PROCESSO")
				.antMatchers("/clientes").hasRole("VISUALIZACAO_CLIENTE")
				.antMatchers("/processos/**").hasAnyRole("ADMINISTRADOR", "CADASTRO_PROCESSO", "EDICAO_PROCESSO", "EXCLUSAO_PROCESSO")
				.antMatchers("/contasReceber/**").hasAnyRole("ADMINISTRADOR", "CADASTRO_CONTA_RECEBER", "EDICAO_CONTA_RECEBER", "EXCLUSAO_CONTA_RECEBER")
				.antMatchers("/contasReceberLancamento/**").hasAnyRole("ADMINISTRADOR", "CADASTRO_CONTA_RECEBER", "EDICAO_CONTA_RECEBER", "EXCLUSAO_CONTA_RECEBER")
				.antMatchers("/processos/**").hasAnyRole("ADMINISTRADOR", "CADASTRO_PROCESSO", "EDICAO_PROCESSO", "EXCLUSAO_PROCESSO")
				.antMatchers("/clientes/**").hasAnyRole("ADMINISTRADOR", "CADASTRO_CLIENTE", "EDICAO_CLIENTE", "EXCLUSAO_CLIENTE")
				.antMatchers("/usuarios/**").hasAnyRole("ADMINISTRADOR")
				.antMatchers("/arquivos/**").hasAnyRole("ADMINISTRADOR")
				.anyRequest().authenticated()
			.and()
				.formLogin()
				.loginPage("/login")
				.permitAll()
			.and()
				.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.and()
				.sessionManagement().maximumSessions(1)
				.expiredUrl("/login");		
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}	
}