import java.util.*;
import java.io.*;

/*
인코딩 이후 최소 길이는 
적절히 오른쪽으로 시프트했을때 연속된 클러스터의 갯수가 알파벳의 종류의 갯수와 최대한 같아야 함. 
->  aaaabbbcbbaaacaaa 이런식으로 있으면 절대 종류 = 클러스터 갯수 될수 없음.

그리고 문자열 자체 길이라 
a가 10개 있으면 a10이라 2가 아니라 3임

제일 긴 걸 한번에 묶어서 처리해야함 

linkedHashMap
-> 키의 유일성이 유지가 안돼서 쓸 수 없음.

그냥 N번 시프팅해보고 그때마다 인코딩해서 길이 최소화로 갱신??
*/

public class Main {

    static int getEncodeSize(char[] a){
        char prev = a[0];
        int cnt = 1;
        int n = a.length;

        StringBuilder sb = new StringBuilder();

        for(int i = 1; i<n; i++){
            if(prev == a[i]){
                cnt++;
            }
            else{
                sb.append(prev).append(cnt);
                prev = a[i];
                cnt = 1;
            }
        }
        sb.append(prev).append(cnt);
        //System.out.println(sb);
        return sb.toString().length();
    }

    static void print(char[] a){
        for(int i = 0; i<a.length; i++){
            System.out.print(a[i]);
        }
        System.out.println();
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] A = br.readLine().toCharArray();
        int N = A.length;

        int ans = 21;
        for(int i = 0; i<N; i++){
            char[] copy = new char[N];
            for(int j = 0; j<N; j++){
                copy[(j+i)%N] = A[j];
            }

            //print(copy);

            ans = Math.min(ans, getEncodeSize(copy));
            A = copy;
        }
        System.out.print(ans);
    }
}