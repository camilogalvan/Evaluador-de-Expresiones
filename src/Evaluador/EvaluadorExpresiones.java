/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Evaluador;
import ufps.util.colecciones_seed.*;
import ufps.util.varios.ArchivoLeerURL;
/**
 *
 * @author estudiante
 */
public class EvaluadorExpresiones {
    
    //listaCD<ListaCd<ListaCd<string>>>
    private ListaCD<Expresion> expresiones=new ListaCD();

    
    public EvaluadorExpresiones(String url) {
        
    ArchivoLeerURL file=new ArchivoLeerURL(url);
    Object v[]=file.leerArchivo();    
    for(Object dato:v)
        {
            Expresion nueva=new Expresion(dato.toString());
            this.expresiones.insertarAlFinal(nueva);
        }
    
    
    }
    
    
    public String toString()
    {
        String msg="";
        for(Expresion dato:this.expresiones)
            msg+=dato.toString()+"\n";
     return msg;
    }

    public ListaCD<Expresion> getExpresiones() {
        return expresiones;
    }

    public void setExpresiones(ListaCD<Expresion> expresiones) {
        this.expresiones = expresiones;
    }
    
    
    
    
}
