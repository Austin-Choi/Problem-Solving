import java.util.*;
import java.io.*;



public class Main {
    static class Node{
        Node[] children = new Node[26];
        int count;
        boolean isEnd;

        Node(){
            for(int i =0 ; i<26; i++)
                this.children[i] = null;
            this.isEnd = false;
        }
    }

    static Node root = new Node();
    static String[] A;

    static void insert(String s){
        Node t = root;
        for(int i = 0; i<s.length(); i++){
            int idx = s.charAt(i) - 'a';
            if(t.children[idx] == null){
                t.children[idx] = new Node();
            }
            t = t.children[idx];
            // 접두사 count라 이동후에
            t.count++;
        }
        t.isEnd = true;
    }
    
    // 문자열의 길이 * 해당 문자열이 접두사가 되는 서로 다른 단어의 수
    // 트리의 깊이 * 그때의 count 의 최대값
    static long ans = 0;
    static void getMax(Node cur, int depth){
        ans = Math.max(ans,(long) cur.count * depth);

        for(int i = 0; i<26; i++){
            if(cur.children[i] != null){
                getMax(cur.children[i], depth+1);
            }
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        A = new String[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i<N; i++){
            A[i] = st.nextToken();
            insert(A[i]);
        }
        getMax(root, 0);
        System.out.print(ans);
    }
}