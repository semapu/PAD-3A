
import java.io.*;

public class EditableBufferedReader extends BufferedReader{
    
    final int RIGHT = Integer.MIN_VALUE;
    final int LEFT = Integer.MIN_VALUE+1;
    final int HOME = Integer.MIN_VALUE+2;
    final int END = Integer.MIN_VALUE+3;
    final int DEL = Integer.MIN_VALUE+4;
    final int INS = Integer.MIN_VALUE+5;
    final int BKSP = Integer.MIN_VALUE+6;
        

    public EditableBufferedReader(Reader r){
            super(r);
    }

    private static void setRaw() throws IOException {
        // put terminal in raw mode
        String[] cmd = {"/bin/sh", "-c", "stty -echo raw </dev/tty"};
        Runtime.getRuntime().exec(cmd);
    }
    
    private static void unsetRaw() throws IOException {
        // put terminal in cooked mode
        String[] cmd = {"/bin/sh", "-c", "stty echo cooked </dev/tty"};
        Runtime.getRuntime().exec(cmd);
    }

    /**
    *  RIGHT: Esc [ C
    *  LEFT: Esc [ D
    *  HOME: Esc O H, Esc [ 1 ~
    *  END: Esc O F, Esc [ 4 ~
    *  DEL: Esc [ 3 ~
    *  INS: Esc [ 2 ~
     * @return 
     * @throws IOException 
    */
    @Override
    public int read() throws IOException{ 
        //CAL MODIFICAR LES VARIABLES PER EVITAR REDUNDANCIES EN EL CODI -> MODIFICACIÓ UN COP FUNCIONI
        int caracter = super.read();
        int resultat=0;

        if(caracter == 27){ //Seqncia d'esc
            int instruccio = super.read();
            if(instruccio=='['){
                switch(super.read()){
                    case 'C':
                        resultat = RIGHT ;
                        break;
                    case 'D':
                        resultat = LEFT;
                        break;
                    case '4':
                        if(super.read()=='~'){
                            resultat = END;
                        }
                        break;
                    case '1':
                        if(super.read()=='~'){
                            resultat = HOME;
                        }else if(super.read()=='P'){
                            System.out.print("BKSP");
                            resultat = BKSP;
                        }
                        break;
                    case '3':
                        if(super.read()=='~'){
                            resultat = DEL;
                        }
                        break;
                    case '2':
                        if(super.read()=='~'){
                            resultat = INS;
                        }
                        break;
                }
            }else if(instruccio =='O'){
                    switch(super.read()){
                    case 'H':
                            resultat = HOME;
                            break;
                    case 'F':
                            resultat = END;
                            break;
                    }
            }
            return resultat;
        
        }else if (caracter== '^'){
            if((caracter = super.read()) == 'H'){
                    System.out.print("BKSP");
                return BKSP;
            }
        }else if(caracter == 8){
            System.out.print("BKSP");
            return BKSP;
        }
        return caracter;
    }

        
        /*if (caracter== '^'){
            if((super.read()) == '?'){
                return BKSP;
            }
        }		
        return caracter;
    }*/
    
    
    @Override
    public String readLine() throws IOException{

        setRaw();
        int lec;
        Line line = new Line();
        try{
            while((lec=read()) != '\r'){
                switch(lec){
                    case RIGHT:
                        if(line.right())
                            System.out.print("\033[C");
                        break;
                    case LEFT:
                        if(line.left())
                            System.out.print("\033[D");
                        break;
                    case END:
                        line.end();
                        //BUSCAR COM IMPRIMIR EL CURSOR AL FINAL DE LA LINEA
                        break; 
                    case HOME:
                        System.out.print("\033[1~"); //interacció desprès de introduir una lletra
                        line.home(); //Line coneix la nova possició del cursor
                        break;
                    case DEL:
                        //BKSP pero amb previ moviment del cursor --> System.out.print("\033[1P");
                        if(line.del()){
                            //System.out.print("\033[C");
                            System.out.print("\033[1P");
                        }
                        break;      
                    case INS:
                        System.out.print("\033[1@");  //Ens fa un espai
                        line.ins();
                        break;
                    case BKSP:
                        if(line.bksp()){
                            System.out.print('\b');
                            System.out.print("\033[1P");    
                        }
                        break;
                    default:     
                        char ch = (char)lec; //Casting per convertit el resultat de READ
                        line.add(ch); //Invocar line per afegir el caracter --> line.add(lec);
                        System.out.print(ch);
                        break;
                }
            }
            
        }catch(Exception ex){
        }finally{
            unsetRaw();
            return line.toString();
        }
    }
}



    
    
   

    

