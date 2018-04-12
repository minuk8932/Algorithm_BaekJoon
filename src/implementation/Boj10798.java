package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Boj10798 {
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		ArrayList<char[]> arrayList = new ArrayList<>();
		int maxLen = 0;

		for (int i = 0; i < 5; i++) {
			String line = br.readLine();
			arrayList.add(line.toCharArray());
			maxLen = Math.max(maxLen, line.length());
		}

		for (int i = 0; i < maxLen; i++) {
			for (char[] chars : arrayList) {
				if (i < chars.length) {
					sb.append(chars[i]);
				}
			}
		}

		System.out.println(sb.toString());
	}
}

//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//char[][] data = new char[5][];
//int maxLen = 0;
//
//for (int i = 0; i < 5; i++) {
//	String line = br.readLine();
//	data[i] = line.toCharArray();
//	maxLen = Math.max(maxLen, line.length());
//}
//
//br.close();
//
//StringBuilder sb = new StringBuilder();
//
//for (int i = 0; i < maxLen; i++) {
//	for (char[] chars : data) {
//		if (i < chars.length) {
//			sb.append(chars[i]);
//		}
//	}
//}
//
//System.out.println(sb.toString());