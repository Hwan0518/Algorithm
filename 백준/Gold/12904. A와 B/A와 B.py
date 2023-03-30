'''
Condition
    - TL : 2s(4000만)
      ML : 512(128*100만)
    - 1<=S<=999, 2<T<=1000, S<T
    
Question
    - 다음의 두 연산만 가능하다
        1. 문자열의 마지막에 A를 추가
        2. 문자열을 뒤집고 마지막에 B를 추가
    - S를 T로 바꿀 수 있는지 확인하여라
      가능하면 1을, 불가능하면 0을 출력

Access
    - 예상 분류 : 문자열, 구현
      실제 분류 :

    try_1)
        - 1번연산을 하면 마지막에 A가, 2번연산은 B가 생기는것에 초점을 둔다 
        - 연산의 역과정을 거쳐서 T를 S로 바꿀 수 있는지 확인한다
B
BA 1
ABB 2
ABBA 1
'''
from sys import stdin
input = stdin.readline



#define function
def solution():
    current = t[:]
    def change(current:list):
        # 끝이 A라면 마지막 A만 pop시킨다
        if current[-1] == 'A':
            current.pop()
        # 끝이 B라면 pop시키고 뒤집는다
        else:
            current.pop()
            current.reverse()
        return current
    
    current = change(current)
    # current가 s보다 크거나 같을때만 1,2번 연산을 수행할 수 있다
    while len(current) >= len(s):
        word = ''.join(current)
        if word == s:
            return 1
        else:
            current = change(current)
    return 0


#main
s=input().strip()
t=list(input().strip())
print(solution())