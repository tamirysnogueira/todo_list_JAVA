package br.com.tamirys.todolist.filter;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.tamirys.todolist.task.ITaskRepository;
import br.com.tamirys.todolist.user.IUserRepository;
//tudo o que se tem de web do spring boot é baseado no servlet
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component // toda classe que quero que o spring gerencie, assim como o RestController
// component é a classe mais genérica que se tem do spring. Então toda vez que
// quero falar para aplicação passar por uma classe
// tem que se adicionar o component

// toda req antes de ir para a rota, passa no filtro
public class FilterTaskAuth extends OncePerRequestFilter {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private ITaskRepository taskRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        var servletPath = request.getServletPath();

        if (servletPath.startsWith("/tasks/")) {
            String authorization = request.getHeader("Authorization");

            if (authorization == null) {
                response.sendError(403, "Authorization is Mandatory");
            } else {
                // sysout é um atalho para System.out.println

                String authEncoded = authorization.substring("Basic".length()).trim();

                byte[] authDecoded = Base64.getDecoder().decode(authEncoded);

                var authString = new String(authDecoded);

                String[] credentials = authString.split(":");

                String username = credentials[0];
                String password = credentials[1];

                var user = this.userRepository.findByUsername(username);

                if (user == null) {
                    response.sendError(401, "Credenciais inválidas");
                } else {
                    var passwordVerify = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());

                    if (passwordVerify.verified) {
                        request.setAttribute("idUser", user.getId());
                        filterChain.doFilter(request, response);
                    } else {
                        response.sendError(401, "Credenciais inválidas");

                    }

                }

            }

        } else {
            filterChain.doFilter(request, response);

        }

    }

}
