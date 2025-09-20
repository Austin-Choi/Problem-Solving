/*
앞 뒤로 잘라내서 직접 더하고
가운데는 패턴 파악해서 통으로 계산
digit 올려가며 반복 (s<=e)

1) 앞뒤 정리
시작 s를 0으로 만들고 e를 9로 끝나게 만들기

2) 10~99, 100~999 패턴 파악
각 숫자가 자릿수마다 반복적으로 등장
(e/10 - s/10 + 1) * digit을 모든 숫자에 더함

3) digit 올리기
s/=10, e/=10, digit*=10해서 
자릿수 올려가며 반복
 */
import java.io.*;
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long N = Long.parseLong(br.readLine());

        long[] ans = new long[10];
        long s = 1;
        long e = N;
        long curDigit = 1;

        while(s <= e){
            while(s%10 != 0 && s <= e){
                long x = s;
                while(x > 0){
                    ans[(int)(x%10)] += curDigit;
                    x /= 10;
                }
                s++;
            }

            while(e%10 != 9 && s <= e){
                long x = e;
                while(x>0){
                    ans[(int)(x%10)] += curDigit;
                    x /= 10;
                }
                e--;
            }

            if(s>e)
                break;

            long block = (e/10 - s/10 + 1);
            for(int i = 0; i<10; i++){
                ans[i] += block * curDigit;
            }

            s /= 10;
            e /= 10;
            curDigit *= 10;
        }

        for(long a : ans){
            System.out.print(a+" ");
        }
    }
}