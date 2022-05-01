package com.example.dydproyect.entidades;

import java.util.HashMap;
import java.util.Map;

public class Personaje {

    private String nombre;
    private String exp;
    private int nivel;
    private String clase;
    private String raza;
    private int fuerza;
    private int destreza;
    private int constitucion;
    private int inteligencia;
    private int sabiduria;
    private int carisma;
    private String alineamiento;


    public String getAlineamiento() {
        return alineamiento;
    }

    public void setAlineamiento(String alineamiento) {
        this.alineamiento = alineamiento;
    }

    public Personaje(){

    }

    public Personaje(int fuerza, int destreza, int constitucion, int inteligencia, int sabiduria, int carisma) {
        this.fuerza = fuerza;
        this.destreza = destreza;
        this.constitucion = constitucion;
        this.inteligencia = inteligencia;
        this.sabiduria = sabiduria;
        this.carisma = carisma;
    }

    public Personaje(String nombre, String exp, int nivel, String clase, String raza, int fuerza, int destreza, int constitucion, int inteligencia, int sabiduria, int carisma, String alineamiento) {
        this.nombre = nombre;
        this.exp = exp;
        this.nivel = nivel;
        this.clase = clase;
        this.raza = raza;
        this.fuerza = fuerza;
        this.destreza = destreza;
        this.constitucion = constitucion;
        this.inteligencia = inteligencia;
        this.sabiduria = sabiduria;
        this.carisma = carisma;
        this.alineamiento = alineamiento;
    }


    public Personaje(String nombre, String exp, int nivel) {
        this.nombre = nombre;
        this.exp = exp;
        this.nivel = nivel;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public void setFuerza(int fuerza) {
        this.fuerza = fuerza;
    }

    public void setDestreza(int destreza) {
        this.destreza = destreza;
    }

    public void setConstitucion(int constitucion) {
        this.constitucion = constitucion;
    }

    public void setInteligencia(int inteligencia) {
        this.inteligencia = inteligencia;
    }

    public void setSabiduria(int sabiduria) {
        this.sabiduria = sabiduria;
    }

    public void setCarisma(int carisma) {
        this.carisma = carisma;
    }

    public String getNombre() {
        return nombre;
    }

    public String getExp() {
        return exp;
    }

    public int getNivel() {
        return nivel;
    }

    public String getClase() {
        return clase;
    }

    public String getRaza() {
        return raza;
    }

    public int getFuerza() {
        return fuerza;
    }

    public int getDestreza() {
        return destreza;
    }

    public int getConstitucion() {
        return constitucion;
    }

    public int getInteligencia() {
        return inteligencia;
    }

    public int getSabiduria() {
        return sabiduria;
    }

    public int getCarisma() {
        return carisma;
    }
}
