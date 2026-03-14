package com.example.demo.services;

import java.util.List;
import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.UsuarioDto;
import com.example.demo.dto.UsuarioRegistroDto;
import com.example.demo.mapper.UsuarioMapper;
import com.example.demo.models.Usuario;
import com.example.demo.models.UsuarioAuth;
import com.example.demo.repositories.UsuarioAuthRepository;
import com.example.demo.repositories.UsuarioRepository;

@Service
public class UsuarioServiceImple implements UsuarioService {

    private final UsuarioRepository userRepo;

    private final UsuarioAuthRepository authRepo;

    private final UsuarioMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    public UsuarioServiceImple(UsuarioRepository userRepo, UsuarioAuthRepository authRepo, UsuarioMapper userMapper,
            PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.authRepo = authRepo;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    // Crear un nuevo usuario
    @Override
    public UsuarioDto create(UsuarioDto usuarioDto) {
        Usuario usuario = userMapper.toUsuario(usuarioDto);
        return userMapper.toDto(userRepo.save(usuario));
    }

    // Listar todos los usuarios
    @Override
    public List<UsuarioDto> ListUsuarios() {
        return userMapper.toDtoList(userRepo.findAll());
    }

    // Actualizar un usuario existente
    @Override
    public UsuarioDto update(String id, UsuarioDto usuarioDto) {
        Usuario usuario = userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + id));
        userMapper.updateUsuario(usuarioDto, usuario);
        return userMapper.toDto(userRepo.save(usuario));
    }

    // Eliminar un usuario por su ID
    @Override
    public void delete(String id) {
        if (!userRepo.existsById(id)) {
            throw new RuntimeException("Usuario no encontrado con id: " + id);
        }
        userRepo.deleteById(id);
    }

    // Buscar un usuario por su número de documento
    @Override
    public UsuarioDto UsuarioByDocNum(String docnum) {
        Usuario usuario = userRepo.findByDocNum(docnum)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con número de documento: " + docnum));
        return userMapper.toDto(usuario);
    }

    // Buscar un usuario por su número de documento (método alternativo) para menjo
    // de excepciones
    @Override
    public UsuarioDto UsuarioByDocum(String docnum) {
        return userRepo.findByDocNum(docnum)
                .map(userMapper::toDto)
                .orElseThrow(() -> new RuntimeException(
                        "Usuario no encontrado con  el documento: " + docnum));
    }

    @Transactional
    @Override
    public UsuarioRegistroDto registrarUsuario(UsuarioRegistroDto dto) {
        // 1. Guardar perfil
        Usuario perfil = Usuario.builder()
                .id(UUID.randomUUID().toString()) // mismo ID para perfil y auth
                .nom(dto.getNombre())
                .ape(dto.getApellido())
                .doc(dto.getDocumento())
                .dir(dto.getDireccion())
                .email(dto.getEmail())
                .build();

        Usuario perfilGuardado = userRepo.save(perfil);

        // 2. Guardar auth
        UsuarioAuth auth = new UsuarioAuth();
        auth.setId(perfilGuardado.getId()); // mismo ID
        auth.setUser(dto.getUsuario());
        auth.setPass(passwordEncoder.encode(dto.getPassword())); // encriptar
        auth.setRoles(dto.getRoles());

        authRepo.save(auth);

        return dto;
    }

}
