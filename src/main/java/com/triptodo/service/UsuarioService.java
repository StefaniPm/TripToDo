package com.triptodo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.triptodo.dto.UsuarioRequestDTO;
import com.triptodo.dto.UsuarioResponseDTO;
import com.triptodo.exception.BusinessException;
import com.triptodo.exception.ResourceNotFoundException;
import com.triptodo.model.Usuario;
import com.triptodo.repository.UsuarioRepository;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public UsuarioResponseDTO criar(UsuarioRequestDTO dto) {
        validarEmailUnico(dto.getEmail(), null);

        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(dto.getSenha());

        return new UsuarioResponseDTO(usuarioRepository.save(usuario));
    }

    @Transactional(readOnly = true)
    public List<UsuarioResponseDTO> listarTodos() {
        return usuarioRepository.findAll()
                .stream()
                .map(UsuarioResponseDTO::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public UsuarioResponseDTO buscarPorId(Long id) {
        return new UsuarioResponseDTO(buscarUsuarioExistente(id));
    }

    @Transactional
    public UsuarioResponseDTO atualizar(Long id, UsuarioRequestDTO dto) {
        Usuario usuario = buscarUsuarioExistente(id);
        validarEmailUnico(dto.getEmail(), id);

        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(dto.getSenha());

        return new UsuarioResponseDTO(usuarioRepository.save(usuario));
    }

    @Transactional
    public void deletar(Long id) {
        usuarioRepository.delete(buscarUsuarioExistente(id));
    }

    private Usuario buscarUsuarioExistente(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com ID: " + id));
    }

    private void validarEmailUnico(String email, Long idAtual) {
        usuarioRepository.findByEmail(email).ifPresent(usuario -> {
            if (idAtual == null || !usuario.getId().equals(idAtual)) {
                throw new BusinessException("Já existe um usuário cadastrado com este e-mail.");
            }
        });
    }

    @Transactional(readOnly = true)
    public UsuarioResponseDTO login(String email, String senha) {

        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new BusinessException("Email ou senha inválidos."));

        if (!usuario.getSenha().equals(senha)) {
            throw new BusinessException("Email ou senha inválidos.");
        }

        return new UsuarioResponseDTO(usuario);
    }
}