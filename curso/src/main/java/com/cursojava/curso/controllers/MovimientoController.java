package com.cursojava.curso.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cursojava.curso.dao.MovimientoDao;
import com.cursojava.curso.models.Movimiento;


@RestController
public class MovimientoController{

    @Autowired
    private MovimientoDao movimientoDao;


    @RequestMapping(value = "movimiento", method = RequestMethod.GET)
    public List<Movimiento> consultarMovimiento(@RequestHeader(value="Correo") String email) {
        return movimientoDao.getMovimientos(movimientoDao.getIdEmail(email));
    }

    @RequestMapping(value = "movimiento", method = RequestMethod.PUT)
    public void actualizarMovimiento(@RequestBody Movimiento movimiento) {
        movimientoDao.actualizar(movimiento);
    }

    @RequestMapping(value = "movimiento", method = RequestMethod.POST)
    public void registrarMovimiento(@RequestBody Movimiento movimiento, @RequestHeader(value="Correo") String email) {
        movimiento.setUsuarios_id(movimientoDao.getIdEmail(email));
        movimientoDao.registrar(movimiento);
    }

    @RequestMapping(value = "movimiento", method = RequestMethod.DELETE)
    public void eliminarMovimiento(@RequestBody Integer id_movimiento, @RequestHeader(value="Correo") String email) {
        movimientoDao.eliminar(id_movimiento);
    }
}
