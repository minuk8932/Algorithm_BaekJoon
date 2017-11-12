package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj9996 {
	private static final String YES = "DA";
	private static final String NO = "NE";
	private static final String NEW_LINE = "\n";

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String line = br.readLine();

		char[] word = line.toCharArray();
		int cnt = 0;

		int leng = word.length;

		for (int i = 0; i < leng; i++) {
			if (word[i] == '*') {
				cnt = i;
			}
		}

		StringBuilder sb = new StringBuilder();

		while (N-- > 0) {
			char[] file = br.readLine().toCharArray();
			int chk = 0;

			if(file.length < leng - 1){
				sb.append(NO).append(NEW_LINE);
			}
			else{
				for (int i = 0; i < leng - 1; i++) {
					if (i < cnt) {
						if (file[i] == word[i]) {
							chk++;
						}
					} 
					else {
						if (file[file.length - (leng - i) + 1] == word[i + 1]) {
						chk++;
						}
					}
				}
				if (chk == leng - 1) {
					sb.append(YES).append(NEW_LINE);
				} 
				else {
					sb.append(NO).append(NEW_LINE);
				}
			}
		}
		System.out.println(sb.toString());
	}
}
