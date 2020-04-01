package implementation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 18795번: 이동하기 3
 *
 * @see https://www.acmicpc.net/problem/18795/
 *
 */
public class Boj18795 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        System.out.println(input(N + 1, br.readLine()) + input(M + 1, br.readLine()));
    }

    private static long input (int size, String line){
        long result = 0;

        StringTokenizer st = new StringTokenizer(line);
        for(int i = 1; i < size; i++){
            result += Long.parseLong(st.nextToken());
        }

        return result;
    }
}
