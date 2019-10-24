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
//        String url = "https://gitlab.com/madarme/archivos-persistencia/raw/master/expresiones/expresion.txt.txt";
//        EvaluadorExpresiones ev=new EvaluadorExpresiones(url);
////        System.out.println(ev);
//        System.out.println(ev.evaluarExpresion());
        Expresion e = new Expresion("a,+,b,*,c,+,d,/,e");
//        System.out.println(e.expresionValida());
        
//        System.out.println(e.getPosfijo("a,+,b,*,c,+,d,/,e"));
//        System.out.println(e.getPosfijo("a,+,b,*,c"));
//        System.out.println(e.getPosfijo("a,+,b,-,c"));
//        System.out.println(e.getPosfijo("123,*,(,12,+,3,),+,4,*,(,6,+,2,)"));
//        System.out.println(e.getPosfijo("10,+,12,*,12"));
//        System.out.println(e.getPosfijo("10,+,(,7,+,12,)"));
//        System.out.println(e.getPosfijo("10,/,6"));
//        System.out.println(e.getPosfijo("(,(,(,5,+,9,),*,2,),+,(,6,*,5,),)"));
//        System.out.println(e.getPosfijo("25,*,6,+,4,+,5,*,7"));
        System.out.println(e.getPosfijo("25,*,6,+,(,6,+,8,)"));
        
//        
    }
}
