'''
Condition
    - TL : 2s(4000만)
      ML : 512(128*100만)
    - 3<=n<=15, 0<=최소 영양성분, 식재료 영양성분<=500
    - 식재료 번호는 1부터 시작

    
Question
    - 영양소 기준을 만족시키는 조합의 최소 비용을 구하여라
    - 첫째줄에 최소비용, 둘째줄에 식재료 번호를 오름차순으로
    - 동일한 비용의 식재료 조합이 존재한다면, 사전순으로 빠른것을 출력
    - 만족하는 조합이 없다면 -1을 출력
    
    
Access
    try_1)
        - 분류 : 브루트포스, 백트래킹
            >>> n**m(선택개수), m은 6이하여야함
            
            - answer = [(cost,식재료1,식재료2, ...), (cost, 식재료1, 식재료2, ...)]
            - comb = (cost,식재료1,식재료2,...)
            - curNutrient = [단,지,탄,비] >> food[i]의 구조와 동일
            - visited 사용해서 방문 처리
              visited = set()

            1. curNutrient에 food[0]부터 대입
            2. food를 하나씩 대입할때마다 check해보기
            3. 백트래킹을 위해 이전상태로 되돌리기

'''
import sys
sys.setrecursionlimit(10**8)
input = sys.stdin.readline




#define function
def check(curNutrient):
    # 현재까지 모든 영앙소가 기준치를 넘겼다면 True를, 하나라도 넘지 못했다면 False를 반환
    for i in range(4):
        if curNutrient[i] < minNutrient[i]:
            return False
    return True



def back(curNutrient, comb, idx):
    global answer, ansFood
    # 1. 만약 answer보다 값이 커졌다면, 선택할 필요가 없으므로 선택을 취소
    if comb[0] > answer:
        return
    
    # 2. answer보다 작다면 check를 통해, 최소 기준을 넘었는지 확인 -> 넘었다면 answer 갱신
    if check(curNutrient):
        # 현재 cost가 answer보다 작은경우는 곧바로 갱신
        if comb[0] < answer:
            answer = comb[0]
            ansFood = comb[1:]
            
        # 현재 cost == answer인 경우는 선택한 재료를 사전순으로 갱신
        else:
            ansFood = min(ansFood,comb[1:])
        return 
    
    # 3. idx == n 이라면 재료 끝에 도달했으므로 return
    if idx == n:
        return
            
    # 4. 재료의 끝이 아니고, check를 통과하지 못했다면 다음 재료 선택 : comb[0] = 비용, comb[1:] = 선택한 재료
    comb[0] += food[idx][-1]
    comb.append(idx+1)
    for j in range(4):
        curNutrient[j] += food[idx][j]
    
    # 다시 check를 위해 재귀호출       
    back(curNutrient, comb, idx+1)

    # 이전 상태로 복구
    comb.pop()
    comb[0] -= food[idx][-1]
    for j in range(4):
        curNutrient[j] -= food[idx][j]
    back(curNutrient, comb, idx+1)





#main
n=int(input())
minNutrient = tuple(map(int,input().split()))
food = [tuple(map(int,input().split())) for _ in range(n)]

answer= 1e9             #최소cost
ansFood = [1e9]         #최소 cost일때 food들의 idx
back([0,0,0,0],[0], 0)  # curNutrient(현재 선택한 영양소정보), comb(현재 선택한 재료들의 cost와 idx), idx = 현재 선택한 재료

if answer != 1e9:
    print(answer)
    print(*ansFood)    
else:
    print(-1)