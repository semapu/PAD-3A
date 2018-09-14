/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package EditableBufferedReaderMVC;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author lpadusr11
 */
public class Mode extends BufferedReader{
    
    public Mode(InputStreamReader in) {
        super(in);
        
    }
    
    public int read() throws IOException{
        int a, i, j, k;
        a= super.read();
     //   System.out.print("printem a:" + a);
        switch (a){
            case 27:{ //Seqüencia d'ESC (27)
                i = super.read();
          //      System.out.println(" printem i: " + i);
                switch (i){
                    case 91:{ // Rep [
                        j = super.read();
                       // System.out.print(" printem j: " + j);
                        if (j == 67){//Lletra C, fletxa dreta
                      //      System.out.println("derecha");
                            a=Dades.RIGHT;
                        }
                        else if (j==68){// Lletra D, fletxa esquerra
                            a=Dades.LEFT;
                        }
                        else if ((j>=49) && (j<=52)){
                            k=super.read();
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
}
