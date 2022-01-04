#include <stdio.h>
#include <string.h>
#include <stdlib.h>

typedef struct Tree
{
    int* value;
    struct Tree* children;
}Tree;

//平衡二叉树 平衡 LL 右旋 RR 左旋 LR 左旋（不平衡节点左孩子）+右旋 RL 右旋（不平衡右孩子）+左旋
//左旋：不平衡节点C变为C右孩子B的左孩子，B原先左孩子变为C的右孩子  并更新B、C节点高度
//右旋：不平衡节点C变为C左孩子B的右孩子，B原先右孩子变为C的左孩子
typedef struct BinaryTree
{
    int value;
    int height;
    struct BinaryTree* left;
    struct BinaryTree* right;
}BinaryTree;

//红黑树
// (1) 每个节点或者是黑色，或者是红色。
// (2) 根节点是黑色。
// (3) 每个叶子节点是黑色。 [注意：这里叶子节点，是指为空的叶子节点！]
// (4) 如果一个节点是红色的，则它的子节点必须是黑色的。
// (5) 从一个节点到该节点的子孙节点的所有路径上包含相同数目的黑节点。
// 有相关定义，查找，插入和删除复杂度都是O(logn)，n个节点的红黑树，高度不会超过2log(n+1),高度为h的红黑树，它的包含的内节点个数至少为 2h/2方-1个
//红黑树变换 插入时的case
// 插入颜色设置为红色，这样只可能破坏4
// case1：父节点是红色，叔叔节点是红色 -> 父节点变为黑色，叔叔节点为黑色，祖父节点为红色，递归平衡祖父节点
// case2：父节点是红色，叔叔节点为空，当前节点、父节点、祖父节点在一条线上 -> 对祖父节点进行左（右，当前节点时左孩子）旋，父节点置黑，祖父节点置红
// case3：父节点是红色，叔叔节点为空，当前节点、父节点、祖父节点是一条折线 -> 对父节点进行左（右，当前节点时左孩子）旋，对祖父节点进行右（左）选，当前节点置黑，祖父节点置红
// 删除操作（删除的是黑色节点） ===  删除替换节点  中序遍历前继或者后驱 递归到根节点或者节点为红色完成平衡
// 从兄弟节点上借调黑色的节点过来，如果兄弟节点没有黑节点可以借调的话，就只能往上追溯，将每一级的黑节点数减去一个，使得整棵树符合红黑树的定义。
// case1：待删除的节点的兄弟节点是红色的节点。  对父节点进行旋转，父节点变为红色，兄弟节点置位黑色，变成case2
// case2：待删除的节点的兄弟节点是黑色的节点，
//没有孩子：兄弟节点置红，对父节点进行向上递归。（父节点为红色，直接置黑完成平衡）
//都是红色：对父节点进行旋转，完成平衡
// case3：待调整的节点的兄弟节点是黑色的节点，且兄弟节点的左子节点是红色的，右节点是黑色的(兄弟节点在右边)，如果兄弟节点在左边的话，就是兄弟节点的右子节点是红色的，左节点是黑色的。 
//对兄弟节点进行右旋（左旋），并置换兄弟节点和兄弟节点的左孩子颜色，变为case4  
// case4：待调整的节点的兄弟节点是黑色的节点，且兄弟节点右子节点是是红色的(兄弟节点在右边)，如果兄弟节点在左边，则就是对应的就是左节点是红色的。
//借调黑色节点，兄弟节点变为父节点的父节点。对父节点进行左旋
// case5：调整的节点的兄弟节点是黑色的节点，且兄弟节点的左子节点是黑色的，右节点是红色的(兄弟节点在右边)  ------case3 反例
typedef struct RedBlackTree
{
    int value;
    struct BinaryTree* left;
    struct BinaryTree* right;
    int red;
}RedBlackTree;

/**
 * 递归中序遍历
 **/
void printTree(BinaryTree* itemTree)
{
    if (!itemTree)
    {
        return;
    }
    printTree(itemTree->left);
    printf("%d ", itemTree->value);
    printTree(itemTree->right);
}
/**
 * 层序遍历
 **/
