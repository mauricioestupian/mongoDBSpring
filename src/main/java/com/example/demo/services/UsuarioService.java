package com.example.demo.services;

import java.util.List;

import com.example.demo.dto.UsuarioDto;
import com.example.demo.dto.UsuarioRegistroDto;

public interface UsuarioService {

    UsuarioDto create(UsuarioDto usuarioDto);

    List<UsuarioDto> ListUsuarios();

    UsuarioDto update(String id, UsuarioDto usuarioDto);

    void delete(String id);

    UsuarioDto UsuarioByDocNum(String docnum);

    UsuarioDto UsuarioByDocum(String docnum);

    UsuarioRegistroDto registrarUsuario(UsuarioRegistroDto dto);

    UsuarioDto UsuarioById(String id);

}
