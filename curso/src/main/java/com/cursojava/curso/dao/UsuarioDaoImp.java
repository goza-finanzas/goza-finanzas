package com.cursojava.curso.dao;

import com.cursojava.curso.models.Usuario;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

//Repositorio indica que va a acceder al repositorio de la base de datos
@Repository

//Transactional es para tratar las consultas a la base de datos.
@Transactional
public class UsuarioDaoImp implements UsuarioDao{

    //Nos habilita por así decirlo el EntityManager en el contexto para
    //usarlo de forma automatica.
    @PersistenceContext
    //Nos va a servir para hacer la conexión con la base de datos
    EntityManager entityManager;

    //Significa que se esta sobrescribiendo
    @Override
    public List<Usuario> getUsuarios() {
        //Consultas sobre hibernate
        String query = "FROM Usuario";
        //Vamos a pasarle al entitiy la consulta y vamos a retornar una lista
        return entityManager.createQuery(query).getResultList();
        //para identificar la tabla de la base de datos se hace mediante la entidad a clases
    }

    @Override
    public void eliminar(Integer id) {
        //Vamos a recibir el id, mediante ese ID el entity manager tiene
        //un metodo para buscar por id, luego nos retorna el usuario
        //luego con el metodo de borrar borramos dicho usuario
        Usuario usuario = entityManager.find(Usuario.class, id);
        entityManager.remove(usuario);
    }

    @Override
    public void registrar(Usuario usuario) {
        // Con merge unimos el usuario que tenemos a la base de datos
        // el merge
        entityManager.merge(usuario);
    }

    @Override
    public Usuario obtenerUsuarioPorCredenciales(Usuario usuario) {
        //con :email y : password evitamos la inyección SQL
        String query = "FROM Usuario WHERE email = :email";
        //Creamos una lista porque esto nos devuelve el objeto en forma de lista
        //por lo tanto debemos guardarlo en otra lista
        List<Usuario> lista = entityManager.createQuery(query).setParameter("email", usuario.getEmail()).getResultList();

        //Para evitar un error que es en caso tal que la lista siga vacia y trate de obtener la
        //propiedad password, verificamos si la lista esta vacia y si es así returna falso de inmediato
        // e ignora el resto de líneas de código

        if (lista.isEmpty()) {
            return null;
        }

        String passwordHashed = lista.get(0).getPassword();

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        if (argon2.verify(passwordHashed, usuario.getPassword().toCharArray())) {
            return lista.get(0);
        }
        return null;



    }
}
