'''
Condition
    - TL : 2s(약4000만)
      ML : 128(약 32*100만)
    - 2<=n<=10만
    - 1<i<n
    
    
Question
    - i번 스위치를 누르면, (i-1,i,i+1)의 상태가 바뀜
    - 1번 스위치 > 1,2번 전구
      n번 스위치 > n-1,n번 전구
    - 현재 상태에서 원하는 상태를 만들기 위해서
      최소 몇번 눌러야 되는가?
    
    
Access
    try_1)
        - 예상 분류 : dp or ad-hoc
        - 문제 분류 : 그리디
        
            1. curState를 1~n까지 탐색하며 wantState와 비교
            2. 두 상태를 비교하여 다음과 같이 진행
                2-1. 두 상태가 같다면 계속해서 탐색
                2-2. 두 상태가, i번째에서 달라진다면
                     i+1번 스위치를 누르고 다시 i+1부터 1번 진행
            3. n까지 탐색하면, n-1번째에서 두 상태를 비교
               같다면 횟수를 return, 다르다면 -1을 return
            >>> O(N)이라 될거같음
            >>> fail...
            
    try_2)
        - i(1~n-1)번째에서 (i-1,i,i+1)중 want와 다른 상태인 전구가 있다면
          무조건 i번째를 바꿔본다!
          
    try_3)
        - hint : 첫번째 전구를 켰을때, 껐을때 2가지 경우로 나누어야 한다
        
'''
from sys import stdin
input = stdin.readline

#define function
def switching(i):
    # i번째 전구 상태 변경
    curState[i] = (curState[i]+1)%2
    
    # i가 0보다 크다면, i-1번째 전구 상태 변경
    if i >0:
        curState[i-1] = (curState[i-1]+1)%2
    
    # i가 n-1보다 작다면 i+1번째 전구 상태 변경
    if i < n-1:
        curState[i+1] = (curState[i+1]+1)%2    
    return


def solution():
    global curState
    min_cnt = 1e9
    
    # 첫번째 전구의 상태로 경우의 수를 나눈다
    for zero in range(2):
        cnt=0
        curState = inputState[:]
        
        # zero == 0이면 그대로 진행, 1이면 첫번째 스위치를 누르고 진행한다
        if zero:
            cnt +=1
            switching(0)
        
        # i==1부터 n-1까지 전체 탐색
        for i in range(1,n):
            
            # i-1번째 전구의 상태를 확인해서, 다르다면 i번째 스위치를 누른다
            if curState[i-1] != wantState[i-1]:
                cnt +=1
                switching(i)

        # 두 상태가 같다면 min_cnt를 갱신
        if curState == wantState:
            min_cnt = min(min_cnt,cnt)
    
    # min_cnt가 존재한다면 return, 그렇지 않다면 -1을 return
    return min_cnt if min_cnt != 1e9 else -1
    
    
#main
n=int(input())
inputState = list(map(int,list(input().strip())))
wantState = list(map(int,list(input().strip())))

print(solution())