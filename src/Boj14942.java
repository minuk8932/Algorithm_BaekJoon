import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Boj14942 {
    private static ArrayList<Node>[] path;
    private static int[] energy;

    private static class Node{
        int node;
        int cost;

        public Node(int node, int cost){
            this.node = node;
            this.cost = cost;
        }
    }

    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        path = new ArrayList[n];

        for(int i = 0; i < n; i++){
            energy[i] = Integer.parseInt(br.readLine());
            path[i] = new ArrayList<>();
        }

        int loop = n - 1;
        while(loop-- > 0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken());

            path[a].add(new Node(b, c));
        }
    }
}
