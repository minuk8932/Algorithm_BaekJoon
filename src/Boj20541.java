import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Boj20541 {
    private static final String MAKE_ALBUM = "mkalb";
    private static final String REMOVE_ALBUM = "rmalb";
    private static final String INS = "insert";
    private static final String DEL = "delete";
    private static final String CST = "ca";

    private static final String BEFORE = "..";
    private static final String ROOT = "/";
    private static final String PO = "1", NE = "-1", ZE = "0";

    private static HashSet<String> albums;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        while(N-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();
            albums = new HashSet<>();

            switch (cmd) {
                case MAKE_ALBUM:
                    break;

                case REMOVE_ALBUM:
                    break;

                case INS:
                    break;

                case DEL:
                    break;

                case CST:
                    break;
            }
        }

        System.out.println(sb.toString());
    }
}
