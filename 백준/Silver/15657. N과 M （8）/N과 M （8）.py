'''
gpt 이용 최적화
- before로 시작 인덱스를 제어하고 있으므로, visited로 중복을 제어할 필요가 없음
- 결과를 리스트에 저장하지 않고 바로 출력해도 되므로 answer가 필요가 없음
- 저장하지 않아도 되므로 seq를 복사하여 저장할 필요가 없음
'''
def dfs(cnt, seq, before, lst):
    # 조건 확인
    if cnt == m:
        print(*seq)
        return
    
    # 탐색
    for i in range(before, n):
        seq.append(lst[i])
        dfs(cnt + 1, seq, i, lst)  # 중복 허용, i를 그대로 사용
        seq.pop()

def main():
    global n, m
    n, m = map(int, input().split())
    lst = sorted(map(int, input().split()))  # 입력값 정렬
    dfs(0, [], 0, lst)

main()