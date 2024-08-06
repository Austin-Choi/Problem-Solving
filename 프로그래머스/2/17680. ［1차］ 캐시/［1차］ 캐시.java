import java.util.*;
// []
// [jeju]
// [jeju pangyo]
// jeju pangyo seoul
// pangyo seoul newyork
// seoul newyork la 

// 5 5 5 5 5 5 5 5 5 

//lru miss나면 가장 오래동안 사용되지 않은 캐시를 교체
// 죄다 소문자로 변한

// 최대 300만
// LinkedHashMap 해서 
// map.getOrDefault(map.key, 0)+1;
// miss나면 도시 키로 넣고 value는 0
// hit 나면 

// LinkedHashSet
// 쭉 나열하고 
// 조회되면 찾아서 삭제하고 뒤에 붙임 +1
// 없고 자리가 남으면 뒤에 붙이고 +5
// 없고 자리 없으면 앞에꺼 삭제하고 뒤에 붙임 +5
class Solution {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        LinkedHashSet<String> s = new LinkedHashSet<>();
        for(String city : cities){
            city = city.toLowerCase();
            //cache hit
            if(s.contains(city)){
                s.remove(city);
                s.add(city);
                answer += 1;
            }
            //cache miss
            else{
                if(s.size()<cacheSize){
                    s.add(city);
                }
                else{
                    if(cacheSize != 0){
                        Iterator it = s.iterator();
                        if(it.hasNext()){
                            s.remove(it.next());
                        }
                        s.add(city);
                    }
                }
                answer+=5;
            }
        }
        
        
            
        return answer;
    }
}