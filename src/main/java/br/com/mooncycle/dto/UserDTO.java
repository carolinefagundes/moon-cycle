package br.com.mooncycle.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Getter
@Setter
public class UserDTO {

    @NotBlank(message = "O nome completo é obrigatório.")
    private String name;

    @NotBlank(message = "O e-mail é obrigatório.")
    @Email(message = "Insira um e-mail válido.")
    private String email;

    @NotBlank(message = "A senha é obrigatória.")
    @Size(min = 6, message = "A senha deve conter no mínimo 6 dígitos.")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&\\W]).+$",
            message = "A senha deve conter letras maiúsculas, minúsculas, números e pelo menos um caractere especial."
    )
    private String password;

    @NotNull(message = "A data de nascimento é obrigatória.")
    private LocalDate birthDate;

    // Campo opcional (pode ser nulo)
    private LocalTime birthTime;

    @NotNull(message = "A informação se ainda menstrua é obrigatória.")
    private Boolean stillMenstruates;
}