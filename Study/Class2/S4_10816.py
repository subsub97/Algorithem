# sort + binary search try2
# 메모리 사용을 줄여보자

n = int(input())
n_list = list(map(int,input().split()))
m = int(input())
m_list = list(map(int,input().split()))

# 이분 탐색이 가능하게 정렬을 해준다.
n_list.sort()

for m_num in m_list:
    start = 0
    end = n - 1
    mid = n // 2
    cnt = 0
    flag =0
    while start <= end:
        if n_list[mid] == m_num:
            while n_list[mid] == m_num:
                if flag == 1:
                    break
                if mid - 1 > 0 and n_list[mid - 1] == m_num:
                    mid -= 1
                else:
                    while n_list[mid] == m_num:
                        cnt += 1
                        if mid + 1 < n:
                            mid += 1
                        else:
                            flag = 1
                            break
            break

        if n_list[mid] < m_num:
            start = mid + 1
            mid = (start + end) // 2
        else:
            end = mid - 1
            mid = (start + end) // 2
    print(cnt, end=' ')
