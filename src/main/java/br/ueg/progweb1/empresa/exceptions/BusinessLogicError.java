package br.ueg.progweb1.empresa.exceptions;

import lombok.Getter;

@Getter
public enum BusinessLogicError {
    GERAL(0L, "Erro desconhecido"),

    REGISTRO_DUPLICADO(1L, "Registro duplicado"),
    CAMPO_VAZIO(2L, "Parametro vazio"),
    ERROR_ID(3L, "Id não encontrado"),
    CNPJ_INVALIDO(4L, "CNPJ inválido, o cnpj deve ter 14 digitos"),;

    private Long id;
    private String message;


    BusinessLogicError(Long id, String nomeFantasia) {
        this.id = id;
        this.message = nomeFantasia;
    }
}
