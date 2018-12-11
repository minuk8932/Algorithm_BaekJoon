package disjoint_set;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 10774번: 저지
 *
 *	@see https://www.acmicpc.net/problem/10774/
 *
 */
public class Boj10774 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int J = Integer.parseInt(br.readLine());
		int A = Integer.parseInt(br.readLine());
		
		int[] jersey = new int[J + 1];
		for(int i = 1; i < J + 1; i++) {
			char size = br.readLine().charAt(0);
			jersey[i] = getSizeByChar(size);
		}
		
		int res = 0;
		
		for(int i = 0; i < A; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			char size = st.nextToken().charAt(0);
			int num = Integer.parseInt(st.nextToken());
			
			if(jersey[num] >= getSizeByChar(size)) {		// 저지 사이즈가 원하는 것과 같은 번호를 가지며 크거나 같은 사이즈인 경우
				jersey[num] = -1;
				res++;
			}
		}
		
		System.out.println(res);
	}
	
	private static int getSizeByChar(char c) {
		if(c == 'L') return 2;
		else if(c == 'M') return 1;
		else return 0;
	}
}
