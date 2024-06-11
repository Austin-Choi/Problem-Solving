class Solution {
    public int solution(String myString, String pat) {
        int answer = 0;
        //substring(start)
        //매개변수가 두개면 start, end고
        //매개변수가 한개면 start에서 무조건 끝까지
        for(int i = 0; i<myString.length(); i++){
            if(myString.substring(i).startsWith(pat))
                answer++;
        }
        return answer;
    }
}