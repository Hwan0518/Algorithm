'''
Condition
    - TL : 2s(4000만)
      ML : 128(32*100만)
    - 1<=s<=100만
    - 1<=boom<=36

Question
    - 모든 폭발이 끝난 후 남는 문자열을 출력하여라
      남은 문자열이 없다면 'FRULA'를 출력하여라

Access
    - 예상 분류 : 구현, 스택
      실제 분류 : 문자열, 스택

    try_1)
        - boom의 첫 글자가 나올때마다 boom인지 check한다
        >>> fail
    
    try_2)
        - PPAP와 같은 문제
        - 스택으로 풀어본다

'''
from collections import deque
from sys import stdin
input = stdin.readline


#define function
def solution():
    answer = []
    size = len(boom)
    for s in inputString:
        temp = []
        answer.append(s)
        
        # answer가 size만큼 쌓였다면 검사진행
        if len(answer) >= size:
            for _ in range(size):
                temp.append(answer.pop())
            # 검사
            temp.reverse()
            if temp == boom:
                continue
            else:
                answer.extend(temp)
            
    return answer if answer else 'FRULA'

    



#main
inputString = deque(input().strip())
boom = list(input().strip())
print(*solution(),sep='')

