package com.inap.crud.api.model;

public class Empleado {
    private int id_empleado;
    private String nombre_empleado;
    private int edad_empleado;
    private String telefono_empleado;
    private String genero_empleado;
    private String fecha_nac_empleado;

    // Getters y setters
    public int getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(int id_empleado) {
        this.id_empleado = id_empleado;
    }

    public String getNombre_empleado() {
        return nombre_empleado;
    }

    public void setNombre_empleado(String nombre_empleado) {
        this.nombre_empleado = nombre_empleado;
    }

    public int getEdad_empleado() {
        return edad_empleado;
    }

    public void setEdad_empleado(int edad_empleado) {
        this.edad_empleado = edad_empleado;
    }

    public String getTelefono_empleado() {
        return telefono_empleado;
    }

    public void setTelefono_empleado(String telefono_empleado) {
        this.telefono_empleado = telefono_empleado;
    }

    public String getGenero_empleado() {
        return genero_empleado;
    }

    public void setGenero_empleado(String genero_empleado) {
        this.genero_empleado = genero_empleado;
    }

    public String getFecha_nac_empleado() {
        return fecha_nac_empleado;
    }

    public void setFecha_nac_empleado(String fecha_nac_empleado) {
        this.fecha_nac_empleado = fecha_nac_empleado;
    }
}
