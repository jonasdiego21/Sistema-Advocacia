package br.com.jdrmservices.araujocafe.controller.page;
	
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.web.util.UriComponentsBuilder;
	
public class PageWrapper<T> {
	
	private Page<T> page;
	private UriComponentsBuilder uriBuilder;
	
	public PageWrapper(Page<T> page, HttpServletRequest httpServletRequest) {
		
		this.page = page;
		
		String httpUrl = httpServletRequest.getRequestURL().append(
						httpServletRequest.getQueryString() != null ? "?" + httpServletRequest.getQueryString() : "")
						.toString().replaceAll("\\+", "%20");
		
		this.uriBuilder = UriComponentsBuilder.fromHttpUrl(httpUrl);
	}
	
	/**
	 * Verifica se tem algum elemento 
	 * na lista retornada.
	 * 
	 * @return true caso o conteúdo seja vazio
	 */
	public boolean isVazia() {
		return page.getContent().isEmpty();
	}
	
	/**
	 * Retorna um {@link List} do tipo
	 * parâmetrizado {@link T}.
	 * 
	 * @return um {@link List} de {@link T}
	 */
	public List<T> getConteudo() {
		return page.getContent();
	}
	
	/**
	 * Retorna o número da página
	 * corrente/atual.
	 * 
	 * @return o número da página atual
	 */
	public int getAtual() {
		return page.getNumber();
	}
	
	/**
	 * Verifica se está na 
	 * primeira página, caso
	 * esteja, retorna true.
	 * 
	 * @return true caso esteja na primeira página
	 */
	public boolean isPrimeira() {
		return page.isFirst();
	}
	
	/**
	 * Verifica se está na 
	 * última página, caso
	 * esteja, retorna true.
	 * 
	 * @return true caso esteja na última página
	 */
	public boolean isUltima() {
		return page.isLast();
	}
	
	/**
	 * Retorna o total de páginas.
	 * 
	 * @return o número de páginas.
	 */
	public int getTotal() {
		return page.getTotalPages();
	}
	
	public String urlParaPagina(int pagina) {
		return uriBuilder.replaceQueryParam("page", pagina).build(true).encode().toUriString();
	}
	
	public String urlOrdenada(String propriedade) {
		
		UriComponentsBuilder uriBuilderOrder = UriComponentsBuilder.
				fromUriString(uriBuilder.build(true).encode().toUriString());
		
		String valorSort = String.format("%s,%s", propriedade, inverterDirecao(propriedade));
		
		return uriBuilderOrder.replaceQueryParam("sort", valorSort).build(true).encode().toUriString();
	}
	
	public String inverterDirecao(String propriedade) {
		
		String direcao = "asc";
		
		Order order = page.getSort() != null ? page.getSort().getOrderFor(propriedade) : null;
		if (order != null) {
			direcao = Sort.Direction.ASC.equals(order.getDirection()) ? "desc" : "asc";
		}
		
		return direcao;
	}
	
	public boolean descendente(String propriedade) {
		return inverterDirecao(propriedade).equals("asc");
	}
	
	public boolean ordenada(String propriedade) {
		
		Order order = page.getSort() != null ? page.getSort().getOrderFor(propriedade) : null;
		
		if (order == null) {
			return false;
		}
		
		return page.getSort().getOrderFor(propriedade) != null ? true : false;
	}
	
}
