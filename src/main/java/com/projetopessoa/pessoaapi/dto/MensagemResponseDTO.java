package com.projetopessoa.pessoaapi.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MensagemResponseDTO {

    private String mensagem;
}
