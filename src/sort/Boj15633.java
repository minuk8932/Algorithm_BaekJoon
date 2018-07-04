package sort;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

/**
 * 
 * 	@author minchoba
 *	백준 15633번: 수 정렬하기 5
 *
 *	@see https://www.acmicpc.net/problem/15633/
 *
 */
public class Boj15633 {
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 입출력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		
		int[] arr = new int[N];
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(arr);		// 퀵소트 실행
		
		for(int i = 0; i < N; i++) {
			bw.write(arr[i] + "\n");		// 결과를 버퍼에 담고
		}
		
		bw.flush();		// 결과값 한번에 출력
	}
}
