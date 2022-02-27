package two_pointer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 *
 * 백준 24523번: 내 뒤에 나와 다른 수
 * @see https://www.acmicpc.net/problem/24523
 *
 */
public class Boj24523 {

    private static final String SPACE = " ";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(twoPointer(arr));
    }

    private static String twoPointer(int[] arr) {
        StringBuilder sb = new StringBuilder();
        int left = 0;
        int right = 0;

        while(true) {
            if(right == arr.length) break;

            if(arr[left] != arr[right]) {
                sb.append(right + 1).append(SPACE);
                left++;
            }
            else {
                right++;
            }
        }

        for(int i = left; i < arr.length; i++) {
            sb.append(-1).append(SPACE);
        }

        return sb.toString();
    }
}
