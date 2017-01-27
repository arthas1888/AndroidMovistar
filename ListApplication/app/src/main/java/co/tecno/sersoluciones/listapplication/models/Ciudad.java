package co.tecno.sersoluciones.listapplication.models;


import java.io.Serializable;

public class Ciudad implements Serializable{
    private String nombre;
    private String departamento;
    private int codigoPostal;
    private int altitud;

    public Ciudad() {
    }

    public Ciudad(String nombre, String departamento, int codigoPostal, int altitud) {
        this.nombre = nombre;
        this.departamento = departamento;
        this.codigoPostal = codigoPostal;
        this.altitud = altitud;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public int getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(int codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public int getAltitud() {
        return altitud;
    }

    public void setAltitud(int altitud) {
        this.altitud = altitud;
    }
}
