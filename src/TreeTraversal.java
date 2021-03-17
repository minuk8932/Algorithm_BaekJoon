public class TreeTraversal {

    public static class TreeNode {
        Object data;
        TreeNode left;
        TreeNode right;

        public TreeNode(Object data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    public static class LinkedTree {
        private static TreeNode root;

        public TreeNode makeBT(TreeNode bt1, Object data, TreeNode bt2) {
            TreeNode root = new TreeNode(data);
            root.left = bt1;
            root.right = bt2;
            return root;
        }

        public void order(TreeNode root, int flag) {
            if (root != null) {
                if(flag == 0) System.out.print(root.data + " ");
                order(root.left, flag);
                if(flag == 1) System.out.print(root.data + " ");
                order(root.right, flag);
                if(flag == 2) System.out.print(root.data + " ");
            }
        }
    }

    public static void main(String[] args) {
        LinkedTree tree = new LinkedTree();

        TreeNode n7 = tree.makeBT(null, 'D', null);
        TreeNode n6 = tree.makeBT(null, 'C', null);
        TreeNode n5 = tree.makeBT(null, 'B', null);
        TreeNode n4 = tree.makeBT(null, 'A', null);
        TreeNode n3 = tree.makeBT(n6, '/', n7);
        TreeNode n2 = tree.makeBT(n4, '*', n5);
        TreeNode n1 = tree.makeBT(n2, '-', n3);

        System.out.print("\nPreorder : ");
        tree.order(n1, 0);

        System.out.print("\nInorder : ");
        tree.order(n1, 1);

        System.out.print("\nPostorder : ");
        tree.order(n1, 2);
    }
}
