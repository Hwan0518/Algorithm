'''
0~n-1 까지를 고유하게 가짐
p를 a에 적용(길이n) -> b가 된다(길이n): B[p[i]] = a[i]
P를 출력

조건
- B가 오름차순(+ 사전순으로 앞서게)이 되어야한다

그냥 구현해보자
1. A_Map(V,Idx 로 구성) = (2:0) (1:1) (3:2) (1:3)
2. B_Map(V,a_idx 로 구성) = (1,1) (1,3) (2,0) (3,2)
3. B_idx:A_idx = (0:1) (1:3) (2:0) (3:2) 
4. A_idx = P_idx와 동일,
    따라서 P[a_idx] = B_idx[a_idx] -> P = B_idx[0], B_idx[1], B_idx[2], B_idx[3] = 2 0 3 1
'''
n = int(input())
a = list(map(int,input().split()))
a_map = [(a[i]   ,i) for i in range(n)]
# 오름차순이 되는 B
b = sorted(a_map)
p = [0]*n
for b_idx in range(n):                          
    v,i = b[b_idx]
    p[i] = b_idx
print(*p)