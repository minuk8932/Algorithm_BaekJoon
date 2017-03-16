package realization;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj2290 {
	public static final String SPACE = " ";
	public static final String NEW_LINE = "\n";

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		String W = "-", H = "|";
		int N = Integer.parseInt(st.nextToken());
		String num = st.nextToken();
		char[] tmp = new char[num.length()];
		tmp = num.toCharArray();

		int nLeng = num.length();
		for (int i = 0; i < nLeng; i++) {
			switch(tmp[i]){
			case '1':
			case '4':
				sb.append(SPACE);
				for(int j = 0; j < N; j++){
					sb.append(SPACE);
				}
				sb.append(SPACE).append(SPACE);
				break;
				
			case '2':
			case '3':
			case '5':
			case '6':
			case '7':
			case '8':
			case '9':
			case '0':
				sb.append(SPACE);
				for(int j = 0; j < N; j++){
					sb.append(W);
				}
				sb.append(SPACE).append(SPACE);
				break;
			}
		}
		sb.append(NEW_LINE);

		for (int j = 0; j < N; j++) {
			for (int i = 0; i < nLeng; i++) {
				switch (tmp[i]) {
				case '1':
				case '2':
				case '3':
				case '7':
					sb.append(SPACE);
					for(int k = 0; k < N; k++){
						sb.append(SPACE);
					}
					sb.append(H);
					sb.append(SPACE);
					break;

				case '5':
				case '6':
					sb.append(H);
					for(int k = 0; k < N; k++){
						sb.append(SPACE);
					}
					sb.append(SPACE);
					sb.append(SPACE);
					break;

				case '4':
				case '8':
				case '9':
				case '0':
					sb.append(H);
					for(int k = 0; k < N; k ++){
						sb.append(SPACE);
					}
					sb.append(H);
					sb.append(SPACE);
					break;
				}
			}
			sb.append(NEW_LINE);
		}
		
		for (int i = 0; i < nLeng; i++) {
			switch(tmp[i]){
			case '1':
			case '0':
			case '7':
				sb.append(SPACE);
				for(int j = 0; j < N; j++){
					sb.append(SPACE);
				}
				sb.append(SPACE).append(SPACE);
				break;
				
			case '2':
			case '3':
			case '5':
			case '6':
			case '8':
			case '9':
			case '4':
				sb.append(SPACE);
				for(int j = 0; j < N; j++){
					sb.append(W);
				}
				sb.append(SPACE).append(SPACE);
				break;
			}
		}
		sb.append(NEW_LINE);
		
		for (int j = 0; j < N; j++) {
			for (int i = 0; i < nLeng; i++) {
				switch (tmp[i]) {
				case '1':
				case '4':
				case '5':
				case '3':
				case '7':
				case '9':
					sb.append(SPACE);
					for(int k = 0; k < N; k++){
						sb.append(SPACE);
					}
					sb.append(H);
					sb.append(SPACE);
					break;

				case '2':
					sb.append(H);
					for(int k = 0; k < N; k++){
						sb.append(SPACE);
					}
					sb.append(SPACE);
					sb.append(SPACE);
					break;

				case '8':
				case '6':
				case '0':
					sb.append(H);
					for(int k = 0; k < N; k ++){
						sb.append(SPACE);
					}
					sb.append(H);
					sb.append(SPACE);
					break;
				}
			}
			sb.append(NEW_LINE);
		}
		for (int i = 0; i < nLeng; i++) {
			switch(tmp[i]){
			case '1':
			case '4':
			case '7':
				sb.append(SPACE);
				for(int j = 0; j < N; j++){
					sb.append(SPACE);
				}
				sb.append(SPACE).append(SPACE);
				break;
				
			case '2':
			case '3':
			case '5':
			case '6':
			case '8':
			case '9':
			case '0':
				sb.append(SPACE);
				for(int j = 0; j < N; j++){
					sb.append(W);
				}
				sb.append(SPACE).append(SPACE);
				break;
			}
		}
		

		System.out.println(sb.toString());
	}

}
