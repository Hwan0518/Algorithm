'''
Condition
    - TL : 0.5s (1000만)
      ML : 128 (32*100만)
    - 10<=N<10만, 0<S<=1억, 1<=각원소<=1만

Question
    - 수열에서 연속된 수들의 부분합 중, 그 합이 s이상이 되는 부분합의 최소길이를 구하여라
      s이상이 불가능하면 0을 출력
      
Access
    - 예상 분류 : 투포인터
      실제 분류 : 누적합, 투포인터

    try_1)
        - 수열의 전체합이 s미만이면 0출력
        - s이상을 만드는것과, s이상을 찾는것 두 가지로 구분
            1. 수열의 첫번째부터 계속 더하여 합이 s이상인 부분합을 찾는다
            2. 이후 포인터를 옮겨가며 그 수들을 +-해주고 s이상인지 확인한다
                2-1) s이상이라면 최소길이를 갱신
                2-2) s이하라면 포인터를 옮겨 2번을 다시 진행
        
'''
from sys import stdin
input = stdin.readline



#define function
def solution():
    summation = 0
    if sum(sequence) <s:
        return 0
    
    # 합이 s이상이 되도록 함
    stt = 0
    end = n-1
    for i in range(n):
        summation += sequence[i]
        if summation >=s:
            end = i
            break
    minSize = end-stt+1

    # s 이상인 부분합들을 찾음
    while end<=n-1:
        if summation >=s:
            minSize = min(minSize,end-stt+1)
            summation -= sequence[stt]
            stt +=1
        else:
            end +=1
            if end == n:
                break
            summation += sequence[end]
    return minSize



#main
n,s = map(int,input().split())
sequence = list(map(int,input().split()))
print(solution())

