/*
그래프?
log n 번 이동해서 어디에 도착하느냐
희소 배열 2차원 = log(maxN)+1* n+1

희소배열 정의
쿼리에서 주어지는 이동횟수의 최댓값의 log값으로

쿼리
n을 이진수로 쪼개서 켜져있는 비트만큼 점프하기
 */

import java.util.*;
import java.io.*;
public class Main {
    static int N;
    static int[] arr;
    static int[][] sparseTable;
    static int maxN;
    static void init(){
        int maxlog = 1;
        while((1<<maxlog) <= maxN)
            maxlog++;
        sparseTable = new int[maxlog][N+1];

        for(int i = 1; i<=N; i++){
            sparseTable[0][i] = arr[i];
        }

        // log N 번까지 이동해서 도착하는 정점
        for(int k = 1; k<sparseTable.length; k++){
            for(int i = 1; i<=N; i++){
                int next = sparseTable[k-1][i];
                sparseTable[k][i] = sparseTable[k-1][next];
            }
        }
    }

    static int query(int n, int v){
        for(int k = 0; n>0; k++){
            if((n & 1) == 1)
                v = sparseTable[k][v];
            n >>= 1;
        }
        return v;
    }

    static class Query{
        int n;
        int v;

        Query(int n, int v){
            this.n = n;
            this.v = v;
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N+1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i<N+1; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int q = Integer.parseInt(br.readLine());
        Query[] queries = new Query[q];
        maxN = 0;
        for(int i = 0; i<q; i++){
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            maxN = Math.max(maxN, n);
            queries[i] = new Query(n,x);
        }

        init();
        for(Query qq : queries){
            System.out.println(query(qq.n, qq.v));
        }
    }
}
