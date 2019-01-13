package sort;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

/**
 * 
 * 	@author minchoba
 *	백준 11536번: 줄 세우기
 *
 *	@see https://www.acmicpc.net/problem/11536/
 *
 */
public class Boj11536 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		String[] names = new String[N];
		String[] comp = new String[N];
		
		for(int i = 0; i < N; i++) {
			names[i] = br.readLine();
			comp[i] = names[i];
		}
		
		boolean increase = isSorted(N, names, comp, 0, N , 1);
		boolean decrease = isSorted(N, names, comp, 0, N, -1);
		
		if(increase) System.out.println("INCREASING");
		else if(decrease) System.out.println("DECREASING");
		else System.out.println("NEITHER");
	}
	
	private static boolean isSorted(int n, String[] target, String[] comp, int start, int end, int diff) {
		Arrays.sort(comp, new Comparator<String>() {

			@Override
			public int compare(String str1, String str2) {		// 첫자리 비교 후 같으면 전체 비교
				if(str1.charAt(0) < str2.charAt(0)) {
					return -1;
				}
				else if(str1.charAt(0) > str2.charAt(0)) {
					return 1;
				}
				else {
					return str1.compareTo(str2);
				}
			}
		});
		
		for(int i = start; i < end; i++) {				// 증가 감소에 따라 접근 인덱스를 바꿔줌
			int idx = diff == 1 ? i : n - 1 - i;
			if(comp[i] != target[idx]) return false;
		}
		
		return true;
	}
}
