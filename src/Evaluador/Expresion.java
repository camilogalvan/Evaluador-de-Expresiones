/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Evaluador;

import ufps.util.colecciones_seed.*;

/**
 *
 * @author estudiante
 */
public class Expresion {

    private ListaCD<String> expresiones = new ListaCD();

    public Expresion() {
    }

    public Expresion(String cadena) {

        String v[] = cadena.split(",");
        for (String dato : v) {
            this.expresiones.insertarAlFinal(dato);
        }
    }

    public String expresionValida() {

        Object v[] = this.expresiones.aVector();
        String cadena = "";
        String msg = "";
        cadena += v[0];
        for (int i = 1; i < v.length; i++) {
            cadena += "," + v[i];
        }
        if (validarParentesis(cadena) && validarOperadores(cadena)) {
            msg += "Posfijo: " + getPosfijo(cadena) + "\n";
            msg += "Prefijo: " + getPrefijo(voltearCadenaPre(getPosfijo(cadena))) + "\n";
            msg += "Resultado: " + getEvaluarPosfijo(cadena) + "\n";
        } else {
            msg += "Error de expresión: " + cadena + "\n";
        }
        return msg;
    }

    private String voltearCadenaPre(String c) {
        String[] v = c.split(",");
        String msg = "";
        msg += v[v.length - 1];

        for (int i = v.length - 2; i >= 0; i--) {
            msg += "," + v[i];
        }
        return msg;
    }

    private boolean esOperador(String l) {
        return (l.equals("+") || l.equals("*") || l.equals("-")
                || l.equals("/"));
    }

    public String negacion(String c) {
        String[] v = c.split(",");
        String op = v[0];
        int numN = 0;
        ListaCD<String> pos = new ListaCD<>();

        for (int i = 1; i < v.length; i++) {
            if (op.equals("-") && op.equals(v[i])) {
                numN++;
            } else {
                if (op.equals("-") && !esOperador(v[i])) {
                    if (numN % 2 != 0) {
                        pos.insertarAlFinal("+");
                    } else {
                        pos.insertarAlFinal(op);
                    }
                } else {
                    pos.insertarAlFinal(op);
                }
            }
            op = v[i];
        }
        pos.insertarAlFinal(v[v.length - 1]);
        return getPostfYPref(pos);
    }

    private boolean validarOperadores(String c) {
        String[] v = c.split(",");
        String op = v[0];

        for (String dato : v) {
            if (esOperador(op) && esOperador(dato) && !dato.equals("-")) {
                return false;
            } else if (!op.equals("-") && !dato.equals("-") && esOperador(op) && esOperador(dato)) {
                return false;
            } else if (op.equals("(") && !dato.equals("-") && esOperador(dato)) {
                return false;
            } else if (!esOperador(op)&& !op.equals("(") && dato.equals("(")) {
                return false;
            } else if (op.equals(")") && !esOperador(dato)) {
                return false;
            } 
            op = dato;
        }
        return true;
    }

    private boolean validarParentesis(String c) {
        Pila<String> p1 = new Pila<>();
        String[] v = c.split(",");
        for (String e : v) {
            if (e.equals("(")) {
                p1.apilar(e);
            } else if (e.equals(")")) {
                if (!p1.esVacia()) {
                    p1.desapilar();
                } else {
                    return false;
                }
            }
        }
        return p1.esVacia();
    }

    public String getPosfijo(String c) {
        String[] v = c.split(",");
        Pila<String> p1 = new Pila<>();
        ListaCD<String> posfijo = new ListaCD<>();

        for (String dato : v) {
            switch (dato) {
                case "+":
                case "-":
                case "*":
                case "/":
                    if (p1.esVacia() || p1.getTope().equals("(")) {
                        p1.apilar(dato);
                    } else {
                        while (!p1.esVacia()) {
                            if (getPrioridadExpresion(dato) > getPrioridadPila(p1.getTope())) {
                                p1.apilar(dato);
                                break;
                            } else {
                                posfijo.insertarAlFinal(p1.desapilar());
                                if (p1.esVacia()) {
                                    p1.apilar(dato);
                                    break;
                                }
                            }
                        }
                    }
                    break;
                case "(":
                    p1.apilar(dato);
                    break;
                case ")":
                    while (!p1.getTope().equals("(")) {
                        posfijo.insertarAlFinal(p1.desapilar());
                    }
                    p1.desapilar();
                    break;
                default:
                    posfijo.insertarAlFinal(dato);
                    break;
            }
        }
        while (!p1.esVacia()) {
            posfijo.insertarAlFinal(p1.desapilar());
        }
        return getPostfYPref(posfijo);
    }

