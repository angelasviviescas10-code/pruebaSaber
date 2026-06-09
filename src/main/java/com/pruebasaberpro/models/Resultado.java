package com.pruebasaberpro.models;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "resultados")
public class Resultado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "estudiante_id", nullable = false)
    private Estudiante estudiante;

    @Column(nullable = false)
    private Double puntajeGlobal;

    private Double lecturaEscritura;
    private Double razonamientoCuantitativo;
    private Double competenciasCiudadanas;
    private Double inglesComponente;
    private Double componenteEspecifico;

    @Column(nullable = false)
    private LocalDate fechaPrueba;

    @Column(nullable = false)
    private String tipoPrueba;

    public Long getId() { return id; }
    public Estudiante getEstudiante() { return estudiante; }
    public Double getPuntajeGlobal() { return puntajeGlobal; }
    public Double getLecturaEscritura() { return lecturaEscritura; }
    public Double getRazonamientoCuantitativo() { return razonamientoCuantitativo; }
    public Double getCompetenciasCiudadanas() { return competenciasCiudadanas; }
    public Double getInglesComponente() { return inglesComponente; }
    public Double getComponenteEspecifico() { return componenteEspecifico; }
    public LocalDate getFechaPrueba() { return fechaPrueba; }
    public String getTipoPrueba() { return tipoPrueba; }
    public void setId(Long id) { this.id = id; }
    public void setEstudiante(Estudiante estudiante) { this.estudiante = estudiante; }
    public void setPuntajeGlobal(Double puntajeGlobal) { this.puntajeGlobal = puntajeGlobal; }
    public void setLecturaEscritura(Double v) { this.lecturaEscritura = v; }
    public void setRazonamientoCuantitativo(Double v) { this.razonamientoCuantitativo = v; }
    public void setCompetenciasCiudadanas(Double v) { this.competenciasCiudadanas = v; }
    public void setInglesComponente(Double v) { this.inglesComponente = v; }
    public void setComponenteEspecifico(Double v) { this.componenteEspecifico = v; }
    public void setFechaPrueba(LocalDate fechaPrueba) { this.fechaPrueba = fechaPrueba; }
    public void setTipoPrueba(String tipoPrueba) { this.tipoPrueba = tipoPrueba; }
}