void platPrintTreeSelf(BinaryTree** tree, int size)
{
    if (!tree || size < 1)
    {
        free(tree);
        return;
    }
    
    BinaryTree** next = (BinaryTree**) calloc(size * 2, sizeof(BinaryTree*));
    int curIndex = 0;
    for (size_t i = 0; i < size; i++)
    {
        printf("%d ", tree[i]->value);
        if (tree[i]->left)
        {
           next[curIndex++] = tree[i]->left; 
        }
        if (tree[i]->right)
        {
            next[curIndex++] = tree[i]->right;
        }
    }
    printf("\n");
    free(tree);
    tree = NULL;
    platPrintTreeSelf(next, curIndex);
}
void platPrintTree(BinaryTree* itemTree)
{
    BinaryTree** tree =(BinaryTree**) calloc(1, sizeof(BinaryTree*));
    tree[0] = itemTree;
    platPrintTreeSelf(tree, 1);
}

/**
 * 初始化树
 **/
void buildTree(BinaryTree* parent, int depth)
{
    if (depth == 3)
    {
        return;
    }
    BinaryTree* left = (BinaryTree*) calloc(1, sizeof(BinaryTree));
    BinaryTree* right = (BinaryTree*) calloc(1, sizeof(BinaryTree));
    left->value = parent->value * 2 + 1;
    right->value = parent->value * 2 + 2;
    parent->left = left;
    parent->right = right;
    buildTree(left, depth + 1);
    buildTree(right, depth + 1);
}

BinaryTree* initTree()
{
    BinaryTree* head = (BinaryTree*) calloc(1, sizeof(BinaryTree));
    head->value = 0;
    buildTree(head, 1);
    return head;
}

/**
 * 插入排序二叉树
 **/
void insert(BinaryTree* head, BinaryTree* data)
{
    if (!head)
    {
        *head = *data;
        return;
    }
    if (!data)
    {
        return;
    }
    
    if (head->value >= data->value)
    {
        if (head->left == NULL)
        {
            head->left = data;
        }
        else
        {
            insert(head->left, data);
        }
    }
    else
    {
        if (head->right == NULL)
        {
            head->right = data;
        }
        else
        {
            insert(head->right, data);
        }
    }
}

/**
 * 构建成排序二叉树
 **/
void rebuildInsert(BinaryTree* head, BinaryTree* data)
{
    if (!head || !data)
    {
        return;
    }
    BinaryTree* lefetNode = data->left;
    BinaryTree* rightNode = data->right;
    data->left = NULL;
    data->right = NULL;
    insert(head, data);
    rebuildInsert(head, lefetNode);
    rebuildInsert(head, rightNode);
}
BinaryTree* rebuild(BinaryTree* head)
{
    if (!head)
    {
        return NULL;
    }
    BinaryTree* lefetNode = head->left;
    BinaryTree* rightNode = head->right;
    head->left = NULL;
    head->right = NULL;
    rebuildInsert(head, lefetNode);
    rebuildInsert(head, rightNode);
    return head;
}

//移除一个元素
int removeByValue(BinaryTree* head, int value)
{
    if (head->value == value)
    {
        BinaryTree* left = head->left;
        BinaryTree* right = head->right;
        if (head->left == NULL)
        {
            free(head);
            *head = *right;
            return 1;
        }
        insert(left, right);
        free(head);
        *head = *left;
        return 1;
    }
    int result = removeByValue(head->left, value);
    if (!result)
    {
        return removeByValue(head->right, value);
    }
    return result;
}


//二叉树的深度
int maxDepth(BinaryTree* head)
{
    if (head == NULL)
    {
        return 0;
    }
    int left = maxDepth(head->left);
    int right = maxDepth(head->right);
    int max = left > right ? left : right;
    return max + 1;
}

//左旋
BinaryTree* leftRoate(BinaryTree* node)
{
    if (node->right)
    {
        BinaryTree* right = node->right;
        BinaryTree* rightLeft = right->left;
        right->left = node;
        node->right = rightLeft;
        right->height = maxDepth(right);
        node->height = maxDepth(node);
        return right;
    }
    return node;
}

