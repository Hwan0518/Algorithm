'''
Condition
    - TL : 2s(4000만)
      ML : 128(32*100만)
    - n<=50, 각 원소<=100만, s<=100만
    - 교환은 연속된 두 수만을 교체
    - 교환을 최대 s번 가능
    
Question
    - 정렬한 결과가 사전순으로 가장 뒷서는 것을 출력

Access
    - 예상 분류 : 정렬
      실제 분류 : 정렬, 그리디

    try_1)
        - 버블솔트와 같은 것 같으므로, 구현해본다
        >>> fail
        
    try_2)
        - hint : https://www.acmicpc.net/board/view/92397
        - s에서 여유가 있는만큼, 가장 큰 수를 찾아서 앞으로 가져오는 작업을 해야한다
    
'''
from sys import stdin
input = stdin.readline


#define function
def bubbleSort(s):
    bestSorted = sorted(sequence,reverse=True)
    cnt = 0    # 정렬이 안된 남은 수 중에서, 가장 큰 수가 와야하는 자리
    
    # 배열이 bestSorted와 같거나 s가 0이되면 종료
    while sequence!=bestSorted and s>0:
        # 그렇지 않다면 교환 가능한 범위 내에서 가장 큰 수를 앞으로 가져옴
        end = min(n,cnt+s+1)
        largest = max(sequence[cnt : end])
        largestIdx = sequence.index(largest)
        
        # largestIdx가 cnt와 같다면 이미 정렬된 상태이므로 cnt만 증가시키고 넘어감
        if largestIdx == cnt:
            cnt+=1
            continue
        
        # 교환 가능한 개수 내에서 가장 큰 수를 교환
        del sequence[largestIdx]
        sequence.insert(cnt,largest)
        
        # 교환 했으므로, 이동한만큼 s를 줄이고 cnt +=1
        s -= largestIdx-cnt
        cnt+=1
    return sequence


#main
n = int(input())
sequence = list(map(int,input().split()))
s = int(input())
print(*bubbleSort(s))
