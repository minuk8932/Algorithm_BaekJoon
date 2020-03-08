package line_sweeping;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 6549번: 히스토그램에서 가장 큰 직사각형
 *
 * @see https://www.acmicpc.net/problem/6549/
 *
 */
public class Boj6549 {
    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            if(N == 0) break;

            long[] rec = new long[N];
            for(int i = 0; i < N; i++){
                rec[i] = Long.parseLong(st.nextToken());
            }

            sb.append(histogram(N, rec)).append(NEW_LINE);
        }

        System.out.println(sb.toString());
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

        while(!stack.isEmpty()){                        // is remained?
            long h = rec[stack.pop()];
            long w = n;

            if (!stack.isEmpty()) w = n - stack.peek() - 1;
            if (result < w * h) result = w * h;
        }

        return result;
    }
}
