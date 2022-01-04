#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include "header.h"
#include <stdarg.h>
#define HELLO_WROD "helloworld"
#undef  HELLO_WROD
#define HELLO_WROD 42
#ifndef MESSAGE
   #define MESSAGE "You wish!"
#endif
#ifdef DEBUG
   /* Your debugging statements here */
#endif

/**
 * 静态变量
 **/
void testStatic(void) 
{
	static int a = 0;
	++a;
	printf("test static a: %d\n", a);
}

/**
 * 数组
 **/
void testArray(int arr[]) {
	int size = sizeof(&arr) / sizeof(int);
	printf("arr size is value:%d\n", size);
	for (int i = 0; i < size; i++)
	{
		printf("arr[%d] value is:%d\n", i, arr[i]);
	}
	printf("arr[2] value is %d \n", arr[2]);
}

void testArrayPointer(int *arr, int size)
{
	for (size_t i = 0; i < size; i++)
	{
		printf("arr value i: %d \n", arr[i]);
	}
	
}

/**
 * 枚举
 **/
enum DAY
{
	MON=1, TUE, WED, THU, FRI, SAT, SUN
} day;

/**
 * 指针基操，指针数组，数组指针，字符串数组
 **/
void testPointer() 
{
	int a = 10;
	int* p = &a;
	int** pp = &p;
	int*** ppp = &pp;
	printf("value a address is %p \n", p);
	printf("use poiner get a value is %d \n", *p);
	printf("use poiner s pointer get a value is %d \n", **pp);
	printf("use poiner s pointer get a value is %d \n", ***ppp);

	p++;
	printf("value a address is %p \n", p);
	printf("use poiner get a value is %d \n", *p);

	int arr[] = {2,3,4};
	int* arrp = arr;
	for (size_t i = 0; i < 3; i++)
	{
		printf("use pointer visit arr value is %d \n", *arrp);
		++arrp;
	}
	

	int *p1 = NULL;
	printf("c language NULL is %p \n", p1);
	printf("c language NULL is %p \n", (void*)0);


	const char *names[] = {
                   "Zara Ali",
                   "Hina Ali",
                   "Nuha Ali",
                   "Sara Ali",
   };
   int i = 0;
 
   for ( i = 0; i < 4; i++)
   {
      printf("Value of names[%d] = %s\n", i, names[i] );
   }
}
/**
 * 指针改变形参
 **/
void modifyValue(int *p) {
	*p = 1;
}
int add(int a, int b) 
{
	return a + b;
}
/**
 * 指针函数 typedef int (*fun_ptr)(int,int); 
 **/
void useFunctionPointer() 
{
	int (*p)(int, int) = &add;
	printf("use pointer function get add value:%d \n", (*p)(1,2));
}

/**
 * 回调函数
 **/
int testCallBackFunction(int (*p) (int, int))
{
	return p(1,2);
}
void testCallBack() {
	int (*p) (int, int) = add;
	printf("test callback function:%d \n", testCallBackFunction(p));
}

/**
 * 函数返回指针
 **/
char* getArr()
{
	return "hello world";
}

/**
 * 字符串
 **/
void testString()
{
	char arr[] = {'h', 'e', 'l', '\0', 'l', 'o', '\0'};
	printf("hello char array:%s \n", arr);

	char s1[] = "hello";
	printf("test string length:%lu sizeof value:%lu \n", strlen(s1), sizeof(s1));
}


/**
 * 结构体
 **/
struct student
{
	int id;
	char name;
	double age;
} SOM = {1, '2', 3};

typedef struct
{
	int id;
	int age;
} MyStruct;

void testStruct()
{
	printf("onwer struct student %d \n", SOM.id);
	struct student *p = &SOM;
	printf("onwer struct student %d \n", p -> id);
	MyStruct a = {1 ,2};
	printf("test typedef %d \n", a.age);
}

/**
 * 共同体
 **/
union RESULT
{
	int id;
	int age;
	double number;
};

void testUnion() 
{
	union RESULT one;
	printf("unioin sizeof is:%lu\n", sizeof(one));
	printf("struct sizeof is:%lu\n", sizeof(SOM));
	one.id = 1;
	printf("unioin RESULT id is:%d\n", one.id);
	printf("unioin RESULT age is:%d\n", one.age);
}
/**
 * 位域
 **/
/* 定义简单的结构 */
struct
{
  unsigned int widthValidated;
  unsigned int heightValidated;
} status1;
 
/* 定义位域结构 */
struct
{
  unsigned int widthValidated : 1;
  unsigned int heightValidated : 1;
} status2;

void testBitSizeStruct()
{
	printf( "Memory size occupied by status1 : %lu\n", sizeof(status1));
  	printf( "Memory size occupied by status2 : %lu\n", sizeof(status2));
}

/** typedef **/

void testTypeDef()
{
	typedef int MYINT;
	MYINT a = 2;
	int b = 2;
	printf("myint and int value compare result:%d \n", a != b);
	printf("myint size:%lu \n", sizeof(MYINT));
}


/** 输入输出 **/
void testStdIo()
{
	int a;
	//scanf("%d", &a);
	//printf("您输入的内容是：%d \n", a);
	char str[100];
	gets(str);
	printf("gets str from stdin:%s \n", str);
}
/* 文件读写 */

