package prefix_sum;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 12841번: 정보대 등산
 *
 * @see https://www.acmicpc.net/problem/12841/
 *
 */
public class Boj12841 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] cross = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            cross[i] = Integer.parseInt(st.nextToken());
        }

        long[] left = distance(n, br.readLine());           // make prefix sum
        long[] right = distance(n, br.readLine());

        System.out.println(getMinDistance(n, cross, left, right));
    }

    private static long[] distance(int n, String input){
        long[] arr = new long[n];
        StringTokenizer st = new StringTokenizer(input);

        for(int i = 1; i < arr.length; i++){
            arr[i] = Integer.parseInt(st.nextToken()) + arr[i - 1];
        }

        return arr;
    }

    private static String getMinDistance(int n, int[] c, long[] l, long[] r){
        long min = Long.MAX_VALUE;
        int idx = Integer.MAX_VALUE;

        for(int i = 0; i < c.length; i++){
            long sum = c[i] + (r[r.length - 1] - r[i]) + l[i];      // find minimum distance
            if(sum <= min){
                if(sum == min) idx = Math.min(idx , i);             // minimun cross road
                else idx = i;

                min = sum;
            }
        }

        StringBuilder sb = new StringBuilder();
        return sb.append(idx + 1).append(" ").append(min).toString();
    }
}
