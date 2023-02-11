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
        >>> SUCCESS 4624ms

    try_2)
        - 카드뭉치를 입력 받을때마다 heappush를 하지 않고,
          다 입력받은 후 한번에 heapify를 사용한다
                
** 단순히 sort 시킨후 차례대로 더한다면, 언젠가는 앞의 카드뭉치가 뒤의 카드뭉치보다 커지게 된다
따라서 항상 작은 두 뭉치를 골라내기 위해서 heapq를 사용한다
'''
import heapq

#define function
def min_compare():
    # 1. heapify를 사용해 힙정렬
    heapq.heapify(card)
    result = 0
    # 2. 카드뭉치가 하나가 될때까지 비교
    while len(card) >1:
        temp =0
        # 가장 작은 카드뭉치 두 개를 비교한다
        for _ in range(2):
            temp += heapq.heappop(card)
        result += temp
        # 비교해서 더한 카드뭉치를 다시 힙push한다
        heapq.heappush(card,temp)
    return result

#main    
n=int(input())
card=[int(input()) for _ in range(n)]
print(min_compare())
