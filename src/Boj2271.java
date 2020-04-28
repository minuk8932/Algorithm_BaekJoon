import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.SortedSet;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Boj2271 {
    private static SortedSet<Integer> set = new TreeSet<>();

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        while(T-- > 0){
            int N = Integer.parseInt(br.readLine());

            int[] arr = new int[N];
            int[] rev = new int[N];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i = 0; i < N; i++){
                arr[i] = Integer.parseInt(st.nextToken());
                rev[N - 1 - i] = arr[i];
            }

            sb.append(weakable(N, arr) || weakable(N, rev) ? "Yes\n": "No\n");
        }

        System.out.println(sb.toString());
    }

    private static boolean weakable(int n, int [] arr) {
        for (int i = 0 ; i < n ; ++i) {
            int max = 0;

            for (int j = i + 1 ; j < n ; ++j) {
                if(arr[i] >= arr[j] || arr[j] >= max){
                    max = Math.max(max, arr[j]);
                    continue;
                }

                SortedSet<Integer> val = set.tailSet(max);
                if (val.last() != val.last() && arr[j] < -val.first()) {
                    return true;
                }
            }

            set.add(-arr[i]);
        }

        return false;
    }
}
