package com.example.demo.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.demo.dto.UsuarioDto;
import com.example.demo.models.Usuario;

@Component
public class UsuarioMapperImpl implements UsuarioMapper {

    @Override
    public Usuario toUsuario(UsuarioDto usuarioDto) {
        if (usuarioDto == null)
            return null;
        return Usuario.builder()
                .id(usuarioDto.getId())
                .nom(usuarioDto.getNombre())
                .ape(usuarioDto.getApellido())
                .email(usuarioDto.getEmail())
                .doc(usuarioDto.getDocumento())
                .dir(usuarioDto.getDireccion())
                .build();
        /**
         * Convierte un DTO en una entidad Usuario.
         * Usamos Builder para inicializar el objeto de forma clara y segura.
         */
    }

    @Override
    public UsuarioDto toDto(Usuario usuario) {
        if (usuario == null)
            return null;
        return UsuarioDto.builder()
                .id(usuario.getId())
                .nombre(usuario.getNom())
                .apellido(usuario.getApe())
                .email(usuario.getEmail())
                .documento(usuario.getDoc())
                .direccion(usuario.getDir())
                .telefono(usuario.getTel())
                .build();
        /**
         * Convierte una entidad Usuario en un DTO.
         * Builder permite mapear campo por campo sin depender del orden del
         * constructor.
         */

    }

    @Override
    public List<UsuarioDto> toDtoList(List<Usuario> usuarios) {
        if (usuarios == null)
            return null;
        return usuarios.stream()
                .map(this::toDto)
                .toList();
        /**
         * Convierte una lista de entidades Usuario en una lista de DTOs.
         * Usamos stream + map para aplicar el método toUsuarioDto a cada elemento.
         */
    }

    @Override
    public void updateUsuario(UsuarioDto usuarioDto, Usuario usuario) {
        if (usuario == null) {
            throw new IllegalArgumentException("La entidad Usuario no puede ser nula");
        }
        if (usuarioDto == null) {
            throw new IllegalArgumentException("El DTO UsuarioPerfilDTO no puede ser nulo");
        }
        // Si ambos son válidos, actualizamos los campos de la entidad con los datos del
        // DTO
        usuario.setNom(usuarioDto.getNombre());
        usuario.setApe(usuarioDto.getApellido());
        usuario.setEmail(usuarioDto.getEmail());
        usuario.setDoc(usuarioDto.getDocumento());
        usuario.setDir(usuarioDto.getDireccion());
        usuario.setTel(usuarioDto.getTelefono());
        /**
         * Actualiza una entidad Usuario existente con los datos de un DTO.
         * Aquí no usamos Builder porque ya tenemos el objeto creado,
         * simplemente actualizamos sus campos con setters.
         */
    }

}
