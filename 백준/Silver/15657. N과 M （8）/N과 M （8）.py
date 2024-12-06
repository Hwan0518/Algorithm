'''
Condition
- n개중 m개 고름
- 중복해서 뽑을 수 있음
- 오름차순

Answer
- 중복값 출력x

>>>> 백트래킹으로 풀자
'''

def dfs(cnt, seq:list, before, lst:list):
    global visited, answer
    # 조건 확인
    if cnt == m and tuple(seq) not in visited:
        answer.append(seq[:])
        visited.add(tuple(seq))
        return
    # 탐색
    for i in range(before, n):
        num = lst[i]
        seq.append(num)
        dfs(cnt+1, seq, i, lst)
        seq.pop()

def main():
    global n, m, visited, answer
    n, m = list(map(int,input().split()))
    lst = sorted(list(map(int,input().split())))
    visited = set()
    answer = []
    dfs(0, [], 0, lst)
    for a in answer:
        print(*a)

main()