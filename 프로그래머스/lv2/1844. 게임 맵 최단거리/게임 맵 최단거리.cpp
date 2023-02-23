#include <vector>
#include <queue>
using namespace std;
int dy[4] = {-1,1,0,0};
int dx[4] = {0,0,-1,1};
vector<vector<bool> > visit;
int dist[100][100] = {0, };
int answer = 0;

int bfs(int y, int x, vector<vector<int> > maps){
     //행몇개 m
    int row_size = maps.size();
    //열몇개 n
    int col_size = maps[0].size();
    
    for(int j = 0; j<row_size; j++){
        for(int i = 0; i<col_size; i++){
            dist[j][i]=0;
        }
    }
    dist[0][0]=1;
    
    queue<pair<int,int>> q;
    q.push(make_pair(y,x));
    
    while(!q.empty()){
        int cur_y = q.front().first;
        int cur_x = q.front().second;
        q.pop();
        
        for(int i = 0; i<4; i++){
           int ny = cur_y + dy[i];
           int nx = cur_x + dx[i];
            
            if(ny>=0 && nx>=0 && ny < row_size && nx < col_size){
                if(maps[ny][nx]==1 && dist[ny][nx] == 0)
                {
                    dist[ny][nx] = dist[cur_y][cur_x] + 1;
                    q.push(make_pair(ny,nx));
                }
            }
        }
    }
    if(dist[row_size-1][col_size-1]==0)
        return -1;
    else
         return dist[row_size-1][col_size-1];
}
int solution(vector<vector<int> > maps)
{
    return bfs(0,0, maps);
}