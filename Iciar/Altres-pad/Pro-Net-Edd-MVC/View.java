/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package EditableBufferedReaderMVC;

import java.io.IOException;

/**
 *
 * @author lpadusr11
 */
public class View {
    private String str;
    private boolean ins;
    private int cursor, max;
    public View() {
        str = new String(" ");
        ins = true;  //false--> sobreescritura, true-->insercion
        cursor = 0;
        max = str.length();
    }
    
    public void setRaw() throws IOException{
        String[] cmd = {"/bin/sh", "-c", "stty -echo raw </dev/tty"};
        Runtime.getRuntime().exec(cmd);
    }
    
    public void unsetRaw() throws IOException{
        String[] cmd = {"/bin/sh", "-c", "stty echo cooked </dev/tty"}; //portar de casa com tornar a no echo
        Runtime.getRuntime().exec(cmd);        
    }
    
    public void right(){
        if (cursor < (max-1)){
            cursor ++;
	    System.out.print("\033[C");
        }
        else{ //cursor=max
            System.out.print("\007");
        }
    }
    public void left(){
        if (cursor > 0){
            cursor --;
	    System.out.print("\033[D");
        }
        else{ //cursor=0
            System.out.print("\007");
        }
    }
    public void delete(){ //es el suprimir
        if ((cursor > 0) && (cursor < max)){
            str=str.substring(0, cursor)+str.substring(cursor+1);
	    System.out.print("\033[1P");
            max--;
        }
        else if (cursor == max){
            System.out.print("\007");
	}
        else{ //estas en la posicion inicial
             str=str.substring(cursor+1);
	     System.out.print("\033[1P");
             max--;
        }
        
    }
    public void backspace(){
        if (cursor > 1){
             str=str.substring(0, cursor-1)+str.substring(cursor);
             cursor --;
             max = str.length();
	     System.out.print('\b');
             System.out.print("\033[1P");
        }
        else if (cursor == 1){
             str=str.substring(cursor);
             cursor --;
             max = str.length();
	     System.out.print('\b');
             System.out.print("\033[1P");
        }
        else{
             System.out.print("\007");
        }
    }
    public void toggle(){
        if (ins == true){
             ins = false;
        }
        else{
             ins = true;
        }
    }
    public void add(int i){
	String a = "";
        String str2 = a+(char)i;
        if ((ins == true) || ((ins==false) && cursor>=max)){  //esta en modo insercion           
             String str1 = str.substring(0, cursor);
             String str3 = str.substring(cursor);
             str = str1 + str2 + str3;
             cursor++;
             System.out.print("\033[1@");
        }
        else{ //esta en modo sobreescritura
             String str1 = str.substring(0, cursor);
             String str3 = str.substring(cursor+1);
             str = str1 + str2 + str3;
             cursor++;
        }
        max = str.length();
	System.out.print((char)i);
    }
    public void home(){
	if(cursor != 0){
	     for (int i = cursor; i > 0; i--){
		this.left();
	     }
	     cursor = 0;
	}
	else{
	     System.out.print("\007");
        }
    }
    public void fin(){
	if(cursor != max){
	     for (int i = cursor; i < max; i++){
		this.right();
	     }
	     cursor = max;
	}
	else{
	     System.out.print("\007");
        } 
    }
    public String toString(){
        return str;
    }
}
