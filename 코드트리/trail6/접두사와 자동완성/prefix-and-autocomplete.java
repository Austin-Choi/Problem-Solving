import java.util.*;
import java.io.*;

/*
apple app application appointment banana
a l e, a, a l i, a o , b
3, 1, 3, 2, 1
-> 최소 1임
-> 자식 갯수 , isEnd 여부

1. trie에 다 넣고
2. dfs로 자식 갯수 세고
3. 각 단어 trie 따라가면서 
cur.isEnd || cur.countChild != 1 -> 직접 입력
그외 -> 자동완성
*/

public class Main {
    static class Node{
        Node[] child = new Node[26];
        int count;
        boolean isEnd = false;

        Node(){
            for(int i = 0; i<26; i++)
                this.child[i] = null;
        }
    }

    static Node root = new Node();
    static String[] A;

    static void insert(String s){
        Node t = root;
        for(int i = 0; i<s.length(); i++){
            int idx = s.charAt(i) - 'a';
            if(t.child[idx] == null)
                t.child[idx] = new Node();
            t = t.child[idx];
        }
        t.isEnd = true;
    }

    static void countChild(Node cur){
        for(int i = 0; i<26; i++){
            if(cur.child[i] != null){
                cur.count++;
                countChild(cur.child[i]);
            }
        }
    }

    // 자동완성에 필요한 직접 입력 글자수 세기
    // cur은 한칸 앞의 것을 나타냄
    // app의 경우
    // 1) "" 아무것도 없으니 a 쳐야함
    // 2) "a" a의 count는 4이고 isEnd = false, i!=0
    // 3) "ap" p의 count는 4이고 isEnd = false, i!=0
    // 끝 -> 1
    static int countType(String s){
        Node cur = root;
        int ans = 0;

        for(int i = 0; i<s.length(); i++){
            if(i == 0 || cur.isEnd || cur.count != 1)
                ans++;
            cur = cur.child[s.charAt(i) - 'a'];
        }

        return ans;
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

        countChild(root);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<N; i++){
            sb.append(countType(A[i]) + " ");
        }
        System.out.print(sb);
    }
}