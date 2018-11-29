package segment_tree;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 11505번: 구간 곱 구하기
 *
 *	@see https://www.acmicpc.net/problem/11505/
 *
 */
public class Boj11505 {
	private static final int MOD = 1_000_000_007;
	private static final char NEW_LINE = '\n';
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int S = 1;
		while(S < N) S <<= 1;			// N보다 큰 2의 제곱수 구하기
		
		long[] seg = new long[S * 2];
		Arrays.fill(seg, 1);
		
		for(int i = S; i < S + N; i++) {
			seg[i] = Long.parseLong(br.readLine());		// 트리 가장 아래 첫번째 노드부터 값을 채워줌
		}
		
		StringBuilder sb = new StringBuilder();
		int loop = M + K;
		
		seg = init(seg);
		
		while(loop-- > 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			switch (a) {
			case 1:
				seg = nodeMod(seg, b + S - 1, c);
				break;
				
			case 2:
				sb.append(sectionMulti(seg, b + S - 1, c + S - 1)).append(NEW_LINE);
			}
		}
		
		System.out.println(sb);		// 결과 출력
	}
	
	private static long[] init(long[] tree) {
		for(int i = tree.length - 1; i > 0; i -= 2) {
			tree[i / 2] = (tree[i] * tree[i - 1]) % MOD;
		}
		
		return tree;
	}
	
	private static long modifyArray(int mod, int idx, long[] arr) {		// 나머지 0 -> +1, 1 -> -1
		int diff = -2 * mod + 1;
		return (arr[idx] % MOD) * (arr[idx + diff] % MOD) % MOD;
	}
	
	private static long[] nodeMod(long[] tree, int idx, int val) {
		tree[idx] = val;
		
		while(idx > 0) {
			tree[idx / 2] = modifyArray(idx % 2, idx, tree);	// 함수를 통해 해당 노드와 부모 노드의 값 모두 수정
			idx /= 2;
		}
		
		return tree;
	}
	
	private static long setTotal(long val, int idx, long[] arr) {		
		return ((val % MOD) * (arr[idx] % MOD)) % MOD;
	}
	
	private static long sectionMulti(long[] tree, int from, int to) {
		long total = 1;
		
		while(from <= to) {								// 시작 인덱스가 홀수거나 끝 인덱스가 짝수이면, 동떨어진 노드 즉 바로 계산해서 곱해줘야함
			if(from % 2 == 1) {
				total = setTotal(total, from, tree);
				from++;
			}
			if(to % 2 == 0) {
				total = setTotal(total, to, tree);
				to--;
			}
			
			from /= 2;
			to /= 2;
		}
		
		return total % MOD;
	}
}
