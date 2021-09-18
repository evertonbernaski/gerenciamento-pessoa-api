package com.projetopessoa.pessoaapi.service;

import com.projetopessoa.pessoaapi.dto.MensagemResponseDTO;
import com.projetopessoa.pessoaapi.dto.PessoaDTO;
import com.projetopessoa.pessoaapi.entity.Pessoa;
import com.projetopessoa.pessoaapi.exception.PessoaNotFoundException;
import com.projetopessoa.pessoaapi.mapper.PessoaMapper;
import com.projetopessoa.pessoaapi.repository.PessoaRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PessoaService {

    private PessoaRepository pessoaRepository;

    private final PessoaMapper pessoaMapper = PessoaMapper.INSTANCE;

    public MensagemResponseDTO criarPessoa(PessoaDTO pessoaDTO) {
        Pessoa pessoaToSave = pessoaMapper.toModel(pessoaDTO);

        Pessoa savedPessoa = pessoaRepository.save(pessoaToSave);
        return criarMensagemResponse(savedPessoa.getId(), "Pessoa criada com o ID ");
    }

    public List<PessoaDTO> listAll() {
        List<Pessoa> todasPessoa = pessoaRepository.findAll();
        return todasPessoa.stream().map(pessoaMapper::toDTO).collect(Collectors.toList());
    }

    public PessoaDTO findById(Long id) throws PessoaNotFoundException {
        Pessoa pessoa = verifyIfExists(id);

        return pessoaMapper.toDTO(pessoa);
    }

    public void deletar(Long id) throws PessoaNotFoundException {
        verifyIfExists(id);
        pessoaRepository.deleteById(id);
    }

    public MensagemResponseDTO atualizarById(Long id, PessoaDTO pessoaDTO) throws PessoaNotFoundException {
        verifyIfExists(id);

        Pessoa pessoaToUpdate = pessoaMapper.toModel(pessoaDTO);

        Pessoa atualizarPessoa = pessoaRepository.save(pessoaToUpdate);
        return criarMensagemResponse(atualizarPessoa.getId(), "Pessoa atualizada com o ID ");
    }

    private Pessoa verifyIfExists(Long id) throws PessoaNotFoundException {
        return pessoaRepository.findById(id)
                .orElseThrow(() -> new PessoaNotFoundException(id));
    }

    private MensagemResponseDTO criarMensagemResponse(Long id, String menssagem) {
        return MensagemResponseDTO
                .builder()
                .mensagem(menssagem + id)
                .build();
    }

}
