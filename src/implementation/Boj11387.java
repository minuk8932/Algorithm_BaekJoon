package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 11387번: 님 무기가 좀 나쁘시네여
 *
 *	@see https://www.acmicpc.net/problem/11387/
 *
 */
public class Boj11387 {
	private static final String NEW_LINE = "\n";
	private static final String UP = "+";
	private static final String DOWN = "-";
	private static final String NOT = "0";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		Inven[] equiped = new Inven[4];
		
		for(int i = 0; i < equiped.length; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			// 장비의 해당 능력치 입력
			equiped[i] = new Inven(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()), 
					Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()));	
		}
		
		for(int i = 0; i < 2; i++) {				// 장비간 차
			equiped[i].sp -= equiped[i + 2].sp;
			equiped[i].str -= equiped[i + 2].str;
			equiped[i].cp -= equiped[i + 2].cp;
			equiped[i].cd -= equiped[i + 2].cd;
			equiped[i].as -= equiped[i + 2].as;
		}
		
		// 장비를 해제 하고 다른 장비를 채운 결과를 변수에 저장
		long res1 = power(equiped[0], equiped[3]) - power(equiped[0], equiped[2]);
		long res2 = power(equiped[1], equiped[2]) - power(equiped[1], equiped[3]);
		
		sb.append(res1 == 0 ? NOT : (res1 > 0 ? UP : DOWN)).append(NEW_LINE);		// 버퍼에 조건에 따라 값을 담고
		sb.append(res2 == 0 ? NOT : (res2 > 0 ? UP : DOWN)).append(NEW_LINE);
		
		System.out.println(sb.toString());	// 결과 값 한번에 출력
	}
	
	/**
	 * 인벤토리 이너 클래스
	 *
	 */
	private static class Inven{
		long sp;
		long str;
		long cp;
		long cd;
		long as;
		
		public Inven(long sp, long str, long cp, long cd, long as) {
			this.sp = sp;		// 공격력
			this.str = str;		// 힘
			this.cp = cp;		// 치확
			this.cd = cd;		// 치뎀
			this.as = as;		// 공속
		}
	}
	
	/**
	 * 
	 *  @return 장비 능력치 합산을 반환
	 */
	private static long power(Inven c, Inven p) {
		return (c.sp + p.sp) * (100 + c.str + p.str) * 
				(10000 - 100 * Math.min(c.cp + p.cp, 100) + Math.min(c.cp + p.cp, 100) * (c.cd + p.cd)) * (100 + c.as + p.as);
	}
}
