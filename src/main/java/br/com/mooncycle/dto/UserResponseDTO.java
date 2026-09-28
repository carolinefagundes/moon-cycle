package br.com.mooncycle.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Builder
@AllArgsConstructor
public class UserResponseDTO {
    private String id;
    private String name;
    private String email;
    private LocalDate birthDate;
    private LocalTime birthTime;
    private Boolean stillMenstruates;
}