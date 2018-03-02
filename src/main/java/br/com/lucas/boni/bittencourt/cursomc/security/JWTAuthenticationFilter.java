package br.com.lucas.boni.bittencourt.cursomc.security;

import br.com.lucas.boni.bittencourt.cursomc.dto.CredenciaisDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Ele já entende que tem que interceptar o /login que já é recervado do spring security
 */
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter{


    private AuthenticationManager authenticationManager;

    private JWTUtil jwtUtil;

    /**
     * injeta pelo contrutor
     * @param authenticationManager
     * @param jwtUtil
     */
    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        try {
            CredenciaisDTO creds = new ObjectMapper()                             //pega os dados que vinheram da requisicao e adiciona no objeto
                    .readValue(request.getInputStream(),CredenciaisDTO.class);

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(creds.getEmail(),creds.getSenha(),new ArrayList<>());
            Authentication authentication = authenticationManager.authenticate(authenticationToken);//verifica se o ususario e senha é valido
            return authentication;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }



    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        super.successfulAuthentication(request, response, chain, authResult);

        String username = ((UserSS)authResult.getPrincipal()).getUsername();
        String token = jwtUtil.generateToken(username);
        response.addHeader("Autorization","Bearer " + token);//adiciona no cabecalho
    }
}
