import java.util.*;

class Solution {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        //Integer.toBinaryString 비트연산 or
        //n 주어지는건 앞에 0 처리하라고 준듯?
        //tobinstr 하고 길이 n보다 작으면 앞에 0 채워주고
        //순환하면서 1이면 # 0이면 공백 찍어주기
        String[] ar1 = new String[arr1.length];
        StringBuilder sb = new StringBuilder();
        
        for(int i = 0; i<arr1.length; i++){
            ar1[i] = Integer.toBinaryString(arr1[i] | arr2[i]);   
            for(int j = 0; j<n-ar1[i].length(); j++){
                sb.append(0);
            }
            ar1[i] = sb.toString() + ar1[i];
            sb = new StringBuilder();
        }
        
        for(int i = 0; i<ar1.length; i++){
            ar1[i] = ar1[i].replace("1","#");
            ar1[i] = ar1[i].replace("0"," ");
        }
        return ar1;
    }
}