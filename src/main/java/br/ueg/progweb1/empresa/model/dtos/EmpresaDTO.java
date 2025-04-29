package br.ueg.progweb1.empresa.model.dtos;

import lombok.*;

import java.time.LocalDate;
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EmpresaDTO {
    private String nomeFantasia;
    private String logradouro;
    private String cep;
    private LocalDate dataFundacao;
    private String CNPJ;
    private Boolean status;
}
