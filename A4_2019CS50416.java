import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



public class A4_2019CS50416 {
    public HashMap<String, HashMap<String, Integer>> nodes = new HashMap<String, HashMap<String, Integer>>(400);
//    public ArrayList<str_int> arr_nodes = new ArrayList<>();
    public HashMap<String, Boolean> visited = new HashMap<String, Boolean>(400);
    public ArrayList<ArrayList<String>> components = new ArrayList<ArrayList<String>>();
    public ArrayList<String> one_component = new ArrayList<String>();
    public float V, E;

    class str_int{
        String string;
        int integer;
        str_int(String my_str, int my_int) {
            this.string = my_str;
            this.integer = my_int;
        }
    }

    public void average() {
        float z = (2 * E) / V;
        String rounded = String.format("%.2f", z);
        System.out.println(rounded);
    }

//    public void iterate_map() {
//        for (Map.Entry<String, HashMap<String, Integer>> entry : nodes.entrySet()) {
//            int t = 0;
//            if (entry.getValue()!=null){
//                for (Map.Entry<String, Integer> node : entry.getValue().entrySet()) {
//                    t = t + node.getValue();
//                }
//                str_int obj = new str_int(entry.getKey(), t);
//                arr_nodes.add(obj);
//            }
//            else {
//                str_int obj = new str_int(entry.getKey(), 0);
//                arr_nodes.add(obj);
//            }
//        }
//    }

    public void initialize() {
        for (Map.Entry<String, HashMap<String, Integer>> entry : nodes.entrySet()) {
            visited.put(entry.getKey(), false);
        }
    }

    public void mergesort(ArrayList<str_int> arrayList, int p, int r) {
        if (p<r) {
            int q = (p+r)/2;
            mergesort(arrayList, p, q);
            mergesort(arrayList, q+1, r);
            merge(arrayList, p, q, r);
        }
     }

    public void merge(ArrayList<str_int> arrayList, int p, int q, int r){
        ArrayList<str_int> my_new_array = new ArrayList<>();
        int lt = p;
        int rt = q+1;
        int u=0, v=p;
        while (lt<=q && rt <= r) {
            str_int left = arrayList.get(lt);
            str_int right = arrayList.get(rt);
            if (left.integer<=right.integer) {
                if (left.integer==right.integer) {
                    String str1 = left.string;
                    String str2 = right.string;
                    int val = str1.compareTo(str2);
                    if (val<0) {
                        my_new_array.add(left);
                        lt++;
                    }
                    else {
                        my_new_array.add(right);
                        rt++;
                    }
                }
                else {
                    my_new_array.add(left);
                    lt++;
                }
            }
            else {
                my_new_array.add(right);
                rt++;
            }
        }
         while(lt<=q){
             my_new_array.add(arrayList.get(lt));
             lt++;
         }
         while(rt<=r){
             my_new_array.add(arrayList.get(rt));
             rt++;
         }
         while (u<my_new_array.size()) {
             arrayList.set(v, my_new_array.get(u));
             u++;
             v++;
         }
     }

    public void independent_storylines_dfs(){
        DFS();
        mergesort_components(components, 0, components.size()-1);
        for (ArrayList<String> component:components) {
            mergesort_component(component, 0, component.size()-1);
        }
        print();
        components.removeAll(components);
        one_component.removeAll(one_component);
    }

    public void print() {
        for (int i = components.size()-1; i > -1; i--) {
            print_component(components.get(i));
            System.out.println();
        }
    }

    public void print_component(ArrayList<String> component){
        System.out.println(components.get(0).size());
        for (int i = component.size()-1;i>0;i--) {
            System.out.print(component.get(i)+",");
        }
        System.out.print(component.get(0));
    }

    public void merge_component(ArrayList<String> component, int p, int q, int r) {
        ArrayList<String> my_new_array = new ArrayList<>();
        int lt = p;
        int rt = q+1;
        int u=0, v=p;
        while (lt<=q && rt <= r) {
            String left = component.get(lt);
            String right = component.get(rt);
            if (left.compareTo(right)<0) {
                my_new_array.add(left);
                lt++;
            }
            else {
                my_new_array.add(right);
                rt++;
            }
        }
        while(lt<=q){
            my_new_array.add(component.get(lt));
            lt++;
        }
        while(rt<=r){
            my_new_array.add(component.get(rt));
            rt++;
        }
        while (u<my_new_array.size()) {
            component.set(v, my_new_array.get(u));
            u++;
            v++;
        }
    }
    public void mergesort_component(ArrayList<String> component, int p, int r) {
        if (p<r) {
            int q = (p+r)/2;
            mergesort_component(component, p, q);
            mergesort_component(component, q+1, r);
            merge_component(component, p, q, r);
        }
    }

