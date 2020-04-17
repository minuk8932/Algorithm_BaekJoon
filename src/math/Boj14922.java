package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 14922번: 부분 평균
 *
 * @see https://www.acmicpc.net/problem/14922/
 *
 */
public class Boj14922 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        double[] arr = new double[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            arr[i] = Double.parseDouble(st.nextToken());
        }

        System.out.println(minAvg(N, arr));
    }

    private static int minAvg(int n, double[] arr) {
        double min = Integer.MAX_VALUE;
        int idx = n;

        if(n == 2) return 0;

        for(int i = 1; i < n - 1; i++){
            double prev = (arr[i - 1] + arr[i]) / 2;                        // make partition
            double in = (arr[i - 1] + arr[i] + arr[i + 1]) / 3;
            double post = (arr[i] + arr[i + 1]) / 2;

            double org = min;
            min = Math.min(min, Math.min(prev, Math.min(post, in)));

            if(prev == min){
                if(org == min) idx = Math.min(idx, i - 1);
                else idx = i - 1;
            }
            else if(in == min){
                if(org == min) idx = Math.min(idx, i - 1);
                else idx = i - 1;
            }
            else{
                if(org == min) idx = Math.min(idx, i);
                else idx = i;
            }
        }

        return idx;
    }
}
