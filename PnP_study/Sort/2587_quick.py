# 백준 2587 평균값과 중앙값 출력


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

# 값 입력
arr = [int(input()) for _ in range(5)]
# 정렬
quick_sort(arr,0,4)
# 출력
print(sum(arr)//5)
print(arr[2])



