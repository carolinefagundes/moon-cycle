# 🌙 MoonCycle

O **MoonCycle** é uma aplicação voltada para a saúde e bem-estar feminino, desenvolvida para permitir que as utilizadoras acompanhem os seus ciclos menstruais e auto-observação diária através da metodologia da **Mandala Lunar**. A plataforma possibilita o registo de emoções, sensações físicas, intensidades e a associação de cores a cada dia do ciclo.

---

## 🌺 O que é a Mandala Lunar e quais os seus Benefícios?

A **Mandala Lunar** é um diagrama circular de auto-observação que conecta a ciclicidade do corpo feminino com as fases da lua. Em vez de uma abordagem apenas cronológica e linear, a mandala permite mapear o ciclo em quatro vertentes: física, emocional, mental e espiritual.

### Principais Benefícios:
- **Autoconhecimento e Reconhecimento de Padrões**: Ajuda a identificar flutuações de humor, picos de criatividade e variações de energia em cada fase do ciclo (folicular, ovulatória, lútea e menstrual).
- **Aceitação dos Ritmos Naturais**: Desmistifica as mudanças de disposição ao longo do mês, promovendo o respeito pelos ritmos do próprio corpo.
- **Gestão Emocional**: O registo diário através de cores e intensidades atua como uma ferramenta de reflexão e *mindfulness*.
- **Rastreio Físico**: Facilita a previsão e o planeamento da rotina com base em sintomas físicos (como cólicas, acne, retenção de líquidos e variações de libido).
- **Conexão com as Fases Lunares**: Observação da relação entre a lua interna (fases do ciclo) e a lua externa (astronómica).

---

## 🛠️ Tecnologias Utilizadas

### **Backend**
- **Java 17** & **Spring Boot 3**
- **Spring Data JPA** & **Hibernate**
- **Spring Validation** (validação com `@Valid`)
- **PostgreSQL** (Banco de dados relacional)
- **Lombok**

### **Frontend**
- **Flutter** (Dart)
- **easy_localization** (Internacionalização/i18n)
- **http** (Comunicação REST)

---

## 📐 Arquitetura e Modelagem de Dados

- **User (1:N) DailyLog**: Um utilizador possui múltiplos registos diários.
- **Unique Constraint (`user_id`, `log_date`)**: Impede registos duplicados na mesma data para o mesmo utilizador (lógica de atualização/upsert).
- **`DailyLogEmotionColor` (`@Embeddable`)**: Tabela embutida (`daily_log_emotion_colors`) contendo o nome da emoção (`emotionName`), a cor em formato Hexadecimal ou nome (`colorCode`) e a intensidade (`LOW`, `MEDIUM`, `HIGH`).

---

## 🚀 Como Executar o Projeto

### **Pré-requisitos**
- JDK 17+
- Maven 3.8+
- PostgreSQL
- Flutter SDK 3.x+

---

### 1️⃣ Configuração do Backend (Spring Boot)

1. Navegue até a pasta do backend:
   ```bash
   cd backend