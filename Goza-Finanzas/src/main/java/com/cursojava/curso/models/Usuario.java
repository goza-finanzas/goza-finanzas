package com.cursojava.curso.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

//Entidad que hará referencia a la base de datos
@Entity
@Table(name="usuarios")

@ToString @EqualsAndHashCode
public class Usuario  {

    //Ahora bien con la libreria lombock solo con anotaciones nos crea automaticamente
    // los getters y setters
    //Ahora bien HIBERNATE NO SE DA CUENTA QUE COLUMNA DE LA TABAL USAR AUTOMATICAMENTE
    // SE LA DEBEMOS ESPECIFICAR para evitar errores aunque si tienen el mismo nombre no pasa nada.


    //debemos de indicar que será autoincrementable
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter @Column(name = "id")
    @Id
    private Integer id;

    @Getter @Setter @Column(name = "nombre")
    private String nombre;

    @Getter @Setter @Column(name = "apellido")
    private String apellido;

    @Getter @Setter @Column(name = "email")
    private String email;

    @Getter @Setter @Column(name = "telefono")
    private String telefono;

    @Getter @Setter @Column(name = "password")
    private String password;

    //FetType.LAZY es para indicar una consulta perezosa, solo utilizaremos los recursos
    //cuando se haga un fetch directamente y no cada vez que se consulte.

}

