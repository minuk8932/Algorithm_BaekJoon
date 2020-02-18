package hashing;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.PriorityQueue;

/**
 *
 * @author exponential-e
 * 백준 4358번: 생태학
 *
 * @see https://www.acmicpc.net/problem/4358/
 *
 */
public class Boj4358 {
    private static HashMap<String, Integer> eco = new HashMap<>();
    private static final String NEW_LINE = "\n";
    private static final String SPACE = " ";

    private static int size;

    private static class Ecosystem implements Comparable<Ecosystem>{
        String name;
        double value;

        public Ecosystem(String name, double value){
            this.name = name;
            this.value = value;
        }

        @Override
        public int compareTo(Ecosystem e) {
            return this.name.compareTo(e.name);
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String name = "";
        while((name = br.readLine()) != null){
            if(eco.containsKey(name)) eco.put(name, eco.get(name) + 1);
            else eco.put(name, 1);
            size++;                     // total
        }

        System.out.println(print());
    }

    private static String print(){
        PriorityQueue<Ecosystem> pq = new PriorityQueue<>();
        Iterator<String> iter = eco.keySet().iterator();

        while(iter.hasNext()){
            String key = iter.next();
            pq.offer(new Ecosystem(key, eco.get(key) * 100.0 / size));      // make proportion
        }

        StringBuilder sb = new StringBuilder();

        while(!pq.isEmpty()){
            Ecosystem cur = pq.poll();
            sb.append(cur.name).append(SPACE).append(String.format("%.4f", cur.value)).append(NEW_LINE);
        }

        return sb.toString();
    }
}
