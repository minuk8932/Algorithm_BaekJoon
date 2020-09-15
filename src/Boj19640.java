import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Boj19640 {
    private static ArrayList<Empolyee> emps = new ArrayList<>();

    private static class Empolyee {
        int date;
        long hurry;
        int index;
        boolean isdeck;

        public Empolyee(int date, long hurry, int index, boolean isdeck) {
            this.date = date;
            this.hurry = hurry;
            this.index = index;
            this.isdeck = isdeck;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            long h = Long.parseLong(st.nextToken());

            emps.add(new Empolyee(d, h, i % M, K == 0));
            K--;
        }
    }
}
