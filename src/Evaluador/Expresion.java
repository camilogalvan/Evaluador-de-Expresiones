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
    
  private ListaCD<String> expresiones=new ListaCD();  

    public Expresion() {
    }
    
    public Expresion(String cadena) {
        
       String v[]=cadena.split(",");
       for(String dato:v) 
           this.expresiones.insertarAlFinal(dato);
    }

    public ListaCD<String> getExpresiones() {
        return expresiones;
    }

    public void setExpresiones(ListaCD<String> expresiones) {
        this.expresiones = expresiones;
    }

    @Override
    public String toString() {
     String msg="";
     for(String dato:this.expresiones)
         msg+=dato+"<->";
    return msg;
    }
    
    public String getPrefijo()
    {
        return "";
    }
    
    
    public String getPosfijo()
    {
        return "";
    }
    
    
    public float getEvaluarPosfijo()
    {
        return 0.0f;
    }
}
