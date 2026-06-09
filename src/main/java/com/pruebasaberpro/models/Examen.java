package com.pruebasaberpro.models;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "examenes")
public class Examen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "estudiante_id", nullable = false)
    private Estudiante estudiante;

    @Column(nullable = false)
    private String rutaArchivo;

    @Column(nullable = false)
    private LocalDate fechaSubida;

    @Column(nullable = false)
    private String estado = "PENDIENTE";

    public Long getId() { return id; }
    public Estudiante getEstudiante() { return estudiante; }
    public String getRutaArchivo() { return rutaArchivo; }
    public LocalDate getFechaSubida() { return fechaSubida; }
    public String getEstado() { return estado; }
    public void setId(Long id) { this.id = id; }
    public void setEstudiante(Estudiante estudiante) { this.estudiante = estudiante; }
    public void setRutaArchivo(String rutaArchivo) { this.rutaArchivo = rutaArchivo; }
    public void setFechaSubida(LocalDate fechaSubida) { this.fechaSubida = fechaSubida; }
    public void setEstado(String estado) { this.estado = estado; }
}