package stack;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Boj3986 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int cnt = 0;

		while (N-- > 0) {
			String line = br.readLine();
			int len = line.length();

			char[] words = new char[len];
			words = line.toCharArray();

			Stack<Character> stk = new Stack<>();
			stk.push(words[0]);
			
			for(int i = 1; i < words.length; i++){
				if(!stk.isEmpty() && stk.peek() == words[i]){
					stk.pop();
				}
				else{
					stk.push(words[i]);
				}
				
			}
			
			if(stk.isEmpty()){
				cnt++;
			}
		}

		br.close();
		System.out.println(cnt);
	}

}