//右旋
BinaryTree* rightRoate(BinaryTree* node)
{
    if (node->left)
    {
        BinaryTree* left = node->left;
        BinaryTree* leftRight = left->right;
        left->right = node;
        node->left = leftRight;
        node->height = maxDepth(node);
        left->height = maxDepth(left);
        return left;
    }
    return node;
}

int getHeight(BinaryTree* node)
{
    if (node == NULL)
    {
        return 0;
    }
    return node->height;
}

BinaryTree* balanceBinaryTree(BinaryTree* head)
{
    int factor = getHeight(head->right) - getHeight(head->left);
    if (factor > 1)
    {
        factor = getHeight(head->right->right) - getHeight(head->right->left);
        if (factor > 0)
        {
            //RR
            return leftRoate(head);
        }
        else
        {
            //RL 右旋+左旋
            head->right = rightRoate(head->right);
            return leftRoate(head);
        }
    }
    else if (factor < -1)
    {
        factor = getHeight(head->left->right) - getHeight(head->left->left);
        if (factor > 0)
        {
            //LR
            head->left = leftRoate(head->left);
            return rightRoate(head);
        }
        else
        {
            //LL
            return leftRoate(head);
        }
    }
    return head;
}


//平衡树添加元素
BinaryTree* insertBalanceTree(BinaryTree** head, int value)
{
    BinaryTree* temp = *head;
    if (temp == NULL)
    {
        BinaryTree* data = (BinaryTree*) calloc(1, sizeof(BinaryTree));
        data->value = value;
        data->height = 1;
        data->right = data->left = NULL;
        *head = data;
        return data;
    }
    if (value == temp->value)
    {
        return NULL;
    }
    if (value > temp->value)
    {
        temp->right = insertBalanceTree(&temp->right, value);
    }
    else
    {
        temp->left = insertBalanceTree(&temp->left, value);
    }
    int leftHeight = getHeight(temp->left);
    int rightHeight = getHeight(temp->right);
    int max = leftHeight > rightHeight ? leftHeight : rightHeight;
    temp->height = max + 1;
    //banlace
    return balanceBinaryTree(temp);
}

int deleteBinarySortTreeMin(BinaryTree** root)
{
    BinaryTree* node = *root;
    if (node == NULL)
    {
        return -1;
    }
    if (node->left == NULL)
    {
        int min = node->value;
        free(node);
        *root = NULL;
        return min;
    }
    return deleteBinarySortTreeMin(&node->left);
}

//删除平衡树
BinaryTree* deleteBalanceTree(BinaryTree** head, int value)
{
    BinaryTree* temp = *head;
    if (temp == NULL)
    {
        return NULL;
    }
    if (value == temp->value)
    {
        if (temp->right)
        {
            //将右孩子的最小值删除，赋值给*head value
            temp->value = deleteBinarySortTreeMin(&temp->right);
        }
        else
        {
            BinaryTree* toFree = temp;
            *head = temp->left;
            free(toFree);
        }
        return *head;
    }
    if (value > temp->value)
    {
        deleteBalanceTree(&temp->right, value);
    }
    else
    {
        deleteBalanceTree(&temp->left, value);
    }
    int leftHeight = getHeight(temp->left);
    int rightHeight = getHeight(temp->right);
    int max = leftHeight > rightHeight ? leftHeight : rightHeight;
    temp->height = max + 1;
    //banlace
    return balanceBinaryTree(temp);
}

//构建平衡树
BinaryTree* buildBalanceTree()
{
    BinaryTree* root = (BinaryTree*) calloc(1, sizeof(BinaryTree));
    root->value = 0;
    root->height = 1;
    root->right = root->left = NULL;
    return root;
}



int main()
{
    BinaryTree* head = initTree();
    platPrintTree(head);

    BinaryTree* root = buildBalanceTree();
    platPrintTree(root);
    printf("the now tree depth is %d\n", maxDepth(root));

    for (size_t i = 1; i < 10; i++)
    {
        root = insertBalanceTree(&root, i);
    }
    printf("the now tree depth is %d\n", maxDepth(root));

    platPrintTree(root);
    printf("the now tree depth is %d\n", maxDepth(root));

    root = deleteBalanceTree(&root, 0);
    platPrintTree(root);
    printf("the now tree depth is %d\n", maxDepth(root));
    return 0;
}