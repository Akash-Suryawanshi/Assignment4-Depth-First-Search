import java.io.*;
import java.util.ArrayList;
import java.util.Vector;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Iterator;
import java.util.Map;


public class assignment4 {
    public HashMap<String, HashMap<String, Integer>> nodes = new HashMap<String, HashMap<String, Integer>>(400);
    public float V, E;

    public void average() {
        float z = (2 * E) / V;
        String rounded = String.format("%.2f", z);
        System.out.println(rounded);
    }
    public void independent_storylines_dfs(){

    }

//    public void read_input

    public void rank() {

    }

    public static void main(String[] args) throws Exception {
        Scanner s = new Scanner(System.in);
        String[] input = s.nextLine().split(" ");
        String nodes_file = input[0];
        String edges_file = input[1];
        String function_name = input[2];
        assignment4 Graph = new assignment4();
        int nodes = 0;
        BufferedReader reader = new BufferedReader(new FileReader(nodes_file));
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
            Graph.nodes.put(name, null);
            line = reader.readLine();
        }
        Graph.V = nodes;
        reader.close();

        // ADDING EDGES

        BufferedReader reader2 = new BufferedReader(new FileReader(edges_file));
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
                    else if (word[k]==',' && no_of_quotation%2==0) {
                        no_of_comma++;
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
            HashMap<String, Integer> map1 = new HashMap<String, Integer>();
            map1.put(b, weight);
//            Graph.nodes.putIfAbsent(a, map1);
            HashMap<String, Integer> map2 = new HashMap<String, Integer>();
            map2.put(a, weight);
//            Graph.nodes.putIfAbsent(b, map2);

//            if (Graph.nodes.get(a)==null) {
//                HashMap<String, Integer> map = new HashMap<String, Integer>();
//                map.put(b, weight);
//                Graph.nodes.put(a, map);
//            }
//            if (Graph.nodes.get(b)==null) {
//                HashMap<String, Integer> map = new HashMap<String, Integer>();
//                map.put(a, weight);
//                Graph.nodes.put(b, map);
//            }


            /*HashMap<String, Integer> val2 = */
//            Graph.nodes.get(b).putIfAbsent(a, weight);
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
        Graph.E = edges;
        int mapping = Graph.nodes.size();
//        Collection<HashMap<String, Integer>> set= Graph.nodes.values();
//        System.out.println(edges);
//        System.out.println(mapping);
        if (function_name=="average") {
            Graph.average();
        }
        else if (function_name=="rank"){
            Graph.rank();
        }
        else if (function_name=="independent_storylines_dfs"){
            Graph.independent_storylines_dfs();
        }
    }
}
// Hellcat / Patsy Walker
// Hellcat / Patsy Walker
// Helcat / Patsy Walker