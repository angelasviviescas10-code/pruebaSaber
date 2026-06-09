package com.pruebasaberpro.models;

import jakarta.persistence.*;

@Entity
@Table(name = "estudiantes")
public class Estudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String documento;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @Column(nullable = false)
    private String correo;

    @Column(nullable = false)
    private String telefono;

    @Column(nullable = false)
    private String tipoPrueba;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "facultad_id", nullable = false)
    private Facultad facultad;

    @Column(nullable = false)
    private boolean aprobado = false;

    @Column
    private String rutaPago;

    @Column
    private Boolean pagoAprobado; // null = sin revisar, true = aprobado, false = rechazado

    public Long getId() { return id; }
    public String getDocumento() { return documento; }
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public String getCorreo() { return correo; }
    public String getTelefono() { return telefono; }
    public String getTipoPrueba() { return tipoPrueba; }
    public Facultad getFacultad() { return facultad; }
    public boolean isAprobado() { return aprobado; }
    public String getRutaPago() { return rutaPago; }
    public Boolean getPagoAprobado() { return pagoAprobado; }
    public void setId(Long id) { this.id = id; }
    public void setDocumento(String documento) { this.documento = documento; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    public void setCorreo(String correo) { this.correo = correo; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public void setTipoPrueba(String tipoPrueba) { this.tipoPrueba = tipoPrueba; }
    public void setFacultad(Facultad facultad) { this.facultad = facultad; }
    public void setAprobado(boolean aprobado) { this.aprobado = aprobado; }
    public void setRutaPago(String rutaPago) { this.rutaPago = rutaPago; }
    public void setPagoAprobado(Boolean pagoAprobado) { this.pagoAprobado = pagoAprobado; }
}