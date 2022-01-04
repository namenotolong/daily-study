#include <stdlib.h>
#include <stdio.h>
#include <string.h>

typedef struct Node
{
    void* value;
}Node;

Node* buffer;

int curSize = 0;
int curMaxSize = 16;

void init()
{
    buffer = (Node*) calloc(curMaxSize, sizeof(Node));
    if (buffer == NULL)
    {
        perror("内存分配失败");
        exit(-1);
    }
}

void printNode()
{
    for (size_t i = 0; i < curSize; i++)
    {
        printf("node value:%s \n", buffer[i].value);
    }
}

void insert(Node* node)
{
    if (curSize == curMaxSize)
    {
        printf("%d容量不够 进行扩容\n", curSize);
        Node* newBuffer = (Node*)calloc((curMaxSize = curMaxSize << 1), sizeof(Node));
        if (newBuffer == NULL)
        {
            perror("内存分配失败");
            exit(-1);
        }
        memcpy(newBuffer, buffer, curSize * sizeof(Node));
        free(buffer);
        buffer = newBuffer;
    }
    buffer[curSize] = *node;
    ++curSize;
}

void removeItem(int index)
{
    if (index >= curSize)
    {
        printf("该索引%d不存在\n", index);
        return;
    }
    int perSize = sizeof(Node);
    Node* temp =(Node*) calloc(curMaxSize, perSize);
    memcpy(temp, buffer, index * perSize);
    memcpy(&temp[index], &buffer[index + 1], (curMaxSize - index - 1) * perSize);
    free(buffer);
    buffer = temp;
    --curSize;
}

int main()
{
    init();
    Node* a = (Node*) malloc(sizeof(Node));
    a->value = "first";
    insert(a);

    Node* b = (Node*) malloc(sizeof(Node));
    b->value = "second";
    insert(b);

    printNode();
    printf("start remove\n");
    removeItem(1);
    printNode();
    return 0;
}