    public String getPrefijo(String c) {
        String[] v = c.split(",");
        Pila<String> p1 = new Pila<>();
        ListaCD<String> prefijo = new ListaCD<>();

        for (String dato : v) {
            switch (dato) {
                case "+":
                case "-":
                case "*":
                case "/":
                    if (p1.esVacia() || p1.getTope().equals("(")) {
                        p1.apilar(dato);
                    } else {
                        while (!p1.esVacia()) {
                            if (getPrioridadExpresion(dato) > getPrioridadPila(p1.getTope())) {
                                p1.apilar(dato);
                                break;
                            } else {
                                prefijo.insertarAlInicio(p1.desapilar());
                                if (p1.esVacia()) {
                                    p1.apilar(dato);
                                    break;
                                }
                            }
                        }
                    }
                    break;
                case ")":
                    p1.apilar(dato);
                    break;
                case "(":
                    while (!p1.getTope().equals(")")) {
                        prefijo.insertarAlInicio(p1.desapilar());
                    }
                    p1.desapilar();
                    break;
                default:
                    prefijo.insertarAlInicio(dato);
                    break;
            }
        }
        while (!p1.esVacia()) {
            prefijo.insertarAlInicio(p1.desapilar());
        }
        return getPostfYPref(prefijo);
    }

    private String getPostfYPref(ListaCD<String> l) {
        String pos = "";
        for (String dato : l) {
            pos += dato + ",";
        }
        return pos;
    }

    public float getEvaluarPosfijo(String c) {
        String[] v = this.getPosfijo(negacion(c)).split(",");
        Pila<String> p1 = new Pila<>();
        float op1 = 1;
        float op2 = 1;
        float resultado;
        boolean esNegativo = false;

        for (String dato : v) {
            if (!esOperador(dato)) {
                p1.apilar(dato);
            } else {
                op2 = !p1.esVacia() ? Float.parseFloat(p1.desapilar()) : op2;
                if (dato.equals("-") && p1.esVacia()) {
                    op1 = -1;
                    esNegativo = true;
                }
                op1 = !p1.esVacia() ? Float.parseFloat(p1.desapilar()) : dato.equals("+") ? 0 : op1;

                switch (dato) {
                    case "+":
                        resultado = op1 + op2;
                        p1.apilar(Float.toString(resultado));
                        break;
                    case "-":
                        resultado = !esNegativo ? op1 - op2 : op1 * op2;
                        p1.apilar(Float.toString(resultado));
                        break;
                    case "*":
                        resultado = op1 * op2;
                        p1.apilar(Float.toString(resultado));
                        break;
                    case "/":
                        resultado = op1 / op2;
                        p1.apilar(Float.toString(resultado));
                        break;
                    default:
                        break;
                }
            }
        }
        return Float.parseFloat(p1.desapilar());
    }

    private int getPrioridadExpresion(String dato) {
        int prioridad = 6;

        if (dato.equals("(") || dato.equals(")")) {
            prioridad = 5;
            return prioridad;
        }
        if (dato.equals("*") || dato.equals("/")) {
            prioridad = 4;
            return prioridad;
        }
        if (dato.equals("+") || dato.equals("-")) {
            prioridad = 3;
            return prioridad;
        }
        return prioridad;
    }

    private int getPrioridadPila(String dato) {
        int prioridad = 5;

        if (dato.equals("*") || dato.equals("/")) {
            prioridad = 4;
            return prioridad;
        }
        if (dato.equals("+") || dato.equals("-")) {
            prioridad = 3;
            return prioridad;
        }
        if (dato.equals("(") || dato.equals(")")) {
            prioridad = 0;
            return prioridad;
        }
        return prioridad;
    }

    public ListaCD<String> getExpresiones() {
        return expresiones;
    }

    public void setExpresiones(ListaCD<String> expresiones) {
        this.expresiones = expresiones;
    }

    @Override
    public String toString() {
        String msg = "";
        for (String dato : this.expresiones) {
            msg += dato + "<->";
        }
        return msg;
    }
}
