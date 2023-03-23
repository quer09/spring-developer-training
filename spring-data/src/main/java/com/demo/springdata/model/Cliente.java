package com.demo.springdata.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "cliente")
@Setter
@Getter
public class Cliente {
    @Id // It's a unique property
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "nombre") // You can add a different name
    @NotNull(message = "Name cannot be null")
    private String nombre;

    @Column(length = 30)
    private String apellidos;
    @Column(columnDefinition = "varchar(12)") // You can add constrains
    private String cedula;
    private String telefono;

    private String paisNacimiento;
    @OneToMany(mappedBy = "cliente")
    private List<Direccion> direcciones;

    @OneToMany(mappedBy = "cliente")
    private List<Cuenta> cuentas;

    @OneToMany(mappedBy = "cliente")
    private List<Cuenta> tarjetas;

   // @OneToMany(mappedBy = "cliente")
   // private List<Producto> productos;

}
