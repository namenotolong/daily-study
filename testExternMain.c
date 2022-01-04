#include <stdio.h>

extern void writeExtern();
int count;

int main()
{
    count = 4;
    writeExtern();
}