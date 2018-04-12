package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj4153 {
	public static final String BREAK = "0 0 0";
	public static final String NEXT_LINE = "\n";
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(true){
			String numStr = br.readLine().trim();
			
			if(numStr.equals(BREAK)) break;
			
			StringTokenizer st = new StringTokenizer(numStr, " ");
			int[] mkTri = new int[3];
			
			for(int i = 0; i < mkTri.length; i++){
				mkTri[i] = Integer.parseInt(st.nextToken().trim());
			}
			
			Arrays.sort(mkTri);
			if(Math.pow(mkTri[0], 2) + Math.pow(mkTri[1], 2) == Math.pow(mkTri[2], 2)){
				sb.append("right").append(NEXT_LINE);
			}
			
			else{
				sb.append("wrong").append(NEXT_LINE);
			}
			
		}
		System.out.println(sb.toString());
	}

}
