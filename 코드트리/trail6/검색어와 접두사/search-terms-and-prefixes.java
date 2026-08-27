import java.util.*;
import java.io.*;

/*
trie에 검색 문자 먼저 넣고 
n개 넣을때마다 
주어진 단어 한글자씩 넣어서 
*/

public class Main {
    static class Node{
        Node[] children = new Node[26];
        int[] count = new int[26];
        boolean isEnd;

        Node(){
            for(int i = 0; i<26; i++){
                children[i] = null;
            }
            isEnd = false;
        }
    }

    static Node root = new Node();

    static void insert(String s){
        Node t = root;
        for(int i = 0; i<s.length(); i++){
            int idx = s.charAt(i) - 'a';
            if(t.children[idx] == null){
                t.children[idx] = new Node();
            }
            t.count[idx]++;
            t = t.children[idx];
        }
        t.isEnd = true;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<N; i++){
            insert(st.nextToken());
        }        
        String B = br.readLine();

        StringBuilder sb = new StringBuilder();
        Node t = root;
        int b  = 0;
        for(int i= 0; i<B.length(); i++){
            int idx = B.charAt(i) - 'a';
            sb.append(t.count[idx]+" ");
            if(t.children[idx] == null){
                b = i+1;
                break;
            }
            t = t.children[idx];
        }
        for(; b<B.length(); b++)
            sb.append(0+" ");
        System.out.print(sb);
    }
}