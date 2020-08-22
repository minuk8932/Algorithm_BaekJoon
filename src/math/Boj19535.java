package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 19535번: ㄷㄷㄷㅈ
 *
 * @see https://www.acmicpc.net/problem/19535
 *
 */
public class Boj19535 {
    private static ArrayList<Integer>[] tree;
    private static final String DUN = "DUDUDUNGA", D = "D", G = "G";

    private static final BigInteger THREE = new BigInteger("3");

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        tree = new ArrayList[N];
        for(int i = 0; i < N; i++) {
            tree[i] = new ArrayList<>();
        }

        int loop = N - 1;
        while(loop-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;

            tree[u].add(v);
            tree[v].add(u);
        }

        System.out.println(treeShape(N));
    }

    private static String treeShape(int n) {
        BigInteger g = BigInteger.ZERO;
        BigInteger d = BigInteger.ZERO;

        for(int i = 0; i < n; i++) {            // find shape G, combination(n, 3)
            long linked = tree[i].size();

            if(linked > 2) {
                g = g.add(sequentialMultiply(new BigInteger(linked + "")).divide(new BigInteger("6")));
            }
        }

        for(int cur = 0; cur < n; cur++) {      // find shape D, (nsize - 1) * (msize - 1)
            int a, b;

            for(int adj: tree[cur]) {
                a = tree[cur].size() - 1;
                b = tree[adj].size() - 1;

                d = d.add(new BigInteger(a + "").multiply(new BigInteger(b + "")));
            }
        }

        d = d.divide(new BigInteger("2"));

        if(d.compareTo(g.multiply(THREE)) > 0) return D;
        else if(d.compareTo(g.multiply(THREE)) < 0) return G;
        else return DUN;
    }

    private static BigInteger sequentialMultiply(BigInteger a) {
        return a.multiply(a.subtract(BigInteger.ONE)).multiply(a.subtract(new BigInteger("2")));
    }
}
