package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 5177번: 출력 형식이 잘못되었습니다.
 *
 *	@see https://www.acmicpc.net/problem/5177/
 *
 */
public class Boj5177 {
	private static final String DATA_SET = "Data Set ";
	private static final String COLON = ": ";
	private static final String NEW_LINE = "\n";
	private static final String SPACE = " ";
	private static final String EMPTY = "";
	
	private static final char[] EXCEPT = {'(', ')', '[', ']', '{', '}', '.', ',', ';', ':'};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int K = Integer.parseInt(br.readLine());
		
		for(int i = 1; i < K + 1; i++) {
			String s1 = br.readLine();
			String s2 = br.readLine();
			
			StringTokenizer st = new StringTokenizer(s1);
			String[] str1 = new String[st.countTokens()];
			for(int idx = 0; idx < str1.length; idx++) {
				String next = st.nextToken().toLowerCase();
				
				if(SPACE.equals(next)) continue;		// 공백 제거
				str1[idx] = next;
			}
			
			st = new StringTokenizer(s2);
			String[] str2 = new String[st.countTokens()];
			for(int idx = 0; idx < str2.length; idx++) {
				String next = st.nextToken().toLowerCase();
				
				if(SPACE.equals(next)) continue;
				str2[idx] = next;
			}
			
			sb.append(DATA_SET).append(i).append(COLON).append(isEqual(str1, str2)).append(NEW_LINE).append(NEW_LINE);
		}
		
		br.close();
		System.out.println(sb);		// 결과 출력
	}
	
	private static String isEqual(String[] arr1, String[] arr2) {
		arr1 = doManipulate(arr1);
		arr2 = doManipulate(arr2);
		
		return isSame(arr1, arr2) ? "equal": "not equal";
	}
	
	private static String[] doManipulate(String[] arr) {
		for(int idx = 0; idx < arr.length; idx++) {
			int leng = arr[idx].length();
			String tmp = "";
			
			for(int i = 0; i < leng; i++) {			// 배열의 문자열마다 문자 하나씩 비교해가며 특수문자를 제거
				char idxChar = arr[idx].charAt(i);
				boolean pass = true;
				
				for(char w: EXCEPT) {
					if(idxChar == w) {
						pass = false;
						break;
					}
				}
				
				if(pass) tmp += idxChar;
			}
			
			arr[idx] = tmp;
		}
		
		return arr;
	}
	
	private static boolean isSame(String[] arr1, String[] arr2) {
		int leng = arr1.length > arr2.length ? arr1.length : arr2.length;
		
		for(int i = 0; i < leng; i++) {
			LinkedList<String> ll1 = makeList(arr1);
			LinkedList<String> ll2 = makeList(arr2);
			
			if(ll1.size() != ll2.size()) return false;
			
			while(!ll1.isEmpty() && !ll2.isEmpty()) {		// 정제된 문자열을 하나씩 비교
				String first = ll1.remove(0);
				String second = ll2.remove(0);
				
				if(!first.equals(second)) return false;
			}
		}
		
		return true;
	}
	
	private static LinkedList<String> makeList(String[] arr){
		LinkedList<String> list = new LinkedList<>();
		
		for(int i = 0; i < arr.length; i++) {										// 스페이스를 모두 공백으로 만들어줌
			if(arr[i].contains(SPACE)) arr[i] = arr[i].replaceAll(SPACE, EMPTY);	// 배열이 공백이면 리스트에 담지 않음
			if(arr[i].equals(EMPTY)) continue;
			
			list.add(arr[i]);
		}
		
		return list;
	}
}
