'''
Condition
    - TL : 1s (2000만)
      ML :
    - 접시수n, 가짓수d, 연속k, 쿠폰c
    - 2<=n<=300만, 2<=d<=3000, 2<=k<=n<=3000, 1<=c<=d

Question
    - k개를 연속으로 먹음
    - 쿠폰에 해당하는 초밥 하나를 무료로 제공
    >>> 손님이 먹을 수 있는 초밥 가짓수의 최댓값은?

Access
    - 예상 분류 : 투포인터
      실제 분류 :

    try_1)
        -
'''
from sys import stdin
input = stdin.readline


#define function
def solution():
    maxCnt = 0
    eat = set()
    eat.add(c)
    eat.update(sushi[:k])
    # 1~k까지의 접시에, 각 종류가 몇개씩 있는지 미리 파악    
    typeOfSushi = [0] * (d+1)
    for i in range(k):
        typeOfSushi[sushi[i]] +=1
    
    for i in range(n):
        maxCnt = max(maxCnt, len(eat))
        if sushi[i] != c and typeOfSushi[sushi[i]] ==1:
            eat.remove(sushi[i])

        # i번째 초밥은 type개수에서 빼주고, i+k번째 초밥은 type 개수에 추가해야함
        typeOfSushi[sushi[i]] -=1
        typeOfSushi[sushi[i+k]] +=1
        eat.add(sushi[i+k])
    return maxCnt
        


#main
n,d,k,c = map(int,input().split())
sushi = [int(input()) for _ in range(n)]
sushi += sushi
print(solution())
