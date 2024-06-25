
class Solution {
    public int[] solution(String s) {
        int[] answer = new int[2];
        StringBuilder sb = new StringBuilder(s);
        int biCount = 0;
        int removedZero = 0;
        while(!s.equals("1")){
            while(s.contains("0")){
                sb = new StringBuilder(s);
                sb.deleteCharAt(sb.indexOf("0"));
                s = sb.toString();
                removedZero++;
            }
            s = Integer.toBinaryString(s.length());
            biCount++;
        }
        answer[0] = biCount;
        answer[1] = removedZero;
        return answer;
    }
}