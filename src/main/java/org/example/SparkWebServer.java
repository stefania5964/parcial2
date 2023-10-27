package org.example;


import java.util.ArrayList;
import java.util.List;

import static spark.Spark.*;
public class SparkWebServer {
    private static List<Integer> miLista = new ArrayList<Integer>();

    public static void main(String... args) {
        port(getPort());

        // Endpoint para cargar la página con el formulario
        get("/lucasseq", (req, res) -> {
            res.type("text/html");
            return "<html><body>" +
                    "<h1>Secuencia de Lucas</h1>" +
                    "<form method='post' action='/lucasseq'>" +
                    "Ingrese un valor: <input type='text' name='value'><br>" +
                    "<input type='submit' value='Calcular'>" +
                    "</form>" +
                    "</body></html>";
        });

        // Endpoint POST para procesar el formulario y devolver una respuesta JSON
        post("/lucasseq", (req, res) -> {
            res.type("application/json");
            int value = Integer.parseInt(req.queryParams("value"));
            List<Integer> x = getLucas(value);
            String operation = "Secuencia de Lucas";

            return "{\n" +
                    "\t\"operation\": \"" + operation + "\",\n" +
                    "\t\"input\": " + value + ",\n" +
                    "\t\"output\": " + x + "\n" +
                    "}";
        });
    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }

    private static List<Integer> getLucas(int x) {
        miLista.clear();
        int a = 2;
        int b = 1;
        miLista.add(a);
        miLista.add(b);
        for (int i = 2; i <= x; i++) {
            int c = miLista.get(i - 1) + miLista.get(i - 2);
            miLista.add(c);
        }
        return miLista;
    }
}



