package breadth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 17114번: 하이퍼 토마토
 *
 *	@see https://www.acmicpc.net/problem/17114/
 *
 */
public class Boj17114 {
	private static Queue<Point> que = new LinkedList<>();
	private static int N, M, O, P, Q, R, S, T, U, V, W;
	private static int[][][][][][][][][][][] tomato;
	private static int[][][][][][][][][][][] visit;

	private static final int[][] DIRECTIONS = { { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, -1, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, -1, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, -1, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, -1, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, -1, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, -1, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, -1, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1 }, };

	private static class Point {
		int n;
		int m;
		int o;
		int p;
		int q;
		int r;
		int s;
		int t;
		int u;
		int v;
		int w;

		public Point(int w, int v, int u, int t, int s, int r, int q, int p, int o, int n, int m) {
			this.n = n;
			this.m = m;
			this.o = o;
			this.p = p;
			this.q = q;
			this.r = r;
			this.s = s;
			this.t = t;
			this.u = u;
			this.v = v;
			this.w = w;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		O = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		U = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());

		tomato = new int[W][V][U][T][S][R][Q][P][O][N][M];
		visit = new int[W][V][U][T][S][R][Q][P][O][N][M];

		for (int w = 0; w < W; w++) {
			for (int v = 0; v < V; v++) {
				for (int u = 0; u < U; u++) {
					for (int t = 0; t < T; t++) {
						for (int s = 0; s < S; s++) {
							for (int r = 0; r < R; r++) {
								for (int q = 0; q < Q; q++) {
									for (int p = 0; p < P; p++) {
										for (int o = 0; o < O; o++) {
											for (int n = 0; n < N; n++) {
												st = new StringTokenizer(br.readLine());
												
												for (int m = 0; m < M; m++) {
													tomato[w][v][u][t][s][r][q][p][o][n][m] = Integer.parseInt(st.nextToken());

													if (tomato[w][v][u][t][s][r][q][p][o][n][m] == 1) {
														visit[w][v][u][t][s][r][q][p][o][n][m] = 1;
														que.offer(new Point(w, v, u, t, s, r, q, p, o, n, m));
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}

		System.out.println(bfs());
	}

	private static int bfs() {
		while (!que.isEmpty()) {
			Point current = que.poll();

			for (final int[] DIRECTION : DIRECTIONS) {
				int nextM = current.m + DIRECTION[10];
				int nextN = current.n + DIRECTION[9];
				int nextO = current.o + DIRECTION[8];
				int nextP = current.p + DIRECTION[7];
				int nextQ = current.q + DIRECTION[6];
				int nextR = current.r + DIRECTION[5];
				int nextS = current.s + DIRECTION[4];
				int nextT = current.t + DIRECTION[3];
				int nextU = current.u + DIRECTION[2];
				int nextV = current.v + DIRECTION[1];
				int nextW = current.w + DIRECTION[0];

				if (isExceed(nextW, nextV, nextU, nextT, nextS, nextR, nextQ, nextP, nextO, nextN, nextM)) continue;
				if (visit[nextW][nextV][nextU][nextT][nextS][nextR][nextQ][nextP][nextO][nextN][nextM] != 0) continue;
				if (tomato[nextW][nextV][nextU][nextT][nextS][nextR][nextQ][nextP][nextO][nextN][nextM] == -1) continue;
				visit[nextW][nextV][nextU][nextT][nextS][nextR][nextQ][nextP][nextO][nextN][nextM] = 
						visit[current.w][current.v][current.u][current.t][current.s][current.r][current.q][current.p][current.o][current.n][current.m] + 1;

				que.offer(new Point(nextW, nextV, nextU, nextT, nextS, nextR, nextQ, nextP, nextO, nextN, nextM));
			}
		}

		return getDay();
	}

	private static boolean isExceed(int w, int v, int u, int t, int s, int r, int q, int p, int o, int n, int m) {
		return n < 0 || n >= N || m < 0 || m >= M || o < 0 || o >= O || p < 0 || p >= P || q < 0 || q >= Q || r < 0
				|| r >= R || s < 0 || s >= S || t < 0 || t >= T || u < 0 || u >= U || v < 0 || v >= V || w < 0
				|| w >= W;
	}

	private static int getDay() {
		int max = 0;

		for (int w = 0; w < W; w++) {
			for (int v = 0; v < V; v++) {
				for (int u = 0; u < U; u++) {
					for (int t = 0; t < T; t++) {
						for (int s = 0; s < S; s++) {
							for (int r = 0; r < R; r++) {
								for (int q = 0; q < Q; q++) {
									for (int p = 0; p < P; p++) {
										for (int o = 0; o < O; o++) {
											for (int n = 0; n < N; n++) {
												for (int m = 0; m < M; m++) {
													if (tomato[w][v][u][t][s][r][q][p][o][n][m] == 0
															&& visit[w][v][u][t][s][r][q][p][o][n][m] == 0) return -1;

													if (visit[w][v][u][t][s][r][q][p][o][n][m] > max) max = visit[w][v][u][t][s][r][q][p][o][n][m];
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}

		return max - 1;
	}
}
