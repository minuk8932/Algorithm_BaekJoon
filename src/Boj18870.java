import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Boj18870 {
    private static TreeMap<Integer, Integer> sorted = new TreeMap<>();
    
    private static class Value implements Comparable<Value> {
        int value;
        int index;

        public Value (int value, int index) {
            this.value = value;
            this.index = index;
        }

        @Override
        public int compareTo(Value v) {
            return this.value < v.value ? -1: 1;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {

        }
    }
}
