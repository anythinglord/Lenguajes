
import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
//import java.util.Iterator;



public class Sintax {

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

        rul.add("B");rul.add("C");rules.add(rul);
        rul = new ArrayList<String>();

        rul.add("ant");rul.add("A");rul.add("all");rules.add(rul);
        rul = new ArrayList<String>();

        Gramatica.put("A", rules);keys.add("A");

        rul = new ArrayList<String>();rules = new ArrayList<ArrayList<String>>();

        rul.add("big");rul.add("C");rules.add(rul);
        rul = new ArrayList<String>();

        rul.add("bus");rul.add("A");rul.add("boss");rules.add(rul);
        rul = new ArrayList<String>();

        rul.add(" ");rules.add(rul);
        Gramatica.put("B", rules);keys.add("B");

        rul = new ArrayList<String>();rules = new ArrayList<ArrayList<String>>();

        rul.add("cat");rules.add(rul);
        rul = new ArrayList<String>();

        rul.add("cow");rules.add(rul);
        Gramatica.put("C", rules);keys.add("C");
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

        String rule,rule2 ;
        ArrayList<String> elem = new ArrayList<String>();
        ArrayList<String> aux = new ArrayList<String>();

        // recorre todas las producciones del no terminal key 
        for (int i = 0; i < Gramatica.get(key).size(); i++) {
        	// recorre cada caracter de cada produccion
            rule = String.valueOf(Gramatica.get(key).get(i).get(0));
            //System.out.println("rule: "+rule);
        	// si es no terminal
            if (keys.contains(rule) == false){
                elem.add(rule);
            }else {
                aux = CalFirst(rule);

                if (aux.contains(" ")) {
                    elem = join(elem,aux);
            		aux = new ArrayList<String>();
            		for (int j = 1; j < Gramatica.get(key).get(i).size(); j++) {
            			rule2 = String.valueOf(Gramatica.get(key).get(i).get(j));
            			if (keys.contains(rule2)==true){
            				aux = CalFirst(rule2);
                        }else {
            				aux.add(rule2);
            				break;
            			}
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
                //System.out.println((j+1)+" "+tam);
                for (int k = 0; k < tam ; k++) {
                    em = Gramatica.get(keys.get(i)).get(j).get(k);
                    //System.out.println("em: "+em);
                    if (k < tam-1){
                        em1 = Gramatica.get(keys.get(i)).get(j).get(k+1);
                        //System.out.println("em1: "+em1);
                    }

                    if(em == key ){
                        if(k == tam-1){
                            aux = CalNext(keys.get(i));
                            elem = join(aux,elem);
                        }else{
                            // si es un No terminal
                            if(keys.contains(em1) == true){

                                elem = join(First.get(em1),elem);
                                if (First.get(em1).contains(" ")){
                                    elem = join(aux,elem);
                                }
                            }else{
                                elem.add(em1);
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
            /*for (int j = 0; j < a.get(key).size() ; j++) {
                val = a.get(key).get(j);
                if(val.equals(" ") && exE(key) == false)
                    a.get(key).remove(val);

            }*/
            if(a.get(key).contains(" ") && exE(key) == false)
                a.get(key).remove(" ");
        }
        return a;
    }
    public static void main(String[] args) {

        Sintax s = new Sintax();
        ArrayList<String> elem ;
        ArrayList<ArrayList<String>> all ;
        s.fillG();
        // Muestra la gramatica
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
