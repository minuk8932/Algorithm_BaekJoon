package tree_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 *
 * @author exponential-e
 * 백준 22856번: 트리 순회
 *
 * @see https://www.acmicpc.net/problem/22856
 *
 */
public class Boj22856 {

    private static Node[] tree;

    private static int answer;

    private static class Node {
        int left;
        int right;

        public Node(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = PARSE.apply(br.readLine());
        tree = ARRY.apply(N);

        int loop = N;
        while(loop-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int node = PARSE.apply(st.nextToken()) - 1;
            int left = PARSE.apply(st.nextToken()) - 1;
            int right = PARSE.apply(st.nextToken()) - 1;

            tree[node] = new Node(left, right);
        }

        int node = 0;
        while(true) {
            node = tree[node].right;
            if(!HAS_SON.test(node)) break;

            answer--;
        }

        recursion(0);
        System.out.println(answer);
    }

    private static void recursion(int current) {
        Node next = tree[current];

        if(HAS_SON.test(next.left)) {
            answer++;
            recursion(next.left);
            answer++;
        }

        if(HAS_SON.test(next.right)) {
            answer++;
            recursion(next.right);
            answer++;
        }
    }

    private static final Function<String, Integer> PARSE = Integer::parseInt;
    private static final Function<Integer, Node[]> ARRY = Node[]::new;

    private static final Predicate<Integer> HAS_SON = node -> node >= 0;

}
