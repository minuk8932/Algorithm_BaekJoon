package disjoint_set;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 1717번: 집합의 표현
 *
 *	@see https://www.acmicpc.net/problem/1717/
 *
 */
public class Boj1717 {
	public static int[] set = new int[1000001];
	
	private static final String Y = "YES";
	private static final String N = "NO";
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		
		for(int i = 1; i < n + 1; i++) {		// 각 원소가 자신을 가리키도록 초기화
			set[i] = i;
		}
		
		StringBuilder sb = new StringBuilder();
		
		while(m-- > 0){
			st = new StringTokenizer(br.readLine());
			int command = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if(command == 0) {			// 집합 union 명령
				union(a, b);
			}
			else {						// 같은 집합 내 원소인지 체크하는 명령
				boolean yOrN = isSameSet(a, b);
				sb.append(yOrN ? Y : N).append(NEW_LINE);	// isSameSet 결과에 따라 YES, NO를 각각 버퍼에 담음
			}
		}
		
		System.out.println(sb.toString());		// 결과값 한번에 출력
    }
    
	/**
	 * 집합을 합하는 메소드
	 * @param: 합칠 원소들
	 */
	public static void union(int element1, int element2) {
		element1 = find(element1);
		element2 = find(element2);
		
		// 같은 부모를 가지고 있지 않을 때
		if(element1 != element2) {
			if(element2 < element1) {	// y가 x 보다 크도록 바꾼 후
				int tmp = element2;
				element2 = element1;
				element1 = tmp;
			}
			
			set[element2] = element1; // 더 큰 원소의 집합에 작은 원소를 추가
		}
	}
    
	/**
	 * 원소가 어떤 집합에 포함되어 있는지 확인하는 메소드
	 * @param element
	 * @return 
	 */
	private static int find(int element) {
		if(element == set[element])				// 최종적으로 자기자신을 가리킬 수 있는가?
			return element;
		else 
			return set[element] = find(set[element]);	// 자기자신이 나오지 않는다면 find 메소드를 통해 계속 올라가며 탐색해 root 값을 각 배열에 담아줌
	}
    
	/**
	 * 같은 집합에 속하는지 확인
	 * @param 비교할 원소들
	 * @return 원소들이 같은 집합에 속하는가?
	 */
	public static boolean isSameSet(int element1, int element2) {
		element1 = find(element1);					// find 메소드를 통해 각 원소의 루트(최종) 원소를 찾고, 
		element2 = find(element2);
		
		if(element1 == element2)		// 같으면? true
			return true;
		else							// 다르면? false
			return false;
	}	
}