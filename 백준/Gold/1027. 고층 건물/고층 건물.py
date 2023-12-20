'''
TL : 2s (약 4000만)
ML : 128mb (약 32*10만)
N <= 50
H <= 1000,000,000

Q) 보이는 빌딩 수의 max value는?

Access)
    - a,b,f(a),f(b)를 지나는 선분은 다음과 같다
        : y = [{f(a)-f(b)}/(a-b)]*x + [f(a)-{a*(f(a)-f(b))}/(a-b)] 
        >>> a대신 i, b대신 j를 대입하면 된다(i=기준빌딩,j=다른빌딩)
    - 좌우로 탐색한다
    - 왼쪽으로 탐색
        : i,j을 지나는 선분에 대해 i와j사이의 빌딩을 k라 한다면,
        선분이 (k,b[k])를 지나지 않는다면 count 해준다
    
    - 오른쪽으로 탐색
        : 왼쪽 탐색에서 방향만 반대로 바꿔준다
'''
from sys import stdin
input = stdin.readline

#define function
def segment(s,c):
    gradient = (building[s]-building[c])/(s-c)
    intercept = building[s]-(s*(building[s]-building[c]))/(s-c)
    return gradient, intercept

def search_left(standard):
    if standard ==0:
        return 0
    cnt=0
    for current in range(standard-1,-1,-1):
        count=True
        gradient, intercept = segment(standard,current)
        for compare in range(standard-1,current,-1):
            value = gradient*compare +intercept
            if building[compare] >= value:
                count=False
                break
        if count: cnt +=1
    return cnt

def search_right(standard):
    if standard == n-1:
        return 0
    cnt=0
    for current in range(standard+1,n):
        count=True
        gradient, intercept = segment(standard,current)
        for compare in range(standard+1,current,1):
            value = gradient*compare +intercept
            if building[compare] >= value:
                count=False
                break
        if count: cnt+=1
    return cnt

def solution():
    max_cnt = 0
    for standard in range(n):
        l_cnt = search_left(standard)
        r_cnt = search_right(standard)
        cnt = l_cnt + r_cnt
        max_cnt = max(max_cnt,cnt)
    return max_cnt

#main()
n=int(input())
building = list(map(int,input().split()))
print(solution())

