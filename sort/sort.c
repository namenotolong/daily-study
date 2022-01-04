#include <stdio.h>
#include <string.h>
#include <stdlib.h>

void swap(void* s, void* t, int size)
{
    void* temp = calloc(1, size);
    memcpy(temp, s, size);
    memcpy(s, t, size);
    memcpy(t, temp, size);
    free(temp);
}
void printArr(int* arr, int size)
{
    for (size_t i = 0; i < size; i++)
    {
        printf("%d ", arr[i]);
    }
    printf("\n");
}

void quickSort(int* arr, int start, int end)
{
    if (start >= end)
    {
        return;
    }
    
    int temp = arr[start];
    int i = start;
    int j = end;
    while (i < j)
    {
        //必须这个在前  不然会出现值比temp大而不能移动 因为i==j
        while (i < j && arr[j] >= temp)
        {
            --j;
        }
        while (i < j && arr[i] < temp)
        {
            ++i;
        }
        swap(&arr[i], &arr[j], sizeof(int));
    }
    if (i > start)
    {
        quickSort(arr, start, i);
    }
    if (j < end)
    {
        quickSort(arr, j + 1, end);
    }
}

void downFilter(int* arr, int start, int end)
{
    while (start < end)
    {
        int i = start * 2 + 1;
        if (i > end)
        {
            break;
        }
        if (i + 1 <= end && arr[i + 1] > arr[i])
        {
            ++i;
        }
        if (arr[i] > arr[start])
        {
            swap(&arr[i], &arr[start], sizeof(int));
        }
        start = i;
    }
    
}
void heapSort(int* arr, int start, int end)
{
    int mid = (end - start) / 2;
    for (int i = mid; i >= 0; i--)
    {
        downFilter(arr, i, end);
    }
    for (int i = end; i > 0; )
    {
        swap(&arr[start], &arr[i], sizeof(int));
        i--;
        downFilter(arr, start, i);
    }
}

void mergeSort(int* arr, int start, int end)
{
    if (start == end)
    {
        return;
    }
    int mid = (start + end) / 2;
    mergeSort(arr, start, mid);
    mergeSort(arr, mid + 1, end);
    int leftSize = mid - start;
    int rightSize = end - mid + 1;
    int* result = (int*) calloc(end - start + 1, sizeof(int));
    int leftCurIndex = 0;
    int rightCurIndex = 0;
    int curIndex = 0;
    while (leftCurIndex < leftSize && rightCurIndex < rightSize)
    {
        int value;
        if (arr[leftCurIndex] < arr[rightCurIndex])
        {
            value = arr[leftCurIndex++];
        } else {
            value = arr[rightCurIndex++];
        }
        result[curIndex++] = value;
    }
    if (leftCurIndex < leftSize)
    {
        memcpy(&result[curIndex], &arr[leftCurIndex], (leftSize - leftCurIndex) * sizeof(int));
    }
    else
    {
        memcpy(&result[curIndex], &arr[rightCurIndex], (rightSize - rightCurIndex) * sizeof(int));
    }
    memcpy(&arr[start], result, (end - start + 1) * sizeof(int));
    free(result);
}


void insertSort(int* arr, int start, int end)
{
    for (size_t i = start + 1; i < end; i++) {
        int temp = arr[i];
        int j = i;
        while (j > start && arr[j - 1] > temp)
        {
            arr[j] = arr[j - 1];
            --j;
        }
        arr[j] = temp;
    }
}
int main(int argc,char *argv[])
{
    char* value = argv[argc - 1];
    int* arr = (int*) calloc(100, sizeof(int));
    int index = 0;
    if (argv != NULL && value != NULL)
    {
        char* pch = strtok(value, ",");
        printf("%s \n", pch);
        pch = strtok(NULL,",");
        while (pch != NULL)
        {
            arr[index++] = atoi(pch);
            pch = strtok(NULL,",");
        }
    }
    //quickSort(arr, 0, size - 1);
    //heapSort(arr, 0, size - 1);
    //mergeSort(arr, 0, size - 1);
    insertSort(arr, 0, index);
    printArr(arr, index);
    return 0;
}