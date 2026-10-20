/*
스택쓰고 
?는 ? 짝이 맞으면 없애기 가능

각 문자에서의 전체 밸런스 max 값과 min 값 구하기

*/
import java.util.*;
import java.io.*;

public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        char[] A = br.readLine().toCharArray();

        
        int max = 0;
        int min = 0;

        for(int i = 0; i<N; i++){
            int cur = 0;
            if(A[i] == '('){
                max = max+1;
                min = min+1;
            }
            else if(A[i] == ')'){
                max = max-1;
                min = min-1;
            }
            // ?는 와일드 카드이므로 항상 유리한 쪽으로 보정
            else{
                max = max+1;
                min = min-1;
            }

            min = Math.max(0, min);
            // 밸런스 최대값이 음수인 경우는 뭘해도 no임
            if(max < 0){
                System.out.print("No");
                return;
            }
        }

        if(min == 0 && max % 2 == 0)
            System.out.print("Yes");
        else
            System.out.print("No");
    }
}