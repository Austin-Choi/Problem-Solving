class Solution {
    // 해결해야 할 것 
    // 1. 대체 어느 숫자까지 계산해서 calcResult에 split해서 저장해야하나?
    // -> 생각할 필요 없음 
    // 걍 n진법으로 표현한거 1개씩 짤라서 m*t까지 calcResult로 String[]로 저장하면 됨
    
    // 2. n진법 String 어떻게 나타냄?
    // -> 일단 1씩 변환할 숫자 증가시켜주고, 변환 결과값을 갱신하는 식으로 길이 저장해줌
    // -> 길이가 m*t 이상이면 stop
    
    // n는 진법
    // t는 result의 길이 -> m*t 자리까지 calcResult로 저장
    // m은 인원 -> calcResult를 몇번마다 자를 것인가
    // p는 튜브의 순서 -> 잘린 calcResult의 인덱스 값 = p - 1; 이것만 뽑아내서 최종 result에 저장하면됨
    
    public String solution(int r, int t, int m, int p) {
        String answer = "";
        StringBuilder sb = new StringBuilder();
        // Integer.toString(n, r) 10진수 n을 r진수 string으로 변환 
        // Integer.parseInt(x, r) r진수로 표현된 String x를 10진수로 변환
        sb.append(0);
        int n = 1;
        while(true){
            if(sb.length() >= m*t)
                break;
            sb.append(Integer.toString(n, r).toUpperCase());
            n++;
        }
        String[] calcResult = sb.toString().split("");
        for(int i = 0; i<(calcResult.length/m); i++){
            answer += calcResult[(i*m)+p-1];
        }
        return answer;
    }
}