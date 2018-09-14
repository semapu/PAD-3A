
public class Line {
    protected String line;
    protected boolean insert;
    protected int pos, max;ºº			
    
    public Line(){
        line = new String();
        insert = false;
        pos=0;
        max= line.length();
    }
    
    public boolean right(){
        if(pos< max){
            pos++;
            return true;
        }else{
            return false;
        }
    }
    public boolean left(){
        if (pos > 0){
            pos --;
            return true;
        }
        else{
            return false;
        }
    }    
    public void home(){
        pos = 0;
        
    }
    public void end(){
        pos = max;
        
    }
    public String toString(){
        return line;
    }
    
    public boolean del(){
        /*if ((pos > 0) && (pos < max)){
            line=line.substring(0, pos)+line.substring(pos+1);
            return true;
        }else if (pos == max){
            return false;
        }
        line=line.substring(pos+1);
        max = line.length();
        return true;*/
        
        return true; //BORRAR AL DESCOMENTAR
        
    }
    
    
    public void ins(){
        insert = insert != true;
        
        //Codi inicial. Actual ajuda netbeans
        /*if (insert == true){
             insert = false;
        }
        else{
             insert = true;
        }*/
    }
    

    public boolean bksp(){
        if (pos > 1){
             line=line.substring(0, pos-1)+line.substring(pos);
             pos --;
             max = line.length();
             return true;
        }
        else if (pos == 1){
             line=line.substring(pos);
             pos --;
             max = line.length();
	     return true;
        }else{
            return false;
        }

    }

    public void add(char ch){
        //NO ETEM FENT RES AMB ch
        
        //String line2=new String(ch);
        
        //substring(int beginIndex, int endIndex)
        
        if (insert == true){             
             String line1 = line.substring(0, pos);
             String line3 = line.substring(pos);
             line = line1 + /*line2 +*/ line3;
             pos++;
             max = line.length();
        } else{
             String line1 = line.substring(0, pos);
             String line3 = line.substring(pos+1);
             line = line1 + /*line2 +*/ line3;
             pos++;
        }
        
        /*if (insert)
        	line.insert(pos,ch);
        else
        	line.replace(pos,pos,Character.toString(ch));
        pos++;
        max = line.length(); 
        }*/
    }
}

