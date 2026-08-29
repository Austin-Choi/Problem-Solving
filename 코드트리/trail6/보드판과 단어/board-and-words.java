import java.util.*;
import java.io.*;

/*
n개의 단어를 trie에 넣기
*/

public class Main {
    static int[] di = {-1,-1,0,1,1,1,0,-1};
    static int[] dj = {0,1,1,1,0,-1,-1,-1};
    static char[][] board = new char[4][4];
    static int N;

    static class Node{
        Node[] children = new Node[26];
        boolean isEnd;

        Node(){
            for(int i = 0; i<26; i++)
                children[i] = null;
            isEnd = false;
        }
    }
    static Node root = new Node();

    static void insert(String s){
        Node t = root;
        for(int i = 0; i<s.length(); i++){
            int idx = s.charAt(i) - 'a';
            if(t.children[idx] == null)
                t.children[idx] = new Node();
            t = t.children[idx];
        }    
        t.isEnd = true;
    }

    static int maxLen = 0;
    static boolean[][] v = new boolean[4][4];

    static void dfs(int ci, int cj, int depth, Node cur){
        if(depth > 8)
            return;
        // 단어 완성됬을 때만 갱신
        if(cur.isEnd)
            maxLen = Math.max(maxLen, depth);

        for(int d= 0; d<8; d++){
            int ni = ci + di[d];
            int nj = cj + dj[d];

            if(ni < 0 || nj < 0 || ni >= 4 || nj >= 4)
                continue;

            int idx = board[ni][nj] - 'a';
            if(cur.children[idx] != null){
                if(!v[ni][nj]){
                    v[ni][nj] = true;
                    dfs(ni, nj, depth+1, cur.children[idx]);
                    v[ni][nj] = false;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        String[] A = new String[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i<N; i++){
            A[i] = st.nextToken();
            insert(A[i]);
        }

        for(int i = 0; i<4; i++){
            char[] t = br.readLine().toCharArray();
            for(int j = 0; j<4; j++){
                board[i][j] = t[j];
            }
        }

        for(int i = 0; i<4; i++){
            for(int j = 0; j<4; j++){
                // 맨앞 1칸 이미 사용하고 dfs 들어감
                if(root.children[board[i][j] - 'a'] != null){
                    v[i][j] = true;
                    dfs(i,j,1,root.children[board[i][j] - 'a']);
                    v[i][j] = false;
                }
            }
        }
        System.out.print(maxLen);
    }
}