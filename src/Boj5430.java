import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Boj5430 {
	private static final String SEPARATE = ",";
	private static final String NEW_LINE = "\n";
	private static final String ERROR = "error";
	private static final String DELETE = "D";

	private static final char REV = 'R';
	private static final char DEL = 'D';
	private static final char PRE = '[';
	private static final char POS = ']';

	private static ArrayList<Integer> nums = null;
	private static int cursor = 0;
	private static int size = 0;
	private static boolean isVisited = false;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		while (T-- > 0) {
			String line = br.readLine();

			char[] command = line.toCharArray();
			int N = Integer.parseInt(br.readLine());
			boolean isZero = false;

			nums = new ArrayList<>();
			String arr = br.readLine();

			StringTokenizer st = new StringTokenizer(arr.substring(1, arr.length() - 1), SEPARATE);
			for (int i = 0; i < N; i++) {
				int tmp = Integer.parseInt(st.nextToken());
				nums.add(tmp);
			}

			isVisited = false;
			cursor = 0;
			size = nums.size();

			if (size == 0 && line.contains(DELETE)) {
				isZero = true;
			} else {
				for (int i = 0; i < command.length; i++) {
					if (command[i] == REV) {
						if (!isVisited) {
							cursor = size - 1;
							isVisited = true;
						} else {
							cursor = 0;
							isVisited = false;
						}
					}

					else if (command[i] == DEL) {
						if (size == 0) {
							isZero = true;
							break;
						}

						nums.remove(cursor);
						cursor = isVisited ? cursor - 1 : cursor;
						size--;
					}
				}
			}

			if (size == 0 && !isZero) {
				sb.append(PRE).append(POS).append(NEW_LINE);
				continue;
			}

			boolean hasElement = false;

			if (isZero) {
				sb.append(ERROR).append(NEW_LINE);
				continue;
			}

			sb.append(PRE);

			if (cursor == 0) {
				for (int i = 0; i < size; i++) {
					sb.append(nums.get(i)).append(SEPARATE);
					hasElement = true;
				}
			} 
			else {
				for (int i = size - 1; i >= 0; i--) {
					sb.append(nums.get(i)).append(SEPARATE);
					hasElement = true;
				}
			}

			if (hasElement)
				sb.deleteCharAt(sb.length() - 1);

			sb.append(POS).append(NEW_LINE);
		}

		System.out.println(sb.toString());
	}
}
