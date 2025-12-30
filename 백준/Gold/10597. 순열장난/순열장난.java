/*
1~N의 숫자가 순서 상관없이 섞인 수열임
입력 문자열 길이 L
-> L > 9 면 L-=9; L/2 + 9 가 N임
else N = L

N+1길이의 bool 배열을 만듬

그리디하게 한칸숫자부터 보면 뒤에 나오는 1같은거 못잡음
-> 백트래킹으로 완탐
67891054321-> 10이 뒤 1 자리 먹음
 */
import java.io.*;
import java.util.ArrayList;

public class Main {
    static int N;
    static boolean[] visited;
    static String[] in;
    static ArrayList<Integer> out = new ArrayList<>();
    static String make(int si, int l){
        String rst = "";
        for(int i = si; i < si+l ; i++)
            rst += in[i];
        return rst;
    }
    static boolean bt(int idx){
        if(idx == in.length){
            return true;
        }

        for(int i = 1; i<=2; i++){
            if(idx + i > in.length)
                continue;

            int num = Integer.parseInt(make(idx, i));
            if(num > N || visited[num])
                continue;

            visited[num] = true;
            out.add(num);

            if(bt(idx + i))
                return true;

            visited[num] = false;
            out.remove(out.size()-1);
        }
        return false;
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        int L = input.length();
        if(L>9){
            L-=9;
            N = L/2 + 9;
        }
        else
            N = L;

        StringBuilder sb = new StringBuilder();
        visited = new boolean[N+1];
        visited[0] = true;
        in = input.split("");
        
        bt(0);
        for(int i : out){
            sb.append(i).append(" ");
        }
        System.out.println(sb);
    }
}
