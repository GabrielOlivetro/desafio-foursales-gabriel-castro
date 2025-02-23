package com.gaba.games.service;

import com.gaba.games.model.Role;
import com.gaba.games.model.Usuario;
import com.gaba.games.repository.UsuarioRepository;
import com.gaba.games.security.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AutenticacaoService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private AuthenticationManager authenticationManager;

    public String registrar(String username, String password, Role role) {
        Usuario usuario = new Usuario();
        usuario.setUsername(username);
        usuario.setPassword(passwordEncoder.encode(password));
        usuario.setRole(role);
        usuarioRepository.save(usuario);

        return tokenService.generateToken(usuario);
    }

    public String autenticar(String username, String password) {
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        return tokenService.generateToken(usuario);
    }
}
