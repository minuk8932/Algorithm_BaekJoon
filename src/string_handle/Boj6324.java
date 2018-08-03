package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 6324번: URLs
 *
 *	@see https://www.acmicpc.net/problem/6324/
 *
 */
public class Boj6324 {
	private static final String TC = "URL #";
	private static final String PR = "Protocol = ";
	private static final String H = "Host     = ";
	private static final String PO = "Port     = ";
	private static final String PA = "Path     = ";
	private static final String DEFAULT = "<default>";

	private static final String NEW_LINE = "\n";
	private static final char SLASH = '/';
	private static final char COLON = ':';

	private static String protocol = "";
	private static String host = "";
	private static String port = "";
	private static String path = "";

	public static void main(String[] args) throws Exception {
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine());

		for (int i = 0; i < n; i++) {
			String url = br.readLine();
			protocol = "";
			host = "";
			port = "";
			path = "";

			int split = 0;

			for (char word : url.toCharArray()) {		// protocol 입력
				if (word == COLON) break;

				protocol += word;
				split++;
			}

			url = url.substring(split + 3);
			split = 0;

			boolean hasPort = false;
			boolean hasPath = false;

			for (char word : url.toCharArray()) {		// host 입력
				if (word == COLON) {				// 중간에 쿨롱으로 끊기는 경우 port가 존재
					hasPort = true;
					break;
				}
				
				if(word == SLASH) {					// 중간에 슬래쉬로 끊기는 경우 port는 없고, 패스는 path는 존재
					hasPath = true;
					break;
				}
				
				host += word;
				split++;
			}

			if (hasPort) {							// port가 있는 경우
				url = url.substring(split + 1);
				split = 0;
				
				for (char word : url.toCharArray()) {
					if (word == SLASH) {				// 중간에 슬래쉬로 끊기면, path 존재
						hasPath = true;
						break;
					}

					port += word;
					split++;
				}
				
				if(hasPath) {							// port, path 모두 존재하는 경우 path 입력
					url = url.substring(split + 1);
					split = 0;
					
					for (char word : url.toCharArray()) {
						path += word;
					}
				}
			}

			if (!hasPort && hasPath) {					// port는 없고 path만 있는 경우	
				url = url.substring(split + 1);
				
				for (char word : url.toCharArray()) {
					path += word;
				}
			}
			
			// 파싱한 문자열들을 버퍼에 담아줌
			sb.append(TC).append(i + 1).append(NEW_LINE);
			sb.append(PR).append(protocol).append(NEW_LINE);
			sb.append(H).append(host).append(NEW_LINE);
			sb.append(PO).append(port.equals("") ? DEFAULT : port).append(NEW_LINE);
			sb.append(PA).append(path.equals("") ? DEFAULT : path).append(NEW_LINE).append(NEW_LINE);
		}

		System.out.println(sb.toString()); // 결과값 헌번에 출력
	}
}
