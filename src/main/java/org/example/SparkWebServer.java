package org.example;


import java.util.ArrayList;
import java.util.List;

import static spark.Spark.*;
public class SparkWebServer {
    private static List<Integer> miLista = new ArrayList<Integer>();
    private int variable = 0;
    public static void main(String... args){

        port(getPort());
        get("hello", (req,res) -> "Hello Docker!");

        get("lucasseq", (req,res) -> {
            int value = Integer.parseInt(req.queryParams("value"));
            List<Integer> x = getLucas(value);
            String operation = "Secuencia de Lucas";

            return "" +
                    "{\n" +
                    "\t\"operation\": \"" + operation + "\",\n" +
                    "\t\"input\": " + value + ",\n"+
                    "\t\"output\": \"" + x + "\"\n" +
                    "}";


        });
    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 22;
    }
    private static int getx(int x) {
        return x;
    }
    private static List<Integer> getLucas(int x){
        getx(x);
        miLista.clear();
        int a=2;
        int b=1;
        miLista.add(a);
        miLista.add(b);
        for(int i = 2; i <= x; i = i + 1) {
            int c = miLista.get(i - 1) + miLista.get(i - 2);
            miLista.add(c);
        }
        return miLista;




    }



}