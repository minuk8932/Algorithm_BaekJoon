package brute_force;
/**
 * 
 * 	@author minchoba
 *	백준 6679번: 싱기한 네자리 숫자
 *
 *	@see https://www.acmcipc.net/problem/6679/
 *
 */
public class Boj6679 {
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		StringBuilder sb = new StringBuilder();
		
		for(int i = 2992; i < 10000; i++) {			// 2992 부터 10000전 까지 진법 변환 후 모든 자리를 더했을 때 모두 동일한 값을 나오는 숫자를 버퍼에 저장
			int a = system(i, 10);
			int b = system(i, 12);
			int c = system(i, 16);
			if(a == b && b == c) sb.append(i).append(NEW_LINE);
		}
		
        System.out.println(sb.toString());		// 결과 값 한번에 출력
	}
	
	/**
	 * 진법 변환 메소드
	 * @param num: 진법을 바꿀 숫자
	 * @param ary: n진법
	 * @return: 바뀐 진법의 각자리 덧셈의 결과
	 */
	private static int system(int num, int ary) {
		int res = 0;
		
		while(num != 0) {
			res += (num % ary);
			num /= ary;
		}
		
		return res;
	}
}
