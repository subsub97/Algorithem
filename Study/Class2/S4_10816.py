# sort + binary search try1

n = int(input())

memo = [
    [0] * 20000002
]

n_list = list(map(int,input().split()))
for num in n_list:
    if num == 0:
        memo[0][0] += 1
    else:
        memo[0][num + 10000001] += 1

m= int(input())
m_list =list(map(int,input().split()))
for m_num in m_list:
    if m_num == 0:
        print(memo[0][0],end=' ')
    else:
        print(memo[0][m_num+10000001],end=' ')
