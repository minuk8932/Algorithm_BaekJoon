package line_sweeping;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 12846번: 무서운 아르바이트
 *
 * @see https://www.acmicpc.net/problem/12846/
 *
 */
public class Boj12846 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[] rec = new long[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            rec[i] = Long.parseLong(st.nextToken());
        }

        System.out.println(histogram(N, rec));
    }

    private static long histogram(long n, long[] rec){
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        long result = 0;

        for(int i = 0; i < n; i++){
            while(!stack.isEmpty() && rec[i] < rec[stack.peek()]){      // find optimistic pay & duration
                long h = rec[stack.pop()];
                long w = i;

                if(!stack.isEmpty()) w = (i - stack.peek() - 1);
                if(result < w * h) result = w * h;
            }

            stack.push(i);
        }

        while(!stack.isEmpty()){
            long h = rec[stack.pop()];
            long w = n;

            if (!stack.isEmpty()) w = n - stack.peek() - 1;
            if (result < w * h) result = w * h;
        }

        return result;
    }
}
