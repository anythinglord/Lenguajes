import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class TestClass {

    public static class Lexico{

        static ArrayList<String> Tokens = new ArrayList<String>();
        static ArrayList<String> rwords = new ArrayList<String>();
        static HashMap<String,String> tokener = new HashMap<String, String>();
        static boolean sicadi = false;
        static boolean sichri = false;
        static int times = 0;

        public static class set{
            public String word;
            public int pos;
            public int movdelta;
            public set(String word,int pos,int movdelta){
                this.pos = pos;
                this.word = word;
                this.movdelta = movdelta;
            }
        }

        public void fill(){

            //lenar el HashMap con los tokens
            tokener.put("+", "tk_mas");tokener.put("-", "tk_menos");
            tokener.put("*", "tk_mult");tokener.put("/", "tk_div");
            tokener.put("%", "tk_mod");tokener.put("=", "tk_asig");
            tokener.put("<", "tk_menor");tokener.put(">", "tk_mayor");
            tokener.put("<=", "tk_menor_igual");tokener.put(">=", "tk_mayor_igual");
            tokener.put("==", "tk_igual");tokener.put("&&", "tk_y");
            tokener.put("||", "tk_o");tokener.put("!=", "tk_dif");
            tokener.put("!", "tk_neg");tokener.put(":", "tk_dosp");
            tokener.put("'", "tk_comilla_sen");tokener.put("\"", "tk_comilla_dob");
            tokener.put(";", "tk_pyc");tokener.put(",", "tk_coma");
            tokener.put(".", "tk_punto");tokener.put("(", "tk_par_izq");
            tokener.put(")", "tk_par_der");

            //llenar el HashMap con las palabras reservadas
            rwords.add("funcion_principal");rwords.add("fin_principal");
            rwords.add("booleano");rwords.add("caracter");
            rwords.add("entero");rwords.add("real");
            rwords.add("cadena");rwords.add("verdadero");
            rwords.add("falso");rwords.add("leer");
            rwords.add("imprimir");rwords.add("si");
            rwords.add("si_no");rwords.add("fin_si");
            rwords.add("entonces");rwords.add("para");
            rwords.add("fin_para");rwords.add("mientras");
            rwords.add("fin_mientras");rwords.add("hacer");
            rwords.add("seleccionar");rwords.add("caso");
            rwords.add("romper");rwords.add("defecto");
            rwords.add("fin_seleccionar");rwords.add("estructura");
            rwords.add("fin_estructura");rwords.add("funcion");
            rwords.add("fin_funcion");rwords.add("retornar");
            rwords.add("entre");
        }

        public static set delta(int state,char c){

            switch (state){
                case 1:
                    if (c == 32){/*return 1;*/ return new set("blank",0,1);}
                    if (c == 47){/*return 10;*/return new set("",1,9);}
                    if (c == 42){/*return 10;*/return new set("",1,10);}
                    if( c >= 65 && c <= 90 || c >= 97 && c <= 122 || c == 95){/*return 2;*/return new set("",1,2);}

                case 2:
                    if( c >= 65 && c <= 90 || c >= 97 && c <= 122 || c == 95){/*return 2;*/return new set("",0,2);}
                    if ( c >= 48 && c <= 57 ){/*return 2;*/return new set("",0,2);}
                    if ( c == 32){return new set("word",1,3);}
                    if ( c == 34){return new set("cadi",1,11);}
                    if ( c == 39){return new set("chri",1,13);}
                    if ( c == 62){return new set("word",1,15);}
                    if ( c == 60){return new set("word",1,17);}
                    if ( c == 61){return new set("word",1,19);}
                    if ( c == 33){return new set("word",1,21);}
                    if ( c == 124){return new set("word",1,23);}
                    if ( c == 38){return new set("word",1,25);}
                    if (tokener.containsKey(String.valueOf(c)) == true){return new set("word",1,4);}
                    if (c == 42){/*return 10;*/return new set("",1,10);}
                    else {return new set("other",0,-1);}

                case 3:
                    if ( c >= 65 && c <= 90 || c >= 97 && c <= 122 || c == 95){/*return 2;*/return new set("blank",1,2);}
                    if ( c == 32){/*return 3;*/return new set("",0,3);}
                    if ( c == 34){sicadi=true;return new set("blank",1,11);}
                    if ( c == 39){sichri=true;return new set("blank",1,13);}
                    if ( c == 62){return new set("blank",1,15);}
                    if ( c == 60){return new set("blank",1,17);}
                    if ( c == 61){return new set("blank",1,19);}
                    if ( c == 33){return new set("blank",1,21);}
                    if ( c == 124){return new set("blank",1,23);}
                    if ( c == 38){return new set("blank",1,25);}
                    if ( tokener.get(String.valueOf(c)) != null)return new set("blank",1,4);
                    if ( c >= 48 && c <= 57 ){/*return 5;*/return new set("blank",1,5);}
                    else{return new set("blank",1,8);}

                case 4:
                    if ( c == 32){return new set("tk",1,3);}
                    if( c >= 65 && c <= 90 || c >= 97 && c <= 122 || c == 95){/*return 2;*/return new set("tk",1,2);}
                    if ( c == 34){sicadi=true;return new set("tk",1,11);}
                    if ( c == 39){sichri=true;return new set("tk",1,13);}
                    if ( c == 62){return new set("tk",1,15);}
                    if ( c == 60){return new set("tk",1,17);}
                    if ( c == 61){return new set("tk",1,19);}
                    if ( c == 33){return new set("tk",1,21);}
                    if ( c == 124){return new set("tk",1,23);}
                    if ( c == 38){return new set("tk",1,25);}
                    if ( c >= 48 && c <= 57 ){/*return 5;*/;return new set("tk",1,5);}
                    if ( tokener.get(String.valueOf(c)) != null){/*return 4;*/return new set("tk",1,4);}

                case 5:
                    if( c >= 65 && c <= 90 || c >= 97 && c <= 122 || c == 95){return new set("tk_entero",0,2);}
                    if ( c >= 48 && c <= 57 ){/*return 5;*/;return new set("",0,5);}
                    if ( c == 34){return new set("cadi",1,11);}
                    if ( c == 32){return new set("tk_entero",1,3);}
                    if ( c == 39){return new set("chri",1,13);}
                    if ( c == 62){return new set("tk_entero",1,15);}
                    if ( c == 60){return new set("tk_entero",1,17);}
                    if ( c == 61){return new set("tk_entero",1,19);}
                    if ( c == 33){return new set("tk_entero",1,21);}
                    if ( c == 124){return new set("tk_entero",1,23);}
                    if ( c == 38){return new set("tk_entero",1,25);}
                    if ( c == 46){return new set("",0,6);}
                    if ( tokener.get(String.valueOf(c)) != null){return new set("tk_entero",1,4);}
                    else return new set("tk_entero",1,8);

                case 6:
                    if ( c >= 48 && c <= 57 ){return new set("",0,7);}
                    if ( c == 34){return new set("cadi",1,11);}
                    if ( c == 62){return new set("tk",1,15);}
                    if ( c == 60){return new set("tk",1,17);}
                    if ( c == 61){return new set("tk",1,19);}
                    if ( c == 33){return new set("tk",1,21);}
                    if ( c == 124){return new set("tk",1,23);}
                    if ( c == 38){return new set("tk",1,25);}
                    if ( c == 44){return new set("chri",1,13);}
                    else {return new set("tk_entero",2,8);}

                case 7:
                    if ( c >= 48 && c <= 57 ){return new set("",0,7);}
                    if ( c == 32){/*return 3;*/return new set("tk_real",1,3);}
                    if ( c == 34){return new set("cadi",1,11);}
                    if ( c == 39){return new set("chri",1,13);}
                    if ( c == 62){return new set("tk_real",1,15);}
                    if ( c == 60){return new set("tk_real",1,17);}
                    if ( c == 61){return new set("tk_real",1,19);}
                    if ( c == 33){return new set("tk_real",1,21);}
                    if ( c == 124){return new set("tk_real",1,23);}
                    if ( c == 38){return new set("tk_real",1,25);}
                    if ( tokener.get(String.valueOf(c)) != null){/*return 4;*/return new set("tk_real",1,4);}
                    else return new set("tk_real",1,8);

                case 8:
                    return new set("Error",1,8);

                case 9:
                    if (c == 47){/*return 10;*/return new set("COM",1,1);}
                    if (c == 42){/*return 10;*/return new set("COM2",1,9);}
                    else {return new set("tk",1,1);}

                case 10:
                    if (c == 47)
                        return new set("COM2f",1,10);

                case 11:
                    if ( c == 34){return new set("cadi",1,12);}
                    else{return new set("",1,11);}

                case 12:
                    if ( c == 32){/*return 3;*/return new set("cadf",1,3);}
                    if( c >= 65 && c <= 90 || c >= 97 && c <= 122 || c == 95){return new set("cadf",1,2);}
                    if ( c >= 48 && c <= 57 ){return new set("cadf",1,5);}
                    if ( tokener.get(String.valueOf(c)) != null){/*return 4;*/return new set("cadf",1,4);}

                case 13:
                    if ( c == 39){return new set("chri",1,14);}
                    else{return new set("",1,13);}

                case 14:
                    if ( c == 32){return new set("chrf",1,3);}
                    if( c >= 65 && c <= 90 || c >= 97 && c <= 122 || c == 95){return new set("chrf",1,2);}
                    if ( c >= 48 && c <= 57 ){return new set("chrf",1,5);}
                    if ( tokener.get(String.valueOf(c)) != null){/*return 4;*/return new set("chrf",1,4);}

                case 15:
                    if ( c == 61){return new set("",1,16);}
                    if ( c == 32){return new set("tk",1,3);}
                    if( c >= 65 && c <= 90 || c >= 97 && c <= 122 || c == 95){return new set("tk",1,2);}
                    if ( c >= 48 && c <= 57 ){return new set("tk",1,5);}
                    if ( tokener.get(String.valueOf(c)) != null){/*return 4;*/return new set("tk",1,4);}

                case 16:
                    if ( c == 32){return new set("tk_mayor_igual",1,3);}
                    if( c >= 65 && c <= 90 || c >= 97 && c <= 122 || c == 95){return new set("tk_mayor_igual",1,2);}
                    if ( c >= 48 && c <= 57 ){return new set("tk_mayor_igual",1,5);}
                    if ( tokener.get(String.valueOf(c)) != null){/*return 4;*/return new set("",1,4);}

                case 17:
                    if ( c == 61){return new set("",1,18);}
                    if ( c == 32){return new set("tk",1,3);}
                    if( c >= 65 && c <= 90 || c >= 97 && c <= 122 || c == 95){return new set("tk",1,2);}
                    if ( c >= 48 && c <= 57 ){return new set("tk",1,5);}
                    if ( tokener.get(String.valueOf(c)) != null){/*return 4;*/return new set("tk",1,4);}


                case 18:
                    if ( c == 32){return new set("tk_menor_igual",1,3);}
                    if( c >= 65 && c <= 90 || c >= 97 && c <= 122 || c == 95){return new set("tk_menor_igual",1,2);}
                    if ( c >= 48 && c <= 57 ){return new set("tk_menor_igual",1,5);}
                    if ( tokener.get(String.valueOf(c)) != null){/*return 4;*/return new set("",1,4);}

                case 19:
                    if ( c == 61){return new set("",1,20);}
                    if ( c == 32){return new set("tk",1,3);}
                    if( c >= 65 && c <= 90 || c >= 97 && c <= 122 || c == 95){return new set("tk",1,2);}
                    if ( c >= 48 && c <= 57 ){return new set("tk",1,5);}
                    if ( tokener.get(String.valueOf(c)) != null){/*return 4;*/return new set("tk",1,4);}

                case 20:
                    if ( c == 32){return new set("tk_igual",1,3);}
                    if( c >= 65 && c <= 90 || c >= 97 && c <= 122 || c == 95){return new set("tk_igual",1,2);}
                    if ( c >= 48 && c <= 57 ){return new set("tk_igual",1,5);}
                    if ( tokener.get(String.valueOf(c)) != null){/*return 4;*/return new set("",1,4);}

                case 21:
                    if ( c == 61){return new set("",1,22);}
                    if ( c == 32){return new set("tk",1,3);}
                    if( c >= 65 && c <= 90 || c >= 97 && c <= 122 || c == 95){return new set("tk",1,2);}
                    if ( c >= 48 && c <= 57 ){return new set("tk",1,5);}
                    if ( tokener.get(String.valueOf(c)) != null){/*return 4;*/return new set("tk",1,4);}

                case 22:
                    if ( c == 32){return new set("tk_dif",1,3);}
                    if( c >= 65 && c <= 90 || c >= 97 && c <= 122 || c == 95){return new set("tk_dif",1,2);}
                    if ( c >= 48 && c <= 57 ){return new set("tk_dif",1,5);}
                    if ( tokener.get(String.valueOf(c)) != null){/*return 4;*/return new set("",1,4);}

                case 23:
                    if ( c == 124){return new set("tk_o",1,24);}
                    if ( c == 32){return new set("tk",1,3);}
                    if( c >= 65 && c <= 90 || c >= 97 && c <= 122 || c == 95){return new set("tk",1,2);}
                    if ( c >= 48 && c <= 57 ){return new set("tk",1,5);}
                    if ( tokener.get(String.valueOf(c)) != null){/*return 4;*/return new set("tk",1,4);}

                case 24:
                    if ( c == 32){return new set("",1,3);}
                    if( c >= 65 && c <= 90 || c >= 97 && c <= 122 || c == 95){return new set("",1,2);}
                    if ( c >= 48 && c <= 57 ){return new set("",1,5);}
                    if ( tokener.get(String.valueOf(c)) != null){/*return 4;*/return new set("",1,4);}

                case 25:
                    if ( c == 38){return new set("tk_y",1,26);}
                    if ( c == 32){return new set("tk",1,3);}
                    if( c >= 65 && c <= 90 || c >= 97 && c <= 122 || c == 95){return new set("tk",1,2);}
                    if ( c >= 48 && c <= 57 ){return new set("tk",1,5);}
                    if ( tokener.get(String.valueOf(c)) != null){/*return 4;*/return new set("tk",1,4);}

                case 26:
                    if ( c == 32){return new set("",1,3);}
                    if( c >= 65 && c <= 90 || c >= 97 && c <= 122 || c == 95){return new set("",1,2);}
                    if ( c >= 48 && c <= 57 ){return new set("",1,5);}
                    if ( tokener.get(String.valueOf(c)) != null){/*return 4;*/return new set("",1,4);}
                default:
                    return new set("",0,1);

            }
        }
        public boolean search(String a){
            boolean hallo = false;
            String [] coll = {"tk_mayor_igual","tk_menor_igual","tk_igual","tk_dif","tk_o","tk_y"};
            for (int i = 0; i <coll.length ; i++) {
                if(a.equals(coll[i])){
                    hallo = true;break;
                }
            }
            return hallo;
        }
        public void All()throws IOException{

            System.out.println("Analisis Lexico");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            fill();
            String cadena = "";
            boolean com = false;
            int cadi = 0;int chri = 0;int row = 1;

            while((cadena = br.readLine()) != null){
                times = 0;
                Tokens = new ArrayList<>();
                cadena = cadena + " ";
                boolean only = true,only2 = true;
                int bk = 0;
                set s = new set("",0,1);

                for (int i = 0; i < cadena.length()  ; i++) {

                    s = delta(s.movdelta,cadena.charAt(i));
                    if (sicadi == true && only == true){cadi = i;only = false;}
                    if (sichri == true && only2 == true){chri = i;only2 = false;}

                    if(com == false){
                        if(s.word !="" || s.word !="blank"){
                            String a = "";
                            switch (s.word){
                                case "word":

                                    a = cadena.substring(bk,i);
                                    if (rwords.contains(a)==true){
                                        //System.out.println("<"+a+","+row+","+(bk+1)+">");
                                        Tokens.add(a);
                                    }
                                    else{
                                        //System.out.println("<"+"id"+","+a+","+row+","+(bk+1)+">");
                                        Tokens.add("id");
                                    }
                                    bk = i;
                                    break;

                                case "tk":
                                    String sim = String.valueOf(cadena.charAt(i-s.pos));
                                    //System.out.println("<"+tokener.get(sim)+","+row+","+(bk+1)+">");
                                    Tokens.add(tokener.get(sim));
                                    bk = i;
                                    break;

                                case "tk_entero":
                                    a = cadena.substring(bk,i);
                                    //System.out.println("<"+s.word+","+a+","+row+","+(bk+1)+">");
                                    Tokens.add(s.word);
                                    bk = i;
                                    break;

                                case "tk_real":
                                    a = cadena.substring(bk,i);
                                    //System.out.println("<"+s.word+","+a+","+row+","+(bk+1)+">");
                                    Tokens.add(s.word);
                                    bk = i;
                                    break;

                                case "cadi":
                                    if (sicadi == false){
                                        cadi = i;
                                        bk = i;
                                    }
                                    break;

                                case "cadf":
                                    //System.out.println("<"+"tk_cadena"+","+cadena.substring(cadi,i)+","+row+","+(bk+1)+">");
                                    Tokens.add("tk_cadena");
                                    sicadi = false;
                                    only = true;
                                    bk = i;
                                    break;
                                case "chri":
                                    if (sichri == false){
                                        chri = i;
                                        bk = i;
                                    }
                                    break;

                                case "chrf":
                                    //System.out.println("<"+"tk_caracter"+","+cadena.substring(chri,i)+","+row+","+(bk+1)+">");
                                    Tokens.add("tk_caracter");
                                    sichri = false;
                                    only2 = true;
                                    bk = i;

                                    break;
                                default:
                                    if(search(s.word) == true){
                                        //System.out.println("<"+s.word+","+row+","+(bk+1)+">");
                                        Tokens.add(s.word);
                                        bk = i;
                                    }
                                    break;
                            }
                        }
                        if(s.word == "blank") {bk=i+1-s.pos;}

                        if(s.word == "Error"){
                            System.out.println(">>> Error lexico (linea: " +row+", posicion: "+(bk+1)+")");
                            return;
                        }
                    }

                    if(s.word=="COM") {break;}
                    if(s.word=="COM2" && com == false) {
                        com = true;
                        break;
                    }
                    if(s.word == "COM2f" && com == true) {
                        com = false;
                        break;
                    }

                }
                row++;
                Sintax sin = new Sintax();
                System.out.println("Analisis Sintactico");
                sin.token = NextToken();
                sin.S();
            }

        }
        public static String NextToken() {
            System.out.println("NextToken!!!");
            String ans = " ";
            if (Tokens.size() > 0){
                ans = Tokens.get(times);
                times++;
            }
            return ans;
        }

    }

    public static class Sintax{

        public static HashMap<String,ArrayList<ArrayList<String>>> Gramatica = new HashMap<String,ArrayList<ArrayList<String>>>();
        public static HashMap<String,ArrayList<ArrayList<String>>> Prediccion = new HashMap<String,ArrayList<ArrayList<String>>>();
        public static HashMap<String,ArrayList<String>> First = new HashMap<String,ArrayList<String>>();
        public static HashMap<String,ArrayList<String>> Next = new HashMap<String,ArrayList<String>>();
        public static ArrayList<String> keys = new ArrayList<String>();

        public static void fillG(){
            ArrayList<ArrayList<String>> rules = new ArrayList<ArrayList<String>>();
            ArrayList<String> rul = new ArrayList<String>();

            rul.add("F");rul.add("E");rul.add("funcion_principal");rul.add("CODE");
            rul.add("fin_principal");rul.add("F");rul.add("E");rules.add(rul);
            rul = new ArrayList<String>();
            Gramatica.put("S",rules);keys.add("S");

            rul = new ArrayList<String>();rules = new ArrayList<ArrayList<String>>();

            rul.add("SC");rul.add("CODE");rules.add(rul);
            rul = new ArrayList<String>();
            rul.add("PYC");rul.add("CODE");rules.add(rul);
            rul = new ArrayList<String>();
            rul.add(" ");rules.add(rul);
            Gramatica.put("CODE",rules);keys.add("CODE");

            rul = new ArrayList<String>();rules = new ArrayList<ArrayList<String>>();

            rul.add("D");rul.add("OPL");rul.add("CV");
            rul.add("COND'");rules.add(rul);
            rul = new ArrayList<String>();
            rul.add("VT");rul.add("OPL");rul.add("CV");
            rul.add("COND'");rules.add(rul);
            rul = new ArrayList<String>();
            rul.add("tk_par_izq");rul.add("COND");rul.add("tk_par_der");
            rul.add("COND'");rules.add(rul);
            Gramatica.put("COND",rules);keys.add("COND");

            rul = new ArrayList<String>();rules = new ArrayList<ArrayList<String>>();

            rul.add("VT");rules.add(rul);
            rul = new ArrayList<String>();
            rul.add("D");rules.add(rul);
            Gramatica.put("CV",rules);keys.add("CV");

            rul = new ArrayList<String>();rules = new ArrayList<ArrayList<String>>();

            rul.add("OPL");rul.add("COND");rul.add("COND'");rules.add(rul);
            rul = new ArrayList<String>();
            rul.add(" ");rules.add(rul);
            Gramatica.put("COND'",rules);keys.add("COND'");

            rul = new ArrayList<String>();rules = new ArrayList<ArrayList<String>>();

            rul.add("D&");rul.add("tk_pyc");rul.add("PYC");rules.add(rul);
            rul = new ArrayList<String>();
            rul.add("COM");rul.add("    tk_pyc");rul.add("PYC");rules.add(rul);
            rul = new ArrayList<String>();
            rul.add(" ");rules.add(rul);
            Gramatica.put("PYC",rules);keys.add("PYC");

            rul = new ArrayList<String>();rules = new ArrayList<ArrayList<String>>();

            rul.add("T");rul.add("D");rul.add("A");rules.add(rul);
            rul = new ArrayList<String>();
            rul.add("D");rules.add(rul);
            rul = new ArrayList<String>();
            rul.add(" ");rules.add(rul);
            Gramatica.put("D&",rules);keys.add("D&");

            rul = new ArrayList<String>();rules = new ArrayList<ArrayList<String>>();

            rul.add("id");rul.add("D'");rules.add(rul);
            Gramatica.put("D",rules);keys.add("D");

            rul = new ArrayList<String>();rules = new ArrayList<ArrayList<String>>();

            rul.add("tk_punto");rul.add("id");rules.add(rul);
            rul = new ArrayList<String>();
            rul.add("id");rul.add("D'");rules.add(rul);
            rul = new ArrayList<String>();
            rul.add("tk_coma");rules.add(rul);
            rul = new ArrayList<String>();
            rul.add(" ");rules.add(rul);
            Gramatica.put("D'",rules);keys.add("D'");

            rul = new ArrayList<String>();rules = new ArrayList<ArrayList<String>>();

            rul.add("D");rul.add("tk_asig");rul.add("A");rul.add("A''");rules.add(rul);
            rul = new ArrayList<String>();
            rul.add("VT");rul.add("A'");rul.add("A''");rules.add(rul);
            rul = new ArrayList<String>();
            rul.add("tk_par_izq");rul.add("A");rul.add("tk_par_der");rul.add("A''");rules.add(rul);
            Gramatica.put("A",rules);keys.add("A");

            rul = new ArrayList<String>();rules = new ArrayList<ArrayList<String>>();

            rul.add("tk_coma");;rules.add(rul);
            rul = new ArrayList<String>();
            rul.add(" ");rules.add(rul);
            Gramatica.put("A'",rules);keys.add("A'");

            rul = new ArrayList<String>();rules = new ArrayList<ArrayList<String>>();

            rul.add("OPA");rul.add("A");rul.add("A");rul.add("A''");rules.add(rul);
            rul = new ArrayList<String>();
            rul.add(" ");rules.add(rul);
            Gramatica.put("A''",rules);keys.add("A''");

            rul = new ArrayList<String>();rules = new ArrayList<ArrayList<String>>();

            rul.add("romper");rules.add(rul);
            rul = new ArrayList<String>();
            rul.add("leer");rul.add(" tk_par_izq");rul.add("id");rul.add("tk_par_der");rules.add(rul);
            rul = new ArrayList<String>();
            rul.add("imprimir");rul.add(" tk_par_izq");rul.add("D");rul.add("tk_par_der");rules.add(rul);
            rul = new ArrayList<String>();
            rul.add("id");rul.add("tk_par_izq");rul.add("D");rul.add("tk_par_der");rules.add(rul);
            Gramatica.put("COM",rules);keys.add("COM");

            rul = new ArrayList<String>();rules = new ArrayList<ArrayList<String>>();

            rul.add("funcion");rul.add("T");rul.add("id");rul.add("tk_par_izq");
            rul.add("D");rul.add("tk_par_der");rul.add("hacer");rul.add("F'");
            rul.add("fin_funcion");rules.add(rul);
            rul = new ArrayList<String>();
            rul.add(" ");rules.add(rul);
            Gramatica.put("F",rules);keys.add("F");

            rul = new ArrayList<String>();rules = new ArrayList<ArrayList<String>>();

            rul.add("PYC");rul.add("retornar");rul.add("D");rules.add(rul);
            Gramatica.put("F'",rules);keys.add("F'");

            rul = new ArrayList<String>();rules = new ArrayList<ArrayList<String>>();

            rul.add("IF&");rul.add("SC");rules.add(rul);
            rul = new ArrayList<String>();
            rul.add("FOR&");rul.add("SC");rules.add(rul);
            rul = new ArrayList<String>();
            rul.add("WHILE&");rul.add("SC");rules.add(rul);
            rul = new ArrayList<String>();
            rul.add("DO_WHILE&");rul.add("SC");rules.add(rul);
            rul = new ArrayList<String>();
            rul.add("SWITCH&");rul.add("SC");rules.add(rul);
            rul = new ArrayList<String>();
            rul.add(" ");rul.add("SC");rules.add(rul);
            Gramatica.put("SC",rules);keys.add("SC");

            rul = new ArrayList<String>();rules = new ArrayList<ArrayList<String>>();

            rul.add("si");rul.add("tk_par_izq");rul.add("COND");rul.add("tk_par_der");
            rul.add("entonces");rul.add("PYC");rul.add("ELSE&");rul.add("fin_si");rules.add(rul);
            Gramatica.put("IF&",rules);keys.add("IF&");

            rul = new ArrayList<String>();rules = new ArrayList<ArrayList<String>>();

            rul.add("si_no");rul.add("PYC");rules.add(rul);
            rul = new ArrayList<String>();
            rul.add(" ");rules.add(rul);
            Gramatica.put("ELSE&",rules);keys.add("ELSE&");

            rul = new ArrayList<String>();rules = new ArrayList<ArrayList<String>>();

            rul.add("para");rul.add("tk_par_izq");rul.add("D");rul.add("tk_par_der");
            rul.add("COND");rul.add("tk_pyc");rul.add("D");rul.add("tk_par_der");
            rul.add("hacer");rul.add("PYC");rul.add("fin_para");rules.add(rul);
            Gramatica.put("FOR&",rules);keys.add("FOR&");

            rul = new ArrayList<String>();rules = new ArrayList<ArrayList<String>>();

            rul.add("mientras");rul.add("tk_par_izq");rul.add("COND");rul.add("tk_par_der");
            rul.add("hacer");rul.add("PYC");rul.add("fin_mientras");rules.add(rul);
            Gramatica.put("WHILE&",rules);keys.add("WHILE&");

            rul = new ArrayList<String>();rules = new ArrayList<ArrayList<String>>();

            rul.add("hacer");rul.add("PYC");rul.add("mientras");rul.add("tk_par_izq");
            rul.add("COND");rul.add("tk_par_der");rul.add("tk_pyc");rules.add(rul);
            Gramatica.put("DO_WHILE&",rules);keys.add("DO_WHILE&");

            rul = new ArrayList<String>();rules = new ArrayList<ArrayList<String>>();

            rul.add("seleccionar");rul.add("tk_par_izq");rul.add("id");
            rul.add("tk_par_der");rul.add("entre");rul.add("CASE&");
            rul.add("fin_seleccionar");rules.add(rul);
            Gramatica.put("SWITCH&",rules);keys.add("SWITCH&");

            rul = new ArrayList<String>();rules = new ArrayList<ArrayList<String>>();

            rul.add("caso");rul.add("VT");rul.add("tk_dosp");rul.add("PYC");
            rul.add("CASE&");rules.add(rul);
            rul = new ArrayList<String>();
            rul.add(" ");rules.add(rul);
            rul = new ArrayList<String>();
            rul.add("defecto");rul.add("tk_dosp");rul.add("PYC");rules.add(rul);
            Gramatica.put("CASE&",rules);keys.add("CASE&");

            rul = new ArrayList<String>();rules = new ArrayList<ArrayList<String>>();

            rul.add("estructura");rul.add("id");rul.add("D");rul.add("fin_estructura");rules.add(rul);
            Gramatica.put("E",rules);keys.add("E");

            rul = new ArrayList<String>();rules = new ArrayList<ArrayList<String>>();

            rul.add("caracter");rules.add(rul);
            rul = new ArrayList<String>();
            rul.add("booleano");rules.add(rul);
            rul = new ArrayList<String>();
            rul.add("cadena");rules.add(rul);
            rul = new ArrayList<String>();
            rul.add("entero");rules.add(rul);
            rul = new ArrayList<String>();
            rul.add("real");rules.add(rul);
            Gramatica.put("T",rules);keys.add("T");

            rul = new ArrayList<String>();rules = new ArrayList<ArrayList<String>>();

            rul.add("tk_entero");rules.add(rul);
            rul = new ArrayList<String>();
            rul.add("tk_real");rules.add(rul);
            rul = new ArrayList<String>();
            rul.add("tk_cadena");rules.add(rul);
            rul = new ArrayList<String>();
            rul.add("tk_caracter");rules.add(rul);
            rul = new ArrayList<String>();
            rul.add("verdadero");rules.add(rul);
            rul = new ArrayList<String>();
            rul.add("falso");rules.add(rul);
            Gramatica.put("VT",rules);keys.add("VT");

            rul = new ArrayList<String>();rules = new ArrayList<ArrayList<String>>();

            rul.add("tk_mas");rules.add(rul);
            rul = new ArrayList<String>();
            rul.add("tk_menos");rules.add(rul);
            rul = new ArrayList<String>();
            rul.add("tk_mult");rules.add(rul);
            rul = new ArrayList<String>();
            rul.add("tk_div");rules.add(rul);
            rul = new ArrayList<String>();
            rul.add("tk_mod");rules.add(rul);
            Gramatica.put("OPA",rules);keys.add("OPA");

            rul = new ArrayList<String>();rules = new ArrayList<ArrayList<String>>();

            rul.add("tk_menor");rules.add(rul);
            rul = new ArrayList<String>();
            rul.add("tk_mayor");rules.add(rul);
            rul = new ArrayList<String>();
            rul.add("tk_menor_igual");rules.add(rul);
            rul = new ArrayList<String>();
            rul.add("tk_mayor_igual");rules.add(rul);
            rul = new ArrayList<String>();
            rul.add("tk_igual");rules.add(rul);
            rul = new ArrayList<String>();
            rul.add("tk_y");rules.add(rul);
            rul = new ArrayList<String>();
            rul.add("tk_o");rules.add(rul);
            rul = new ArrayList<String>();
            rul.add("tk_dif");rules.add(rul);
            rul = new ArrayList<String>();
            rul.add("tk_neg");rules.add(rul);
            Gramatica.put("OPL",rules);keys.add("OPL");

        }

        public static ArrayList join(ArrayList a, ArrayList b){
            ArrayList c = new ArrayList<String>();
            for (int i = 0; i < a.size(); i++)
                c.add(a.get(i));

            for (int i = 0; i < b.size(); i++)
                c.add(b.get(i));

            return c;
        }

        public static ArrayList CalFirst(String key){

            String rule,rule2 ;
            ArrayList<String> elem = new ArrayList<String>();
            ArrayList<String> aux ;
            int index = 0;
            for (int i = 0; i < Gramatica.get(key).size(); i++) {

                rule = Gramatica.get(key).get(i).get(index);
                System.out.println("rule: "+rule);
                if (keys.contains(rule) == false){elem.add(rule);}
                else {
                    aux = CalFirst(rule);
                    if (aux.contains(" ")) {
                        elem = join(elem,aux);
                        aux = new ArrayList<>();
                        rule2 = Gramatica.get(key).get(i).get(index+1);
                        if(keys.contains(rule2) == false){elem.add(rule2);}
                        else{
                            if (key == rule2){aux = elem;}
                            else {
                                aux = CalFirst(Gramatica.get(key).get(i).get(index+1));
                                if (aux.contains(" ")){
                                    aux.add("funcion_principal");
                                }
                            }
                            elem = join(elem,aux);
                        }
                    }
                    elem = join(elem,aux) ;
                }
            }
            return elem;
        }

        public static ArrayList CalNext(String key){

            String em,em1=" ";
            ArrayList<String> elem = new ArrayList<String>();
            ArrayList<String> aux = new ArrayList<String>();
            int tam = 0 ;

            for (int i = 0; i < Gramatica.size() ; i++) {
                for (int j = 0; j < Gramatica.get(keys.get(i)).size()  ; j++) {
                    tam = Gramatica.get(keys.get(i)).get(j).size();
                    for (int k = 0; k < tam ; k++) {
                        em = Gramatica.get(keys.get(i)).get(j).get(k);
                        if (k < tam-1){em1 = Gramatica.get(keys.get(i)).get(j).get(k+1);}

                        if(em == key ){
                            if(k == tam - 1 ){

                                if(key == keys.get(i)){aux = elem;}
                                else{aux = CalNext(keys.get(i));}
                                elem = join(aux,elem);
                            }else{
                                if(keys.contains(em1) == true){
                                    elem = join(First.get(em1),elem);
                                    if (First.get(em1).contains(" ")){elem.remove(" ");}
                                    elem = join(aux,elem);

                                }else {elem.add(em1);break;}
                            }
                        }
                    }
                }
            }
            return elem;
        }

        public static ArrayList CalPred(String key){

            ArrayList<String> elem = new ArrayList<String>();
            String el,e1;
            ArrayList<ArrayList<String>> all = new ArrayList<>();
            for (int i = 0; i < Gramatica.get(key).size() ; i++) {

                el = Gramatica.get(key).get(i).get(0);
                elem = new ArrayList<String>();
                if(keys.contains(el)){
                    for (int j = 0; j < Gramatica.get(key).get(i).size() ; j++) {
                        e1 = Gramatica.get(key).get(i).get(j);
                        if(keys.contains(e1)){
                            if(First.get(e1).contains(" ")==true){
                                //System.out.println("contains e");
                                First.get(e1).remove(" ");
                                elem = join(elem,First.get(e1));
                                System.out.println("elemTpred: "+elem);
                            }else{
                                //System.out.println("no contains e");
                                elem = join(elem,First.get(e1));
                                System.out.println("elemTpred: "+elem);
                            }
                        }else{
                            elem.add(e1);
                            break;
                        }

                    }
                    elem = join(elem,Next.get(key));
                }else
                    elem.add(el);

                if(el.equals(" ")== true)
                    elem = Next.get(key);

                HashSet<String> hashSet;
                hashSet = new HashSet<String>(elem);
                elem.clear();
                elem.addAll(hashSet);
                all.add(elem);
            }
            return all;
        }

        public static void CalSets(){

            ArrayList<String> elem ;
            ArrayList<ArrayList<String>> all ;

            for (int i = 0; i < Gramatica.size(); i++) {

                elem = new ArrayList<String>();
                elem = CalFirst(keys.get(i));
                First.put(keys.get(i),elem);

            }

            First = UnicoN(First);
            First = quitE(First);

            for (int i = 0; i < Gramatica.size(); i++) {
                elem = new ArrayList<String>();
                elem = CalNext(keys.get(i));
                if (elem.size()==0)elem.add("$");
                Next.put(keys.get(i),elem);

            }

            Next = UnicoN(Next);


            for (int i = 0; i < Gramatica.size(); i++) {

                all = new ArrayList<ArrayList<String>>();
                //elem = new ArrayList<String>();
                all = CalPred(keys.get(i));
                //all.add(elem);
                Prediccion.put(keys.get(i),all);
            }

            for (int i = 0; i < Prediccion.size() ; i++) {

                System.out.println(keys.get(i)+": ");
                for (int j = 0; j < Prediccion.get(keys.get(i)).size() ; j++) {
                    System.out.println(Prediccion.get(keys.get(i)).get(j));
                }

            }
        }

        public static HashMap UnicoN(HashMap<String,ArrayList<String>> a){
            String key ;
            HashSet<String> hashSet;
            for (int i = 0; i < a.size() ; i++) {
                key = keys.get(i);
                hashSet = new HashSet<String>(a.get(key));
                a.get(key).clear();
                a.get(key).addAll(hashSet);

            }
            return a;
        }

        public static boolean exE(String key){
            boolean ans = false;
            for (int i = 0; i < Gramatica.get(key).size() ; i++) {
                for (int j = 0; j < Gramatica.get(key).get(i).size() ; j++) {
                    if(Gramatica.get(key).get(i).contains(" ")){
                        ans = true;break;
                    }
                }
            }
            return ans;
        }

        public static HashMap quitE(HashMap<String,ArrayList<String>> a){
            String key ;
            String val;
            ArrayList<String> temp;
            for (int i = 0; i < a.size() ; i++) {
                key = keys.get(i);
                if(a.get(key).contains(" ") && exE(key) == false)
                    a.get(key).remove(" ");
            }
            return a;
        }

        Lexico lexico = new Lexico();
        String token = " ";
        static  ArrayList<String> pred = new ArrayList<String>();
        public void Emparejar(String tokenE){
            if(token.equals(tokenE)){
                token = Lexico.NextToken();

            }else{
                System.out.println("ERRROR SINTAX: "+token);
            }
        }

        public void S(){
            System.out.println("in S");
            for (int i = 0; i < Prediccion.get("S").size() ; i++) {
                pred =Prediccion.get("S").get(i);
                System.out.println("PRED IN S: "+pred);
                System.out.println("token: "+token);
                if(pred.contains(token) == true){
                    F();
                    E();
                    Emparejar("funcion_principal");
                    CODE();
                    Emparejar("fin_principal");
                    F();
                    E();
                }else{
                    System.out.println("ERROR SINTAX");
                }
            }
        }
        public void CODE(){
            for (int i = 0; i < Prediccion.get("CODE").size() ; i++) {
                pred = Prediccion.get("CODE").get(i);
                if(pred.contains(token) && i == 0 ){
                    SC();
                    CODE();
                }
                if(pred.contains(token) && i == 1){
                    PYC();
                    CODE();
                }
                if(pred.contains(token) && i == 2){
                    Emparejar(" ");
                }
                else System.out.println("ERROR SINTAX");

            }
        }

        public void COND(){
            for (int i = 0; i < Prediccion.get("COND").size() ; i++) {
                pred = Prediccion.get("COND").get(i);
                if(pred.contains(token) && i == 0 ){
                    D();
                    OPL();
                    CV();
                    COND2();
                }
                if(pred.contains(token) && i == 1){
                    VT();
                    OPL();
                    CV();
                    COND2();
                }
                if(pred.contains(token) && i == 2){
                    Emparejar("tk_par_izq");
                    COND();
                    Emparejar("tk_par_der");
                    COND2();
                    Emparejar(" ");
                }
                else System.out.println("ERROR SINTAX");
            }
        }

        public void CV(){
            for (int i = 0; i < Prediccion.get("CV").size() ; i++) {
                pred = Prediccion.get("CV").get(i);
                if(pred.contains(token) && i == 0 )
                    VT();

                if(pred.contains(token) && i == 1)
                    D();

                else System.out.println("ERROR SINTAX");

            }
        }
        public void COND2(){
            for (int i = 0; i < Prediccion.get("COND'").size() ; i++) {
                pred = Prediccion.get("COND'").get(i);
                if(pred.contains(token) && i == 0 ){
                    OPL();
                    COND();
                    COND2();
                }
                if(pred.contains(token) && i == 1)
                    Emparejar(" ");

                else System.out.println("ERROR SINTAX");
            }
        }

        // function for COND'
        public void PYC(){

            for (int i = 0; i < Prediccion.get("PYC").size() ; i++) {
                pred = Prediccion.get("PYC").get(i);
                if(pred.contains(token) && i == 0 ){
                    DY();
                    Emparejar("tk_pyc");
                    PYC();
                }
                if(pred.contains(token) && i == 1){
                    COM();
                    Emparejar("tk_pyc");
                    PYC();
                }
                if(pred.contains(token) && i == 2)
                    Emparejar(" ");

                else System.out.println("ERROR SINTAX");
            }
        }

        public void DY(){

            for (int i = 0; i < Prediccion.get("D&").size() ; i++) {
                pred = Prediccion.get("D&").get(i);
                if(pred.contains(token) && i == 0 ){
                    T();
                    D();
                    A();
                }
                if(pred.contains(token) && i == 1)
                    D();

                if(pred.contains(token) && i == 2)
                    Emparejar(" ");

                else System.out.println("ERROR SINTAX");
            }
        }

        public void D(){
            pred = Prediccion.get("D").get(0);
            if(pred.contains(token)){
                Emparejar("id");
                D2();
            }
            else System.out.println("ERROR SINTAX");
        }

        public void D2(){

            for (int i = 0; i < Prediccion.get("D'").size() ; i++) {
                pred = Prediccion.get("D'").get(i);
                if(pred.contains(token) && i == 0 ){
                    Emparejar("tk_punto");
                    Emparejar("id");
                }
                if(pred.contains(token) && i == 1){
                    Emparejar("id");
                    D2();
                }
                if(pred.contains(token) && i == 2)
                    Emparejar("tk_coma");

                if(pred.contains(token) && i == 3)
                    Emparejar(" ");

                else System.out.println("ERROR SINTAX");
            }

        }

        public void A() {
            for (int i = 0; i < Prediccion.get("A").size() ; i++) {
                pred = Prediccion.get("A").get(i);
                if(pred.contains(token) && i == 0 ){
                    D();
                    Emparejar("tk_asig");
                    A();
                    A12();
                }
                if(pred.contains(token) && i == 1){
                    VT();
                    A2();
                    A12();
                }
                if(pred.contains(token) && i == 2){
                    Emparejar("tk_par_izq");
                    A();
                    Emparejar("tk_par_der");
                    A12();
                }
                else System.out.println("ERROR SINTAX");
            }
        }

        public void A2() {
            for (int i = 0; i < Prediccion.get("A'").size() ; i++) {
                pred = Prediccion.get("A'").get(i);
                if(pred.contains(token) && i == 0 )
                    Emparejar("tk_coma");

                if(pred.contains(token) && i == 1)
                    Emparejar(" ");

                else System.out.println("ERROR SINTAX");
            }
        }

        public void A12(){
            for (int i = 0; i < Prediccion.get("A''").size() ; i++) {
                pred = Prediccion.get("A''").get(i);
                if(pred.contains(token) && i == 0 ){
                    OPA();
                    A();
                    A12();
                }
                if(pred.contains(token) && i == 1 )
                    Emparejar(" ");

                else System.out.println("ERROR SINTAX");
            }
        }

        public void COM(){
            for (int i = 0; i < Prediccion.get("COM").size() ; i++) {
                pred = Prediccion.get("COM").get(i);
                if(pred.contains(token) && i == 0 )
                    Emparejar("romper");

                if(pred.contains(token) && i == 1){
                    Emparejar("leer");
                    Emparejar("tk_par_izq");
                    Emparejar("id");
                    Emparejar("tk_par_der");
                }
                if(pred.contains(token) && i == 2){
                    Emparejar("imprimir");
                    Emparejar("tk_par_izq");
                    D();
                    Emparejar("tk_par_der");
                }
                if(pred.contains(token) && i == 3){
                    Emparejar("id");
                    Emparejar("tk_par_izq");
                    D();
                    Emparejar("tk_par_der");
                }
                else System.out.println("ERROR SINTAX");
            }
        }

        public void F(){
            for (int i = 0; i < Prediccion.get("F").size() ; i++) {
                pred = Prediccion.get("F").get(i);
                if(pred.contains(token) && i == 0){
                    Emparejar("funcion");
                    T();
                    Emparejar("id");
                    Emparejar("tk_par_izq");
                    D();
                    Emparejar("tk_par_der");
                    Emparejar("hacer");
                    PYC();
                    Emparejar("retornar");
                    D();
                    Emparejar("fin_funcion");
                }
                if(pred.contains(token) && i == 1)
                    Emparejar(" ");

                else System.out.println("ERROR SINTAX");
            }
        }

        public void SC(){
            for (int i = 0; i < Prediccion.get("SC").size() ; i++) {
                pred = Prediccion.get("SC").get(i);
                if(pred.contains(token) && i == 0){
                    IFY();
                    SC();
                }
                if(pred.contains(token) && i == 1){
                    FORY();
                    SC();
                }
                if(pred.contains(token) && i == 2){
                    WHILEY();
                    SC();
                }
                if(pred.contains(token) && i == 3){
                    DO_WHILEY();
                    SC();
                }
                if(pred.contains(token) && i == 4){
                    SWITCHY();
                    SC();
                }
                if(pred.contains(token) && i == 5)
                    Emparejar(" ");

                else System.out.println("ERROR SINTAX");
            }
        }

        public void IFY(){
            pred = Prediccion.get("IF&").get(0);
            if(pred.contains(token)){
                Emparejar("si");
                Emparejar("tk_par_izq");
                COND();
                Emparejar("tk_par_der");
                Emparejar("entonces");
                PYC();
                ELSEY();
                Emparejar("fin_si");
                D2();
            }
            else System.out.println("ERROR SINTAX");
        }

        public void ELSEY(){
            for (int i = 0; i < Prediccion.get("ELSE&").size() ; i++) {
                pred = Prediccion.get("ELSE&").get(i);
                if(pred.contains(token) && i == 0){
                    Emparejar("si_no");
                    PYC();
                }
                if(pred.contains(token) && i == 1)
                    Emparejar(" ");

                else System.out.println("ERROR SINTAX");
            }
        }

        public void FORY(){
            pred = Prediccion.get("FOR&").get(0);
            if(pred.contains(token)){
                Emparejar("para");
                Emparejar("tk_par_izq");
                D();
                Emparejar("tk_pyc");
                COND();
                Emparejar("tk_pyc");
                D();
                Emparejar("tk_par_der");
                Emparejar("hacer");
                PYC();
                Emparejar("fin_para");
            }
            else System.out.println("ERROR SINTAX");
        }

        public void WHILEY(){
            pred = Prediccion.get("WHILE&").get(0);
            if(pred.contains(token)){
                Emparejar("mientras");
                Emparejar("tk_par_izq");
                COND();
                Emparejar("tk_par_der");
                Emparejar("hacer");
                PYC();
                Emparejar("fin_mientras");
            }
            else System.out.println("ERROR SINTAX");
        }

        public void DO_WHILEY(){
            pred = Prediccion.get("DO_WHILE&").get(0);
            if(pred.contains(token)){
                Emparejar("hacer");
                PYC();
                Emparejar("mientras");
                COND();
                Emparejar("tk_par_izq");
                COND();
                Emparejar("tk_par_der");
                Emparejar("tk_pyc");
            }
            else System.out.println("ERROR SINTAX");
        }

        public void SWITCHY(){
            pred = Prediccion.get("SWITCH&").get(0);

            if(pred.contains(token)){
                Emparejar("seleccionar");
                Emparejar("tk_par_izq");
                Emparejar("id");
                Emparejar("tk_par_der");
                Emparejar("entre");
                CASEY();
                Emparejar("fin_seleccionar");
            }
            else System.out.println("ERROR SINTAX");
        }

        public void CASEY(){

            for (int i = 0; i < Prediccion.get("CASE&").size() ; i++) {
                pred = Prediccion.get("CASE&").get(i);
                if(pred.contains(token) && i == 0){
                    Emparejar("caso");
                    VT();
                    Emparejar("tk_dosp");
                    PYC();
                    CASEY();
                }
                if(pred.contains(token) && i == 1)
                    Emparejar(" ");

                if(pred.contains(token) && i == 0){
                    Emparejar("defecto");
                    Emparejar("tk_dosp");
                    PYC();
                }
                else System.out.println("ERROR SINTAX");
            }
        }

        public void E(){
            pred = Prediccion.get("E").get(0);
            if(pred.contains(token)){
                Emparejar("estructura");
                Emparejar("id");
                D();
                Emparejar("fin_estructura");
            }
            else System.out.println("ERROR SINTAX");
        }

        public void T(){
            for (int i = 0; i < Prediccion.get("T").size() ; i++) {
                pred = Prediccion.get("T").get(i);
                if(pred.contains(token) && i == 0)
                    Emparejar("caracter");

                if(pred.contains(token) && i == 1)
                    Emparejar("booleano");

                if(pred.contains(token) && i == 2)
                    Emparejar("cadena");

                if(pred.contains(token) && i == 3)
                    Emparejar("entero");

                if(pred.contains(token) && i == 4)
                    Emparejar("real");

                else System.out.println("ERROR SINTAX");
            }
        }

        public void VT(){
            for (int i = 0; i < Prediccion.get("VT").size() ; i++) {
                pred = Prediccion.get("VT").get(i);
                if(pred.contains(token) && i == 0)
                    Emparejar("tk_entero");

                if(pred.contains(token) && i == 1)
                    Emparejar("tk_real");

                if(pred.contains(token) && i == 2)
                    Emparejar("tk_cadena");

                if(pred.contains(token) && i == 3)
                    Emparejar("tk_caracter");

                if(pred.contains(token) && i == 4)
                    Emparejar("verdadero");

                if(pred.contains(token) && i == 5)
                    Emparejar("falso");

                else System.out.println("ERROR SINTAX");
            }
        }

        public void OPA(){
            for (int i = 0; i < Prediccion.get("OPA").size() ; i++) {
                pred = Prediccion.get("OPA").get(i);
                if(pred.contains(token) && i == 0)
                    Emparejar("tk_mas");

                if(pred.contains(token) && i == 1)
                    Emparejar("tk_menos");

                if(pred.contains(token) && i == 2)
                    Emparejar("tk_mult");

                if(pred.contains(token) && i == 3)
                    Emparejar("tk_div");

                if(pred.contains(token) && i == 4)
                    Emparejar("tk_mod");

                else System.out.println("ERROR SINTAX");
            }
        }

        public void OPL(){
            for (int i = 0; i < Prediccion.get("OPL").size() ; i++) {
                pred = Prediccion.get("OPL").get(i);
                if(pred.contains(token) && i == 0)
                    Emparejar("tk_menor");

                if(pred.contains(token) && i == 1)
                    Emparejar("tk_mayor");

                if(pred.contains(token) && i == 2)
                    Emparejar("tk_menor_igual");

                if(pred.contains(token) && i == 3)
                    Emparejar("tk_mayor_igual");

                if(pred.contains(token) && i == 4)
                    Emparejar("tk_igual");

                if(pred.contains(token) && i == 5)
                    Emparejar("tk_y");

                if(pred.contains(token) && i == 6)
                    Emparejar("tk_o");

                if(pred.contains(token) && i == 7)
                    Emparejar("tk_dif");

                if(pred.contains(token) && i == 8)
                    Emparejar("tk_neg");

                else System.out.println("ERROR SINTAX");
            }
        }

    }

    public static void main(String  [] args)throws IOException{

        Sintax s = new Sintax();
        Lexico l = new Lexico();

        // Carga  lo nececsario para el analisis lexico
        l.fill();
        // Carga la gramatica
        s.fillG();
        // Calcula los conjuntos necesarios
        s.CalSets();
        // Realiza el analisis lexico y guarda los tokens
        l.All();

    }
}
