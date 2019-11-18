import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
public class TestClass {
    //static HashMap<Integer,String> Source = new HashMap<Integer,String>();
    static ArrayList<String> rwords = new ArrayList<String>();
    static HashMap<String,String> tokener = new HashMap<String, String>();

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

        //llenar "word" con las palabras reservadas
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

    }

    public static set delta(int state,char c){
        //set s = new set(" ",state);
        switch (state){
            case 1:
                if (c == 32){/*return 1;*/ return new set("blank",0,1);}
                // si es comentario
                if (c == 47){/*return 10;*/return new set("",1,9);}
                if (c == 42){/*return 10;*/return new set("",1,10);}
                if( c >= 65 && c <= 90 || c >= 97 && c <= 122 || c == 95){/*return 2;*/return new set("",1,2);}

            case 2:
                if( c >= 65 && c <= 90 || c >= 97 && c <= 122 || c == 95){/*return 2;*/return new set("",0,2);}
                if ( c >= 48 && c <= 57 ){/*return 2;*/return new set("",0,2);}
                if ( c == 32){/*return 3*/;return new set("word",1,3);}
                // comillas dobles
                if ( c == 34){return new set("cadi",1,11);}
                // comillas sencillas
                if ( c == 39){return new set("chri",1,13);}

                // mayor_igual
                if ( c == 62){return new set("word",1,15);}
                // menor_igual
                if ( c == 60){return new set("word",1,17);}
                // igual_igual
                if ( c == 61){return new set("word",1,19);}
                // diferente
                if ( c == 33){return new set("word",1,21);}
                // OR
                if ( c == 124){return new set("word",1,23);}
                // AND
                if ( c == 38){return new set("word",1,25);}


                if (tokener.containsKey(String.valueOf(c)) == true){/*return 4;*/return new set("word",1,4);}
                if (c == 42){/*return 10;*/return new set("",1,10);}
                else {/*return -1;*/return new set("other",0,-1);}

            case 3:
                if ( c >= 65 && c <= 90 || c >= 97 && c <= 122 || c == 95){/*return 2;*/return new set("blank",1,2);}
                if ( c == 32){/*return 3;*/return new set("",0,3);}
                // comillas dobles
                if ( c == 34){return new set("cadi",1,11);}
                // comillas sencillas
                if ( c == 39){return new set("chri",1,13);}

                // mayor_igual
                if ( c == 62){return new set("blank",1,15);}
                // menor_igual
                if ( c == 60){return new set("blank",1,17);}
                // igual_igual
                if ( c == 61){return new set("blank",1,19);}
                // diferente
                if ( c == 33){return new set("blank",1,21);}
                // OR
                if ( c == 124){return new set("blank",1,23);}
                // AND
                if ( c == 38){return new set("blank",1,25);}


                if ( tokener.get(String.valueOf(c)) != null){
                    return new set("blank",1,4);
                }
                if ( c >= 48 && c <= 57 ){/*return 5;*/return new set("blank",1,5);}
                else{return new set("blank",1,8);}

            case 4:

                if ( c == 32){/*return 3;*/return new set("tk",1,3);}
                if( c >= 65 && c <= 90 || c >= 97 && c <= 122 || c == 95){/*return 2;*/return new set("tk",1,2);}
                // comillas dobles
                if ( c == 34){return new set("cadi",1,11);}
                // comillas sencillas
                if ( c == 39){return new set("chri",1,13);}

                // mayor_igual
                if ( c == 62){return new set("tk",1,15);}
                // menor_igual
                if ( c == 60){return new set("tk",1,17);}
                // igual_igual
                if ( c == 61){return new set("tk",1,19);}
                // diferente
                if ( c == 33){return new set("tk",1,21);}
                // OR
                if ( c == 124){return new set("tk",1,23);}
                // AND
                if ( c == 38){return new set("tk",1,25);}


                if ( c >= 48 && c <= 57 ){/*return 5;*/;return new set("tk",1,5);}
                // cualquier token
                if ( tokener.get(String.valueOf(c)) != null){/*return 4;*/return new set("tk",1,4);}
            case 5:
                if ( c >= 48 && c <= 57 ){/*return 5;*/;return new set("",0,5);}
                // comillas dobles
                if ( c == 34){return new set("cadi",1,11);}
                // comillas sencillas
                if ( c == 39){return new set("chri",1,13);}

                // mayor_igual
                if ( c == 62){return new set("tk_entero",1,15);}
                // menor_igual
                if ( c == 60){return new set("tk_entero",1,17);}
                // igual_igual
                if ( c == 61){return new set("tk_entero",1,19);}
                // diferente
                if ( c == 33){return new set("tk_entero",1,21);}
                // OR
                if ( c == 124){return new set("tk_entero",1,23);}
                // AND
                if ( c == 38){return new set("tk_entero",1,25);}


                if ( c == 46){return new set("",0,6);}
                if ( tokener.get(String.valueOf(c)) != null){/*return 4;*/return new set("tk_entero",1,4);}

                // reemplazado por lo de arriba
                //else{/*return 8;*/return new set("tk_entero",1,8);}

            case 6:
                if ( c >= 48 && c <= 57 ){return new set("",0,7);}
                // comillas dobles
                if ( c == 34){return new set("cadi",1,11);}
                // comillas sencillas

                // mayor_igual
                if ( c == 62){return new set("tk",1,15);}
                // menor_igual
                if ( c == 60){return new set("tk",1,17);}
                // igual_igual
                if ( c == 61){return new set("tk",1,19);}
                // diferente
                if ( c == 33){return new set("tk",1,21);}
                // OR
                if ( c == 124){return new set("tk",1,23);}
                // AND
                if ( c == 38){return new set("tk",1,25);}


                if ( c == 44){return new set("chri",1,13);}
                else {return new set("tk_entero",2,8);}

            case 7:
                if ( c >= 48 && c <= 57 ){return new set("",0,7);}
                // comillas dobles
                if ( c == 34){return new set("cadi",1,11);}
                // comillas sencillas
                if ( c == 39){return new set("chri",1,13);}

                // mayor_igual
                if ( c == 62){return new set("tk_real",1,15);}
                // menor_igual
                if ( c == 60){return new set("tk_real",1,17);}
                // igual_igual
                if ( c == 61){return new set("tk_real",1,19);}
                // diferente
                if ( c == 33){return new set("tk_real",1,21);}
                // OR
                if ( c == 124){return new set("tk_real",1,23);}
                // AND
                if ( c == 38){return new set("tk_real",1,25);}


                if ( tokener.get(String.valueOf(c)) != null){/*return 4;*/return new set("tk_real",1,4);}
                //else if (tokener.get(String.valueOf(c)) != null){return 8;}
                //else {/*return -1;*/return new set("tk_real",1,8);}

            case 8:
                return new set("Error",1,8);
            case 9:
                if (c == 47){/*return 10;*/return new set("COM",1,1);}
                if (c == 42){/*return 10;*/return new set("COM2",1,9);}
                else {return new set("tk",1,1);}

            case 10:
                if (c == 47){
                    return new set("COM2f",1,10);
                }
            case 11:
                if ( c == 34){return new set("cadf",1,12);}
                else{return new set("",1,11);}

            case 12:
                if ( c == 32){/*return 3;*/return new set("",1,3);}
                if( c >= 65 && c <= 90 || c >= 97 && c <= 122 || c == 95){return new set("",1,2);}
                if ( c >= 48 && c <= 57 ){return new set("",1,5);}
                // cualquier token
                if ( tokener.get(String.valueOf(c)) != null){/*return 4;*/return new set("",1,4);}

            case 13:
                if ( c == 39){return new set("chrf",1,14);}
                else{return new set("",1,13);}

            case 14:
                if ( c == 32){return new set("",1,3);}
                if( c >= 65 && c <= 90 || c >= 97 && c <= 122 || c == 95){return new set("",1,2);}
                if ( c >= 48 && c <= 57 ){return new set("",1,5);}
                // cualquier token
                if ( tokener.get(String.valueOf(c)) != null){/*return 4;*/return new set("",1,4);}

            case 15:
                if ( c == 61){return new set("tk_mayor_igual",1,16);}

                if ( c == 32){return new set("tk",1,3);}
                if( c >= 65 && c <= 90 || c >= 97 && c <= 122 || c == 95){return new set("tk",1,2);}
                if ( c >= 48 && c <= 57 ){return new set("tk",1,5);}
                // cualquier token
                if ( tokener.get(String.valueOf(c)) != null){/*return 4;*/return new set("tk",1,4);}

            case 16:
                //if ( c == 61){return new set("tk_menor_igual",1,16);}
                if ( c == 32){return new set("",1,3);}
                if( c >= 65 && c <= 90 || c >= 97 && c <= 122 || c == 95){return new set("",1,2);}
                if ( c >= 48 && c <= 57 ){return new set("",1,5);}
                // cualquier token
                if ( tokener.get(String.valueOf(c)) != null){/*return 4;*/return new set("",1,4);}

            case 17:
                if ( c == 61){return new set("tk_igual",1,18);}

                if ( c == 32){return new set("tk",1,3);}
                if( c >= 65 && c <= 90 || c >= 97 && c <= 122 || c == 95){return new set("tk",1,2);}
                if ( c >= 48 && c <= 57 ){return new set("tk",1,5);}
                // cualquier token
                if ( tokener.get(String.valueOf(c)) != null){/*return 4;*/return new set("tk",1,4);}


            case 18:
                //if ( c == 61){return new set("tk_dif",1,18);}

                if ( c == 32){return new set("",1,3);}
                if( c >= 65 && c <= 90 || c >= 97 && c <= 122 || c == 95){return new set("",1,2);}
                if ( c >= 48 && c <= 57 ){return new set("",1,5);}
                // cualquier token
                if ( tokener.get(String.valueOf(c)) != null){/*return 4;*/return new set("",1,4);}

            case 19:
                if ( c == 61){return new set("tk_dif",1,20);}

                if ( c == 32){return new set("tk",1,3);}
                if( c >= 65 && c <= 90 || c >= 97 && c <= 122 || c == 95){return new set("tk",1,2);}
                if ( c >= 48 && c <= 57 ){return new set("tk",1,5);}
                // cualquier token
                if ( tokener.get(String.valueOf(c)) != null){/*return 4;*/return new set("tk",1,4);}

            case 20:
                //if ( c == 61){return new set("tk_dif",1,18);}

                if ( c == 32){return new set("",1,3);}
                if( c >= 65 && c <= 90 || c >= 97 && c <= 122 || c == 95){return new set("",1,2);}
                if ( c >= 48 && c <= 57 ){return new set("",1,5);}
                // cualquier token
                if ( tokener.get(String.valueOf(c)) != null){/*return 4;*/return new set("",1,4);}

            case 21:
                if ( c == 61){return new set("tk_dif",1,22);}

                if ( c == 32){return new set("tk",1,3);}
                if( c >= 65 && c <= 90 || c >= 97 && c <= 122 || c == 95){return new set("tk",1,2);}
                if ( c >= 48 && c <= 57 ){return new set("tk",1,5);}
                // cualquier token
                if ( tokener.get(String.valueOf(c)) != null){/*return 4;*/return new set("tk",1,4);}

            case 22:
                //if ( c == 61){return new set("tk_dif",1,18);}
                if ( c == 32){return new set("",1,3);}
                if( c >= 65 && c <= 90 || c >= 97 && c <= 122 || c == 95){return new set("",1,2);}
                if ( c >= 48 && c <= 57 ){return new set("",1,5);}
                // cualquier token
                if ( tokener.get(String.valueOf(c)) != null){/*return 4;*/return new set("",1,4);}

            case 23:
                if ( c == 124){return new set("tk_o",1,24);}

                if ( c == 32){return new set("tk",1,3);}
                if( c >= 65 && c <= 90 || c >= 97 && c <= 122 || c == 95){return new set("tk",1,2);}
                if ( c >= 48 && c <= 57 ){return new set("tk",1,5);}
                // cualquier token
                if ( tokener.get(String.valueOf(c)) != null){/*return 4;*/return new set("tk",1,4);}

            case 24:
                //if ( c == 124){return new set("tk_o",1,19);}

                if ( c == 32){return new set("",1,3);}
                if( c >= 65 && c <= 90 || c >= 97 && c <= 122 || c == 95){return new set("",1,2);}
                if ( c >= 48 && c <= 57 ){return new set("",1,5);}
                // cualquier token
                if ( tokener.get(String.valueOf(c)) != null){/*return 4;*/return new set("",1,4);}

            case 25:
                if ( c == 38){return new set("tk_y",1,26);}

                if ( c == 32){return new set("tk",1,3);}
                if( c >= 65 && c <= 90 || c >= 97 && c <= 122 || c == 95){return new set("tk",1,2);}
                if ( c >= 48 && c <= 57 ){return new set("tk",1,5);}
                // cualquier token
                if ( tokener.get(String.valueOf(c)) != null){/*return 4;*/return new set("tk",1,4);}

            case 26:
                //if ( c == 38){return new set("tk_y",1,20);}
                if ( c == 32){return new set("",1,3);}
                if( c >= 65 && c <= 90 || c >= 97 && c <= 122 || c == 95){return new set("",1,2);}
                if ( c >= 48 && c <= 57 ){return new set("",1,5);}
                // cualquier token
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
    public static void main(String args []) throws IOException {

        TestClass LB = new TestClass();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        LB.fill();
        String cadena = "";
        boolean com = false;
        // inicio de cadena
        int cadi = 0;
        int chri = 0;

        int row = 1;
        while((cadena = br.readLine()) != null){

            cadena = cadena + " ";

            int bk = 0;
            set s = new set("",0,1);
            for (int i = 0; i < cadena.length()  ; i++) {
                s = delta(s.movdelta,cadena.charAt(i));
                if(com == false){
                    if(s.word !="" || s.word !="blank"){
                        //System.out.println("cadenai: "+cadena.charAt(i));
                        //System.out.println("response: "+s.movdelta);
                        //System.out.println("rword: "+s.word);
                        String a = "";
                        switch (s.word){
                            case "word":
                                a = cadena.substring(bk,i);
                                if (rwords.contains(a)==true){System.out.println("<"+a+","+row+","+(bk+1)+">");}
                                else{System.out.println("<"+"id"+","+a+","+row+","+(bk+1)+">");}
                                bk = i;
                                break;

                            case "tk":
                                //System.out.println("chatti: "+cadena.charAt(i));
                                String sim = String.valueOf(cadena.charAt(i-s.pos));
                                //System.out.println("s.word(tk): "+sim);
                                System.out.println("<"+tokener.get(sim)+","+row+","+(bk+1)+">");
                                bk = i;
                                break;

                            case "tk_entero":
                                a = cadena.substring(bk,i);
                                System.out.println("<"+s.word+","+a+","+row+","+(bk+1)+">");
                                bk = i;
                                break;

                            case "tk_real":
                                a = cadena.substring(bk,i);
                                System.out.println("<"+s.word+","+a+","+row+","+(bk+1)+">");
                                bk = i;
                                break;

                            case "cadi":
                                cadi = i;
                                bk = i;
                                break;

                            case "cadf":
                                System.out.println("<"+"tk_cadena"+","+cadena.substring(cadi,(i+1))+","+row+","+(bk+1)+">");
                                bk = i+1;
                                break;
                            case "chri":
                                chri = i;
                                bk = i;
                                break;

                            case "chrf":
                                System.out.println("<"+"tk_caracter"+","+cadena.substring(chri,(i+1))+","+row+","+(bk+1)+">");
                                bk = i+1;

                                break;
                            default:
                                if(LB.search(s.word) == true){
                                    System.out.println("<"+s.word+","+row+","+(bk+1)+">");
                                    bk = i;
                                }
                                break;
                        }
                    }
                    if(s.word == "blank") {bk=i+1-s.pos;}


                    if(s.word == "Error"){
                        System.out.println(">>> Error lexico (linea: " +row+" ,posicion: "+(bk+1)+")");
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
        }
    }
}
