import java.util.*;
import java.io.*;

// dp[i] = T의 앞에서 i글자를 만드는 경우의 수

public class Main {
    static class Node{
        Node[] children = new Node[26];
        boolean isEnd;

        Node(){
            for(int i = 0; i<26; i++){
                children[i] = null;
            }
        }
    }
    
    static String T;
    static final int MOD = 1_000_000_007;
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
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        T = st.nextToken();
        int N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<N; i++){
            insert(st.nextToken());
        }

        int[] dp = new int[T.length()+1];
        dp[0] = 1;

        for(int i = 0; i<T.length(); i++){
            Node t = root;

            for(int j = i; j<T.length(); j++){
                int c = T.charAt(j) - 'a';
                if(t.children[c] == null)
                    break;
                t = t.children[c];
                if(t.isEnd)
                    dp[j+1] = (dp[j+1] + dp[i]) % MOD;
            }
        }
        System.out.print(dp[T.length()]);
    }
}