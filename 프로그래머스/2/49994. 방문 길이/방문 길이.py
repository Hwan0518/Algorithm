'''
for문으로 전체 탐색
visited 사용해서 처음 방문하는곳만 카운트 하면 됨
'''
def searching(dirs:list):
    visited = set()
    cnt = 0
    cr,cc = 0,0
    i=1
    for d in dirs:
        if d == "U":
            nr = cr+dr[0]
            nc = cc+dc[0]
        elif d == "D":
            nr = cr+dr[1]
            nc = cc+dc[1]
        elif d == "L":
            nr = cr+dr[2]
            nc = cc+dc[2]
        else:
            nr = cr+dr[3]
            nc = cc+dc[3]
        # 중복확인
        if not(-5<=nr<=5 and -5<=nc<=5):
            continue
        # 처음 가는 길이라면 cnt +1
        if (cr,cc,nr,nc) not in visited or (nr,nc,cr,cc) not in visited:
            visited.add((cr,cc,nr,nc))
            visited.add((nr,nc,cr,cc))
            cnt +=1
        cr,cc = nr,nc
    return cnt

def solution(dirs):
    global dr,dc
    #상하좌우
    dr = [-1,1,0,0]
    dc = [0,0,-1,1]
    answer = searching(dirs)
    return answer