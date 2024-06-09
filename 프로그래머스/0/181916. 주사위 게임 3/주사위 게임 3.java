import java.util.*;

class Solution {
    //set size 2일때 숫자 3개가 같을때 그 숫자 구하기
    //set size 3일때 뭐가 2번 반복되는지 구하기
    private int findSameNum(ArrayList<Integer> l){
        for(int i = 0; i<l.size(); i++){
            int lhs = l.get(i);
            for(int j = i+1; j<l.size(); j++){
                if(l.get(j) == lhs){
                    return lhs;
                }
            }
        }
        return -1;
    }
    public int solution(int a, int b, int c, int d) {
        int answer = 0;
        ArrayList<Integer> l = new ArrayList<>();
        Set<Integer> s = new HashSet<>();
        Map<Character, Integer> m = new HashMap<>();
        l.add(a);
        l.add(b);
        l.add(c);
        l.add(d);
        Collections.sort(l);
        
        for(int i : l){
            s.add(i);
        }
        
        //네 주사위에 적힌 숫자가 모두 다르다면 나온 숫자 중 가장 작은 숫자 만큼의 점수를 얻습니다.
        if(s.size() == 4){
            answer = l.get(0);
        }
        //네 주사위에서 나온 숫자가 모두 p로 같다면 1111 × p점을 얻습니다.
        else if(s.size() == 1){
            answer = 1111 * l.get(0);
        }
        //어느 두 주사위에서 나온 숫자가 p로 같고 나머지 두 주사위에서 나온 숫자가 각각 p와 다른 q, r(q ≠ r)이라면 q × r점을 얻습니다.
        else if(s.size() == 3){
            int p = findSameNum(l);
            s.remove(p);
            Iterator it = s.iterator();
            int rst = 1;
            while(it.hasNext()){
                rst *= (int)it.next();
            }
            return rst;
        }
        else{
            boolean isSameCountTwo = false;
            int listSum = l.stream().mapToInt(i->i).sum();
            int setSum = 0;
            
            Iterator it = s.iterator();
            while(it.hasNext()){
                setSum += (int)it.next();
            }
            
            if(setSum*2 == listSum){
                isSameCountTwo = true;
            }

            //주사위가 두 개씩 같은 값이 나오고, 나온 숫자를 각각 p, q(p ≠ q)라고 한다면 (p + q) × |p - q|점을 얻습니다.
            if(isSameCountTwo){
                ArrayList<Integer> tmp = new ArrayList<>();
                Iterator it2 = s.iterator();
                while(it2.hasNext()){
                    tmp.add((int) it2.next());
                }
                int p = tmp.get(0);
                int q = tmp.get(1);
                return (p+ q) * Math.abs(p-q);
            }
            //세 주사위에서 나온 숫자가 p로 같고 나머지 다른 주사위에서 나온 숫자가 q(p ≠ q)라면 (10 × p + q)2 점을 얻습니다.
            else{
                int p = findSameNum(l);
                int q = -1;
                s.remove(p);
                Iterator it3 = s.iterator();
                while(it3.hasNext()){
                    q = (int) it3.next();
                }
                return (int) Math.pow(((10 * p) + q),2);
            }
        }
        return answer;
    }
}