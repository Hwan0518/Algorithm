'''
- TL : 1s
- 1<=N<=30
- 기호 : (, ), [, ] 만을 사용
- 올바른 괄호열 조건
    - (), [] 는 올바르다
    - X가 올바르다면, (X), [X]도 올바르다
    - X와 Y가 올바르다면, XY도 올바르다
    - ()=2, []=3, (X)=2*X, [X]=3*X, (XY)=X+Y
    - 올바르지 않으면 0
- 괄호 값을 출력하라

>>> 스택을 사용하면 될 것 같음

1. 스택
    - 연산순서 리스트를 만든다 : calc_order_list =[]
    - 스택을 만든다 : stack =[]
    - 스택안에 괄호를 하나씩 쌓으면서 다음처럼 처리한다
        - stack이 비어있다면 그대로 쌓는다
        - 그렇지 않다면,
            - 이전 스택과 합쳤을 때 ()라면, stack을 pop하고, 
                    - 연산순서 리스트의 마지막값이 숫자라면, 'plus'와 2를 추가한다
                    - 연산순서 리스트의 마지막값이 숫자가 아니라면, 2를 추가한다
                - ((라면, 연산순서 리스트에 2와 'multiply'를 추가한다
                - ([라면, 스택에 [를 쌓고, 연산순서 리스트에 2와 'multiply'를 추가한다
            
            - 이전 스택과 합쳤을 때 []라면, stack을 pop하고, 연산순서 리스트에 3을 추가한다
                    - 연산순서 리스트의 마지막값이 숫자라면, 'plus'와 3을 추가한다
                    - 연산순서 리스트의 마지막값이 숫자가 아니라면, 3을 추가한다
                - [[라면, 연산순서 리스트에 3과 'multiply'를 추가한다
                - [(라면, 스택에 (를 쌓고, 연산순서 리스트에 3과 'multiply'를 추가한다
            - 이전 스택과 합쳤을 때 (]이거나 [)라면 0을 return하고 종료한다     
            
2. 스택 + depth를 기억
    - depth가 같으면 +, depth가 다르면 *로 처리
    
3. 계산이 끝난 경우(덧셈하는경우)와 계속하는 경우(곱하는경우)로 나누어서 해결 -> 덧셈은 무조건 나누고, 곱셈을 계속하는 경우로 본다
   ** 또한 문자열 i번째의 바로 직전값으로 result에 더할지 말지 여부를 정하므로, stack이 아닌 string으로 접근해야한다
    
    -> 다음처럼 바꿔서 계산을 한다 생각하면 됨
    원래 계산 : (2+3*3)*2 + (2*3)
    바꾼 계산 : (2*2)+(2*3*3)+(2*3)
    
    구현
    - stack : 들어오는 괄호들이 쌓이는 스택, 빈 리스트로 시작
    - cur_calc : 현재 계산된 변수값, 1로 시작
    - result : 전체 결과값, 0으로 시작
    - 방법
        1. (,[의 경우 -> 여기서 실제 계산이 이루어진다
            : 2 or 3을 cur_calc에 곱해주고, stack.append 
        2. ),]의 경우 -> 계산을 결과에 +해줄지의 여부를 결정한다
            : stack이 비어있거나, 올바르지 않는 조합(], [)라면 예외처리
            올바른 경우라면, 
                - 앞의 괄호가 닫힌 괄호라면 result에  +해준다
                - 앞의 괄호가 열린 괄호라면, cur_calc에 2 or 3을 곱해준다
            마지막으로 2 or 3을 cur_calc에 나눠주고 stack.pop
        3. stack이 남아있다면 올바르지 못한 경우이므로 0을 return

'''
from sys import stdin
input = stdin.readline

def solution(string):
    result = 0
    cur_calc = 1
    stack = []
    # stack쌓기
    for i in range(len(string)):
        s = string[i]
        # (나 [인 경우
        if s == '(': 
            cur_calc *= 2
            stack.append(s)
        elif s == '[':
            cur_calc *= 3
            stack.append(s)
        # ),]의 경우
        elif s == ')':
            # stack이 비어있거나, 짝이 맞지 않은 경우
            if not stack or stack[-1] != '(': 
                return 0
            # 올바른 경우
            if string[i-1] == '(':
                result += cur_calc
            cur_calc //= 2
            stack.pop()
        else:
            # stack이 비어있거나, 짝이 맞지 않은 경우
            if not stack or stack[-1] != '[': 
                return 0
            # 올바른 경우
            if string[i-1] == '[':
                result += cur_calc
            cur_calc //= 3
            stack.pop()
            
    return result if not stack else 0        

    
print(solution(input().strip()))

