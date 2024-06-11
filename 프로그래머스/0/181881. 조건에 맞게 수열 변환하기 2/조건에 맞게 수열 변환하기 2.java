class Solution {
    private boolean isSameArr(int[] a, int[] b){
        for(int i = 0; i<a.length; i++){
            if(a[i] != b[i]){
                return false;
            }
        }
        return true;
    }
    public int solution(int[] arr) {
        int answer = 0;
        int[] curArr = new int[arr.length];
        System.arraycopy(arr,0,curArr,0,arr.length);
        while(true){
            for(int i = 0; i<arr.length; i++){
                if(arr[i]>=50 && arr[i]%2==0)
                    arr[i]/=2;
                else if(arr[i]<50 && arr[i]%2!= 0){
                    arr[i] = arr[i]*2+1;
                }
            }

            if(isSameArr(curArr,arr))
                break;
            else{
                System.arraycopy(arr,0,curArr,0,arr.length);
                answer++;
            }
                
        }
        return answer;
    }
}