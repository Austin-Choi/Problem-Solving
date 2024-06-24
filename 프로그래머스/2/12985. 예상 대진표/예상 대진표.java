class Solution
{
    public int solution(int n, int a, int b)
    {
        int answer = 0;
       
        // 1 2 3 4 5 6 7 8
        // 1씩 더해주고 
        // 2 3 4 5 6 7 8 9
        // 2으로 나누면
        // 1 1 / 2 '2' / 3 3 / '4' 4
        // 1 / 2 / 3 / 4 그룹화되면 2번째꺼 부여받음
        
        // 2 3 / 4 5
        // 1 1 / 2 2
        // 한번 그룹화 과정 쭉 일어나면 count++
        
        
        int count = 0;
        while(true){
            a +=1;
            b +=1;
            if(a != b){
                a/=2;
                b/=2;
            }
            else
                break;
            count++;
        }
        

        return count;
    }
}