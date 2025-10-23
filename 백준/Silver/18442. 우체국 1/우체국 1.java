/*
거리공식때문에 원형 2배 필요없음
P개 선택됬을때 가장 가까운 마을과의 거리합 구해서 최소값 갱신하고
그때의 list 정답 리스트로 갱신하기
V개중 P를 순서없이 뽑는 조합
 */
import java.util.*;
import java.io.*;
public class Main {
    static int V,P;
    static long L;
    static long[] in;
    static long calc(long a, long b){
        long ab = a-b;
        if(ab < 0)
            ab = -1*ab;
        return Math.min(ab, L-ab);
    }

    static List<Long> minList = new ArrayList<>();
    static long minCost = Long.MAX_VALUE;
    static int[] picked;
    static void bt(int start, int depth){
        if(depth == picked.length){
            long c = totalCalc(picked);
            if(c < minCost){
                minCost = c;
                minList = new ArrayList<>();
                for(int n : picked)
                    minList.add(in[n]);
            }
            return;
        }

        for(int i = start; i <= V - (picked.length-depth); i++){
            picked[depth] = i;
            bt(i+1, depth+1);
        }
    }
    static long totalCalc(int[] pick){
        long rst = 0;
        for(int i = 0; i<V; i++){
            long a = in[i];
            long nearest = Long.MAX_VALUE;
            for(int idx : pick){
                long b = in[idx];
                nearest = Math.min(nearest, calc(a,b));
                if(nearest == 0)
                    break;
            }
            rst += nearest;
            if(rst >= minCost)
                return rst;
        }
        return rst;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        L = Long.parseLong(st.nextToken());

        st = new StringTokenizer(br.readLine());
        in = new long[V];
        for(int i = 0; i<V; i++){
            in[i] = Long.parseLong(st.nextToken());
        }

        picked = new int[P];

        bt(0,0);

        StringBuilder sb = new StringBuilder();
        sb.append(minCost).append("\n");
        for(Long l : minList){
            sb.append(l).append(" ");
        }
        System.out.print(sb);
    }
}
