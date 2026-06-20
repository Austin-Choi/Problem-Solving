import java.util.*;
import java.io.*;

/*
M개의 간선의 가중치는 10^9+1로 설정
나머지는 0
M이 2 이상이므로 중앙을 꼭 지나갈 필요는 없긴 함
중앙 지점 추가해서 prim 진행해서 가중치 sum 구함 
-> 아니면 클러스터별로 midCost 제일 작은거 정해서 전부 sum 해도 됨

공사구간 정보는 항상 a, a+1로 주어지므로 a로 나타낼 수 있음 
*/


public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N,M,K;
    static final int INF = Integer.MAX_VALUE / 2;
    static int[] midCost;
    static Set<Integer> m = new HashSet<>();

    public static void main(String[] args) throws IOException{
        N = read();
        M = read();
        K = read();
        midCost = new int[N];
        for(int i = 0; i<N; i++){
            midCost[i] = read();
        }
        while(M-->0){
            int a = read()-1;
            int b = read()-1;
            if(b < a){
                int t = a;
                a = b;
                b = t;
            }
            m.add(a);
        }

        int idx = 0;
        while(m.contains(idx)){
            idx++;
        }

        ArrayList<Integer> mins = new ArrayList<>();
        int s = (idx+1)%N;
        int curMin = INF;

        for(int i = 0; i<N; i++){
            int cur = (s+i) % N;
            curMin = Math.min(curMin, midCost[cur]);

            //cur와 cur+1 공사중
            if(m.contains(cur)){
                mins.add(curMin);
                curMin = INF;
            }
        }

        long sum = 0;
        int minVal = INF;

        for(int x : mins){
            sum += x;
            minVal = Math.min(minVal, x);
        }
        sum += (long) minVal * (mins.size()-2);
        System.out.print(sum <= K ? 1 : 0);
    }
}