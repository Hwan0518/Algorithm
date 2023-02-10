'''
Condition
    - TL : 1s (2000만)
      ML : 512 (128*10만)
    - 1<=N<=26 1<=string(L)<=10만
    - 문자열은 소문자만
    - N개 종류 알파벳만 인식가능

Question
    - 인식할 수 있는 문자열의 최대 길이를 출력 
    
Access
    try_1)
        - 예상분류 : LIS/LCS인가 ? 
                   >>> X
                  그렇다면 N*L 이하인 방법은??
                   >>> 투포인터 (N*L)을 사용해본다!
        - 투포인터
            1. l,r = 0 에서 시작
            2. r을 +1씩 하면서 알파벳 종류를 파악
            3. 종류 개수 == n이 될때의 길이를 파악
            4. 이후에 다시 l+1을 해주고 r==l을 해줌
            5. l==L-1까지 1~4를 반복 
        >>> TLE
    
    try_2)
        - 투포인터에서, 연속된 부분들중 첫번째 부분으로 l을 옮겨다닌다
        >>> Fail
    
    try_3)
        - 탐색을 시작할 l을 문자가 바뀌는 부분으로 설정한다
'''
from sys import stdin
input = stdin.readline

#define function
def seaching_sequence():
    searching.append(0)
    for idx in range(1,L_str):
        cur_alpha = string[idx]
        before_alpha = string[idx-1]
        if cur_alpha != before_alpha:
            searching.append(idx)
    return
    
def solution():
    seaching_sequence()
    max_L = 1
    for l in searching:
        r = l+1
        kind = set()
        kind.add(string[l])
        current_L = 1
        while len(kind)<=n and r<L_str:
            kind.add(string[r])
            current_L +=1
            r+=1
        if len(kind)>n:
            current_L -=1
        max_L = max(max_L,current_L) 
        if max_L == L_str:
            break                   
    return max_L

#main
n=int(input())
string = input().strip()
L_str = len(string)
searching = []
print(solution())
