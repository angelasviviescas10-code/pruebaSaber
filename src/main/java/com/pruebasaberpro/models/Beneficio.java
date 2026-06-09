package com.pruebasaberpro.models;
import jakarta.persistence.*;

@Entity
@Table(name = "beneficios")
public class Beneficio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false, length = 500)
    private String descripcion;

    @Column(nullable = false)
    private String tipoPrograma;

    @Column(nullable = false)
    private String nivelFormacion;

    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public String getTipoPrograma() { return tipoPrograma; }
    public String getNivelFormacion() { return nivelFormacion; }
    public void setId(Long id) { this.id = id; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public void setTipoPrograma(String tipoPrograma) { this.tipoPrograma = tipoPrograma; }
    public void setNivelFormacion(String nivelFormacion) { this.nivelFormacion = nivelFormacion; }
}