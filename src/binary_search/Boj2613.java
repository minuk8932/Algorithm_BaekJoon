package binary_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 2613번: 숫자 구슬
 *
 * @see https://www.acmicpc.net/problem/2613/
 *
 */
public class Boj2613 {
    private static int max;

    private static final String NEW_LINE = "\n";
    private static final String SPACE = " ";

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] bead = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            bead[i] = Integer.parseInt(st.nextToken());
            max += bead[i];
        }

        System.out.println(binarySearch(M, bead));
    }

    private static String binarySearch(int m, int[] arr){
        int start = 0, end = max;
        int res = max;

        while(start <= end) {
            int mid = (start + end) / 2;
            boolean flag = clustering(m, mid, arr);         // can find

            if(flag){
                end = mid - 1;
                if(mid < res) res = mid;
            }
            else {
                start = mid + 1;
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(res).append(NEW_LINE);

        int num = 0, sum = 0;
        int[] tmp = new int[400];
        int size = 1;

        for(int i = 0; i < arr.length; i++){
            sum += arr[i];
            num++;

            if(sum > res){
                sum = arr[i];
                tmp[size++] = num - 1;          // save value
                num = 1;
            }
        }

        tmp[size] = num;
        int differ = (m - size);

        for(int i = 1; i <= size; i++){
            if(tmp[i] == 1){
                sb.append(tmp[i]).append(SPACE);
                continue;
            }

            while(tmp[i] >= 2 && differ >= 1){          // divide by correct size
                sb.append(1).append(SPACE);
                tmp[i]--;
                differ--;
            }

            sb.append(tmp[i]).append(SPACE);
        }

        return sb.toString();
    }

    private static boolean clustering(int m, int target, int[] arr) {
        int sum = 0, count = 1;

        for (int i = 0; i < arr.length; i++) {
            if(arr[i] > target) return false;
            sum += arr[i];

            if (sum > target) {             // clustering each component
                count++;
                sum = arr[i];
            }
        }

        return count <= m;
    }
}
