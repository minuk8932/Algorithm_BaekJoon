package sort;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;

/**
 * 
 * 	@author minchoba
 *	백준 11931번: 수 정렬하기 4
 *
 *	@see https://www.acmicpc.net/problem/11931
 *
 */
public class Boj11931 {
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		LinkedList<Integer> sort = new LinkedList<>();
		for(int i = 0; i < N; i++) sort.add(Integer.parseInt(br.readLine()));
		
		Collections.sort(sort);
		
		StringBuilder sb = new StringBuilder();
		while(!sort.isEmpty()) sb.append(sort.removeLast()).append(NEW_LINE);		// 내림차순 버퍼 저장
		
		System.out.println(sb);		// 결과 값 출력
	}
}
