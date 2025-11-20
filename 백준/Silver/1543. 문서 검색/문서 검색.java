/*
패턴에 대해서 점프할 위치를 미리 구해놓음
-> pi[i] = 0~i까지에서의 반복되는 최대 문자열 길이 저장

원문이랑 비교하다가 불일치가 나면 j = pi[j-1]로 하고 i랑 비교한다
일치하면 쭉 j와 i를 증가시키고 j가 끝나면 count + 1
불일치하면 반복
--
패턴 매칭됬을때 j = pi[j-1]로 하면 겹치게 찾고 j=0하면 안겹치게 찾음
 */

import java.io.*;
public class Main {
    static int[] pi;
    static int[] prefix(String s){
        char[] temp = s.toCharArray();
        int n = s.length();
        int[] p = new int[n];

        int j = 0;
        for(int i = 1; i<n; i++) {

            while (j > 0 && temp[i] != temp[j])
                j = p[j - 1];

            if (temp[i] == temp[j])
                p[i] = ++j;
        }

        return p;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        char[] input = s.toCharArray();
        String pattern = br.readLine();
        char[] p = pattern.toCharArray();
        pi = prefix(pattern);

        int ans = 0;
        int j = 0;
        for(int i = 0; i<input.length;i++){
            while(j>0 && input[i] != p[j])
                j = pi[j-1];
            if(input[i] == p[j]){
                j++;
                if(j == p.length){
                    ans++;
                    j = 0;
                }
            }
        }
        System.out.println(ans);
    }
}