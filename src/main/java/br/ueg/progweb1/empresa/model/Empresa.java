package br.ueg.progweb1.empresa.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name= "Dados_Empresa")
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID", nullable = false)
    private Long id;

    @Column(name = "Nome_Fantasia", nullable = false, length = 150)
    private String nomeFantasia;

    @Column(name = "Logradouro", nullable = false, length = 200)
    private String logradouro;

    @Column(name = "CEP", nullable = false, length = 20)
    private String cep;


    @Column(name = "Data_Fundacao", nullable = false, length = 20)
    private LocalDate dataFundacao;

}

