package hashing;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 16165번: 걸그룹 마스터 준석이
 *
 *	@see https://www.acmicpc.net/problem/16165/
 *
 */
public class Boj16165 {
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		HashMap<String, ArrayList<String>> group = new HashMap<>();
		StringBuilder sb = new StringBuilder();
		
		while(N-- > 0) {
			String grp = br.readLine();
			int count = Integer.parseInt(br.readLine());
			ArrayList<String> list = new ArrayList<>();
			
			while(count-- > 0) {
				list.add(br.readLine());
			}
			Collections.sort(list);
			group.put(grp, list);
		}
		
		
		while(M-- > 0){
			String name = br.readLine();
			int cmd = Integer.parseInt(br.readLine());
			
			if(cmd == 1) {										// 그룹명 출력
				for(String grp: group.keySet()) {
					for(String member: group.get(grp)) {
						if(member.contains(name)) {
							sb.append(grp).append(NEW_LINE);
							break;
						}
					}
				}
			}
			else {
				for(String member: group.get(name)) {			// 멤버 전체 출력
					sb.append(member).append(NEW_LINE);
				}
			}
		}
		
		System.out.println(sb);
	}
}
