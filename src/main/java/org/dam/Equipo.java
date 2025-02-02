package org.dam;

import jakarta.persistence.*;

@Entity
public class Equipo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEquipo;
    private String nombre;
    private String ciudad;
    @Enumerated(EnumType.STRING)
    private Conferencia conferencia;
    @Enumerated(EnumType.STRING)
    private Division division;
    private String nombreCompleto;
    private String abreviatura;

    @OneToOne
    @JoinColumn(name="idEntrenador")
    private Entrenador entrenador;


    public Equipo() {
    }

    public Equipo(String nombre, String ciudad, Conferencia conferencia, Division division, String nombreCompleto, String abreviatura) {
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.conferencia = conferencia;
        this.division = division;
        this.nombreCompleto = nombreCompleto;
        this.abreviatura = abreviatura;
    }

    public Long getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(Long idEquipo) {
        this.idEquipo = idEquipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public Conferencia getConferencia() {
        return conferencia;
    }

    public void setConferencia(Conferencia conferencia) {
        this.conferencia = conferencia;
    }

    public Division getDivision() {
        return division;
    }

    public void setDivision(Division division) {
        this.division = division;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    public Entrenador getEntrenador() {
        return entrenador;
    }

    public void setEntrenador(Entrenador entrenador) {
        this.entrenador = entrenador;
    }

    @Override
    public String toString() {
        return "Equipo{" +
                "idEquipo=" + idEquipo +
                ", nombre='" + nombre + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", conferencia=" + conferencia +
                ", division=" + division +
                ", nombreCompleto='" + nombreCompleto + '\'' +
                ", abreviatura='" + abreviatura + '\'' +
                '}';
    }
}
