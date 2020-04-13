import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeMap;

public class Boj1467 {
    private static int[] numbers = new int[10];
    private static ArrayList<Integer>[] index = new ArrayList[10];
    private static TreeMap<Integer, Integer> result = new TreeMap<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] target = br.readLine().toCharArray();
        char[] erase = br.readLine().toCharArray();

        System.out.println(removed(target, erase));
    }

    private static String removed(char[] t, char[] e) {
        for(int i = 0; i < 10; i++) {
            index[i] = new ArrayList<>();
        }

        for(int i = 0; i < t.length; i++) {
            int value = t[i] - '0';
            numbers[value]++;
            index[value].add(i);
        }

        for(int i = 1; i < 10; i++) {
            Collections.sort(index[i]);
        }

        for(int i = 0; i < e.length; i++) {
            numbers[e[i] - '0']--;
        }

        int prev = -1;

        for(int i = 9; i > 0; i--) {
            if (numbers[i] == 0) continue;

            for(int idx: index[i]) {
                if(numbers[i] == 0) break;
                result.put(idx, i);
                numbers[i]--;
            }
        }

        StringBuilder sb = new StringBuilder();

        for(int key: result.keySet()) {
            sb.append(result.get(key));
        }

        return sb.toString();
    }
}
