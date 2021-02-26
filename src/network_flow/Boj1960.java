package network_flow;

import java.io.*;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 1960번: 행렬만들기
 *
 * @see https://www.acmicpc.net/problem/1960
 * Java get OOM & I dont know reason -> C++17
 *
 */
public class Boj1960 {

    private static int[][] capacity;
    private static int[][] flow;
    private static ArrayList<Integer>[] connection;

    private static int[] row, col;
    private static int[] counter = new int[2];

    private static int S, T, N;
    private static int size;

    public static void main(String[] args) {
        InputReader in = new InputReader(System.in);
        N = in.readInt();
        init();

        for(int i = 1; i <= N; i++) {
            int x = in.readInt();
            linker(S, i, x);
            row[i] = x;
            counter[0] += x;
        }

        for(int i = 1; i <= N; i++){
            int x = in.readInt();
            linker(i + N, T, x);
            col[i] = x;
            counter[1] += x;
        }

        if(counter[0] != counter[1]) System.out.println(-1);
        else networkFlow();
    }

    private static void networkFlow() {
        int[] prev = new int[size];

        while(true) {
            Arrays.fill(prev, -1);
            Queue<Integer> q = new LinkedList<>();

            q.offer(S);

            while(!q.isEmpty() && prev[T] == -1) {
                int current = q.poll();

                for(int next: connection[current]) {
                    if(capacity[current][next] <= flow[current][next]) continue;
                    if(prev[next] != -1) continue;

                    prev[next] = current;
                    q.offer(next);
                }
            }

            if(prev[T] == -1) break;

            for(int i = T; i != S; i = prev[i]){
                flow[prev[i]][i] += 1;
                flow[i][prev[i]] -= 1;
            }
        }

        int[][] result = new int[N + 1][N + 1];

        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= N; j++){
                if(flow[i][j + N] > 0) result[i][j] = 1;
            }
        }

        OutputWriter out = new OutputWriter(System.out);
        if(!counting(result)) {
            out.print(-1);
            return;
        }

        out.printLine(1);

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                out.print(result[i][j]);
            }
            out.printLine();
        }
    }

    private static boolean counting(int[][] arr){
        for(int i = 1; i <= N; i++){
            int[] sum = {0, 0};

            for(int j = 1; j <= N; j++){
                sum[0] += arr[i][j];
                sum[1] += arr[j][i];
            }

            if(sum[0] != row[i] || sum[1] != col[i]) return false;
        }

        return true;
    }

    private static void init () {
        size = (N << 1) + 2;

        row = new int[N + 1];
        col = new int[N + 1];

        capacity = new int[size][size];
        flow = new int[size][size];
        connection = new ArrayList[size];
        for(int i = 0 ; i < size; i++) {
            connection[i] = new ArrayList<>();
        }

        S = 0; T = size - 1;

        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= N; j++){
                linker(i, j + N, 1);
            }
        }
    }

    private static void linker(int from, int to, int cap){
        connection[from].add(to);
        connection[to].add(from);
        capacity[from][to] = cap;
    }

    private static class InputReader {
        private InputStream stream;
        private byte[] buf = new byte[1024];
        private int curChar;
        private int numChars;
        private SpaceCharFilter filter;

        public InputReader(InputStream stream) {
            this.stream = stream;
        }

        public int read() {
            if (numChars == -1) {
                throw new InputMismatchException();
            }
            if (curChar >= numChars) {
                curChar = 0;
                try {
                    numChars = stream.read(buf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }
                if (numChars <= 0) {
                    return -1;
                }
            }
            return buf[curChar++];
        }

        public int readInt() {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            int res = 0;
            do {
                if (c < '0' || c > '9') {
                    throw new InputMismatchException();
                }
                res *= 10;
                res += c - '0';
                c = read();
            } while (!isSpaceChar(c));
            return res * sgn;
        }

        public boolean isSpaceChar(int c) {
            if (filter != null) {
                return filter.isSpaceChar(c);
            }
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        }

        public interface SpaceCharFilter {
            boolean isSpaceChar(int ch);
        }
    }

    private static class OutputWriter {
        private final PrintWriter writer;

        public OutputWriter(OutputStream outputStream) {
            writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(outputStream)));
        }

        public OutputWriter(Writer writer) {
            this.writer = new PrintWriter(writer);
        }

        public void print(Object... objects) {
            for (int i = 0; i < objects.length; i++) {
                if (i != 0) {
                    writer.print(' ');
                }
                writer.print(objects[i]);
            }
            writer.flush();
        }

        public void printLine(Object... objects) {
            print(objects);
            writer.println();
            writer.flush();
        }

        public void close() {
            writer.close();
        }

        public void flush() {
            writer.flush();
        }
    }
}

