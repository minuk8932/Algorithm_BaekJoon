import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Boj14725 {

    private static final String LEVEL = "--";
    private static final String NEW_LINE = "\n";
    private static final String EMPTY = "";

    private static StringBuilder sb = new StringBuilder();
    private static Trie node;

    public static class Trie {
        private ArrayList<Trie> list;
        private String name;

        public Trie(String name) {
            list = new ArrayList<>();
            this.name = name;
        }

        public ArrayList<Trie> getList() {
            return list;
        }

        public String getName() {
            return name;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Trie trie = new Trie(EMPTY);
        while (N-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int K = Integer.parseInt(st.nextToken());

            node = trie;
            while (K-- > 0) {
                String name = st.nextToken();
                indexMapping(name);
            }
        }

        recursion(trie, -1);
        System.out.println(sb.toString());
    }

    private static void indexMapping(String name) {
        int idx = -1;
        int size = node.list.size();

        for (int i = 0; i < size; i++) {
            if (!node.list.get(i).name.equals(name)) continue;
            idx = i;
            break;
        }

        if (idx == -1) {
            node.list.add(new Trie(name));
            node = node.list.get(node.list.size() - 1);
        }
        else {
            node = node.list.get(idx);
        }
    }

    public static void recursion(Trie trie, int depth) {
        Collections.sort(trie.list, Comparator.comparing(Trie::getName));

        if (depth != -1) {
            for (int i = 0; i < depth; i++) {
                sb.append(LEVEL);
            }

            sb.append(trie.name).append(NEW_LINE);
        }

        for (Trie tries : trie.list) {
            recursion(tries, depth + 1);
        }
    }
}
