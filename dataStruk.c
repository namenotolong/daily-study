#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct Link
{
    char * name;
    int id;
    struct Link *next;
}Link;
typedef struct
{
    char * name;
    int id;
    int age;
}Person;
void testCommonStruct()
{
    Person xm = {"xiaoming", 1, 20};
    Person * copy = &xm;
    printf("person strcut xm name:%s\n", copy->name);
}

Link * initLink()
{
    Link * result = (Link*) malloc(sizeof(Link));
    Link * p = result;
    for (size_t i = 0; i < 10; i++)
    {
        Link * temp = (Link*) malloc(sizeof(Link));
        //char v[20];
        //sprintf(v,"%s%zu","hellowrold",i);
        //printf("value:%s\n", v);
        temp->id = i;
        temp->name = "hello world";
        p->next = temp;
        p = p->next;
    }
    return result;
}

Link * findById(Link *p, int id)
{
    while (p)
    {
        if (p->id == id)
        {
            return p;
        }
        p = p->next;
    }
    return NULL;
}

void insert(Link *p, Link *value, int target) {
    while (p)
    {
        if (p->id == target) {
            value->next = p->next;
            p->next = value;
            break;
        } 
        p = p->next;
    }
}
void delete(Link* p, int target) {
    Link *pre = NULL;
    while (p)
    {
        if(p->id == target) {
            if (pre == NULL)
            {
                *p = *(p->next);
            } else {
                pre->next = p->next;
            } 
            free(p);
            break;
        }
        pre = p;
        p = p->next;
    }
}

void iterator(Link *p) {
    while (p) 
    {
        printf("link name:%s id:%d\n", p -> name, p -> id);
        p = p -> next;
    }
}
//传递过来的是一个指针变量，可以理解为指针变量的形参，改变指针变量的引用对形参无用，通过指针变量改变地址内容会影响实参
void modifyLink(Link *p) {
    Link temp = {"hello", 200, NULL};
    //p = &temp;
    *p = temp;
}

extern int errorno;
void testLink()
{
    Link * result = initLink();
    printf("link name:%s id:%d\n", result -> name, result -> id);
    iterator(result);
    Link * search = findById(result, 3);
    printf("test find by id get 3 value:%s %d\n", search->name, search->id);
    printf("link name:%s id:%d\n", result -> name, result -> id);

    Link* insertP = (Link*) malloc(sizeof(Link));
    insertP->name = "hello world";
    insertP->id = 2000;
    puts("start add value");
    insert(result, insertP, 4);
    iterator(result);
    puts("start delete value");
    delete(result, 2000);
    iterator(result);
}

int main()
{
    //testCommonStruct();
    testLink();
    return 0;
}