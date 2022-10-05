package com.cursojava.curso.dao;
import com.cursojava.curso.models.Usuario;
import java.util.List;

//Es para que entre el repositorio de Spring
public interface UsuarioDao {
    List<Usuario>  getUsuarios();

    void eliminar(Integer id);

    void registrar(Usuario usuario);

    Usuario obtenerUsuarioPorCredenciales(Usuario usuario);
}
