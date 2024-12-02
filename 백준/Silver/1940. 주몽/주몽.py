'''
갑옷: 두 재료 번호 합쳐서 M이 되면 완성
즉, 주어진 수열에서 두 번호를 뽑아서 M을 만들 수 있는 쌍의 최대 개수를 출력

완탐
-> nC2 = 15000C2 = 14999 * 7500 >1억 -> TLE

투포인터로 해보자
'''

n = int(input())
m = int(input())
lst = list(map(int,input().split()))
lst.sort()
cnt = 0
end = n-1
for stt in range(end):
    v = lst[stt] + lst[end]
    while v != m and stt<end:
        # v>m이라면, end를 감소시키고 계속
        if v > m:
            end -=1
        # v<m이라면, stt를 증가시켜야 하므로 break
        elif v < m:
            break
        v = lst[stt] + lst[end]
    # stt!=end지만 v!=m인 경우는 cnt증가하지 않음
    if v == m and stt != end:
        cnt +=1
print(cnt)