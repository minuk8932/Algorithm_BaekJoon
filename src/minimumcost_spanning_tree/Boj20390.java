package minimumcost_spanning_tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 20390번: 완전그래프의 최소 스패닝 트리
 *
 * @see https://www.acmicpc.net/problem/20390
 *
 */
public class Boj20390 {
    private static long A, B, C, D;

    private static final long INF = 1_000_000_000_000_000_000L;
    private static boolean[] visit = new boolean[10_001];
    private static long[] X;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Long.parseLong(st.nextToken());
        B = Long.parseLong(st.nextToken());
        C = Long.parseLong(st.nextToken());
        D = Long.parseLong(st.nextToken());

        X = new long[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            X[i] = Long.parseLong(st.nextToken()) % C;
        }

        A %= C;
        B %= C;

        System.out.println(mst(N));
    }

    private static long mst(int n) {
        long result = 0;

        long[] dist = new long[n];
        Arrays.fill(dist, INF);

        dist[0] = 0;

        for (int i = 0; i < n; i++) {
            int cur = 0;
            long min = INF;

            for (int j = 0; j < n; j++) {
                if(visit[j]) continue;
                if(min <= dist[j]) continue;

                min = dist[j];
                cur = j;
            }

            visit[cur] = true;
            result += dist[cur];

            for (int j = 0; j < n; j++) {
                if (visit[j]) continue;

                long cost = getCost(cur, j);
                if (cost > dist[j]) continue;
                dist[j] = cost;
            }
        }

        return result;
    }

    /**
     *
     * find way: process correctly & fast
     *
     * @param a
     * @param b
     * @return
     */
    private static long getCost(int a, int b) {
        if (a > b){
            int tmp = a;
            a = b;
            b = tmp;
        }

        BigInteger Xa = new BigInteger(X[a] + "");
        BigInteger Xb = new BigInteger(X[b] + "");
        BigInteger AA = new BigInteger(A + "");
        BigInteger BB = new BigInteger(B + "");
        BigInteger CC = new BigInteger(C + "");
        BigInteger DD = new BigInteger(D + "");

        return Long.parseLong(((Xa.multiply(AA).add(Xb.multiply(BB))).mod(CC)).xor(DD).toString());
    }
}

/**
 * Solved by pypy3
 *
 * import sys
 *
 * X = [0] * 10001
 * global A
 * global B
 * global C
 * global D
 *
 * def get_cost(a, b):
 * 	if a > b:
 * 		tmp = a
 * 		a = b
 * 		b = tmp
 *
 * 	return ((X[a] * A + X[b] * B) % C) ^ D
 *
 * visit = [False] * 10001
 * dist = [1000000000000] * 10001
 *
 * def mst(n):
 * 	result = 0
 * 	dist[0] = 0
 *
 * 	for i in range(n):
 * 		cur = 0
 * 		min = 1000000000000000000
 *
 * 		for j in range(n):
 * 			if visit[j] or min <= dist[j]:
 * 				continue
 *
 * 			min = dist[j]
 * 			cur = j
 *
 * 		visit[cur] = True
 * 		result += dist[cur]
 *
 * 		for j in range(n):
 * 			if visit[j]:
 * 				continue
 *
 * 			cost = get_cost(cur, j)
 * 			if cost > dist[j]:
 * 				continue
 * 			dist[j] = cost
 *
 * 	return result
 *
 * input = __import__('sys').stdin.readline
 * n = int(input())
 * A, B, C, D = map(int, input().split())
 * X = [int(x) for x in input().split()]
 *
 * print(mst(n))
 */
