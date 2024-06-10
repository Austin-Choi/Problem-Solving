import java.util.*;

class Solution {
    private int[] addElement(int[] arr,int element){
        int[] newArr = Arrays.copyOf(arr, arr.length+1);
        newArr[arr.length] = element;
        return newArr;
    }
    private int[][] addRow(int[][] arr, int[] row){
        int[][] newArr = Arrays.copyOf(arr, arr.length+1);
        newArr[arr.length] = row;
        return newArr;
    }
    public int[][] solution(int[][] arr) {
        int[][] answer = {};
        int row = arr.length;
        int col = arr[0].length;
        //행의 수가 많다면
        //열의 수가 행의 수와 같아지도록 각 행의 끝에 0을 추가하고
        //오른쪽에 0 추가
        if(row>col){
            int n = row - col;
            for(int i = 0; i<row; i++){
                for(int j = 0; j<n; j++){
                    arr[i] = addElement(arr[i], 0);
                }
            }
            return arr;
        }
        //열의 수가 많다면
        //행의 수가 열의 수와 같아지도록 각 열의 끝에 0을 추가
        else if(row<col){
            int n = col - row;
            for(int i = 0; i<n; i++){
                int[] newRow = new int[col];
                for(int j = 0; j<col; j++){
                    newRow[j] = 0;
                }
                arr = addRow(arr, newRow);
            }
            return arr;
        }
        else
            return arr;
    }
}