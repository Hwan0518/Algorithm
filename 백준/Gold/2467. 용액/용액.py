'''
Condition
    - TL : 1s(2000만)
      ML : 128(32*100만)
    - 1<=산성<=10억, -10억<=알칼리<=-1
    - 2<=n<=10만

Question
    - 두 개의 서로 다른 용액을 혼합하여 특성값이 0에 가깝게 만들떄,
      두 용액은 무엇인가?

Access
    - 예상 분류 : 이분탐색, 투포인터?
      실제 분류 : 이분탐색, 투포인터

    try_1)
        - 정렬되어 있으므로 이분탐색을 사용해서,
          current일때 -current에 가장 가까운것을 찾아낸다
          
'''
from sys import stdin
input = stdin.readline




# define function
def binarySearch(i):
    fstSolution = solution[i]
    low = 0
    high = n-1
    mid = 0
    
    # 최솟값 초기 설정
    minMid = -1
    minMixed = 1e12
    
    # mixed가 가장 작은 조합을 이분탐색으로 찾아냄
    while low<=high:
        mid = (high+low)//2
        midValue = solution[mid]
        mixed = midValue+fstSolution
        # mid가 i와 같다면 갱신하고 다시 진행
        if mid == i:
            high = mid-1
            continue
        
        # 현재 min값보다 작다면 갱신
        if abs(mixed) < abs(minMixed):
            minMixed = mixed
            minMid = mid
        # mixed의 결과에 따라서 high와 row를 갱신
        if mixed >0:
            high = mid-1
        elif mixed <0:
            low = mid+1
        else:
            return minMixed,solution[minMid]
    return minMixed,solution[minMid]


def doBinary():
    minMixed = 1e12
    result = [0,0]
    # 전체 용액을 탐색한다
    for i in range(n):
        fstSolution = solution[i]
        mixed,scdSolution = binarySearch(i)
        # 혼합용액이 최소용액보다 작다면 결과를 갱신
        if abs(mixed) < abs(minMixed):
            result = [fstSolution,scdSolution]     
            minMixed = mixed
    
    result.sort()
    return result





# main
n=int(input())
solution = list(map(int,input().split()))
print(*doBinary())

