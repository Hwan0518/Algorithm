'''
Condition
    - TL: 2s
    - 

Goals
    - 

Approach
    - tree 순회 구현
'''
from sys import stdin
input = stdin.readline

n = int(input())
visited = set()
tree = dict()

# tree 생성
for _ in range(n):
    node, left, right = list(input().strip().split())
    if left != '.' or right != '.':
        tree[node] = {"l":None, "r":None}
        tree[node]["l"] = left
        tree[node]["r"] = right

# 방문
def visiting(cur:str, visit:str):
    if cur not in visited:
        visit += cur
        visited.add(cur)
    return visit

# 전위 순회: 뿌리를 먼저 방문 -> 그냥 dfs와 같음
def preorder(visit:str, cur:str):
    visit = visiting(cur, visit)
    if cur in tree:
        for c in [tree[cur]["l"], tree[cur]["r"]]:
            if c == ".":
                continue
            visit = preorder(visit, c)
    return visit

# 중위 순회: 왼쪽 자식 노드 -> root -> 오른쪽 자식 노드 순으로 방문
def inorder(visit:str, cur:str):
    if cur in tree:
        for c in [tree[cur]["l"], tree[cur]["r"]]:
            if c == ".":
                # 왼쪽이 없다면, root->오른쪽 자식 순으로 방문한다
                visit = visiting(cur, visit)
                continue
            # 왼쪽이 있다면, 왼쪽->root 순으로 방문한다
            visit = inorder(visit, c)
            visit = visiting(cur, visit)
    # leaf 노드 추가
    visit = visiting(cur, visit)
    return visit

# 후위 순회
def postorder(visit:str, cur:str):
    if cur in tree:
        for c in [tree[cur]["l"], tree[cur]["r"]]:
            if c == ".":
                continue
            # 왼쪽->오른쪽->root 순으로 방문한다
            visit = postorder(visit, c)
        visit = visiting(cur, visit)
    # leaf 노드 추가
    visit = visiting(cur, visit)
    return visit


traversal = [preorder, inorder, postorder]
for t in traversal:
    visited = set()
    print(t("", "A"))