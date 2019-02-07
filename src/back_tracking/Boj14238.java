package back_tracking;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 
 * 	@author minchoba
 *	백준 14238번: 출근 기록
 *
 *	@see https://www.acmicpc.net/problem/14238/
 *
 */
public class Boj14238 {
	private static boolean[][][][][] isVisited;
	private static int[] alphaCount = {0, 0, 0};
	private static int[] result = new int[100];
	
	private static final int A = 0, B = 1, C = 2;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		
		int size = line.length();
		isVisited = new boolean[size][size][size][3][3];
		Arrays.fill(result, -1);
		
		for(char c: line.toCharArray()) {
			if(c == 65) alphaCount[A]++;
			else if(c == 66) alphaCount[B]++;
			else alphaCount[C]++;
		}
		
		System.out.println(backTracking(0, 0, 0, 0, 0) ? getResult() : -1);
	}
	
	private static boolean backTracking(int a, int b, int c, int dup, int current) {
		if(compare(a, alphaCount[A], true) && compare(b, alphaCount[B], true) && compare(c, alphaCount[C], true)) return true;
		
		if(isVisited[a][b][c][dup][current]) return false;		// 출근한 경우의 수 체크
		isVisited[a][b][c][dup][current] = true;
		
		int idx = a + b + c;
		result[idx] = A;
		if(compare(a, alphaCount[A], false)) {
			if(backTracking(a + 1, b, c, current, A)) return true;
		}
		
		idx = a + b + c;
		result[idx] = B;
		if(compare(b, alphaCount[B], false) && !compare(current, B, true)) {		// B는 2일 연속 출근 불가
			if(backTracking(a, b + 1, c, current, B)) return true;
		}
		
		idx = a + b + c;
		result[idx] = C;
		if(compare(c, alphaCount[C], false) && !compare(dup, C, true) && !compare(current, C, true)) {		// C는 3일 연속 출근 불가
			if(backTracking(a, b, c + 1, current, C)) return true;
		}
		
		return false;
	}
	
	private static boolean compare(int count1, int count2, boolean section) {
		if(section) return count1 == count2 ? true : false;
		else return count1 < count2 ? true : false;
	}
	
	private static StringBuilder getResult() {
		StringBuilder sb = new StringBuilder();
		
		for(int alpha: result) {
			if(alpha == -1) continue;
			sb.append((char) (alpha + 65));		// 더해서 알파벳으로 변환
		}
		
		return sb;
	}
}
