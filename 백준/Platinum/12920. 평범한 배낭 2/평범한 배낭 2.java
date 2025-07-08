import java.util.*;
import java.io.*;

class Info{
    int v; // 무기
    int c; // 만족도
    int k; // 갯수

    public Info(int v, int c, int k){
        this.v = v;
        this.c = c;
        this.k = k;
    }
}

class Item{
    int weight;
    int val;

    public Item(int w, int v){
        this.weight = w;
        this.val = v;
    }
}

public class Main {
    private static int N, M;
    public static void main(String[] args) throws IOException{
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        //최대 무게
        M = Integer.parseInt(st.nextToken());

        Info[] inputs = new Info[N];
        for(int n = 0; n<N; n++){
            st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            inputs[n] = new Info(v,c,k);
        }

        // 이진 분할로 유니크한 아이템화해서
        // 0-1 배낭문제로 만듬
        List<Item> items = new ArrayList<>();
        int[] dp = new int[M + 1];

        for(Info info : inputs){
            int k = info.k;
            int base = 1;

            while(k>0){
                int count = Math.min(base, k);
                int nv = info.v * count;
                int nc = info.c * count;

                for(int j = M; j >= nv; j--){
                    dp[j] = Math.max(dp[j], dp[j-nv] + nc);
                }

                k -= count;
                base *= 2;
            }
        }

        // 배낭 무게가 i 일때 최대 가치
        // 무게 제한이 M이므로 전체 무게는 필요없음
//        int[] dp = new int[M + 1];
//        for(Item it : items){
//            int w = it.weight;
//            int v = it.val;
//
//            for(int j = M; j >= w; j--){
//                dp[j] = Math.max(dp[j], dp[j-w] + v);
//            }
//        }

        int ans = 0;
        for(int i = 0; i<=M; i++){
            if(ans < dp[i]){
                ans = dp[i];
            }
        }

        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
        br.close();
    }
}