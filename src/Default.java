public class Default {
    private static final int INF = 1_00_000;
    public static void main(String[] args) throws Exception {
        System.out.println(1_000 + " " + 1_000 + " " + 9);
        for(int i = 0; i < 9; i++) {
            System.out.print(INF + " ");
        }
        System.out.println();

        System.out.print(1);
        for(int i = 0; i < 998; i++) {
            System.out.print('.');
        }
        System.out.println(2);

        for(int i = 0; i < 998; i++) {
            for(int j = 0; j < 1000; j++) {
                if(i == 213 && j == 200) System.out.print(5);
                else if(i == 444 && j == 888) System.out.print(6);
                else if(i == 676 && j == 1) System.out.print(7);
                else if(i == 812 && j == 332) System.out.print(8);
                else if(i == 555 && j == 690) System.out.print(9);
                else System.out.print('.');
            }
            System.out.println();
        }

        System.out.print(3);
        for(int i = 0; i < 998; i++) {
            System.out.print('.');
        }
        System.out.println(4);
    }
}


