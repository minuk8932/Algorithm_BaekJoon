package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 10219번: Meats on the grill
 *
 *	@see https://www.acmicpc.net/problem/10219/
 *
 */
public class Boj10219 {
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0){
			StringTokenizer st = new StringTokenizer(br.readLine());
			int H = Integer.parseInt(st.nextToken());
			int W = Integer.parseInt(st.nextToken());
			
			for(int i = 0; i < H; i++) {
				String line = br.readLine();
				
				for(int j = W - 1; j >= 0; j--) {		// 불판 전체를 뒤집은 결과를 버퍼에 담고
					sb.append(line.charAt(j));
				}
				sb.append(NEW_LINE);
			}
		}
		
		System.out.println(sb.toString());		// 결과 값 한번에 출력
	}
}
