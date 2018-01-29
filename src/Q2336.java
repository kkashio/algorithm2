import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Seong min on 2018-01-30.
 */
public class Q2336 {
    static int n, MAX=Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n=sc.nextInt();
        int t=3;
        SegTree treeA = new SegTree();
        SegTree treeB = new SegTree();
        SegTree treeC = new SegTree();

        for(int i=1; i<=n; i++){
            int data = sc.nextInt();
            treeA.update(i,data);
        }

        for(int i=1; i<=n; i++){
            int data = sc.nextInt();
            treeB.update(i,data);
        }

        for(int i=1; i<=n; i++){
            int data = sc.nextInt();
            treeC.update(i,data);
        }

        int leaf;
        for(leaf=1; leaf<n; leaf<<=1);
        leaf--;

        for(int i=1; i<=n; i++){
            for(int j=1; j<=n; j++) {
                if (treeA.tree.get(i+leaf) < treeA.tree.get(j+leaf)) {

                }
            }
        }



    }


    static class SegTree {
        ArrayList<Integer> tree;
        int first;
        public SegTree(){
            tree = new ArrayList<>();

            for(first=1; first<n; first<<=1);
            first--;

            for (int i=0; i<n<<2; i++)
                tree.add(MAX);
        }

        public void update(int pos, int val){
            pos += first;
            tree.set(pos, val);

            while((pos>>1) > 0) {
                tree.set(pos >> 1, Math.min(tree.get(pos>>1), tree.get(pos)));
                pos >>=1;
            }
        }

        public int min(int left, int right){
            left += first;
            right += first;
            int result = MAX;

            while(left <= right){
                result = Math.min(result, Math.min(tree.get(left), tree.get(right)));
                left = (left+1)/2;
                right = (right-1)/2;
            }
            return result;
        }


    }
}
