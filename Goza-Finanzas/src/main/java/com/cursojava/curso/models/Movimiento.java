package com.cursojava.curso.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

//Entidad que hará referencia a la base de datos
@Entity
@Table(name="movimientos")

@ToString @EqualsAndHashCode
public class Movimiento  {

    //Ahora bien con la libreria lombock solo con anotaciones nos crea automaticamente
    // los getters y setters
    //Ahora bien HIBERNATE NO SE DA CUENTA QUE COLUMNA DE LA TABAL USAR AUTOMATICAMENTE
    // SE LA DEBEMOS ESPECIFICAR para evitar errores aunque si tienen el mismo nombre no pasa nada.


    //debemos de indicar que será autoincrementable
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter @Column(name = "id")
    @Id
    private Integer id;

    @Getter @Setter @Column(name = "usuarios_id")
    private Integer usuarios_id;

    @Getter @Setter @Column(name = "nombre")
    private String nombre;

    @Getter @Setter @Column(name = "fecha")
    private String fecha;

    @Getter @Setter @Column(name = "monto")
    private Double monto;

    @Getter @Setter @Column(name = "categoria")
    private String categoria;

    @Getter @Setter @Column(name = "descripcion")
    private String descripcion;

}

