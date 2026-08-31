import java.util.*;
import java.io.*;

/*
사칙연산 우선순위는 순서대로 따라감
하나의 수식에서 같은 알파벳에 여러군데에서 등장 가능.
각 소문자 알파벳에 1~4 를 넣고 알파벳은 최대 a~f까지 나옴
입력에서 알파벳 종류세서 거기까지 넣고 다 넣으면 수식 값 계산해서 최대치로 갱신
*/

public class Main {
    static int M = 0;
    static char[] A;
    static ArrayList<Integer>[] al;
    static long ans = Long.MIN_VALUE;
    static void bt(int ci, int[] cur){
        if(ci == 6){
            int num = cur[A[0] - 'a'];
            for(int i = 1; i<A.length; i+=2){
                char cmd = A[i];
                if(cmd == '+'){
                    num += cur[A[i+1] - 'a'];
                }
                else if(cmd == '-'){
                    num -= cur[A[i+1] - 'a'];
                }
                else{
                    num *= cur[A[i+1] - 'a'];
                }
            }

            ans = Math.max(ans, num);
            return;
        }

        int origin = cur[ci];
        for(int i = 1; i<=4; i++){
            cur[ci] = i;
            bt(ci+1, cur);
            cur[ci] = origin;
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        A = br.readLine().toCharArray();
        al = new ArrayList[6];
        for(int i= 0; i<6; i++)
            al[i] = new ArrayList<>();

        for(int i = 0; i<A.length; i++){
            if(Character.isLowerCase(A[i])){
                al[A[i] - 'a'].add(i);
            }
        }

        int[] start = new int[6];
        bt(0, start);
        System.out.print(ans);
    }
}