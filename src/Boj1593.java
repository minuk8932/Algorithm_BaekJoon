import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Boj1593 {
    public static List<Integer> list = new ArrayList<>();
    private static long[] h;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long sum = 0;

        h = new long[N];
        for(int i = 0; i < N; i++){
            int node = Integer.parseInt(br.readLine());
            sum += getHeight(node);
        }

        System.out.println(sum);
    }

    private static int binarySearch(int start, int end, int key){
        while(start < end){
            int mid = (start + end) / 2;

            if(list.get(mid) < key) start = mid + 1;
            else end = mid;
        }

        return end;
    }

    private static long getHeight(int node){
        int size = list.size();
        int target = binarySearch(0, size, node);

        long left = target > 0 ? h[list.get(target - 1)] : 0;
        long right = target < size ? h[list.get(target)] : 0;

        h[node] = Math.max(left, right)+1;
        list.add(target, node);

        return h[node];
    }
}
