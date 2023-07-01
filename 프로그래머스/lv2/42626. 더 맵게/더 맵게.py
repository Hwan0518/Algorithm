'''
- 모든 음식의 스코빌 지수를 k 이상
- 스코빌 지수 가장 낮은 두 음식으로 새로운 음식을 만듦
    newS = fist + (sec*2)
- 모든 음식이 k 이상 될때까지 새로만듦, 이때 최소 횟수를 return
- 2<=n<=100만
- 0<=k<=10억
- 불가능한 경우는 -1

>> 가장 작은 두 수를 뽑으면 되므로 힙큐를 사용해봄
    그냥 정렬 : O(nlogn)
    힙큐 : O(logN)

'''
from heapq import *
def solution(scoville, K):
    answer = 0
    heapify(scoville)
    
    # 음식이 2개 이상 남아있고, 가장 작은 수가 k 이상이 될때까지 반복
    while scoville[0] < K:
        minFst = heappop(scoville)  # 가장 맵지않은 스코빌 지수
        minSec = heappop(scoville)  # 두 번째로 맵지않은 스코빌 지수
        new = minFst + minSec*2     # 새로 만든 음식의 스코빌 지수
        heappush(scoville, new)
        answer +=1                  # 섞은 횟수 +1
        # 음식이 두 개 미만으로 남아있다면 더이상 섞을 수 없으므로 break
        if len(scoville)<2:
            break
    
    # 가장 작은 스코빌 지수가 k 이상이라면 answer를 리턴, 아니라면 -1을 리턴
    return answer if heappop(scoville) >= K else -1