package com.example.prog_sep_2021;

/**
 * Obtiene la información de la tabla de artículos vendidos y sirve para generar sus filas y columnas
 */
public class InsertArticlesTable {
    String cbarra, denominacion, unidades, pcompra, pvp, nif;

    public InsertArticlesTable(String c_barra, String denominacion, String unidades, String p_compra, String pvp, String nif) {
        this.cbarra = c_barra;
        this.denominacion = denominacion;
        this.unidades = unidades;
        this.pcompra = p_compra;
        this.pvp = pvp;
        this.nif = nif;
    }

    public String getCbarra() {
        return cbarra;
    }

    public void setCbarra(String cbarra) {
        this.cbarra = cbarra;
    }

    public String getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    public String getUnidades() {
        return unidades;
    }

    public void setUnidades(String unidades) {
        this.unidades = unidades;
    }

    public String getPcompra() {
        return pcompra;
    }

    public void setPcompra(String pcompra) {
        this.pcompra = pcompra;
    }

    public String getPvp() {
        return pvp;
    }

    public void setPvp(String pvp) {
        this.pvp = pvp;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }
}
