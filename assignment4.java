import java.io.*;
import java.nio.file.Files;
import java.util.*;

public class assignment4 {
    public HashMap<String, HashMap<String, Integer>> nodes = new HashMap<String, HashMap<String, Integer>>(400);
    public float V, E;

    public void average() {
        float z = (2 * E) / V;
        System.out.println(z);
    }

    public void rank() {

    }

    public static void main(String[] args) throws Exception {
        assignment4 Graph = new assignment4();
        int nodes = 0;
        BufferedReader reader = new BufferedReader(new FileReader("nodes.csv"));
        reader.readLine();
        String line = reader.readLine();
        while (line != null) {
            boolean write = false;
            String name = "";
            char[] word = line.toCharArray();
//            System.out.println(word);
            int t = 0;
            int no_of_comma = 0;
            int no_of_quotation = 0;
            for (int j = 0; j < word.length; j++) {
                if (no_of_comma<1) {
//                    System.out.println(3);
                    if (word[j]=='"') {
                        no_of_quotation++;
                    }
                    else if (word[j]==',') {
                        if (no_of_quotation%2==0) {
                            no_of_comma++;
                        }
                    }
                    else {
                        continue;
                    }
                }
                else if (no_of_comma==1) {
                    if (word[j] != '"') {
                        name = name + word[j];
                    }
                }
                if (no_of_comma>1) {
                    System.out.println("Wrong");
                }
            }
            nodes++;
//            System.out.println(name);
            HashMap<String, Integer> list = new HashMap<String,Integer>();
            Graph.nodes.put(name, list);
            line = reader.readLine();
        }
        Graph.V = nodes;
        reader.close();
        BufferedReader reader2 = new BufferedReader(new FileReader("edges.csv"));
        reader2.readLine();
        String line2 = reader2.readLine();
        int edges = 0;
        while (line2!=null) {
            String a = "";
            String b = "";
            String w = "";
            char[] word = line2.toCharArray();
//            System.out.println(word);
            int no_of_quotation = 0;
            int no_of_comma = 0;
            for (int k = 0; k < word.length; k++) {
                if (no_of_comma<2) {
//                    System.out.println(3);
                    if (word[k]=='"') {
                        no_of_quotation++;
                    }
                    else if (word[k]==',') {
                        if (no_of_quotation%2==0) {
                            no_of_comma++;
                        }
                    }
                    else {
                        if (no_of_comma==0) {
                            if (word[k]!='"'){
                                a = a + word[k];
                            }
                        }
                        else {
                            if (word[k]!='"'){
                                b = b + word[k];
                            }
                        }
                    }
                }
                else if (no_of_comma==2) {
                    w = w+word[k];
                }
                if (no_of_comma>2) {
                    System.out.println("Wrong");
                }
            }
            int weight = Integer.parseInt(w);
            /*HashMap<String, Integer> val1 = */Graph.nodes.get(a).putIfAbsent(b, weight);
            /*HashMap<String, Integer> val2 = */Graph.nodes.get(b).putIfAbsent(a, weight);
//            val1.putIfAbsent(b, weight);
//            val2.putIfAbsent(a, weight);
//            map1.put(b, weight);
//            map2.put(a, weight);
//            Graph.nodes.put(a, map1);
//            Graph.nodes.put(b, map2);
//            System.out.println(a+b+weight);
            line2 = reader2.readLine();
//            Graph.nodes
            edges++;
        }
        reader2.close();
        Set<String> mapping = Graph.nodes.keySet();
        Collection<HashMap<String, Integer>> set= Graph.nodes.values();
        System.out.println(mapping.size());
    }
}
// Hellcat / Patsy Walker
// Hellcat / Patsy Walker
// Helcat / Patsy Walker