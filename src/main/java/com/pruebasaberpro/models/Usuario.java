package com.pruebasaberpro.models;
import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String correo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "rol_id", nullable = false)
    private Rol rol;

    // NUEVO: facultad asignada al usuario (nullable — admin/coordinador no la necesitan)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "facultad_id", nullable = true)
    private Facultad facultad;

    @Column(nullable = false)
    private boolean activo = true;

    // ── Getters ──────────────────────────────────────
    public Long getId()           { return id; }
    public String getUsername()   { return username; }
    public String getPassword()   { return password; }
    public String getNombre()     { return nombre; }
    public String getCorreo()     { return correo; }
    public Rol getRol()           { return rol; }
    public Facultad getFacultad() { return facultad; }
    public boolean isActivo()     { return activo; }

    // ── Setters ──────────────────────────────────────
    public void setId(Long id)              { this.id = id; }
    public void setUsername(String u)       { this.username = u; }
    public void setPassword(String p)       { this.password = p; }
    public void setNombre(String n)         { this.nombre = n; }
    public void setCorreo(String c)         { this.correo = c; }
    public void setRol(Rol r)               { this.rol = r; }
    public void setFacultad(Facultad f)     { this.facultad = f; }
    public void setActivo(boolean a)        { this.activo = a; }
}