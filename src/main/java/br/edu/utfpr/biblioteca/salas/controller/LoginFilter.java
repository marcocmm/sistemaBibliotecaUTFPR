package br.edu.utfpr.biblioteca.salas.controller;

/**
 *
 * @author Leonardo Baiser <lpbaiser@gmail.com>
 */
import br.edu.utfpr.biblioteca.salas.model.entity.UsuarioPO;
import java.io.IOException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter {

    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }

    /**
     * Filtro de paginas, se um usuario não estiver logado, ele não tem
     * permisssão para acessar algumas páginas A restrição de páginas dever ser
     * feita no web.xml, através da tag '<filter-mapping>'.
     *
     * @param request
     * @param response
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        UsuarioPO usuario = null;
        HttpSession sess = ((HttpServletRequest) request).getSession(false);

        if (sess != null) {
            usuario = (UsuarioPO) sess.getAttribute("usuarioLogado");
        }

        if (usuario == null) {
            String contextPath = ((HttpServletRequest) request).getContextPath();
            
            ((HttpServletResponse) response).sendRedirect(contextPath + "/faces/index.xhtml");
        } else {
            chain.doFilter(request, response);
        }

    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        // TODO Auto-generated method stub

    }

}
