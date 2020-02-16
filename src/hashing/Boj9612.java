package hashing;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 *
 * @author exponential-e
 * 백준 9612번: Maximum word Frequency
 *
 * @see https://www.acmicpc.net/problem/9612/
 *
 */
public class Boj9612 {
    private static HashMap<String, Integer> count = new HashMap<>();

    public  static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        int max = 0;

        while(n-- > 0){
            String word = br.readLine();

            if(count.containsKey(word)) count.put(word, count.get(word) + 1);
            else count.put(word, 1);

            if(count.get(word) > max) max = count.get(word);
        }

        System.out.println(getWord(max) + " " + max);
    }

    private static String getWord(int m){
        ArrayList<String> list = new ArrayList<>();

        for(String key: count.keySet()){
            if(count.get(key) == m) list.add(key);
        }

        Collections.sort(list);
        return list.get(list.size() - 1);
    }
}
