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
public class Controler extends BufferedReader {
    Mode m;
    
    public Controler(InputStreamReader in) {
        super(in);
        m = new Mode(in);
    }
        
    public String readLine() throws IOException{
        View str = new View();
        try{
            str.setRaw();
            int i;
            while ((i=m.read()) != '\r'){
                switch (i){
                    case Dades.RIGHT:
                        str.right();                        
                        break;
                    case Dades.LEFT:
                        str.left();                        
                        break;                       
                    case Dades.HOME:                        
                        str.home();
                        break;           
                    case Dades.INS:                        
                        str.toggle();
                        break;
                    case Dades.DELETE:
                        str.delete();                        
                        break;
                    case Dades.BACKSPACE:
                        str.backspace();                        
                        break;
                    case Dades.FIN:
                        str.fin();
                        break; 
                    default:                        
                        str.add(i);                        
                        break;
                }
            }
        }catch(Exception ex){ex.printStackTrace();
            
        }finally{
            str.unsetRaw();
            return str.toString(); 
        }
    }
}
