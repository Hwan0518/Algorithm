'''
Condition
    - TL : 2s(4000만)
      ML :

Question
    - 

Access
    - 예상 분류 : dp,투포인터,이분탐색
      실제 분류 : 투포인터

    try_1)
        - 이진탐색을 사용해본다
        
    try_2)
        - 투포인터
    
'''
from sys import stdin
input = stdin.readline


#define function
def solution():
    maxHappy = 0
    left=right=0
    minPrice, Happy = gift[0][0],0
    
    # left = 0부터 차례대로 탐색
    while right <= n-1:
        curPrice, curHappy = gift[right][0], gift[right][1]
        diff = curPrice-minPrice
    
        # diff가 d보다 작다면 happy를 더하고 right증가
        if diff<d:
            Happy += curHappy
            right +=1
            maxHappy = max(maxHappy, Happy)
        
        # diff가 d이상이라면 다음 left로 넘어감
        else:
            Happy -= gift[left][1]
            left +=1
            minPrice = gift[left][0]        

        
    # 최대 만족도를 리턴
    return maxHappy

        


#main
n,d = map(int,input().split())
gift = [tuple(map(int,input().split())) for _ in range(n)]
gift.sort()
print(solution())