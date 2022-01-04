#include <stdio.h>

extern int count;

void writeExtern()
{
    printf("count value is:%d", count);
}