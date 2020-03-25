package implementation;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author exponential-e
 * 백준 17618번: 신기한 수
 *
 * @see https://www.acmicpc.net/problem/17618/
 *
 */
public class Boj17618 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        System.out.println(finder(N));
    }

    private static int finder(int n){
        int count = 0;

        for(int i = 1; i <= n; i++){
            int loop = i;
            int sum = 0;

            while(loop > 0){
                sum += (loop % 10);
                loop /= 10;
            }

            if(i % sum == 0) count++;
        }

        return count;
    }
}
