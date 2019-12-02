package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author exponential-e
 *	백준 17478번: 재귀함수가 뭔가요?
 *
 *	@see https://www.acmicpc.net/problem/17478/
 *
 */
public class Boj17478 {
	private static StringBuilder sb = new StringBuilder();
	private static int N = 0;
	
	private static final String HYPEN = "____";
	private static final String NEW_LINE = "\n";
	private static final String[] LINE = 
		{"\"재귀함수가 뭔가요?\"\n", 
			"\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.\n", 
			"마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.\n",  
			"그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"\n",
			"라고 답변하였지."};
	
	private static final String[] LAST = {"\"재귀함수가 뭔가요?\"\n",
			"\"재귀함수는 자기 자신을 호출하는 함수라네\"\n", 
			"라고 답변하였지.\n"};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		sb.append("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.").append(NEW_LINE);
		
		for(int i = 0; i < LINE.length - 1; i++) {
			sb.append(LINE[i]);
		}
		
		recursion(N - 1);
		sb.append(LINE[LINE.length - 1]);
		System.out.println(sb.toString());
	}
	
	private static void recursion(int n) {
		StringBuilder depth = new StringBuilder();
		for(int i = 0; i < N - n; i++) {
			depth.append(HYPEN);
		}
		
		if(n == 0) {			
			for(int i = 0; i < LAST.length; i++) {
				sb.append(depth).append(LAST[i]);
			}
			
			return;
		}
		
		for(int i = 0; i < LINE.length - 1; i++) {
			sb.append(depth).append(LINE[i]);
		}
		
		recursion(n - 1);
		sb.append(depth).append(LINE[LINE.length - 1]).append(NEW_LINE);
	}
}
