package sort;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj1427 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		String num = st.nextToken();
		char[] charNum = new char[num.length()];
		charNum = num.toCharArray();
		Arrays.sort(charNum);
		
		for(int i = charNum.length - 1; i >= 0; i--){
			sb.append(charNum[i]);
		}
		System.out.println(sb.toString());
	}

}
