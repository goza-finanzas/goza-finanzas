package com.cursojava.curso.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.cursojava.curso.models.Movimiento;
import com.cursojava.curso.models.Usuario;

@Repository

@Transactional
public class MovimientoDaoImp implements MovimientoDao{

    @PersistenceContext
    //Nos va a servir para hacer la conexión con la base de datos
    EntityManager entityManager;

    @Override
    public void registrar(Movimiento movimiento) {
        entityManager.merge(movimiento);
    }

    @Override
    public void actualizar(Movimiento movimiento) {
        entityManager.merge(movimiento);
    }

    @Override
    public Integer getIdEmail(String email){
        String query = "FROM Usuario WHERE email = :email";
        //Creamos una lista porque esto nos devuelve el objeto en forma de lista
        //por lo tanto debemos guardarlo en otra lista
        List<Usuario> lista = entityManager.createQuery(query).setParameter("email", email).getResultList();
        return lista.get(0).getId();
    }

    @Override
    public String getEmailEmail(String email) {
        String query = "FROM Usuario WHERE email = :email";
        //Creamos una lista porque esto nos devuelve el objeto en forma de lista
        //por lo tanto debemos guardarlo en otra lista
        List<Usuario> lista = entityManager.createQuery(query).setParameter("email", email).getResultList();
        return lista.get(0).getEmail();
    }
    
    @Override
    public List<Movimiento> getMovimientos(Integer id) {
        String query = "FROM Movimiento WHERE usuarios_id = :id";
        List<Movimiento> lista = entityManager.createQuery(query).setParameter("id", id).getResultList();
        return lista;
    }

    @Override
    public void eliminar(Integer id_movimiento) {
        Movimiento movimiento = entityManager.find(Movimiento.class, id_movimiento);
        entityManager.remove(movimiento);
    }
}
