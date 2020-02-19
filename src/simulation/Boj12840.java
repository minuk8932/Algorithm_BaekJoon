package simulation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 12840번: 창용이의 시계
 *
 * @see https://www.acmicpc.net/problem/12840
 *
 */
public class Boj12840 {
    private static final String NEW_LINE = "\n";
    private static final String SPACE = " ";

    private static int[] src = new int[3];

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        src[0] = Integer.parseInt(st.nextToken());
        src[1] = Integer.parseInt(st.nextToken());
        src[2] = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();
        int tc = Integer.parseInt(br.readLine());

        int adder = 0;

        while(tc-- > 0){
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());

            if(cmd == 1){
                adder += Integer.parseInt(st.nextToken());      // add
            }
            else if(cmd == 2){
                adder -= Integer.parseInt(st.nextToken());      // sub
            }
            else{
                convert(adder);                                 // lazy
                sb.append(src[0]).append(SPACE).append(src[1]).append(SPACE).append(src[2]).append(NEW_LINE);
                adder = 0;
            }
        }

        System.out.println(sb.toString());
    }

    private static void convert(int a){
        int[] timer = {a / 3600, (a % 3600) / 60, a % 3600 % 60};

        timer[1] = makeTime(timer, 2);
        timer[0] = makeTime(timer, 1);

        src[0] += timer[0];                 // hour
        src[0] = src[0] % 24;
        if(src[0] < 0) src[0] += 24;
    }

    private static int makeTime(int[] timer, int idx){
        src[idx] += timer[idx];                 // make timer with adder
        timer[idx - 1] += src[idx] / 60;
        src[idx] %= 60;

        if(src[idx] < 0){
            src[idx] += 60;
            timer[idx - 1]--;
        }

        return timer[idx - 1];
    }
}
