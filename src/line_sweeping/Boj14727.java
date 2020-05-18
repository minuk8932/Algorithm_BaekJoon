package line_sweeping;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

/**
 *
 * @author exponential-e
 * 백준 14727번: 퍼즐 자르기
 *
 * @see https://www.acmicpc.net/problem/14727/
 *
 */
public class Boj14727 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        long[] h = new long[N];
        for(int i = 0; i < N; i++) {
            h[i] = Long.parseLong(br.readLine());
        }

        System.out.println(histogram(N, h));
    }

    private static long histogram(long n, long[] rec){
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        long result = 0;

        for(int i = 0; i < n; i++){
            while(!stack.isEmpty() && rec[i] < rec[stack.peek()]){      // in stack > rec[i]
                long h = rec[stack.pop()];                               // get bigger one
                long w = i;

                if(!stack.isEmpty()) w = (i - stack.peek() - 1);        // width
                if(result < w * h) result = w * h;
            }

            stack.push(i);
        }

        while(!stack.isEmpty()){                        // remained
            long h = rec[stack.pop()];
            long w = n;

            if (!stack.isEmpty()) w = n - stack.peek() - 1;
            if (result < w * h) result = w * h;
        }

        return result;
    }
}
