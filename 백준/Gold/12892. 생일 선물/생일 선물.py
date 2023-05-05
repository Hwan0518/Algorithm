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
    
    try_3)
        - hint : https://chance0523.github.io/algorithm/2020/09/18/algorithm-%EC%83%9D%EC%9D%BC%EC%84%A0%EB%AC%BC/
'''
import sys
input = sys.stdin.readline



#define function
def solution():
    left = 0
    maxHappy = nList[0][1]
    sumHappy = maxHappy

    # 전체탐색
    for right in range(1, n):
        curPrice, curHappy = nList[right][0], nList[right][1]
        sumHappy += curHappy

        # 차이가 d이상이라면, 제일 처음 값을 빼준다
        while curPrice-(minPrice:=nList[left][0]) >= d:
            mHappy = nList[left][1]
            sumHappy -= mHappy
            left += 1

        # 최댓값 갱신
        maxHappy = max(maxHappy, sumHappy)
    return maxHappy




#main
n, d = map(int, input().split())
nList = [list(map(int, input().split())) for _ in range(n)]
nList.sort()
print(solution())