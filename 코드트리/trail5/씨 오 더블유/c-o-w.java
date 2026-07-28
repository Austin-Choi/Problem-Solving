import java.util.*;
import java.io.*;

/*
문자열에서 3가지별로 이제까지 각 문자 몇번나오는지 세서
cow 만족하려면
c는 o이전에 있는 것들만 유효하고
o는 w이전에 있는 것들만 유효함

그럼 prefix는 pc[i] = i번째까지봤을때 o이전의 c 갯수? 이런건가
-------------
o를 중간으로 두고 
현재 o 이전의 C 갯수, 현재 o 이후의 w갯수
문자열 쭉 보면서 o 나올때마다 처리하기
그러면 prefix는 c만 세서 누적하고
suffix는 w만 세서 누적하기??
*/

public class Main {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        char[] A = br.readLine().toCharArray();

        int[] pc = new int[N+1];
        int[] sw = new int[N+1];

        for(int i = 0; i<N; i++){
            pc[i+1] = pc[i] + (A[i] == 'C' ? 1 : 0); 
        }
        for(int i = N-2; i>=0; i--){
            sw[i] = sw[i+1] + (A[i+1] == 'W' ? 1 : 0);
        }

        long ans = 0;
        for(int i = 0; i<N; i++){
            if(A[i] == 'O'){
                ans += pc[i] * sw[i];
            }
        }
        System.out.print(ans);
    }
}