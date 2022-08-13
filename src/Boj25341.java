import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Boj25341 {

    private static final String NEW_LINE = "\n";

    private static int size;
    private static ArrayList<ArtificialNeuralNetwork> networks;
    private static ArrayList<Integer> outputs;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        networks = new ArrayList<>();
        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int c = Integer.parseInt(st.nextToken());

            int[] p = new int[c];
            for(int i = 0; i < c; i++) {
                p[i] = Integer.parseInt(st.nextToken()) - 1;
            }

            long[] w = new long[c];
            for(int i = 0; i < c; i++) {
                w[i] = Long.parseLong(st.nextToken());
            }

            int b = Integer.parseInt(st.nextToken());
            networks.add(new ArtificialNeuralNetwork(c, p, w, b));
        }

        outputs = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        while(st.hasMoreTokens()) {
            outputs.add(Integer.parseInt(st.nextToken()));
        }

        size = networks.size();
        StringBuilder sb = new StringBuilder();

        while(Q-- > 0) {
            st = new StringTokenizer(br.readLine());

            long[] A = new long[N];
            for(int i = 0; i < N; i++) {
                A[i] = Long.parseLong(st.nextToken());
            }

            sb.append(neuralNetworkProcessing(A)).append(NEW_LINE);
        }

        System.out.println(sb);
    }

    private static long neuralNetworkProcessing(long[] a) {
        long answer = 0;
        long[] sums = new long[size];

        for(int i = 0; i < size; i++) {
            ArtificialNeuralNetwork current = networks.get(i);
            sums[i] = current.B * outputs.get(i);

            for(int j = 0; j < current.C; j++) {
                sums[i] += a[current.P[j]] * current.W[j] * outputs.get(i);
            }

            answer += sums[i];
        }

        return answer + outputs.get(size);
    }

    private static class ArtificialNeuralNetwork {
        int C;
        int[] P;
        long[] W;
        int B;

        public ArtificialNeuralNetwork(int c, int[] p, long[] w, int b) {
            C = c;
            P = p;
            W = w;
            B = b;
        }
    }
}