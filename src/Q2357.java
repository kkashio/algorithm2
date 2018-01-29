import java.util.ArrayList;
import java.util.Scanner;

public class Q2357 {
    static int n,m;
    static final int MAX = Integer.MAX_VALUE, MIN = Integer.MIN_VALUE;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n=sc.nextInt(); m=sc.nextInt();

        SegTree maxValueTree = new SegTree(true);
        SegTree minValueTree = new SegTree(false);

        for (int i=1; i<=n; i++){
            int data = sc.nextInt();
            maxValueTree.update(i, data);
            minValueTree.update(i, data);
        }

        for (int i=1; i<=m; i++){
            int start = sc.nextInt();
            int end = sc.nextInt();
            System.out.println(minValueTree.min(start, end)+" "+maxValueTree.max(start, end));
        }

    }

    static class SegTree {
        int first;
        boolean isMax;
        ArrayList<Integer> tree = new ArrayList<>();

        public SegTree(boolean isMax){
            this.isMax = isMax;

            for(first = 1; first<n; first<<=1);
            first--;

            if(isMax)
                for (int i=0; i<n<<2; i++)
                    tree.add(MIN);
            else
                for (int i=0; i<n<<2; i++)
                    tree.add(MAX);
        }

        public void update(int pos, int val){
            pos += first;
            tree.set(pos,val);

            if(isMax)
                while((pos>>1) > 0) {
                    tree.set(pos >> 1, Math.max(tree.get(pos>>1), tree.get(pos)));
                    pos >>= 1;
                }
            else
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

        public int max(int left, int right){
            left += first;
            right += first;
            int result = MIN;

            while(left <= right){
                result = Math.max(result, Math.max(tree.get(left), tree.get(right)));
                left = (left+1)/2;
                right = (right-1)/2;
            }
            return result;
        }
    }
}
