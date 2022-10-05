package com.cursojava.curso.controllers;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.cursojava.curso.dao.UsuarioDao;
import com.cursojava.curso.models.Usuario;
import com.cursojava.curso.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//Con restcontroller indicaremos que esta clase es un controlador
@RestController
public class UsuarioController {

    //hace que automaticamente se cree un objeto en UsuarioDao y lo guarde
    //en usuarioDao sin necesidad de hacer nada más

    //Si usamos este Autowired en otra parte del proyecto, se comparte en memoria
    //de esta manera no hay que andar creando conexiones a la bd como locos
    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private JWTUtil jwtUtil;

    //Vamos a indicar la url que debería ser para devolver prueba
    //Por defecto el metodo exigido es get, pero podemos determinar que metodo debe ser el que
    //se envie para que el servidor compare
    @RequestMapping(value = "api/usuarios/{id}", method = RequestMethod.GET)
    public Usuario getUsuario(@PathVariable Integer id) {
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setNombre("Lucas");
        usuario.setApellido ("Moy");
        usuario.setEmail("lucasmoy@hotmail.com");
        usuario.setTelefono("234234234");
        return usuario;
    }

    @RequestMapping(value = "api/usuarios", method = RequestMethod.GET)
    // El authorization es el token para mantenernos logeados
    public List<Usuario> getUsuarios(@RequestHeader(value="Authorization") String token) {
        //Ahora hay que verificar la validez de ese token
        //con getkey obtenemos el id con value el correo que pusimos
        if(!validarToken(token)) {
            return null;
        }

        return usuarioDao.getUsuarios();
    }

    private boolean validarToken(String token) {
        String usuarioId = jwtUtil.getKey(token);
        return usuarioId != null;
    }
    // Con ResquestBody exije los datos enviados en el cuerpo de la petición
    // eso lo pasa al construtor del usuario y luego tenemos un objeto
    // con toda esa información
    // los datos se pasan tipo JSOn
    @RequestMapping(value = "api/usuarios", method = RequestMethod.POST)
    public void registrarUsuario(@RequestBody Usuario usuario) {
        //Luego apenas tenga ese objeto con esos datos que nos enviaron
        //Lo pasamos a usuario dao el cual se encargara de registralro en la BD

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(1, 1024, 1, usuario.getPassword().toCharArray());
        usuario.setPassword(hash);

        usuarioDao.registrar(usuario);
    }

    @RequestMapping(value = "api/usuarios/{id}", method = RequestMethod.DELETE)
    public void eliminar(@RequestHeader(value="Authorization") String token, @PathVariable Integer id) {
        if(!validarToken(token)) {
            return ;
        }
        usuarioDao.eliminar(id);
    }

}
