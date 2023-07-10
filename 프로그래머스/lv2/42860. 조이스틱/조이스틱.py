'''
Approach
    - 조이스틱을 조작해서 name을 완성할 때, 조작 횟수의 최솟값을 return
    - up : 다음 알파벳
      down : 이전 알파벳
      left : 입력된 문자의 왼쪽으로 이동
      right : 입력된 문자의 오른쪽으로 커서 이동
    - 1<=n<=20

>>> 백트래킹
    : O(N!)이므로 TLE, 최적이 아님

>>> 그리디 ... 어떻게?
    1. 커서이동
        : 첫 문자에서 A가 아닌 마지막 문자가 더 가까운 방향으로 이동한다
          (왼쪽, 오른쪽 선택)
    2. 알파벳 선택
        : A에서 해당 알파벳까지 더 가까운 방향으로 선택한다
        
>>> 그냥 bfs로 완탐돌림
'''
from string import ascii_uppercase
alphabet = ascii_uppercase
L_alpha = len(alphabet)


def change(s):
    # A가 아닌 다른 알파벳이라면 바꿔줘야한다
    index = alphabet.index(s)
    move = min(index, L_alpha-index)
    return move
    
def bfs(idx, cnt, cur, name):
    global candidate
    if "".join(cur) == name:
        candidate.append(cnt)
        return
    
    # 오른쪽 이동
    r = 0
    while cur[idx+r] == name[idx+r]:
        r +=1
    s = cur[idx+r]
    target = name[idx+r]
    cur[idx+r] = target
    bfs(idx+r, cnt+r+change(target), cur, name)
    cur[idx+r] = s
    
    # 왼쪽 이동
    l = idx
    while cur[idx-l] == name[idx-l]:
        l +=1
    s = cur[idx-l]
    target = name[idx-l]
    cur[idx-l] = target
    bfs(idx-l, cnt+l+change(target), cur, name)        
    cur[idx-l] = s
    
    
def solution(name):
    global candidate
    candidate = []
    cur = ["A"]*len(name)
    idx = 0
    bfs(idx, 0, cur, name)
    answer = min(candidate)
    return answer


print(solution("AAAA"))
print(solution("ZZZZ"))
print(solution("NM"))
print(solution("JAN"))
print(solution("JEROEN"))
print(solution("JAZ"))






