# 퀵정렬을 구현해보기

def quick_sort(list,start,end):
    if start >= end:
        return
    pivot = start
    left = start + 1
    right = end
    temp = 0

    while(left <= right):
        while left <= end and list[left] <= list[pivot]:
            left += 1

        while list[right] >= list[pivot] and right > start:
            right -= 1
        if left > right:
            temp = list[right]
            list[right] = list[pivot]
            list[pivot] = temp
        else:
            temp = list[right]
            list[right] = list[left]
            list[left] = temp

    quick_sort(list,start,right - 1)
    quick_sort(list,right + 1,end)

A = [1,5,3,4,10,7,6,2,8]

quick_sort(A,0,8)
print(A)

