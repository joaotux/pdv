package net.originmobi.pdv.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private LoginUserDatailsService loginUserDetailsService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
			.authorizeRequests()
			.antMatchers("/resources/**", "/css/**", "/js/**", "/fonts/**", "/webjars/**").permitAll()
			
			.antMatchers("/").hasRole("ENTRAR_NO_SISTEMA")
			.antMatchers("/pessoa").hasRole("VISUALIZAR_PESSOA")
			.antMatchers("/pessoa/form").hasRole("EDITAR_PESSOA")
			.antMatchers("/fornecedor").hasRole("VISUALIZAR_FORNECEDOR")
			.antMatchers("/fornecedor/form").hasRole("EDITAR_FORNECEDOR")
			.antMatchers("/grupo").hasRole("VISUALIZAR_GRUPO")
			.antMatchers("/grupo/form").hasRole("EDITAR_GRUPO")
			.antMatchers("/categoria").hasRole("VISUALIZAR_CATEGORIA")
			.antMatchers("/categoria/form").hasRole("EDITAR_CATEGORIA")
			.antMatchers("/produto").hasRole("VISUALIZAR_PRODUTO")
			.antMatchers("/produto/form").hasRole("EDITAR_PRODUTO")
			.antMatchers("/usuario").hasRole("VISUALIZAR_USUARIO")
			.antMatchers("/usuario/form").hasRole("EDITAR_USUARIO")
			
			.antMatchers("/venda/status/ABERTA").hasRole("VISUALIZAR_PEDIDO_ABERTO")
			.antMatchers("/venda/status/FECHADA").hasRole("VISUALIZAR_PEDIDO_FECHADO")
			.antMatchers("/venda/form").hasRole("ABRIR_PEDIDO")
			.antMatchers("/venda/fechar/").hasRole("GERAR_VENDA")
			.antMatchers("/venda/addproduto/").hasRole("INSERIR_PRODUTO_VENDA")
			.antMatchers("/venda/removeproduto/").hasRole("REMOVER_PRODUTO_VENDA")
			.antMatchers("/caixa").hasRole("LISTAR_CAIXA")
			.antMatchers("/caixa/gerenciar/").hasRole("ACESSAR_CAIXA")
			.antMatchers("/caixa/lancamento/suprimento").hasRole("CAIXA_SUPRIMENTO")
			.antMatchers("/caixa/lancamento/sangria").hasRole("CAIXA_SANGRIA")
			.antMatchers("/transferencia").hasRole("CAIXA_TRANSFERENCIA")
			.antMatchers("/caixa/fechar").hasRole("FECHAR_CAIXA")
			.antMatchers("/receber").hasRole("VISUALIZAR_RECEBER")
			.antMatchers("/recebimento/").hasRole("REALIZAR_RECEBIMENTO")
			.antMatchers("/pagar").hasRole("VISUALIZAR_DESPESAS")
			.antMatchers("/pagar/quitar").hasRole("PAGAR_DESPESA")
			.antMatchers("/pagamentotipo").hasRole("VISUALIZAR_FORMA_PAGAMENTO")
			.antMatchers("/pagamentotipo/form").hasRole("CADASTRAR_FORMA_PAGAMENTO")
			.antMatchers("/tributacao").hasAnyRole("LISTA_TRIBUTAÇÃO", "CADASTRA_TRIBUTACAO")
			.antMatchers("/regras").hasAnyRole(
					"CADATRAR_REGRA_TRIBUTACAO", "EXCLUIR_REGRA_TRIBUTACAO", "EDITAR_REGRA_TRIBUTACAO")
			.antMatchers("/notafiscal").hasRole("VISUALIZA_NOTAFISCAL")
			.antMatchers("/empresa").hasRole("EDITAR_PARAMETROS")
			.antMatchers("/banco").hasRole("LISTAR_BANCO")
			.antMatchers("/maquinacartao").hasRole("EDITAR_CARTAO")
			.antMatchers("/titulos").hasRole("EDITAR_TITULO") 
			.antMatchers("/cartaolancamentos").hasAnyRole(
					"GERENCIAR_CARTOES", "ANTECIPAR_CARTAO", "PROCESSAR_CARTAO")
			
			.antMatchers("/ajustes").hasRole("LISTA_AJUSTE")
			.antMatchers("/ajustes/cancelar/").hasRole("FAZ_AJUSTE")
			.antMatchers("/ajustes/processar").hasRole("FAZ_AJUSTE")
			
			.anyRequest().authenticated()
			.and()
			.sessionManagement().invalidSessionUrl("/login")
			.and()
			.formLogin().loginPage("/login").permitAll()
			.and()
			.logout().permitAll();
		
		http.sessionManagement().maximumSessions(1).expiredUrl("/login?expired");
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(loginUserDetailsService)
			.passwordEncoder(new BCryptPasswordEncoder());
	}

}
