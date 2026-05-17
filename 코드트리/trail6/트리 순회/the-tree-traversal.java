import java.util.*;
import java.io.*;

/*
입력에서 관계만 주고 힙 위치 추론은 불가능해서 
left right배열 쓰기
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int readNum() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static char read() throws IOException{
        sst.nextToken();

        if(sst.ttype == StreamTokenizer.TT_WORD)
            return sst.sval.charAt(0);

        return (char) sst.ttype;
    }

    static int N;
    // 왼쪽, 오른쪽 자식
    static char[] left;
    static char[] right;
    static StringBuilder sb = new StringBuilder();

    static void preOrder(int ci){
        char cur = (char)(ci + 'A');
        sb.append(cur);
        if(left[ci] != '.')
            preOrder(left[ci]-'A');
        if(right[ci] != '.')
            preOrder(right[ci]-'A');
    }

    static void inOrder(int ci){
        char cur = (char)(ci + 'A');
        
        if(left[ci] != '.')
            inOrder(left[ci]-'A');
        sb.append(cur);
        if(right[ci] != '.')
            inOrder(right[ci]-'A');
    }

    static void postOrder(int ci){
        char cur = (char)(ci + 'A');
        
        if(left[ci] != '.')
            postOrder(left[ci]-'A');
        if(right[ci] != '.')
            postOrder(right[ci]-'A');
        sb.append(cur);
    }

    public static void main(String[] args) throws IOException{
        // 파싱문제
        sst.ordinaryChar('.');

        N = readNum();
        left = new char[26];
        Arrays.fill(left, '.');
        right = new char[26];
        Arrays.fill(right, '.');

        while(N-->0){
            char u = read();
            char v1 = read();
            char v2 = read();

            left[u-'A'] = v1;
            right[u-'A'] = v2;
        }

        preOrder(0);
        sb.append("\n");
        inOrder(0);
        sb.append("\n");
        postOrder(0);

        System.out.print(sb);
    }
}