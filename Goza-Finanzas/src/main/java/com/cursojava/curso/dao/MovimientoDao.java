package com.cursojava.curso.dao;

import java.util.List;

import com.cursojava.curso.models.Movimiento;

//Es para que entre el repositorio de Spring
public interface MovimientoDao {
    void registrar(Movimiento movimiento);

    Integer getIdEmail(String email);

    String getEmailEmail(String email);

    public List<Movimiento> getMovimientos(Integer id);

    void actualizar(Movimiento movimiento);

    void eliminar(Integer id_movimiento);
}
