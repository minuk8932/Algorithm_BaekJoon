package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj2864 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		String A = st.nextToken();
		String B = st.nextToken();
		String minA = A, minB = B;
		String maxA = A, maxB = B;
		
		if(A.contains("6")){
			minA = A.replaceAll("6", "5");
		}
		if(B.contains("6")){
			minB = B.replaceAll("6", "5");
		}
		
		if(A.contains("5")){
			maxA = A.replaceAll("5", "6");
		}
		if(B.contains("5")){
			maxB = B.replaceAll("5", "6");
		}
		
		int min = Integer.parseInt(minA) + Integer.parseInt(minB);
		int Max = Integer.parseInt(maxA) + Integer.parseInt(maxB);
		System.out.println(min + " " + Max);
	}

}
