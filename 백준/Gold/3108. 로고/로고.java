import java.io.*;
import java.util.*;
/*
* pu명령은 연필 올리는 명령임.
* N개의 직사각형을 그릴때 -> 0,0에서 시작해서 각 4개의 꼭지점을 방문할때
* 연필은 최대한 떼지않고 그릴때 그니까 이 점들은 어떤 그래프를 구성하는데
* 그 구성요소의 갯수 -1 을 구하기
* -> 근데 방문한 정점 또 방문해도 되니까 mst까진 아닌거같고
*
* 가상의 정점 0,0,0,0 직사각형 추가하고
* 직사각형끼리 교차하면 한번에 같이 그릴 수 있음 -> 꼭지점 하나만 교차하거나 두점이 교차하거나
* 그 외는 따로 연필을 한번 뗏다가 그려야함
* dsu로 직사각형을 비교해서 같이 그릴수 있으면 union.
* 그리고 나서 parent의 세기
* */
public class Main{
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException {
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N;
    static int[] parent;
    static ArrayList<int[]> rect;
    static int find(int x){
        if(parent[x] == x)
            return x;
        return parent[x] = find(parent[x]);
    }
    static boolean canUnion(int[] a, int[] b){
        // a안에 b 들어있음
        if(a[0] < b[0] && a[1] < b[1]
                && a[2] > b[2] && a[3] > b[3])
            return false;
        // b안에 a들어있음
        if(b[0] < a[0] && b[1] < a[1] && b[2] > a[2] && b[3] > a[3])
            return false;
        // a,b 둘 다 완전히 떨어져있음
        if(a[2] < b[0] || b[2] < a[0] || a[3] < b[1] || b[3] < a[1])
            return false;
        return true;
    }
    static void union(int a, int b){
        int pa = find(a);
        int pb = find(b);
        if(pa == pb)
            return;
        if(canUnion(rect.get(a), rect.get(b)))
            parent[pb] = pa;
    }

    public static void main(String[] args) throws IOException{
        rect = new ArrayList<>();
        N = read();
        parent = new int[N+1];
        for(int i = 0; i<=N; i++){
            parent[i] = i;
        }
        rect.add(new int[]{0,0,0,0});
        for(int i = 0; i<N; i++){
            rect.add(new int[]{read(), read(), read(), read()});
        }

        // 모든 쌍 비교하고
        // parent[x] == x 면 자기 자신이 루트인거라 그거 세고 -1
        // 가상정점 추가했으므로
        for(int i = 0; i<=N; i++){
            for(int j = i+1; j<=N; j++){
                union(i,j);
            }
        }
        int ans = 0;
        for(int i = 0; i<=N; i++){
            if(parent[i] == i)
                ans++;
        }
        System.out.print(ans-1);
    }
}
