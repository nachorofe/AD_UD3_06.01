package org.dam;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Entrenador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEntrenador;
    private String nombre;
    private LocalDate fechaNacimiento;
    private float salario;
    @OneToOne(mappedBy = "entrenador")
    private Equipo equipo;
}
