import java.util.*;
import java.io.*;

/*
중위순회 결과니까
현재 길이에서 절반이 루트고 그 왼쪽 끝과 처음, 오른쪽 끝과 끝의 절반이 각각 left, right일거고 음..
left가 right보다 커지면 리턴해야함

아 근데 트리 모양이 안되는데?????
레벨에 저장??

*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int K;
    static int[] tree;
    static ArrayList<Integer>[] level;

    static void dfs(int s, int e, int cl){
        // 종료조건
        if(s > e)
            return;

        int mid = (s+e)/2;
        dfs(s,mid-1,cl+1);
        level[cl].add(tree[mid]);
        dfs(mid+1,e,cl+1);
    } 

    public static void main(String[] args) throws IOException{
        K = read();
        tree = new int[(1<<K)-1];
        level = new ArrayList[K];
        for(int i = 0; i<K; i++)
            level[i] = new ArrayList<>();

        for(int i = 0; i<(1<<K)-1; i++){
            tree[i] = read();
        }

        dfs(0, (1<<K)-2, 0);
        StringBuilder sb = new StringBuilder();
        for(ArrayList<Integer> a : level){
            for(int n : a)
                sb.append(n).append(" ");
            sb.append("\n");
        }
        System.out.print(sb);
    }
}