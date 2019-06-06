package simulation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 17269번: 이름궁합 테스트
 *
 *	@see https://www.acmicpc.net/problem/17269/
 *
 */
public class Boj17269 {
	private static final int[] ALPHA = {3, 2, 1, 2, 4, 3, 1, 3, 1, 1, 3, 1, 3, 2, 1, 2, 2, 2, 1, 2, 1, 1, 1, 2, 2, 1};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		System.out.println(getPercent(N, M, st.nextToken().toCharArray(), st.nextToken().toCharArray()) + "%");
	}
	
	private static int getPercent(int n, int m, char[] arr1, char[] arr2) {
		int loop = Math.max(n, m);
		
		LinkedList<Integer> values = new LinkedList<>();
		for(int i = 0; i < loop; i++) {
			if(arr1.length > i) values.add(ALPHA[arr1[i] - 'A']);
			if(arr2.length > i) values.add(ALPHA[arr2[i] - 'A']);
		}
		
		while(true) {
			int size = values.size();
			if(size == 2) break;
			
			size -= 1;
			int prev = values.remove();
			
			while(size-- > 0) {
				int post = values.remove();
				
				values.add((prev + post) % 10);			// 이름 궁합 계산
				prev = post;
			}
		}
		
		return values.remove() * 10 + values.remove();
	}
}