    public void mergesort_components(ArrayList<ArrayList<String>> components, int p, int r) {
        if (p<r) {
            int q = (p+r)/2;
            mergesort_components(components, p, q);
            mergesort_components(components, q+1, r);
            merge_components(components, p, q, r);
        }
    }

    public void merge_components(ArrayList<ArrayList<String>> components, int p, int q, int r) {
        ArrayList<ArrayList<String>> my_new_array = new ArrayList<>();
        int lt = p;
        int rt = q+1;
        int u=0, v=p;
        while (lt<=q && rt <= r) {
            ArrayList<String> left = components.get(lt);
            ArrayList<String> right = components.get(rt);
            if (left.size()<=right.size()) {
                if (left.size()==right.size()) {
                    int a = left.size();
                    int i = 0;
                    while (i<a) {
                        if (left.get(i).compareTo(right.get(i))==0){}
                        else if (left.get(i).compareTo(right.get(i))<0) {
                            my_new_array.add(left);
                            break;
                        }
                        else {
                            my_new_array.add(right);
                            break;
                        }
                        i++;
                    }
                }
                else {
                    my_new_array.add(left);
                    lt++;
                }
            }
            else {
                my_new_array.add(right);
                rt++;
            }
        }
        while(lt<=q){
            my_new_array.add(components.get(lt));
            lt++;
        }
        while(rt<=r){
            my_new_array.add(components.get(rt));
            rt++;
        }
        while (u<my_new_array.size()) {
            components.set(v, my_new_array.get(u));
            u++;
            v++;
        }
    }

    public void DFS() {
        initialize();
        for (Map.Entry<String, HashMap<String, Integer>> hashmap_of_node : nodes.entrySet()) {
            if (!visited.get(hashmap_of_node.getKey())) {
                do_DFS(hashmap_of_node.getKey());
                ArrayList<String> temp = new ArrayList<String>();
                for (String s: one_component) {
                    temp.add(s);
                }
                components.add(temp);
                one_component.removeAll(one_component);
            }
        }
    }

    public void do_DFS(String node) {
        visited.put(node, true);
        one_component.add(node);
        HashMap<String, Integer> neighbours = nodes.get(node);
        for (String neighbour : neighbours.keySet()) {
            if (!visited.get(neighbour)) {
                do_DFS(neighbour);
            }
        }
    }

//    public void rank() {
//        iterate_map();
//        int j = arr_nodes.size();
////        System.out.println(j);
//        mergesort(arr_nodes, 0, j-1);
//        for (int t = j-1;t>0;t--) {
//            System.out.print(arr_nodes.get(t).string+",");
//        }
//        System.out.print(arr_nodes.get(0).string);
//    }

    public static void main(String[] args) throws Exception {
        String nodes_file = args[0];
        String edges_file = args[1];
        String function_name = args[2];
//        String nodes_file = args[0];
//        String edges_file = args[1];
//        String function_name = args[2];
        A4_2019CS50416 Graph = new A4_2019CS50416();
        int nodes = 0;
        BufferedReader reader = new BufferedReader(new FileReader(nodes_file));
        reader.readLine();
        String line = reader.readLine();
        while (line != null) {
            boolean write = false;
            String name = "";
            char[] word = line.toCharArray();
            int t = 0;
            int no_of_comma = 0;
            int no_of_quotation = 0;
            for (int j = 0; j < word.length; j++) {
                if (no_of_comma<1) {
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
            int no_of_quotation = 0;
            int no_of_comma = 0;
            for (int k = 0; k < word.length; k++) {
                if (no_of_comma<2) {
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

            if (Graph.nodes.get(a)==null) {
                HashMap<String, Integer> map = new HashMap<String, Integer>();
                map.put(b, weight);
                Graph.nodes.put(a, map);
            }
            else {
                HashMap map = Graph.nodes.get(a);
                map.put(b, weight);
            }
            if (Graph.nodes.get(b)==null) {
                HashMap<String, Integer> map = new HashMap<String, Integer>();
                map.put(a, weight);
                Graph.nodes.put(b, map);
            }
            else {
                HashMap map = Graph.nodes.get(b);
                map.put(a, weight);
            }
            line2 = reader2.readLine();
//            Graph.nodes
            edges++;
        }
        reader2.close();
        Graph.E = edges;
//        if (function_name.equals("average")) {
//            Graph.average();
//        }
//
//        else if (function_name.equals("rank")){
//            Graph.rank();
//        }
//        else if (function_name.equals("DFS")){
//            Graph.DFS();
//        }
        if (function_name.equals("independent_storylines_dfs")){
            Graph.independent_storylines_dfs();
        }
    }
}