#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void swap(void* a, void* b, int size) {
    void* temp = malloc(sizeof(*a));
    memcpy(temp, a, size);
    memcpy(a, b, size);
    memcpy(b, temp, size);
    free(temp);
}

void sort(int* arr, int start, int end)
{
    if (start >= end)
    {
        return;
    }
    
    int template = arr[start];
    int k = start;
    int j = end;
    while (start < end)
    {
        while (arr[end] >= template && start < end)
        {
            --end;
        }
        while (arr[start] < template && start < end)
        {
            ++start;
        }
        swap(&arr[start], &arr[end], sizeof(int));
    }
    if (k < start)
    {
        sort(arr, k, start);
    }
    if (j > end)
    {
        //为什么要+1？ 因为我们比较的时候是把大于=template的放在右边，可能会出现end值最终与start初始值相同，造成死循环，我们在临界点取end+1
        sort(arr, end + 1, j);
    }
}

int getTime(int* piles, int k, int n)
{
    int sum = 0;
    for (size_t i = 0; i < n; i++)
    {
        int rest = piles[i] % k == 0 ? 0 : 1;
        sum += piles[i] / k + rest;
    }
    return sum;
}
//875
int minEatingSpeed(int* piles, int pilesSize, int h){
    int min = 1;
    int max = piles[0];
    for (size_t i = 0; i < pilesSize; i++)
    {
        if (piles[i] > max)
        {
            max = piles[i];
        }
    }
    while (min < max)
    {
        int mid = (max + min) / 2;
        int result = getTime(piles, mid, pilesSize);
        if (result <= h)
        {
            max = mid;
        } else {
            min = mid + 1;
        }
    }
    return min;
}
//878
int nthMagicalNumber(int n, int a, int b){

}

void testSort() {
    int arr[] = {312884470};
    int size = sizeof(arr) / sizeof(int);
    int value = minEatingSpeed(arr, size, 968709470);
    printf("result:%d\n", value);
}

int main() {
    testSort();
    return 0;
}