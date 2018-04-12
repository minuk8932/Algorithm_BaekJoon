package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj5596 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] strS = br.readLine().trim().split(" ");
		String[] strT =br.readLine().trim().split(" ");
		
		int S=0, T=0;
		
		for(int i = 0; i < 4; i++){
			S += Integer.parseInt(strS[i]);
			T += Integer.parseInt(strT[i]);
		}
		
		if(S > T){
			System.out.println(S);
		} else {
			System.out.println(T);
		}
	}
}
