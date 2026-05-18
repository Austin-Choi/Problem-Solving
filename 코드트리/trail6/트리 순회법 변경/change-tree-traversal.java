import java.util.*;
import java.io.*;

/*
전위니까 맨 앞이 루트값임
문제 조건 설명이 bst같은데 

음.. 
어디서 현재값보다 더 큰 값이 나오는지 O(1)에 구해야하는데
-> 모노토닉 스택
nextGreat[i] = [i] 이후 더 큰 값이 처음 나오는 인덱스 값
-> leftSize  
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N;
    static int[] pre;
    static int[] nextGreat;
    static StringBuilder sb = new StringBuilder();

    // 후위로 출력
    static void dfs(int s, int e){
        if(s > e)
            return;
        
        int root = pre[s];
        int mid = nextGreat[s];

        if(mid > e)
            mid = e+1;

        // l,r, root
        dfs(s+1, mid-1);
        dfs(mid, e);
        sb.append(root).append("\n");
    }

    public static void main(String[] args) throws IOException{
        N = read();
        pre = new int[N];
        for(int i = 0; i<N; i++){
            pre[i] = read();
        }
        nextGreat = new int[N];
        // 만약 root보다 큰 값이 끝까지 없으면 갱신이 안되니까
        Arrays.fill(nextGreat, N);

        // 전에 지난 원소들이 현재 원소보다 작으면 
        // 그 원소들의 다음으로 큰 값 인덱스는 현재 원소의 인덱스임
        Deque<Integer> s = new ArrayDeque<>();
        for(int i = 0; i<N; i++){
            while(!s.isEmpty() && pre[i] > pre[s.peekFirst()])
                nextGreat[s.pollFirst()] = i;
            s.addFirst(i);
        }
        dfs(0,N-1);
        System.out.print(sb);
    }
}