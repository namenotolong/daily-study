#include <stdio.h>
#include <stdlib.h>

typedef struct Node
{
    char* value;
    int id;
    struct Node* next;
}Node;

Node* HEAD = NULL;
Node* TAIL = NULL;

Node* init() {
    Node* itemHead = (Node*) malloc(sizeof(Node));
    itemHead->id = -1;
    itemHead->value = "head";
    Node* result = itemHead;
    HEAD = itemHead;
    for (size_t i = 0; i < 10; i++)
    {
        Node* temp = (Node*) malloc(sizeof(Node));
        temp->id = i;
        temp->value = "test";
        result->next = temp;
        result = temp;
    }
    TAIL = result;
    return HEAD;
}

void printNode() {
    Node* node = HEAD;
    while (node)
    {
        printf("nodeId:%d, nodeValue:%s \n", node->id, node->value);
        node = node->next;
    }
}

void add(Node* node) {
    TAIL->next = node;
}

void delete(int index) {
    Node* temp = HEAD;
    Node* pre = NULL;
    while (temp)
    {
        if (temp->id == index) {
            if (pre != NULL)
            {
                pre->next = temp->next;
            } else {
                HEAD = temp->next;
            }
            free(temp);
            break;
        }
        pre = temp;
        temp = temp->next;
    }
}

int main()
{
    Node* data = init();
    printNode();
    Node* addItem = (Node*) malloc(sizeof(Node));
    addItem->id = 10;
    addItem->value = "addTest";
    printf("start add\n");
    add(addItem);
    printNode();
    printf("start delete\n");
    delete(-1);delete(3);
    printNode();
}