import java.util.Scanner;

/**
 * Created by Seong min on 2018-01-30.
 */
public class Q1102 {
    static int n, k, cost[][], dp[][], MAX = Integer.MAX_VALUE;


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n=sc.nextInt();
        cost=new int[n+1][n+1];
        dp= new int[n+1][1<<n];
        for(int i=1; i<=n; i++)
            for (int j=1; j<1<<n; j++)
                dp[i][j] = -1;
        for(int i=1; i<=n; i++)
            for(int j=1; j<=n; j++)
               cost[i][j] = sc.nextInt();

        String str = sc.next();
        int t = 0;
        int cnt = 0;

        for(int i=0; i<str.length(); i++){
            if(str.charAt(i)=='Y'){
                t |= (1<<i);
                cnt++;
            }
        }

        k = sc.nextInt();

        System.out.println(solve(cnt, t));

        for (int[] a : dp){
            for (int b : a){
                System.out.print(b+" ");
            }
            System.out.println();
        }
    }

    static int solve(int run, int visited){
        if(run >= k) return 0;

        int ret = dp[run][visited];
        if(ret != -1) return ret;
        ret = MAX;

        for(int i=1; i<=n; i++){
            if((visited & (1 <<(i-1))) != 0){
                for (int j=1; j<=n; j++){
                    if((visited & (1 << (j-1))) == 0){
                        ret = Math.min(ret, solve(run+1, visited | (1 << j-1))) + cost[i][j];
                    }
                }
            }
        }
        return ret;
    }
}
