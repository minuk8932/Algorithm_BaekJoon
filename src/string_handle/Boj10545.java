package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 10545번: 뚜기뚜기 메뚜기
 *
 *	@see https://www.acmicpc.net/problem/10545/
 *
 */
public class Boj10545 {
	private static final String PUSH = "#";
	private static final String[] KEY = {"", "abc", "def",
										"ghi", "jkl", "mno", 
										"pqrs", "tuv", "wxyz"};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int[] nums = new int[9];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < nums.length; i++) {
			nums[Integer.parseInt(st.nextToken()) - 1] = i + 1;		// 바뀐 자판을 통해 원래 자판을 찾도록
		}
		
		char[] word = br.readLine().toCharArray();		
		System.out.println(keyClick(nums, word));
	}
	
	private static StringBuilder keyClick(int[] wrongKey, char[] arr) {
		StringBuilder sb = new StringBuilder();
		int before = -1;
		
		for(char c: arr) {
			for(int i = 0; i < KEY.length; i++) {
				int loop = KEY[i].length();
				
				for(int j = 0; j < loop; j++) {
					if(c == KEY[i].charAt(j)) {				// 이전에 같은 자판을 누른다면 한번 푸쉬
						if(before == i) sb.append(PUSH);
						before = i;
						
						int idx = j + 1;
						while(idx-- > 0) {				// 같은 자판을 눌러야하는 횟수
							sb.append(wrongKey[i]);
						}
					}
				}
			}
		}
		
		return sb;
	}
}
