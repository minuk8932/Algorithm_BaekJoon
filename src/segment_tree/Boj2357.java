package segment_tree;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 2357번: 최솟값과 최댓값
 *
 *	@see https://www.acmicpc.net/problem/2357/
 *
 */
public class Boj2357 {
	private static Tree[] tree = null;
	
	private static final String NEW_LINE = "\n";
	private static final String SPACE = " ";
	private static final int INF = 1_000_000_001;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int S = 1;
		while(S < N) S <<= 1;
		
		tree = new Tree[S * 2];
		for(int i = 0; i < tree.length; i++) {				// 배열 내 최대 최소 초기화
			tree[i] = new Tree(INF, 0);
		}
		
		for(int i = S; i < S + N; i++) {					// 가장 초기 값 들을 배열에 배치
			int num = Integer.parseInt(br.readLine());
			tree[i] = new Tree(num, num);
		}
		
		for(int i = tree.length - 1; i >= 0; i -= 2) {
			int min = Math.min(tree[i].min, tree[i - 1].min);
			int max = Math.max(tree[i].max, tree[i - 1].max);
			
			tree[i / 2] = new Tree(min, max);					// 노드별로 구간의 최소 최대를 구해 담아줌
		}
		
		StringBuilder sb = new StringBuilder();
		
		while(M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			Tree res = getMinMax(S + from - 1, S + to - 1);						// getMinMax 메소드를 통해 최소 최대를 받아와 버퍼에 담고
			sb.append(res.min).append(SPACE).append(res.max).append(NEW_LINE);
		}
		
		System.out.println(sb.toString());				// 결과 값 한번에 출력
	}
	
	/**
	 * 트리 정보 이너 클래스
	 * @author minchoba
	 *
	 */
	private static class Tree{
		int min;
		int max;
		
		public Tree(int min, int max) {
			this.min = min;
			this.max = max;
		}
	}
	
	/**
	 * 최소 최댓값 구하기 메소드
	 * 
	 * @return tree.min, tree.max
	 */
	private static Tree getMinMax(int seg1, int seg2) {
		int min = INF, max = 0;
		
		while(seg1 <= seg2) {
			if(seg1 % 2 == 1) {							// 구간없이 하나로 떨어진 노드인 경우 그 노드의 값이 최대 최소에 해당하는지 구하고
				min = Math.min(min, tree[seg1].min);
				max = Math.max(max, tree[seg1].max);
				
				seg1++;
			}
			
			if(seg2 % 2 == 0) {
				min = Math.min(min, tree[seg2].min);
				max = Math.max(max, tree[seg2].max);
				
				seg2--;
			}
			
			seg1 /= 2;									// 두개씩 묶인 모드는 넘어가서 위의 노드에서 해당 구간에 맞춰 값을 찾음
			seg2 /= 2;
		}
		
		return new Tree(min, max);			// 최소/최대 값을 트리 변수에 저장해 반환 
	}
}
