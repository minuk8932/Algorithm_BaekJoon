package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 17952번: 과제는 끝나지 않아!
 *
 *	@see https://www.acmicpc.net/problem/17952/
 *
 */
public class Boj17952 {
	private static class Test{
		int score;
		int time;
		
		public Test(int score, int time) {
			this.score = score;
			this.time = time;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		ArrayList<Test> list = new ArrayList<>();
		int result = 0, idx = -1;
		
		while(N-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int cmd = Integer.parseInt(st.nextToken());
			
			if(cmd == 0) {
				if(idx == -1) continue;						// not any more test
				list.get(idx).time--;
				
				if(list.get(idx).time == 0) result += list.get(idx--).score;
			}
			else {											// add test
				int s = Integer.parseInt(st.nextToken());
				int t = Integer.parseInt(st.nextToken()) - 1;
				
				if(t == 0) {
					result += s;
					continue;
				}
				
				idx++;
				list.add(new Test(s, t));
			}
		}
		
		System.out.println(result);
	}
}
