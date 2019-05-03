package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 6642번: On-line Banking
 *
 *	@see https://www.acmicpc.net/problem/6642/
 *
 */
public class Boj6642 {
	private static final String[] CMD = {"create", "withdraw", "deposit", "transfer", "end"};
	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";
	private static final String LACK = "insufficient funds";
	private static final String DUPLICATED = "already exists";
	private static final String LAST = "goodbye";
	private static final String COLON = ": ";
	private static final String OK = "ok";
	private static final String NOT_EXIST = "no such account";
	private static final String D_BANK = "interbank";
	private static final String S_ACCOUNT = "same account";
	
	private static StringBuilder sb = new StringBuilder();
	private static HashMap<String, Double> account;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			int N = Integer.parseInt(br.readLine());
			if(N == 0) {
				sb.append(LAST);
				break;
			}
			
			StringTokenizer st;
			account = new HashMap<>();
			
			while(N-- > 0) {
				st = new StringTokenizer(br.readLine());
				account.put(st.nextToken(), Double.parseDouble(st.nextToken()));
			}
			
			while(true) {
				st = new StringTokenizer(br.readLine());
				String cmd = st.nextToken();
				
				if(cmd.equals(CMD[4])) {			// end
					sb.append(cmd).append(NEW_LINE);
					break;
				}
				
				sb.append(cmd);
				
				if(cmd.equals(CMD[0])) {			// create
					sb.append(COLON);
					String acc = st.nextToken();
					
					isExisted(acc);	
				}
				else if(cmd.equals(CMD[1])) {		// withdraw
					String acc = st.nextToken();
					double cost = Double.parseDouble(st.nextToken());
					
					sb.append(SPACE).append(String.format("%.2f", cost)).append(COLON);
					withdrawing(acc, cost);
				}
				else if(cmd.equals(CMD[2])) {		// deposit
					String acc = st.nextToken();
					double cost = Double.parseDouble(st.nextToken());
					
					sb.append(SPACE).append(String.format("%.2f", cost)).append(COLON);
					depositing(acc, cost);
				}
				else {								// transfer
					String source = st.nextToken();
					String target = st.nextToken();
					double cost = Double.parseDouble(st.nextToken());
					
					sb.append(SPACE).append(String.format("%.2f", cost)).append(COLON);
					transferring(source, target, cost);
				}
			}
			
			br.readLine();
			sb.append(NEW_LINE);
		}
		
		System.out.println(sb.toString());
	}
	
	private static void isExisted(String newAcc) {
		if(account.containsKey(newAcc)) {
			sb.append(DUPLICATED).append(NEW_LINE);		// 이미 존재하는 계좌
		}
		else {
			sb.append(OK).append(NEW_LINE);				// 계좌 신설
			account.put(newAcc, 0.0);
		}
	}
	
	private static void withdrawing(String acc, double cost) {
		if(!account.containsKey(acc)) {
			sb.append(NOT_EXIST).append(NEW_LINE);			// 없는 계좌
		}
		else if(account.get(acc) < cost) {					// 해당 계좌에 금액이 부족
			sb.append(LACK).append(NEW_LINE);
		}
		else {
			sb.append(OK).append(NEW_LINE);					// 인출 성공
			account.put(acc, account.get(acc) - cost);
		}
	}
	
	private static void depositing(String acc, double cost) {
		if(!account.containsKey(acc)) {						// 없는 계좌
			sb.append(NOT_EXIST).append(NEW_LINE);
		}
		else {
			sb.append(OK).append(NEW_LINE);					// 입금 성공
			account.put(acc, account.get(acc) + cost);
		}
	}
	
	private static void transferring(String s, String t, double cost) {
		if(s.equals(t)) {
			if(!account.containsKey(s) || !account.containsKey(t)) sb.append(NOT_EXIST).append(NEW_LINE);	// 같은 계좌인데, 존재하지 않는다면
			else sb.append(S_ACCOUNT).append(NEW_LINE);		// 존재하는 같은 계좌
		}
		else {
			if(!account.containsKey(s) || !account.containsKey(t)) {	// 서로 다른 계좌이지만, 계좌가 하나라도 존재하지 않는다면
				sb.append(NOT_EXIST).append(NEW_LINE);
			}
			else {
				if(account.get(s) < cost) {								// 계좌번호에는 문제가 없지만 금액이 부족
					sb.append(LACK).append(NEW_LINE);
				}
				else {
					char num = s.charAt(s.length() - 1);
					
					if(num == t.charAt(t.length() - 1)) sb.append(OK).append(NEW_LINE);		// 같은 은행의 계좌인 경우
					else sb.append(D_BANK).append(NEW_LINE);								// 다른 은행의 계좌인 경우
					
					account.put(s, account.get(s) - cost);
					account.put(t, account.get(t) + cost);
				}
			}
		}
	}
}
