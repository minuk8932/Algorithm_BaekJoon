package stack;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Boj9012Study {
	public static final String Y = "YES\n";
	public static final String N = "NO\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0){
			char[] chars = br.readLine().toCharArray();
			Stack<Character> stack = new Stack<>();
			boolean res = true;
			
			for(char c : chars){
				if(c == '('){
					stack.push(c);
				}
				
				else{
					if(!stack.isEmpty()){
						stack.pop();
					}
					
					else{
						res = false;
						
						break;
					}
				}
			}
			sb.append(res && stack.empty() ? Y : N);		//what a wonderful code!
		}
		br.close();
		
		System.out.println(sb.toString());
	}

}
