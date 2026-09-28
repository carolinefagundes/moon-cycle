package br.com.mooncycle.controller;

import br.com.mooncycle.dto.LoginRequest;
import br.com.mooncycle.dto.LoginResponseDTO;
import br.com.mooncycle.dto.UserDTO;
import br.com.mooncycle.dto.UserResponseDTO;
import br.com.mooncycle.entity.User;
import br.com.mooncycle.repository.UserRepository;
import br.com.mooncycle.security.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid UserDTO request) { // Adicionado @Valid para ativar as validações do DTO
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("E-mail já cadastrado");
        }

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .birthDate(request.getBirthDate())
                .birthTime(request.getBirthTime())
                .stillMenstruates(request.getStillMenstruates())
                .build();

        userRepository.save(user);
        return ResponseEntity.ok("Usuário criado com sucesso");
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequest request) {
        // 1. Autentica o usuário com email e senha
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        // 2. Recupera os detalhes do principal com segurança
        org.springframework.security.core.userdetails.UserDetails userDetails =
                (org.springframework.security.core.userdetails.UserDetails) auth.getPrincipal();

        // 3. Busca o seu modelo "User" real do banco de dados pelo e-mail
        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        // 4. Gera o token JWT utilizando o userDetails
        String token = jwtUtil.generateToken(userDetails);

        // 5. Mapeia todos os atributos para o DTO de resposta (sem a senha)
        UserResponseDTO userDto = UserResponseDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .birthDate(user.getBirthDate())
                .birthTime(user.getBirthTime())
                .stillMenstruates(user.getStillMenstruates())
                .build();

        // 6. Retorna o token e os dados do usuário para o Flutter
        return ResponseEntity.ok(new LoginResponseDTO(token, userDto));
    }
}