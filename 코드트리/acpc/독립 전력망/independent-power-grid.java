/*
주어진 그래프를 K개의 사이클 없는 트리들(forest)로 구성할 수 있는지
각 트리에는 발전소 1개가 꼭 있어야 함 발전소 K개 주어짐.
불가능하다면 -1
가능하다면 그렇게 만들수 있는 최소 비용 구하기

마을 최대 20만, 도로 최대 30만, 같은 두 마을을 연결하는 중복되는 도로 있을 수 있음
도로 제거 비용 최대 10^9승

크루스칼 알고리즘이랑 유니온파인드 쓰기
간선 가중치를 sum up해가지고 전체 간선 가중치 sum에서 빼기?
*/
import java.util.*;
import java.io.*;

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N,M,K;
    static int[] parent;
    static boolean[] hasPlant;
    static int selectedCount = 0;
    static long forestCost = 0;

    static int find(int x){
        if(parent[x] == x)
            return x;
        return parent[x] = find(parent[x]);
    }

    static void union(int a, int b, int c){
        int pa = find(a);
        int pb = find(b);

        if(pa == pb)
            return;
        if(hasPlant[pa] && hasPlant[pb])
            return;

        parent[pb] = pa;
        hasPlant[pa] = hasPlant[pa] || hasPlant[pb];
        selectedCount++;
        forestCost += c;
    }

    public static void main(String[] args) throws IOException{
        N = read();
        M = read();
        K = read();

        long allCost = 0;
        parent = new int[N+1];
        for(int i = 0; i<=N; i++){
            parent[i] = i;
        }
        hasPlant = new boolean[N+1];

        for(int k = 0; k<K; k++){
            int p = read();
            hasPlant[p] = true;
        }

        int[][] A = new int[M][3];
        for(int m = 0; m<M; m++){
            int u = read();
            int v = read();
            int c = read();
            allCost += c;
            A[m] = new int[]{u,v,c};
        }

        Arrays.sort(A, Comparator.comparingInt(a->-a[2]));

        /*
        가중치 큰 순으로 정렬하고 크루스칼로 연결하는데 
        연결할때마다 forestCost에 sumup하고 전체에서 빼주기
        */
        for(int m = 0; m<M; m++){
            int u = A[m][0];
            int v = A[m][1];
            int c = A[m][2];

            union(u,v,c);
        }

        if(selectedCount != N-K){
            System.out.print(-1);
            return;
        }

        System.out.print(allCost - forestCost);
    }
}