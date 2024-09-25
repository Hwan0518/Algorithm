'''
- 들어온 순서대로 캐시 크기만큼 저장되어 있다고 생각하면 될 듯
- set을 사용하자
'''

def calc(cacheSize, cities, n):
    time = 0
    cacheSeq, cache = [], set()
    for i in range(n):
        c = cities[i].upper()
        # hit
        if c in cache:
            cacheSeq.remove(c)
            cacheSeq.append(c)
            time +=1
        # miss
        else:
            # 캐시가 다 찼으면 제일 처음 도시를 제거
            if len(cache) == cacheSize:
                cache.remove(cacheSeq[0])
                del cacheSeq[0]
            cache.add(c)
            cacheSeq.append(c)
            time +=5
    return time

def solution(cacheSize, cities):
    # 캐시를 사용하지 않거나, 캐시 크기 > 도시 숫자일 때
    n = len(cities)
    if not cacheSize or cacheSize >= n:
        return 5*n
    # 캐시를 사용할 때
    return calc(cacheSize, cities, n)