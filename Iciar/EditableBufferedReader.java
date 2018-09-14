/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package EditableBufferedReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;

/**
 *
 * @author lpadusr11
 */
public class EditableBufferedReader extends BufferedReader {

    /**
     * @param args the command line arguments
     */
    Boolean enter;
    
    public EditableBufferedReader(InputStreamReader in) {
        super(in);
        
    }
    
    @Override
    public int read() throws IOException{
        int a, i, j;
        a= super.read();
        switch (a){
            case 27:{ //Seqüencia d'ESC (27)
                i = super.read();
                switch (i){
                    case 91:{ // Rep [
                        j = super.read();
                        if (j == 67){//Lletra C, fletxa dreta
                            a=Dades.RIGHT;
                        }
                        else if (j==68){// Lletra D, fletxa esquerra
                            a=Dades.LEFT;
                        }
                        else if ((j>=49) && (j<=52)){
                            int k=super.read();
                            a=Dades.MIN+j;
                        }
                        break;}
                    case 79:{ //Rep un O
                        j=super.read();
                        if (j==70){//Rep una F, fin del petit
                            a=Dades.FIN;
                        }
                        else if (j==72){//Rep una H, home del petit
                            a=Dades.HOME;
                        }
                        break;}
                    default:{
                        
                    }
                }
                break;}
            case 94:{ //Rep ^
                i = super.read();
                if (i==63){//Rep un ?, es un backspace
                    a=Dades.BACKSPACE;
                }
                break;}
            default: { //llegim qualsevol altre cosa
                break;}
        }
    return a;
    }
//PROGRAMA ORIGINAL: fet amb ifs en canvi de switch
//      if (a==27){ //Seqüencia d'ESC (27)
//            int i = super.read();
//            if (i==91){//Rep un [
//                int j=super.read();
//                if (j == 67){//Lletra C, fletxa dreta
//                    a=-1;
//                }
//                else if (j==68){// Lletra D, fletxa esquerra
//                    a=-2;
//                }
//                else if (j==49){//Rep un 1
//                    int k = super.read();
//                    if (k==126){//Rep un ~, secuencia Home KeyPad
//                        a=-3;
//                    }
//                }
//                else if (j==50){//Rep un 2
//                    int k = super.read();
//                    if (k==126){//Rep un ~, secuencia Insert
//                        a=-4;
//                    }
//                }
//                else if(j==51){//Rep un 3
//                    int k = super.read();
//                    if (k==126){//Rep un ~, secuencia suprimir
//                        a=-5;
//                    }
//                }
//                else if (j==52){//Rep un 4
//                    int k = super.read();
//                    if (k==126){//Rep un ~, secuencia Fin KeyPad
//                        a=-6;
//                    }
//                }  
//            }
//            else if (i==79){//Rep una O
//                int j=super.read();
//                if (j==70){//Rep una F, fin del petit
//                    a=-6;
//                }
//                else if (j==72){//Rep una H, home del petit
//                    a=-3;
//                }
//            }
//        }
//        else if (a==94){//Rep ^
//            int i = super.read();
//            if (i==63){//Rep un ?, es un backspace
//                a=-7;
//            }
//        }
//        else {//llegim qualsevol altre cosa
//            
//        }
//
//        return a;
//    }
    
    public void setRaw() throws IOException{
        String[] cmd = {"/bin/sh", "-c", "stty -echo raw </dev/tty"};
        Runtime.getRuntime().exec(cmd);
    }
    
    public void unsetRaw() throws IOException{
        String[] cmd = {"/bin/sh", "-c", "stty echo cooked </dev/tty"}; //portar de casa com tornar a no echo
        Runtime.getRuntime().exec(cmd);        
    }
    
    @Override
    public String readLine() throws IOException{
        Line str = new Line();
        try{
            setRaw();
            int i;
            while ((i=read()) != '\r'){
                switch (i){
                    case Dades.RIGHT:
                        boolean puedo = str.right();
                        if (puedo == ture){
                             System.out.print("\033[C"); //Funciona
                        }
                        else{
                             System.out.print("BELL"); //buscar
                        }
                        break;
                    case Dades.LEFT:
                        boolean puedo = str.left();
                        if (puedo == ture){
                             System.out.print("\033[D"); //Funciona
                        }
                        else{
                             System.out.print("BELL"); //buscar
                        }
                        break;                       
                    case Dades.HOME:
                        System.out.print("\033[1~"); //no fa re, pero tampoc printa re pero despres de posar lletra printa algo
                        str.home();
                        break;           
                    case Dades.INS:
                        System.out.print("\033[1@");  //Ens fa un espai
                        str.toggle();
                        break;
                    case Dades.DELETE:
                        boolean puedo = str.delete();
                        if (puedo == true){
                             System.out.print("\033[1P"); //Funciona
                        }
                        else{
                             System.out.print("BELL"); //buscar
                        }
                        break;
                    case Dades.BACKSPACE:
                        boolean puedo = str.backspace();
                        if (puedo == true){
                             System.out.print('\b');
                             System.out.print("\033[1P"); //Funciona
                        }
                        else{
                             System.out.print("BELL"); //buscar
                        }
                        break;
                    case Dades.FIN:
                        String what = str.fin();
                        System.out.print(what);  //falta buscar
                        break; 
                    default:
                        char a = (char) i;
                        str.add(a);
                        System.out.print(a);
                        break;
                }
                
//                if (i == -1){//Lletra C, fletxa dreta
//                    System.out.print((char) 27+"["+"C");
//                }
//                else if (i==-2){// Lletra D, fletxa esquerra
//                    System.out.print((char) 27+"["+"D");
//                }
//                else if (i==-3){//Home key pad
//                    System.out.print((char) 2); //no funciona
//                }
//                else if (i==-4){//Insert key pad
//                    System.out.print((char) 27+"["+"2~"); //modificar el que ha fer
//                }
//                else if(i==-5){//Rep un 3 supr
//                    System.out.print((char) 27+"["+"C" + (char) 8); //anar a la dreta i borrar no esta be! + borrar de chars
//                }
//                else if (i==-6){//Rep un 4 seqüencia Fin KeyPad
//                    System.out.print((char) 3); //no funciona 
//                }  
//                else if (i==-7 ){//Del
//                    System.out.print((char) 8); //no funciona + borrar de chars
//                }
//                else {
//                    char a =(char)i;
//                    chars.add(a); 
//                    System.out.print(a);
//                }
            }
        }catch(Exception ex){ex.printStackTrace();
            
        }finally{
            unsetRaw();
            return str.toString(); //no esta correcte!
        }
    }
}
