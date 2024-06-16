'''
Condition
    - TL : 1s
    - 1~n으로 이루어진 순열
    - 모든 수는 10진수
    - 모두 공백으로 분리 but 공백 지워짐
    - 최소 1개 ~ 최대 50개의 수로 이루어져있음

Goals
    - 복구된 순열을 공백을 포함해서 출력하라

Approach
    - 백트래킹으로 모든 경우의 수 다 찾아야함..
    - 숫자는 최대 N<=50 밖에 안됨
'''

def dfs(i:int, origin:list):
    global answer
    # 종료 조건 -> 모든 수를 다 썼다면 종료
    if i == SIZE:
        print(*origin)
        exit() 
    # 한자리 숫자 추가
    num1 = int(kriii[i])
    if not visited[num1] and num1 != 0:
        visited[num1] = True
        origin.append(num1)
        dfs(i+1, origin)
        visited[num1] = False
        origin.pop()
    # 두자리 숫자 추가
    if i+1 < SIZE:
        num2 = int(kriii[i:i+2])
        if 10 <= num2 <= N and not visited[num2]:
            visited[num2] = True
            origin.append(num2)
            dfs(i+2, origin)
            visited[num2] = False
            origin.pop()

def main():
    global kriii, N, SIZE, visited, answer
    kriii = input().strip()
    # 123456789 = 9 = len(수열), 12345678910111213 = 9 + 8//2 = 9 + (len(수열)-9)//2
    N = len(kriii) if len(kriii) < 10 else 9 + (len(kriii)-9)//2
    SIZE = len(kriii)
    visited = [False] * (N+1)
    answer = []
    dfs(0, [])
    return answer

main()