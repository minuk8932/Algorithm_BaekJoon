package line_sweeping;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

/**
 *
 * @author exponential-e
 * 백준 1725번: 히스토그램
 *
 * @see https://www.acmicpc.net/problem/1725/
 *
 */
public class Boj1725 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] rec = new int[N];
        for(int i = 0; i < N; i++){
            rec[i] = Integer.parseInt(br.readLine());
        }

        System.out.println(most(N, rec));
    }

    private static int most(int n, int[] rec){
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int result = 0;

        for(int i = 0; i < n; i++){
            while(!stack.isEmpty() && rec[i] < rec[stack.peek()]){      // in stack > rec[i]
                int h = rec[stack.pop()];                               // get bigger one
                int w = i;

                if(!stack.isEmpty()) w = (i - stack.peek() - 1);        // width
                if(result < w * h) result = w * h;
            }

            stack.push(i);
        }

        while(!stack.isEmpty()){                        // is remained?
            int h = rec[stack.pop()];
            int w = n;

            if (!stack.isEmpty()) w = n - stack.peek() - 1;
            if (result < w * h) result = w * h;
        }

        return result;
    }
}