/**
 *
 * C++ 17
 *
 * #include <iostream>
 * #include <vector>
 * #include <cstring>
 * #include <queue>
 * using namespace std;
 *
 * int capacity[1002][1002];
 * int flow[1002][1002];
 * int result[501][501];
 *
 * int N;
 * int S = 0, T = 1001;
 *
 * vector<int> connection[1002];
 * vector<int> row, col;
 *
 * void linker(int from, int to, int cap);
 * bool counting();
 * void networkFlow();
 *
 * int main(){
 * 	ios_base::sync_with_stdio(0); cin.tie(0);
 * 	cin >> N;
 * 	row.resize(N + 1);
 * 	col.resize(N + 1);
 *
 * 	for(int i = 1; i <= N; i++){
 * 		int x; cin >> x;
 *
 * 		linker(S, i, x);
 * 		row[i] = x;
 *        }
 *
 * 	for(int i = 1; i <= N; i++){
 * 		int x; cin >> x;
 *
 * 		linker(i + N, T, x);
 * 		col[i] = x;
 *    }
 *
 * 	for(int i = 1; i <= N; i++){
 * 		for(int j= 1; j <= N; j++){
 * 			linker(i, j + N, 1);
 *        }
 *    }
 *
 * 	networkFlow();
 * }
 *
 * void networkFlow(){
 * 	int prev[1002];
 *
 * 	while(1){
 * 		int mx = 1e9;
 * 		memset(prev, -1, sizeof prev);
 *
 * 		queue<int> q;
 * 		q.push(S);
 *
 * 		while(q.size()){
 * 			int current = q.front(); q.pop();
 *
 * 			for(auto next : connection[current]){
 * 				if(prev[next] != -1) continue;
 * 				if(capacity[current][next] <= flow[current][next]) continue;
 * 				prev[next] = current;
 *
 * 				q.push(next);
 *            }
 *        }
 *
 * 		if(prev[T] == -1) break;
 *
 * 		for(int i = T; i != S; i = prev[i]){
 * 			flow[prev[i]][i] += 1;
 * 			flow[i][prev[i]] -= 1;
 *        }
 *
 *    }
 *
 * 	for(int i = 1; i <= N; i++){
 * 		for(int j = 1; j <= N; j++){
 * 			if(flow[i][j + N]) result[i][j] = 1;
 *        }
 *    }
 *
 * 	if(!counting()) printf("-1");
 * 	else{
 * 		printf("1\n");
 * 		for(int i = 1; i <= N; i++){
 * 			for(int j = 1; j <= N; j++){
 * 				printf("%d", result[i][j]);
 *            }
 * 			printf("\n");
 *        }
 *    }
 * }
 *
 * void linker(int from, int to, int cap){
 * 	connection[from].push_back(to);
 * 	connection[to].push_back(from);
 * 	capacity[from][to] = cap;
 * }
 *
 * bool counting(){
 * 	int sum[2];
 *
 * 	for(int i = 1; i <= N; i++){
 * 		sum[0] = 0;
 * 		sum[1] = 0;
 *
 * 		for(int j = 1; j <= N; j++) {
 * 			sum[0] += result[i][j];
 * 			sum[1] += result[j][i];
 *        }
 *
 * 		if(sum[0] != row[i] || sum[1] != col[i]) return 0;
 *    }
 *
 * 	return 1;
 * }
 *
 */
