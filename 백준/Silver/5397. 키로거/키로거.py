'''
Condition
    - TL : 1s
    - 1<=L<=100만
    - command
        - 백스페이스 : '-', 바로 앞에 글자가 있다면 지움
        - 화살표 : '<', '>', 커서를 움직일 수 있다면 움직임
        - 나머지 : 알파벳 대문자, 소문자, 숫자

Question
    - 강산이의 비밀번호를 출력하여라

Approach
    - 분류 : 스택
    - command를 모두 실행 후, 스택에 남아있는 문자를 출력한다

try_1) 스택 

'''
from collections import deque
from sys import stdin
input = stdin.readline

def execute_command(command:deque):
    stack = []
    tempStack = deque()
    while command:
        c = command.popleft()
        # 백스페이스인 경우
        if c == '-':
            # 앞에 글자가 있는 경우만 지움
            if stack:
                stack.pop()
        # 왼쪽 화살표인 경우
        elif c == '<':
            # 맨 앞이 아니라면 왼쪽으로 이동
            if stack:
                # tempStack에는 현재 커서 뒤의 문자들이 들어간다
                tempStack.appendleft(stack.pop())
        # 오른쪽 화살표인 경우
        elif c == '>':
            # 맨 뒤가 아니라면 오른쪽으로 이동
            if tempStack:
                # stack에는 현재 커서 앞의 문자들이 들어간다
                stack.append(tempStack.popleft())
        # 문자인 경우
        else:
            stack.append(c)
    return ''.join(stack + list(tempStack))

def solution():
    n = int(input())
    for _ in range(n):
        command = deque(input().strip())
        print(execute_command(command))
    return

solution()