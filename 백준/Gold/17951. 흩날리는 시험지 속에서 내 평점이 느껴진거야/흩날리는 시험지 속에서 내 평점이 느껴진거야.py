'''
Condition
    - TL : 1s (2000만)
      ML :
    - 1<=k<=n<=10만
        >>> O(NlogN)까지 가능

Question
    - n개의 수를 k개의 그룹으로 나누었을 때,
      k개 그룹의 합들 중 최솟값이 최대가 되도록 하여라

Access
    - 예상 분류 : dp
      실제 분류 : 이분탐색, 매개 변수 탐색

    try_1)
        - k개로 나누었을 때, 최소 점수가 최대 점수인가?
          == 최소 점수가 특정 값일 떄, k개의 그룹으로 나눌 수 있는가를 확인

        >>> 구현실패
    
    try_2)
        클론코딩 : https://recordofwonseok.tistory.com/425
        - l=0, r=20*100_000
        - mid = 각 그룹이 받을 수 있는 최고점수, 이 값이 넘어가면 그룹을 나눠야함
        
'''
from sys import stdin
input = stdin.readline



#define function
def check(standard):
    curScore, curGroup = 0,0
    # 그룹의 개수를 확인
    for score in answer:    
        if curScore + score >= standard:
            curScore = 0
            curGroup += 1
        else:
            curScore += score
    return curGroup


def binarySearch():
    maxScore = 0
    l=0
    r=20*(10**5)
    # 이분탐색 시작
    while l<=r:
        mid = (l+r)//2
        curGroup = check(mid)
        
        # 만들어진 그룹이 k개보다 작다면, 최솟값을 줄여야함
        if curGroup < k:
            r = mid-1
        # 만들어진 그룹이 k개보다 많다면, 최솟값을 증가시켜야함
        elif curGroup >k:
            l = mid+1
        # 정확히 k개라면 정답을 갱신하고 left를 증가
        else:
            maxScore = max(maxScore,mid)
            l = mid+1
    return maxScore
    
    
def solution():
    maxScore = binarySearch()
    return maxScore





#main
n,k = map(int,input().split())
answer = list(map(int,input().split()))
print(solution())