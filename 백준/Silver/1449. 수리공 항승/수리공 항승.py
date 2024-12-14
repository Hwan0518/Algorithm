'''
슬라이딩 윈도우 -> x
정렬 + 그리디
- 가장 첫 번째 지점부터 탐색하기 위해 물 세는 곳을 정렬
- 정렬된 누수지점을 순환하면서 탐색(visited 필요 x)
- 어차피 누수지점에 붙이면 좌우 0.5는 항상 포함되므로, 그냥 누수 지점만을 탐색해도 가능(0.5단위로 볼 필요가 없음)
'''
# 입력
def input_data():
    n,l = map(int,input().split())
    lst = list(map(int,input().split()))
    return n,l,lst

# main
def solution(n:int, l:int ,lst:list):
    lst = sorted(lst)
    cnt = 1         # 테이프 수
    stt = lst[0]    # 테이프 시작 지점
    # 탐색
    for loc in lst:
        # 누수 지점이 범위에 포함되는지 확인
        if loc in range(stt,stt+l):
            continue
        # 포함되지 않으면 stt를 누수지점으로 옮김
        else:
            stt = loc
            cnt+=1
    # 결과
    return cnt

print(solution(*input_data()))