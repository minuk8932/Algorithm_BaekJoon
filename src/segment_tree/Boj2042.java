package segment_tree;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 2042번: 구간 합 구하기
 *
 *	@see https://www.acmicpc.net/problem/2042/
 *
 */
public class Boj2042 {
	private static final String NEW_LINE = "\n";
	private static long[] tree = null;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int start = 1;
		while(start < N) {		// 값을 담을 배열 순번 초기화
			start *= 2;
		}
		
		tree = new long[start * 2];
		
		for(int i = start; i < N + start; i++) {			// 1 ~ N까지 배열에 저장
			tree[i] = Integer.parseInt(br.readLine());
		}
		
		int parent = start - 1;
		while(parent-- > 0) {							// 부모노드에 자손노드에 들어있는 값을 저장 (구간 합)
			int son = parent * 2;
			tree[parent] = tree[son] + tree[son + 1];
		}
		
		StringBuilder sb = new StringBuilder();
		int loop = M + K;
		
		while(loop-- > 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			if(a == 1) {				// 트리의 값 재 설정
				setTree(start, b, c);
			}
			else {						// 트리 구간 합을 구해 버퍼에 담음
				sb.append(getSectionSum(1, b, c, 1, start - 1)).append(NEW_LINE);
			}
		}
		
		System.out.println(sb.toString());		// 결과 값 한번에 출력
	}
	
	/**
	 * 트리의 값 재설정 메소드
	 * 
	 */
	private static void setTree(int start, int oldNumIdx, long newNum) {
		int newIdx = start + oldNumIdx - 1;
		tree[newIdx] = newNum;				// 새로 들어온 값을 가장 밑의 자손 노드에 저장하고
		
		while((newIdx /= 2) > 1) {			// 자손 노드 값에 따라 부모 노드에 구간합 재설정
			int son = newIdx * 2;
			tree[newIdx] = tree[son] + tree[son + 1];
		}
	}
	
	/**
	 * 구간 합 반환 메소드
	 *
	 */
	private static long getSectionSum(int start, long from, long to, int l, int r) {
		if(r < from || l > to) return 0;				// 범위를 벗어나는 경우 함수 종료
		if(from <= l && to >= r) return tree[start];	// 범위내에 존재하는 경우 해당 트리의 값을 반환
		
		int mid = (l + r) / 2;					// 부모 노드의 인덱스를 가져옴
		int next = start * 2;					// 자손 노드의 인덱스를 가져옴
		
		// 왼쪽 자손노드, 오른쪽 자손노드에 해당하는 값을 따라 함수 재귀 호출
		return getSectionSum(next, from, to, l, mid) + getSectionSum(next + 1, from, to, mid + 1, r);
	}
}
