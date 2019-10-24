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
//            if (v[i].equals("(") && !op.equals("(") && !esOperador(op)) {
//                cadena += ",*," + v[i];
//                op = "" + v[i];
//            } else if (op.equals(")") && !esOperador((String) v[i])) {
//                cadena += ",*," + v[i];
//            } else {
//            cadena += "," + v[i];
//                op = "" + v[i];
//            }
            cadena += "," + v[i];
        }
        if (validarParentesis(cadena)) {
            msg += cadena + "\n";
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
            } else if (e.equals(')')) {
                if (!p1.esVacia()) {
                    p1.desapilar();
                } else {
                    return false;
                }
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
        int tamanio = 0;

        for (String dato : v) {
            // 6+5-3*7/4
            //cola-> 6,5,+,3,7,
            //pila-> +,
            //entra-> ('-') -> ('-' <= '+') -> cola->'+' y apila '-'
            //pila-> -,*,/

            switch (dato) {
                case "+":
                case "-":
                case "*":
                case "/":
                    if (p2.esVacia()) {
                        p1.apilar(dato);
                        tamanio++;
                    } else {
                        p2.apilar(dato);
                    }
                    break;
                case "(":
                    p2.apilar(dato);
                    break;
                case ")":
                    while (!p2.esVacia()) {
                        String desapilar = p2.desapilar();
                        if (desapilar.equals("(")) {
                            break;
                        }
                        if (!p2.esVacia()) {
                            String des = p2.desapilar();
                            if (!des.equals("(")) {
                                if (getPrioridad(desapilar) <= getPrioridad(des)) {
                                    posfijo.insertarAlFinal(des);
                                    posfijo.insertarAlFinal(desapilar);
                                } else {
                                    posfijo.insertarAlFinal(desapilar);
                                    posfijo.insertarAlFinal(des);
                                }
                            } else {
                                posfijo.insertarAlFinal(desapilar);
                                break;
                            }
                        } else {
                            posfijo.insertarAlFinal(desapilar);
                        }
                    }
                    break;
                default:
                    
                    if (tamanio > 1) {
                        String tope = p1.desapilar();
                        String des = p1.desapilar();
                        tamanio -= 2;
                        if (getPrioridad(tope) <= getPrioridad(des)) {
                            posfijo.insertarAlFinal(des);
                            posfijo.insertarAlFinal(dato);
                            posfijo.insertarAlFinal(tope);
                        } else {
                            posfijo.insertarAlFinal(dato);
                            posfijo.insertarAlFinal(tope);
                            posfijo.insertarAlFinal(des);
                        }
                    } else {
                        posfijo.insertarAlFinal(dato);
                    }
                    break;
            }
        }

        while (!p1.esVacia()) {
            posfijo.insertarAlFinal(p1.desapilar());
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

    private int getPrioridad(String dato) {
        int prioridad = 6;

        if (dato.equals("(") || dato.equals(")")) {
            prioridad = 6;
        }
        
        if (dato.equals("*") || dato.equals("/")) {
            prioridad = 5;
        }
        if (dato.equals("+") || dato.equals("-")) {
            prioridad = 4;
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
