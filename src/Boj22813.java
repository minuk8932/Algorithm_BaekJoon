import common.RealCoordinate;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj22813 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while(true) {
            int N = Integer.parseInt(br.readLine());
            if (N == 0) break;

            RealCoordinate[] coordinates = new RealCoordinate[N];
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                double x = Double.parseDouble(st.nextToken());
                double y = Double.parseDouble(st.nextToken());
                double z = Double.parseDouble(st.nextToken());
                double r = Double.parseDouble(st.nextToken());

                coordinates[i] = new
            }
        }

        System.out.println(sb.toString());
    }
}