void testFile()
{
	FILE *fp = fopen("/Users/huyong/Documents/workhome/homec/test.txt", "rw+");
	if (fp == NULL)
	{
		printf("打开文件失败\n");
		return;
	}
	
	fprintf(fp, "This is testing for fprintf...\n");
    fputs("This is testing for fputs...\n", fp);
	char buff[255];
	fgets(buff, 255, (FILE*)fp);
	printf("2: %s\n", buff );
    fgets(buff, 255, (FILE*)fp);
    printf("3: %s\n", buff );
    fclose(fp);
}

/* 宏 */
void testAnsi()
{
	printf("the now date define:%s \n", __DATE__);
	printf("the now time define:%s \n", __TIME__);
	printf("the now file define:%s \n", __FILE__);
	printf("the now file line define:%d \n", __LINE__);
	printf("the stdc define:%d \n", __STDC__);
}

/* 错误处理 */
/**大多数的 C 或 UNIX 函数调用返回 1 或 NULL，同时会设置一个错误代码 errno，
 * 该错误代码是全局变量，表示在函数调用期间发生了错误。
 * 您可以在 errno.h 头文件中找到各种各样的错误代码
 * perror() 函数显示您传给它的字符串，后跟一个冒号、一个空格和当前 errno 值的文本表示形式。
 * strerror() 函数，返回一个指针，指针指向当前 errno 值的文本表示形
 **/
extern int errno ;
void testErrorHandle()
{
   FILE * pf;
   int errnum;
   pf = fopen ("unexist.txt", "rb");
   if (pf == NULL)
   {
      errnum = errno;
      fprintf(stderr, "错误号: %d\n", errno);
      perror("通过 perror 输出错误");
      fprintf(stderr, "打开文件错误: %s\n", strerror( errnum ));
	  exit(-1);
   }
   else
   {
      fclose (pf);
   }
   return;
} 

//可变参数
void testArgs(double a, int num, ...)
{
	//定义变量
	va_list list;
	//开始分配变量
	va_start(list, num);
	double sum;
	for (size_t i = 0; i < num; i++)
	{
		//拿出每个值
		sum += va_arg(list, int);
	}
	/* 清理为 list 保留的内存 */
	va_end(list);
	printf("list sum is:%lf \n", sum);
	printf("input a value is:%lf \n", a);

	int i = 0;
	int *addr = &num;
    for (i = 0; i <= num; i++) 
    {
        /* *(addr + i) 从左往右依次取出传递进来的参数,类似于出栈过程 */
        printf("i=%d,value=%d\r\n", i, *(addr + 8));
    }
}

//内存管理
typedef struct
{
  int id;
  char name[0];
}stu_t;
void testMomory()
{
   char name[100];
   char *description;
 
   strcpy(name, "Zara Ali");
 
   /* 动态分配内存 */
   description = (char *)malloc(200 * sizeof(char));
   //description = (char *)calloc(200, sizeof(char));
   if( description == NULL )
   {
      fprintf(stderr, "Error - unable to allocate required memory\n");
	  perror("Error - unable to allocate required memory\n");
   }
   else
   {
      strcpy( description, "Zara ali a DPS student in class 10th");
   }
   printf("Name = %s\n", name );
   printf("Description: %s\n", description );

   description = (char *) realloc( description, 100 * sizeof(char) );

   if( description == NULL )
   {
      fprintf(stderr, "Error - unable to allocate required memory\n");
   }
   else
   {
      strcat( description, "She is in class 10th");
   }
   printf("Name = %s\n", name );
   printf("Description: %s\n", description );
 
   /* 使用 free() 函数释放内存 */
   free(description);
   //动态可变长的结构体：
   //定义该结构体，只占用4字节的内存，name不占用内存。
   stu_t *s = NULL;    //定义一个结构体指针
	s = malloc(sizeof(*s) + 100);//sizeof(*s)获取的是4，但加上了100，4字节给id成员使用，100字节是属于name成员的
	s->id = 1010;
	strcpy(s->name,"hello");
	//注意：一个结构体中只能有一个可变长的成员，并且该成员必须是最后一个成员。
	printf("struct value name is :%s \n", s ->name);
}

/**
 * 类泛型交换变量
 **/
void swap(void *a, void *b, int size) {
	void *p = malloc(sizeof(*a));
	memcpy(p, a, size);
	memcpy(a, b, size);
	memcpy(b, p, size);
	free(p);
}

int main(void)
{
	const char a[] = "hello world";
	//printf("const array char string value:%s \n", a);
	//puts(HELLO_WROD);
	for (int i = 0; i < 3; i++)
	{
		//testStatic();
	}
	//int arr[4] = {1,2,3, 4};
	//printf("arr size is:%lu \n", sizeof(arr) / sizeof(int));
	//testArray(arr);
	//testArrayPointer(arr, sizeof(arr) / sizeof(int));

	//printf("enum test value :%u \n", MON);

	//testPointer();

	//int testModifyValue = 10;
	//modifyValue(&testModifyValue);
	//printf("modifyValue result %d \n", testModifyValue);
	//useFunctionPointer();
	//testCallBack();
	//testString();
	//testStruct();
	//testUnion();
	//testBitSizeStruct();
	//testTypeDef();
	//testStdIo();
	//testFile();
	//testAnsi();
	//testErrorHandle();
	//testArgs(2.0, 5,2,3,4,5,6);

	int arr[] = {1,2,3,4,5};
	int *p = &arr[0];
	for (size_t i = 0; i < 5; i++)
	{
		printf("value:%d", *p);
		++p;
	}
	testMomory();

	double aNumber = 1;
	double bNumber=  2;
	swap(&aNumber, &bNumber, sizeof(double));
	printf("a:%f, b:%f\n", aNumber, bNumber);
	return 0;
}
