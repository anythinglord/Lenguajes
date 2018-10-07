import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class SintaxPN {


    /**
     * @param args the command line arguments
     */
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
        rul.add(" ");
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
        rul.add("imprimir");rul.add(" tk_par_izq");rul.add("D");rul.add(" tk_par_der");rules.add(rul);
        rul = new ArrayList<String>();
        rul.add("id");rul.add("tk_par_izq");rul.add("D");rul.add(" tk_par_der");rules.add(rul);
        Gramatica.put("COM",rules);keys.add("COM");

        rul = new ArrayList<String>();rules = new ArrayList<ArrayList<String>>();

        rul.add("funcion");rul.add("T");rul.add("id");rul.add("tk_par_izq");
        rul.add("D");rul.add("tk_par_der");rul.add("hacer");rul.add("PYC");
        rul.add("retornar");rul.add("D");rul.add("fin_funcion");rules.add(rul);
        Gramatica.put("F",rules);keys.add("F");

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
        rul.add(" ");rules.add(rul);
        Gramatica.put("ELSE&",rules);keys.add("ELSE&");

        rul = new ArrayList<String>();rules = new ArrayList<ArrayList<String>>();

        rul.add("para");rul.add("tk_par_izq");rul.add("D");rul.add("tk_par_der");
        rul.add("COND");rul.add("tk_pyc");rul.add("D");rul.add("tk_par_der");
        rul.add("hacer");rul.add("PYC");rul.add("fin_para");rules.add(rul);
        Gramatica.put("FOR&",rules);keys.add("FOR&");

        rul = new ArrayList<String>();rules = new ArrayList<ArrayList<String>>();

        rul.add("mientras ");rul.add("tk_par_izq");rul.add("COND");rul.add("tk_par_der");
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

    public static void showH(HashMap<String,ArrayList<ArrayList<String>>> a){
        for (int i = 0; i < a.size(); i++) {
            System.out.println("No Teminal: "+keys.get(i));
            for (int j = 0; j < a.get(keys.get(i)).size(); j++) {
                for (int k = 0; k < a.get(keys.get(i)).get(j).size() ; k++) {
                    System.out.print(" "+a.get(keys.get(i)).get(j).get(k));
                }
                System.out.println();
            }
        }
    }

    public static void showHA(HashMap<String,ArrayList<String>> a){
        String key ;
        for (int i = 0; i < a.size() ; i++) {
            key = keys.get(i);
            for (int j = 0; j < a.get(key).size() ; j++) {
                System.out.println(key+": "+a.get(key).get(j));
            }
        }
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

        //System.out.println("calFirst");
        String rule,rule2 ;
        ArrayList<String> elem = new ArrayList<String>();
        ArrayList<String> aux = new ArrayList<String>();

        // recorre todas las producciones del no terminal key
        //System.out.println("Tamaño de la regla: "+Gramatica.get(key).size());
        for (int i = 0; i < Gramatica.get(key).size(); i++) {
            // recorre cada caracter de cada produccion
            rule = String.valueOf(Gramatica.get(key).get(i).get(0));
            //System.out.println("RULE: "+rule);
            //System.out.println("rule: "+rule);
            // si es no terminal
            if (keys.contains(rule) == false){
                //System.out.println("TERMINAL");
                elem.add(rule);
            }else {
                //System.out.println("NO TERMINAL");
                aux = CalFirst(rule);
                //System.out.println("aux: "+aux);
                if (aux.contains(" ")) {
                    elem = join(elem,aux);
                    aux = new ArrayList<String>();
            		/*for (int j = 1; j < Gramatica.get(key).get(i).size(); j++) {
                        System.out.println("RULEEEE2222222222222222222222222222222222");
            		    rule2 = Gramatica.get(key).get(i).get(j);
            			if (keys.contains(rule2) == true){
            				aux = CalFirst(rule2);

                        }else {
            				aux.add(rule2);
            				break;
            			}
            		}*/
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
        // Recorre toda la gramatica para buscar KEY
        for (int i = 0; i < Gramatica.size() ; i++) {

            // Busca en cada una de las Reglas
            for (int j = 0; j < Gramatica.get(keys.get(i)).size()  ; j++) {
                // Tamaño de las expresiones de cada regla
                tam = Gramatica.get(keys.get(i)).get(j).size();


                for (int k = 0; k < tam ; k++) {
                    em = Gramatica.get(keys.get(i)).get(j).get(k);
                    if (k < tam-1){
                        em1 = Gramatica.get(keys.get(i)).get(j).get(k+1);
                    }

                    if(em == key ){
                        if(k == tam - 1 ){
                            if(key == keys.get(i)){
                                //System.out.println("equalsQ");
                                aux = elem;
                            }else{
                                //System.out.println("NequalsQ");
                                aux = CalNext(keys.get(i));
                            }
                            //System.out.println("aux: "+aux);
                            elem = join(aux,elem);
                        }else{
                            // si es un NO terminal
                            if(keys.contains(em1) == true){
                                elem = join(First.get(em1),elem);
                                if (First.get(em1).contains(" ")){
                                    elem = join(aux,elem);
                                }
                            }else {
                                elem.add(em1);
                                break;
                            }
                        }

                    }
                }
            }
        }
        return elem;
    }
    public static ArrayList CalPred(String key){
        ArrayList<String> elem = new ArrayList<String>();
        String el;
        ArrayList<ArrayList<String>> all = new ArrayList<>();
        //ArrayList<String> aux = new ArrayList<String>();
        for (int i = 0; i < Gramatica.get(key).size() ; i++) {
            el = Gramatica.get(key).get(i).get(0);
            elem = new ArrayList<String>();
            //System.out.println("el: "+el);
            if(keys.contains(el)){
                //System.out.println("no terminal");
                if(First.get(el).contains(" ")==true){
                    //System.out.println("contains e");
                    First.get(el).remove(" ");
                    elem = join(elem,First.get(el));
                    elem = join(elem,Next.get(key));
                }else{
                    //System.out.println("no contains e");
                    elem = join(elem,First.get(el));
                }
            }else
                elem.add(el);

            if(el.equals(" ")== true)
                elem = Next.get(key);

            all.add(elem);


        }
        return all;
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
    // para saber si esta epsilon en una de las producciones
    public static boolean exE(String key){
        boolean ans = false;
        for (int i = 0; i < Gramatica.get(key).size() ; i++) {
            for (int j = 0; j < Gramatica.get(key).get(i).size() ; j++) {
                if(Gramatica.get(key).get(i).contains(" ")){
                    ans = true;
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


    public static void main(String [] args){
        SintaxPN s = new SintaxPN();
        ArrayList<String> elem ;
        ArrayList<ArrayList<String>> all ;
        s.fillG();
        // Muestra la gramatica
        System.out.println("tamaño gramatica: "+Gramatica.size());
        s.showH(Gramatica);

        //Calcular primeros por cada No terminal
        for (int i = 0; i < Gramatica.size(); i++) {
            elem = new ArrayList<String>();
            elem = s.CalFirst(keys.get(i));
            First.put(keys.get(i),elem);

        }

        //retira los repetidos
        First = s.UnicoN(First);
        //retira epsilon
        First = s.quitE(First);

        // Mostrar primeros
        System.out.println("FIRST");
        s.showHA(First);

        //Calcular siguientes por cada No terminal
        for (int i = 0; i < Gramatica.size(); i++) {
            elem = new ArrayList<String>();
            elem = s.CalNext(keys.get(i));
            if (elem.size()==0)elem.add("$");
            Next.put(keys.get(i),elem);

        }

        // Mostrar siguientes
        System.out.println("NEXT");
        Next = s.UnicoN(Next);
        s.showHA(Next);

        //Calcular predicción por cada No terminal

        for (int i = 0; i < Gramatica.size(); i++) {
            all = new ArrayList<ArrayList<String>>();
            elem = new ArrayList<String>();
            elem = s.CalPred(keys.get(i));
            all.add(elem);
            Prediccion.put(keys.get(i),all);
        }
        
        // Mostrar conjunto de predicción
        System.out.println("Predicción");
        //s.showH(Prediccion);
        for (int i = 0; i < Prediccion.size() ; i++) {
            System.out.println(keys.get(i)+"="+Prediccion.get(keys.get(i)));
        }

    }


}
