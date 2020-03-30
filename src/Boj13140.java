import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj13140 {
    private static int[] numbers = new int[7];
    private static boolean[] visit;
    private static int[] result = {-1, -1};
    private static boolean flag;

    private static int N;

    private static final int LOOP = 10_000;
    private static final String SPACE = " ", DOUBLE = "  ";
    private static final String PLUS = "+ ";
    private static final String HYPEN = "-------";
    private static final String NEW_LINE = "\n";
    private static final String NO = "No Answer";

    public static void main (String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for(int i = 1; i < 10; i++){
            visit = new boolean[10];
            backTracking(i, 0);
        }

        StringBuilder sb = new StringBuilder();
        if (result[0] == -1 || result[1] == -1) {
            sb.append(NO);
        }
        else {
            sb.append(DOUBLE).append(result[0]).append(NEW_LINE)
                    .append(PLUS).append(result[1]).append(NEW_LINE)
                    .append(HYPEN).append(NEW_LINE)
                    .append(N >= 100_000 ? SPACE: DOUBLE).append(N);
        }

        System.out.println(sb.toString());
    }

    private static void backTracking(int current, int count) {
        if(flag) return;

        visit[current] = true;
        numbers[count] = current;

        if(count == 6){
            int a = make(LOOP, new int[]{0, 4, 2, 2, 3});
            int b = make(LOOP, new int[]{1, 3, 5, 2, 6});

//            for (int n: numbers) {
//                System.out.print(n);
//            }
//            System.out.println();

            if(N == a + b){
                flag = true;
                result[0] = a;
                result[1] = b;
            }

            return;
        }

        for(int next = 0; next < 10; next++){
            if(count == 0 && next == 0) continue;
            if(visit[next]) continue;

            backTracking(next, count + 1);
            visit[next] = false;
        }
    }

    private static int make (int loop, int[] idxs) {
        int res = 0;

        for(int i: idxs) {
            res += numbers[i] * loop;
            loop /= 10;
        }

        return res;
    }
}
