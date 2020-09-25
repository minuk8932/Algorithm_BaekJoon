package binary_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 19845번: 넴모넴모2020
 *
 * @see https://www.acmicpc.net/problem/19845
 *
 */
public class Boj19845 {
    private static final String NEW_LINE = "\n";
    private static Nemo[] nemo;

    private static class Nemo{
        long size;
        int index;

        public Nemo(long size, int index) {
            this.size = size;
            this.index = index;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        long[] arr = new long[N];
        nemo = new Nemo[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < arr.length; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }

        nemo[arr.length - 1] = new Nemo(arr[arr.length - 1], N);
        for(int i = arr.length - 2; i >= 0; i--) {                                  // data initialization
            if(arr[i + 1] == arr[i]) nemo[i] = new Nemo(arr[i], nemo[i + 1].index);
            else nemo[i] = new Nemo(arr[i], i + 1);
        }

        StringBuilder sb = new StringBuilder();
        while (Q-- > 0) {
            st = new StringTokenizer(br.readLine());
            int col = Integer.parseInt(st.nextToken()) - 1;
            int row = Integer.parseInt(st.nextToken()) - 1;

            sb.append(count(arr, row, col)).append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }

    private static long count(long[] arr, int r, int c) {
        if(arr[r] <= c) return 0;                           // out of range
        long target = r;

        int start = r, end = arr.length - 1;

        while(start <= end) {
            int mid = (start + end) / 2;

            if(nemo[mid].size >= c + 1) {                   // find candidate height
                start = mid + 1;
                target = Math.max(target, nemo[mid].index);
            }
            else {
                end = mid - 1;
            }
        }

        long dh = target - r;
        long width = arr[r] - c;

        if(width == 0 || dh == 0) return width + dh;
        else return width + dh - 1;
    }
}
