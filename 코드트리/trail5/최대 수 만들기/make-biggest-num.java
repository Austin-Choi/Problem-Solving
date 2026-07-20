import java.util.*;
import java.io.*;

/*
한번씩만 사용하고 모든 수를 이어붙여서 만들수 있는 최댓값
(문자열로 처리)
-> 두개 비교할때 짧은거 길이로 비교하고 그 값이 더 큰게 우선순위가 있음 
-> 값이 같으면 둘중에 길이 짧은거 먼저 
*/

public class Main {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        String[] arr = new String[N];
        for(int i = 0; i<N; i++){
            arr[i] = br.readLine();
        }

        Arrays.sort(arr, (a,b)->
            (b+a).compareTo(a+b)
        );
        
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<N; i++){
            sb.append(arr[i]);
        }
        System.out.print(sb);
    }
}