/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufps.vistas;

import Evaluador.EvaluadorExpresiones;
import Evaluador.Expresion;

/**
 *
 * @author estudiante
 */
public class TestEvaluador {

    public static void main(String[] args) {
        String url = "https://gitlab.com/juansebastiansp/archivos-persistencia/raw/bd822e4de11efd96f8daaf88518bc2a93e19b1cf/expresiones/expresion.txt.txt";
        EvaluadorExpresiones ev=new EvaluadorExpresiones(url);
        System.out.println(ev);
        System.out.println(ev.evaluarExpresion());
    }
}
