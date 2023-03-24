'''
Condition
    - TL : 1s(2000만)
      ML : 256(64*100만)
    - 1<=N,d<=1000, 1<=w<=100

Question
    - 얻을 수 있는 점수의 최댓값은?

Access
    - 예상 분류 : 그리디, 힙큐
      실제 분류 : 그리디, 정렬, 우선순위 큐

    try_1)
        - 각 일자별로 해결할 수 있는 과제들을 구한다
        - 점수별로 pop한다
        >>> fail. due,score가 같은 과제들이 여럿 존재할 수 있다
        
    try_2)
        - try1을 힙큐로 구현해본다
'''
from heapq import *
from sys import stdin
input = stdin.readline



#define function
def solution():
    homework = []
    # 데이터입력 : heapq에서 가장 큰 점수부터 비교하기 위해 -score를 넣음
    maxDue = 0
    for _ in range(n):
        due, score = map(int,input().split())
        heappush(homework,(-score,due))
        maxDue = max(maxDue,due)
        
    # n일동안 반복
    result = 0
    for i in range(maxDue,0,-1):
        # 모든 과제를 끝냈다면 break
        if not homework:
            break
        
        curScore,curDue = heappop(homework)
        temp=[]
        # curDeu가 i보다 큰 경우가 나올때까지 반복한다
        while curDue <i and homework:
            temp.append((curScore,curDue))
            curScore,curDue = heappop(homework)
        
        # curDue가 i 이상 남은 경우에만 result를 갱신
        if curDue>=i:
            result += -curScore
        # curDue가 i 이상인 경우가 없었다면, 마지막에 빼낸 cur값을 다시 넣어줌
        else:
            temp.append((curScore,curDue))    
        # homework에 temp를 넣어주고 heapify한다
        homework += temp
        heapify(homework)
        
    return result



#main
n=int(input())
print(solution())



