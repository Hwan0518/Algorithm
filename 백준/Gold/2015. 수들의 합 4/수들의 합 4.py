'''
Condition
    - TL : 2s(4000만)
      ML :
    - 1<=n<=20만, -20억<k<20억

Question
    - 

Access
    - 예상 분류 : 투포인터, 이분탐색
      실제 분류 : 누적합

    try_1)
        - 투포인터 : arr이 정렬된 상태가 아니므로 사용하기 힘듦
    
    try_2)
        - 누적합
        
    try_3)
        - 클론코딩 : https://velog.io/@7h13200/Python%EB%B0%B1%EC%A4%80-2015%EB%B2%88-%EC%88%98%EB%93%A4%EC%9D%98-%ED%95%A94
        
'''
from collections import defaultdict
from sys import stdin
input = stdin.readline



#define function
def solution():
    cnt = 0
    summation = 0
    dict[0] = 1
    for i in arr:
        summation += i
        # summation - 부분합 = k가 되어야지 cnt +1을 해준다
        # 따라서 (summation-k)값을 가지는 부분합이 이전에 만들어진적이 있다면, 그 개수만큼 cnt를 증가시킨다
        if (summation-k) in dict:
            cnt += dict[summation - k]
        dict[summation] += 1
    return cnt



#main
n,k = map(int,input().split())
arr = list(map(int,input().split()))
dict = defaultdict(int) # dict는 동일한 값을 가지는 부분합의 개수를 저장해놓는다
print(solution())