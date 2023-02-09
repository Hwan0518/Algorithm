'''
Condition
    - TL : 2s(4000만)
      ML : 128(32*10만)
    - 분류 : 우선순위 큐  
    - 1<=N<=10만
    - 묶음의 크기 <=1000
    
Question
    - 최소 비교 횟수는?

Access 
    try_1)
        - 항상 제일 작은 두 뭉치를 더해주면 된다. 힙큐를 사용해보자
'''
import heapq
n=int(input())
card = []
for _ in range(n):
    heapq.heappush(card, int(input()))
result=0

while len(card) >1:
    temp =0
    for _ in range(2):
        temp += heapq.heappop(card)
    result += temp
    heapq.heappush(card,temp)
print(result)