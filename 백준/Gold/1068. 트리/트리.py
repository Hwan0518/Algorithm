'''
Condition
    - TL : 2s(4000만)
      ML :

Question
    - 주어진 트리에서 노드를 하나 지웠을 때, 남은 트리에서 리프 노드의 개수는?

Access
    - 예상 분류 : 
      실제 분류 : 트리

    try_1)
        - 트리를 모두 연결 > 이후 delTree 밑의 모든 연결을 끊는다
        
        
'''
from collections import deque
from sys import stdin
input = stdin.readline


#define function
class node:
    def __init__(self,item,parent) -> None:
        self.item = item
        self.parent = parent
        self.child = set()
        self.connect = True


def solution():
    treeData = []
    for i in range(n):
        treeData.append(node(i,data[i]))
    delTree = int(input())
    
    # 노드 연결
    for tree in treeData:
        item = tree.item
        parent = tree.parent
        # 부모노드에 자식 노드를 추가
        if parent != -1:
            treeData[parent].child.add(item)
    
    # 연결 끊기
    dq = deque()
    dq.append(delTree)
    while dq:
        byebye = dq.popleft()
        # byebye의 연결을 False
        treeData[byebye].connect = False
        # 부모의 자식에서 자신을 제거
        thisParent = treeData[byebye].parent
        if byebye in treeData[thisParent].child:
            treeData[thisParent].child.remove(byebye)
        # byebye의 자식들을 dq에 추가
        dq.extend(treeData[byebye].child)

    # 리프 노드를 확인
    cnt = 0
    for tree in treeData:
        thisConnect = tree.connect
        thisChild = tree.child
        # 연결되어있고, 자식이 없다면 cnt +=1
        if thisConnect and not thisChild:
            cnt+=1
    return cnt            
            


#main
n=int(input())
data = list(map(int,input().split()))
print(solution())


