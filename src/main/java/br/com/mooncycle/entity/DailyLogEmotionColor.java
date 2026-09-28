package br.com.mooncycle.entity;

import br.com.mooncycle.enums.Intensity;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.awt.*;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DailyLogEmotionColor {

    @Column(name = "emotion_name", nullable = false)
    private String emotionName; // Ex: "Alegria", "Ansiedade", "Nostalgia"

    @Column(name = "color_code", nullable = false)
    private Color colorCode;

    @Enumerated(EnumType.STRING)
    @Column(name = "intensity", nullable = false)
    private Intensity intensity; // LOW, MEDIUM, HIGH

}