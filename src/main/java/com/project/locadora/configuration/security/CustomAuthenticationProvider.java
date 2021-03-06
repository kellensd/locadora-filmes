package com.project.locadora.configuration.security;

import com.project.locadora.business.Criptografia;
import com.project.locadora.model.Usuario;
import com.project.locadora.repository.UsuarioRepository;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Objects;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private UsuarioRepository usuarioRepository;

    public CustomAuthenticationProvider(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Authentication authenticate(final Authentication authentication) throws AuthenticationException {
        final UsernamePasswordAuthenticationToken autenticacao = (UsernamePasswordAuthenticationToken) authentication;
        final String name = (String) authentication.getPrincipal();

        final String password = (String) autenticacao.getCredentials();

        String encodedPassword = Criptografia.getHashMd5(password);

        final String storedPassword = usuarioRepository.findByNomeIgnoreCase(name).map(Usuario::getSenha)
                .orElseThrow(() -> new IllegalArgumentException("illegal id or password"));

        if (Objects.equals(encodedPassword, "") || !Objects.equals(encodedPassword, storedPassword)) {
            throw new AuthenticationServiceException("Unauthorized");
        }

        final Object principal = authentication.getPrincipal();
        final UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(
                principal, authentication.getCredentials(),
                Collections.emptyList());
        result.setDetails(authentication.getDetails());

        return result;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
