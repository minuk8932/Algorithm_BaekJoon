package binary_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 3078번: 좋은 친구
 *
 * @see https://www.acmicpc.net/problem/3078/
 *
 */
public class Boj3078 {
    private static final int CIPHER = 1_000_000;
    private static int[] member;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        member = new int[N];
        for(int i = 1; i <= N; i++){
            member[i - 1] = br.readLine().length() * CIPHER + i;
        }

        Arrays.sort(member);
        System.out.println(makePair(K));
    }

    private static long makePair(int k){
        long result = 0;
        int target = member[0];

        for(int i = 1; i < member.length; i++) {
            int start = i, end = member.length - 1;
            long idx = -1;

            while(start <= end){
                int mid = (start + end) / 2;

                int div = getDiv(target, member[mid]);  // name length
                int mod = getMod(target, member[mid]);  // rank

                if(div != 0){
                    end = mid - 1;
                }
                else{
                    if(mod > k){
                        end = mid - 1;
                    }
                    else{
                        start = mid + 1;
                        idx = mid;
                    }
                }
            }

            if(idx != -1) result += idx - i + 1;        // find good friends
            target = member[i];
        }

        return result;
    }

    private static int getDiv(int a, int b){
        return (a / CIPHER) ^ (b / CIPHER);
    }

    private static int getMod(int a, int b){
        return (b % CIPHER) - (a % CIPHER);
    }
}
