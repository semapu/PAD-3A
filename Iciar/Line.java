/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package EditableBufferedReader;

/**
 *
 * @author lpadusr11
 */
public class LineString {
    private String str;
    private boolean ins;
    private int cursor, max;
    public Line() {
        str = new String(" ");
        ins = false;  //false--> sobreescritura, true-->insercion
        cursor = 0;
        max = str.length();
    }
    public boolean right(){
        if (cursor < max){
            cursor ++;
            return true;
        }
        else{
            //BELL
            return false;
        }
    }
    public boolean left(){
        if (cursor > 0){
            cursor --;
            return true;
        }
        else{
            //BELL
            return false;
        }
    }
    public boolean delete(){
        if ((cursor > 0) && (cursor < max)){
            str=str.substring(0, cursor)+str.substring(cursor+1);
            return true;
        }
        else if (cursor == max){
            //BELL
            return false;
        else{
             str=str.subtring(cursor+1);
             return true;
        }
        max = str.length();
    }
    public boolean backspace(){
        if (cursor > 1){
             str=str.substring(0, cursor-1)+str.substring(cursor);
             cursor --;
             max = str.length();
             return true;
        }
        else if (cursor == 1){
             str=str.substring(cursor);
             cursor --;
             max = str.length();
	     return true;
        }
        else{
             //BELL
		  return false;
        }
        //si borres el primer, cursor en negtiu
    }
    public void toggle(){
        if (ins == true){
             ins = false;
        }
        else{
             ins = true;
        }
    }
    public void add(char i){
        String str2 =new String('i');
        if (ins == true){             
             String str1 = str.substring(0, cursor);
             String str3 = str.substring(cursor);
             str = str1 + str2 + str3;
             cursor++;
             max = str.length();
        }
        else{
             String str1 = str.substring(0, cursor);
             String str3 = str.substring(cursor+1);
             str = str1 + str2 + str3;
             cusror++;
        }
    }
    public void home(){
        cursor = 0;
        
    }
    public void fin(){
        cursor = max;
        
    }
    public String toString(){
        return str;
    }
}
