import java.util.*;
/*
1 2 3
2 1
1 2 4 3
2
2 1 3 4

4 2 3
3
2 3 4 1
2 3
3 2 4 1

일단 {} 처리를 해서 청크별로 나눔
제일 짧은거 원소가 시작점
그다음 자리에서 1번원소 빼고 나머지 뒤에 붙임
그다음 청크에서 1,2번원소 빼고 나머지 뒤에 붙임
그다음 청크에서 1,2,3번 원소 빼고 나머지 뒤에 붙임


2 2,1 2,1,3}A2,1,3,4
*/
class Solution {
    public List<Integer> solution(String s) {
        s = s.substring(2, s.length());
        s = s.substring(0, s.length()-2);
        s = s.replace(",{", "A");
        String[] ls = s.split("}A");
        
        // 4,2,3 | 3 | 2,3,4,1 | 2,3
        List<List<Integer>> li = new ArrayList<List<Integer>>();
        for(String str : ls){
            StringTokenizer st = new StringTokenizer(str,",");
            List<Integer> temp = new ArrayList<Integer>();
            while(st.hasMoreTokens()){
                temp.add(Integer.parseInt(st.nextToken()));
            }
            li.add(temp);
        }
        
        Collections.sort(li, new Comparator<List>(){
            @Override
            public int compare(List o1, List o2){
                return o1.size() - o2.size();
            }
        });
        
        List<Integer> answer = new ArrayList<Integer>();
        for(List<Integer> tmp : li){
            for(int i = 0; i<answer.size(); i++){
                tmp.remove(answer.get(i));
            }
            answer.add(tmp.get(0));
        }
        
        return answer;
    }
}