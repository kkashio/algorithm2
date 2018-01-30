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
        for(int i=0; i<=n; i++)
            for (int j=0; j<1<<n; j++)
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
    }

    static int solve(int run, int visited){
        if(run >= k) return 0;

        if(dp[run][visited] != -1) return dp[run][visited];
        dp[run][visited] = MAX;

        for(int i=1; i<=n; i++){
            if((visited & (1 <<(i-1))) > 0){
                for (int j=1; j<=n; j++){
                    if((visited & (1 << (j-1))) == 0){
                        dp[run][visited] = Math.min(dp[run][visited], solve(run+1, (visited | (1 << (j-1))) > 0 ? 1 : 0) + cost[i][j]);
                    }
                }
            }
        }
        return dp[run][visited];
    }
}
