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
        String op = "";
        op += v[0];
        cadena += op;
        for (int i = 1; i < v.length; i++) {
            if (v[i].equals("(") && !op.equals("(") && !esOperador(op)) {
                cadena += ",*," + v[i];
                op = "" + v[i];
            } else if (op.equals(")") && !esOperador((String) v[i])) {
                cadena += ",*," + v[i];
            } else {
                cadena += "," + v[i];
                op = "" + v[i];
            }
        }
        System.out.println(cadena);
        if (validarParentesis(cadena)) {
            msg += "Posfijo: " + getPosfijo(cadena) + "\n";
        }
        return msg;
    }

    private boolean verificarOperadores(String c) {
        char[] v = c.toCharArray();
        char letra = ' ';

        return false;
    }

    private boolean esOperador(String l) {
        return (l.equals('+') || l.equals('*') || l.equals('-')
                || l.equals('/'));
    }

    private boolean validarParentesis(String c) {
        Pila<String> p1 = new Pila<>();
        String[] v = c.split(",");
        for (String e : v) {
            if (e.equals('(')) {
                p1.apilar(e);
            } else if (e.equals(')') && !p1.esVacia()) {
                    p1.desapilar();
                } else {
                    return false;
                }
            }
        return p1.esVacia();
    }

    
    
    public String getPrefijo() {
        return "";
    }

    public String getPosfijo(String c) {
        String[] v = c.split(",");

        Pila<String> p1 = new Pila<>();
        Pila<String> p2 = new Pila<>();
        ListaCD<String> posfijo = new ListaCD<>();
        String op = "";
        for (String dato : v) {
            switch (dato) {
                case "+":
                case "-":
                case "*":
                case "/":
                    p1.apilar(dato);
                    break;
                case "(":
                    p2.apilar(dato);
                    break;
                case ")":
                    p2.desapilar();
                    while (!p1.esVacia()) {
                        posfijo.insertarAlFinal(p1.desapilar());
                    }
                    break;
                default:
                    posfijo.insertarAlFinal(dato);
                    while (!p1.esVacia() && p2.esVacia()) {
                        posfijo.insertarAlFinal(p1.desapilar());
                    }
                    break;
            }
        }
        String pos = "";
        for (String dato : posfijo) {
            pos += dato + ",";
        }
        return pos;
    }

    public float getEvaluarPosfijo() {
        return 0.0f;
    }
    
    private int getPrioridad(String dato){
      int prioridad=6;
      
      if(dato.equals("*")||dato.equals("/"))prioridad=5;
      if(dato.equals("+")||dato.equals("-"))prioridad=4;
      
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
