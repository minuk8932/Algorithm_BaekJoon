package realization;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj2935 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		char[] sign = new char[10];
		char[] N = new char[101];
		char[] M = new char[101];
		char[] res = new char[101];

		N = br.readLine().toCharArray();
		sign = br.readLine().toCharArray();
		M = br.readLine().toCharArray();
		res = new char[100];

		if (sign[0] == '*') {
			for (int i = 0; i < N.length; i++) {
				res[i] = N[i];
				sb.append(res[i]);
			}
			for (int i = N.length + 1; i < N.length + M.length; i++) {
				res[i] = '0';
				sb.append(res[i]);
			}
		}
		else {
			int max = Math.max(N.length, M.length);
			int min = Math.min(N.length, M.length);

			if (N.length == M.length) {
				for (int i = 0; i < max; i++) {
					if (N[i] == '1') {
						res[i] = '2';
					} 
					else {
						res[i] = N[i];
					}
					sb.append(res[i]);
				}
			} 
			else {
				if(max == N.length){
					for(int i = 0; i < max; i++){					
						if(i == max - min){
							res[i] = '1';
						}
						else{
							res[i] = N[i];
						}
						sb.append(res[i]);
					}
				}
				else{
					for(int i = 0; i < max; i++){
						if(i == max -min){
							res[i] = '1';
						}
						else{
							res[i] = M[i];
						}
						sb.append(res[i]);
					}
				}
			}

		}
		System.out.println(sb.toString());
	}
}
