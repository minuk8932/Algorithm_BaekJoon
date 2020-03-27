import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj3980 {
    private static final String NEW_LINE = "\n";
    private static final int OVER = 2047;
    private static int visit, result;

    private static boolean[] pvisit;
    private static int[][] eleven;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int C = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        while (C-- > 0) {
            eleven = new int[11][11];
            pvisit = new boolean[11];
            result = 0;

            for (int i = 0; i < eleven.length; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());

                for (int j = 0; j < eleven.length; j++) {
                    eleven[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for (int i = 0; i < 11; i++) {
                if (eleven[0][i] == 0) continue;
                backTracking(i, 0, eleven[0][i]);
            }

            sb.append(result).append(NEW_LINE);
        }

        System.out.println(result);
    }

    private static void backTracking(int position, int person, int val) {
        if (visit == OVER) {
            result = Math.max(result, val);
            return;
        }

        pvisit[person] = true;

        int current = 1 << position;
        visit |= current;

        for (int per = 0; per < 11; per++) {
            if (pvisit[per]) continue;

            for (int pos = 0; pos < 11; pos++) {
                if (eleven[per][pos] == 0) continue;

                int next = 1 << pos;
                if ((visit & next) != 0) continue;

                backTracking(pos, per, val + eleven[per][pos]);
                visit ^= next;
            }

            pvisit[per] = false;
        }
    }
}
