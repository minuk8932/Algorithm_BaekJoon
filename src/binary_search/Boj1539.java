package binary_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Boj1539 {
    private static List<Integer> list = new ArrayList<>();
    private static Map<Integer, Integer> depth = new TreeMap<>();

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int d = 0;
        int prev = 0;

        for(int i = 0; i < N; i++){
            int val = Integer.parseInt(br.readLine());

            if (i == 0) {
                depth.put(val, d);
                prev = val;
                d++;
                continue;
            }

            int index = binarySearch(0, prev, val);
        }

        long sum = 0;
        for(int key: depth.keySet()){
            sum = sum + depth.get(key) + 1;
        }

        System.out.println(sum);
    }

    private static int binarySearch(int start, int end, int target){
        while(start < end){
            int mid = (start + end) / 2;

            if(list.get(mid) < target) start = mid + 1;
            else end = mid;
        }

        return end;
    }
}
