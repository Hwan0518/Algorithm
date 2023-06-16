'''
Condition
    - TL : 1s(2000만)
      ML :
    - 3<=n<=1000, 2<=k<=30

Question
    - Hamming Distance = 두 개의 길이가 '같은' 문자열 사이의 거리
                       = 몇 개의 문자를 바꿔야 두 문자열이 같아지느냐를 나타냄
                       = 같은 위치에 있는 두 문자를 비교해서 다른 문자의 수를 세면 됨

    - 해밍 경로 : 모든 인접한 두 코드 사이의 해밍 거리가 1인 경로
    >>> 가장 짧은 해밍 경로를 구하시오

Access
    - 예상 분류 :
      실제 분류 : 그래프

    try_1)
        - bfs
            root를 정한 후, 해밍 거리가 1차이 나는 코드들을 자식 노드로 둔다
            이후에 존재하는지 확인해서 경로를 계산해준다
            >>> MLE, 128 * 4
   
    try_2)
        - reference : hanwg UUU
        - visited를 전역으로 다룸     
'''
from collections import deque
from sys import stdin
input = stdin.readline


#Define function
def getCode():
    dictionary = {}
    lst = []
    for i in range(1,n+1):
        code = list(input().rstrip())
        intCode = "".join(code)
        dictionary[intCode] = i
        lst.append(code)
    return dictionary, lst


def solution():
    minCost = 1e9
    minRoute = []
    sttCode = codeList[stt-1]
    dq = deque()
    route = [stt]
    visited = set()
    visited.add(tuple(sttCode))
    dq.append((sttCode, route))
    # 탐색 시작
    while dq:
        curCode, curRoute = dq.popleft()
        # curCode에서 해밍 거리가 1인 코드들을 확인
        for idx in range(k):
            nCode = curCode[:]
            nCode[idx] = str(1- int(nCode[idx]))
            int_nCode = "".join(nCode)
            # 이미 방문했거나, 주어지지 않은 코드라면 넘어감
            if tuple(nCode) in visited or int_nCode not in codeDict:
                continue
            
            # 해밍 거리가 1인데 end라면 최솟값을 계산하고 다음 코드로 넘어감
            if codeDict[int_nCode] == end and len(curRoute)+1 < minCost:
                minCost = len(curRoute)+1
                minRoute = curRoute + [end]
                break
            
            # 이외의 코드라면 (code,route,visited)를 dq에 추가
            visited.add(tuple(nCode))
            dq.append((nCode, curRoute+[codeDict[int_nCode]]))
       
    # 모든 탐색이 끝나다면 정답을 return
    if minCost==1e9:
        print(-1)
    else:
        print(*minRoute, sep=" ")




#Main
n,k = map(int,input().split())
codeDict, codeList = getCode()
stt,end = map(int,input().split())
solution()
