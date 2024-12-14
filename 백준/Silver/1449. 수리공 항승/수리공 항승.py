'''
슬라이딩 윈도우로 한번 탐색 때리면 될듯!
- 가장 첫 번째 지점부터 탐색하기 위해 물 세는 곳을 정렬
- end = stt + l, (lst[-1]+0.5 in visited == True) 가 될 때까지 탐색
- 방문할 곳이 연속적이라는 보장이 없으므로 visited는 set으로 처리
- (다음 누수지점-0.5, 다음 누수지점, 다음 누수지점+0.5)를 분기처리해서 탐색
'''
# 입력
def input_data():
    n,l = map(int,input().split())
    lst = list(map(int,input().split()))
    return n,l,lst

# main
def solution(n:int, l:int ,lst:list):
    lst = sorted(lst)
    cnt = 1     # 테이프 수
    idx = 0     # 누수 지점 인덱스
    stt = lst[0]-0.5 if lst[0]>=0.5 else 0  # 테이프 시작 지점
    end = stt+l # 테이프 종료 지점
    visited = set() # 방문
    # 탐색
    while (lst[-1]+0.5) not in visited:
        cur = lst[idx]
        # 테이프 범위에 누수 지점이 있는 경우
        if cur+0.5 <= end:
            idx +=1
            visited.add(cur+0.5)
        # 테이프 범위보다 누수 지점 시작값이 큰 경우
        elif cur-0.5 > end:
            stt = cur-0.5
            cnt +=1
        # 테이프 범위보다 누수 지점 중간값이 큰 경우
        elif cur > end:
            stt = cur
            cnt +=1
        # 테이프 범위보다 누수 지점 끝값이 큰 경우
        else:
            stt = cur+0.5
            cnt +=1
        # end 업데이트
        end = stt+l
    return cnt

print(solution(*input_data()))