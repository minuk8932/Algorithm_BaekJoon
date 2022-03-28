package greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 12931번: 두 배 더하기
 *
 * @see https://www.acmicpc.net/problem/12931
 *
 */
public class Boj12931 {

    private static int length;
    private static int[] A;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        A = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            A[i] = Integer.parseInt(st.nextToken());

            if(A[i] != 0) continue;
            length++;
        }

        System.out.println(convertAToB());
    }

    private static int convertAToB() {
        int count = 0;

        while(length < A.length) {
            if(evenExist()) {
                divide();
                count++;
                continue;
            }

            for(int i = 0; i < A.length; i++) {
                if(A[i] % 2 == 0) continue;
                A[i]--;
                count++;

                if(A[i] != 0) continue;
                length++;
            }
        }

        return count;
    }

    private static void divide() {
        for(int i = 0; i < A.length; i++) {
            A[i] >>= 1;
        }
    }

    private static boolean evenExist() {
        for(int i = 0; i < A.length; i++) {
            if(A[i] == 0) continue;
            if(A[i] % 2 == 0) continue;

            return false;
        }

        return true;
    }
}
