import java.util.*;
import java.io.*;

/*
뒤에서부터 넣는게 최적임
한번 공 넣는거 실패하면 break

공항문제랑 똑같음
find로 다음에 넣을 곳 저장 
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N,M;
    static int[] parent;

    static int find(int x){
        if(parent[x] == x)
            return x;
        return parent[x] = find(parent[x]);
    }

    public static void main(String[] args) throws IOException{
        N = read();
        parent = new int[N+1];
        for(int i= 0; i<=N; i++)
            parent[i] = i;
        M = read();
        int ans = 0;
        while(M-->0){
            int cur = read();
            int pc = find(cur);
            if(pc == 0)
                break;
            else{
                parent[pc] = find(pc-1);
                ans++;
            }
        }
        System.out.print(ans);
    }
}