package strongly_connected_component;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.List;

/**
 * 
 * 	@author minchoba
 *	백준 2150번: Strongly Connected Component by Kosaraju's Algorithm
 *
 *	@see https://www.acmicpc.net/problem/2150/
 *
 */
public class Boj2150 {
	private static ArrayDeque<Integer> stack = new ArrayDeque<>();
	private static ArrayList<ArrayList<Integer>> scc;
	private static ArrayList<Integer> component;
	
	private static boolean[] isVisited;
	
	private static final String NEW_LINE = "\n";
	private static final String SPACE = " ";
	
	public static void main(String[] args) throws Exception{
		InputReader in = new InputReader(System.in);
		int V = in.readInt();
		int E = in.readInt();
		
		ArrayList<Integer>[] forward = new ArrayList[V + 1];
		ArrayList<Integer>[] backward = new ArrayList[V + 1];
		scc = new ArrayList<>();
		
		for(int i = 0; i < V + 1; i++) {
			forward[i] = new ArrayList<>();
			backward[i] = new ArrayList<>();
			scc.add(i, new ArrayList<>());
		}
		
		while(E-- > 0) {
			int A = in.readInt();
			int B = in.readInt();
			
			forward[A].add(B);
			backward[B].add(A);
		}
		
		isVisited = new boolean[V + 1];
		for(int i = 1; i < V + 1; i++) {
			if(isVisited[i]) continue;
			isVisited[i] = true;
			
			dfs(forward, i, true);			// 정방향 탐색 후 반환되는 노드 순으로 스택 저장
			stack.push(i);
		}
		
		isVisited = new boolean[V + 1];
		int loop = 0;
		
		while(!stack.isEmpty()) {
			component = new ArrayList<>();
			int start = stack.pop();
			if(isVisited[start]) continue;
			
			component.add(start);
			dfs(backward, start, false);		// 스택에서 가져오며 역방향 탐색 순으로 scc list로 저장
			scc.add(loop++, component);
		}
		
		System.out.println(getRes(loop));
	}
	
	private static void dfs(ArrayList<Integer>[] arr, int current, boolean save) {
		isVisited[current] = true;
		
		for(int next: arr[current]) {
			if(isVisited[next]) continue;
			isVisited[next] = true;
			
			if(!save) component.add(next);
			dfs(arr, next, save);
			if(save) stack.push(next);
		}
	}

	private static StringBuilder getRes(int size) {
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < size; i++) {						// sorting inner list
			Collections.sort(scc.get(i));
		}
		
		Collections.sort(scc, new ListComparator<>());		// sorting list of list
		
		sb.append(size).append(NEW_LINE);
		
		int loop = scc.size();
		for(int i = 0; i < loop; i++) {
			if(scc.get(i).size() == 0) continue;
			
			for(int element : scc.get(i)) {
				sb.append(element).append(SPACE);
			}
			sb.append(-1).append(NEW_LINE);
		}
		
		return sb;
	}
	
	private static class ListComparator<T extends Comparable<T>> implements Comparator<List<T>> {
		@Override
		public int compare(List<T> l1, List<T> l2) {
			for (int i = 0; i < Math.min(l1.size(), l2.size()); i++) {		// 더 작은 사이즈를 받아와서
				int c = l1.get(i).compareTo(l2.get(i));		// 각 리스트 내부를 비교
				if (c != 0) return c;						// -1 또는 1로 나누어 앞으로 당기거나 뒤로 밀어줌
			}
		    
			return Integer.compare(l1.size(), l2.size());	// c == 0 이면 최소 사이즈까지 같은 값이므로 두 리스트 중 짧은 것이 어느것인지 비교하여 반환
		}
	}
	
	private static class InputReader {
		private InputStream stream;
		private byte[] buf = new byte[1024];
		private int curChar;
		private int numChars;
		private SpaceCharFilter filter;

		public InputReader(InputStream stream) {
			this.stream = stream;
		}

		public int read() {
			if (numChars == -1) {
				throw new InputMismatchException();
			}
			if (curChar >= numChars) {
				curChar = 0;
				try {
					numChars = stream.read(buf);
				} catch (IOException e) {
					throw new InputMismatchException();
				}
				if (numChars <= 0) {
					return -1;
				}
			}
			return buf[curChar++];
		}

		public int readInt() {
			int c = read();
			while (isSpaceChar(c)) {
				c = read();
			}
			int sgn = 1;
			if (c == '-') {
				sgn = -1;
				c = read();
			}
			int res = 0;
			do {
				if (c < '0' || c > '9') {
					throw new InputMismatchException();
				}
				res *= 10;
				res += c - '0';
				c = read();
			} while (!isSpaceChar(c));
			return res * sgn;
		}

		public boolean isSpaceChar(int c) {
			if (filter != null) {
				return filter.isSpaceChar(c);
			}
			return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
		}

		public interface SpaceCharFilter {
			public boolean isSpaceChar(int ch);
		}
	}
}
