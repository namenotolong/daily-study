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
// 从兄弟节点上借调红色节点变为黑色的节点过来，如果兄弟节点没有黑节点可以借调的话，就只能往上追溯，将每一级的黑节点数减去一个，使得整棵树符合红黑树的定义。
//先找到替换节点。（自己或者其他）1.删除节点是红色直接删除。2.删除节点时黑色；
//a.兄弟节点时红色时,此时父节点和兄弟节点的子节点都是黑色节点，对兄弟节点变黑，父节点变红，对父节点进行旋转，兄弟节点变为父节点的父节点；此时场景转换为b1
//b.兄弟节点时黑色，此时子节点要么都为空、为红、一红一NILL
//b1:兄弟节点子节点为空，无红色可掉，将兄弟节点染红，递归父节点
//b2:远离替换节点的子节点为红，父节点置黑，兄弟节点置为父节点颜色，兄弟该红色子节点置黑，对父节点进行旋转，使兄弟节点成为父节点的父节点
//b3:靠近替换节点的子节点为红，兄弟节点置红，兄弟该字节置黑，旋转兄弟节点，使该子节点为父节点，此时变为场景b2

typedef struct RedBlackTree
{
    int value;
    struct RedBlackTree* left;
    struct RedBlackTree* right;
    struct RedBlackTree* parent;
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

    if (*tree == NULL)
    {
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

int maxRBDepth(RedBlackTree* head)
{
    if (head == NULL)
    {
        return 0;
    }
    int left = maxRBDepth(head->left);
    int right = maxRBDepth(head->right);
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
        return temp;
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

/**
 * 红黑树模块
 **/ 
RedBlackTree* rb_root = NULL;

//左旋
RedBlackTree* leftRbRoate(RedBlackTree* node)
{
    if (node->right)
    {
        RedBlackTree* parent = node->parent;
        RedBlackTree* right = node->right;
        RedBlackTree* rightLeft = right->left;
        right->left = node;
        right->parent = node->parent;
        if (parent != NULL)
        {
            if (parent->left != NULL && parent->left->value == node->value)
            {
                parent->left = right;
            }
            else
            {
                parent->right = right;
            }
        }
        else
        {
            rb_root = right;
        }
        node->parent = right;
        node->right = rightLeft;
        if (rightLeft != NULL)
        {
            rightLeft->parent = node;
        }
        return right;
    }
    return node;
}

//右旋
RedBlackTree* rightRbRoate(RedBlackTree* node)
{
    if (node->left)
    {
        RedBlackTree* parent = node->parent;
        RedBlackTree* left = node->left;
        RedBlackTree* leftRight = left->right;
        left->right = node;
        left->parent = parent;
        if (parent != NULL)
        {
            if (parent->left != NULL && parent->left->value == node->value)
            {
                parent->left = left;
            }
            else
            {
                parent->right = left;
            }
        }
        else
        {
            rb_root = left;
        }
        node->parent = left;
        node->left = leftRight;
        if (leftRight != NULL)
        {
            leftRight->parent = node;
        }
        return left;
    }
    return node;
}

//修正插入后的红黑树
void fix_up_insert(RedBlackTree* item)
{
    if (item == NULL)
    {
        return;
    }
    RedBlackTree* parent = item->parent;
    if (parent == NULL)
    {
        rb_root = item;
        rb_root->red = 0;
        return;
    }
    RedBlackTree* grandParent = parent->parent;
    //父节点是黑色直接return
    if (!item->parent->red)
    {
        return;
    }
    RedBlackTree* itemRoot = grandParent->parent;
    //父节点是左孩子
    if (grandParent->left != NULL && grandParent->left->value == parent->value)
    {
        RedBlackTree* uncle = grandParent->right;
        //叔叔节点为黑色
        if (uncle == NULL || !uncle->red)
        {
           if (parent->left != NULL && item->value == parent->left->value)
           {
               //在一条线上
               parent->red = 0;
               grandParent->red = 1;
               rightRbRoate(grandParent);
               if (itemRoot == NULL)
               {
                   rb_root = parent;
               }
           }
           else
           {
               //折线
               item->red = 0;
               grandParent->red = 1;
               leftRbRoate(parent);
               rightRbRoate(grandParent);
               if (itemRoot == NULL)
               {
                   rb_root = item;
               }
           }
        }
        else
        {
            //叔叔节点红色
            parent->red = 0;
            uncle->red = 0;
            grandParent->red = 1;
            fix_up_insert(grandParent);
        }
    }
    else
    {
        RedBlackTree* uncle = grandParent->left;
        //叔叔节点为黑色
        if (uncle == NULL || !uncle->red)
        {
           if (parent->right != NULL && item->value == parent->right->value)
           {
               //在一条线上
               parent->red = 0;
               grandParent->red = 1;
               leftRbRoate(grandParent);
               if (itemRoot == NULL)
               {
                   rb_root = parent;
               }
           }
           else
           {
               //折线
               item->red = 0;
               grandParent->red = 1;
               rightRbRoate(parent);
               leftRbRoate(grandParent);
               if (itemRoot == NULL)
               {
                   rb_root = item;
               }
           }
        }
        else
        {
            //叔叔节点红色
            parent->red = 0;
            uncle->red = 0;
            grandParent->red = 1;
            fix_up_insert(grandParent);
        }
    }
    
}
//红黑树添加元素
void insertRedBlackValue(int value)
{
    RedBlackTree* item = (RedBlackTree*) calloc(1, sizeof(RedBlackTree));
    item->value = value;
    item->left = item->right = item->parent = NULL;
    item->red = 1;
    if (rb_root == NULL)
    {
        rb_root = item;
        rb_root->red = 0;
        return;
    }
    RedBlackTree* parent = rb_root;
    RedBlackTree* itemNode = rb_root;
    while (itemNode != NULL)
    {
        parent = itemNode;
        if (itemNode->value == value)
        {
            return;
        }
        if (itemNode->value > value)
        {
            itemNode = itemNode->left;
        } else {
            itemNode = itemNode->right;
        }
    }
    if (parent->value > value)
    {
        parent->left = item;
    }
    else
    {
        parent->right = item;
    }
    item->parent = parent;
    fix_up_insert(item);
}

void fix_up_red_black_remove(RedBlackTree* nextNode)
{
    RedBlackTree* parent = nextNode->parent;
    if (parent == NULL)
    {
        nextNode->red = 0;
        return;
    }
    
    int isLeft = parent->left != NULL && parent->left->value == nextNode->value ? 1 : 0;
    if (nextNode->red)
    {
        return;
    }
    else
    {
        RedBlackTree* brother = isLeft ? parent->right : parent->left;
        if (brother->red)
        {
            //兄弟节点时红色  : 兄弟和父节点互置颜色，对父节点旋转  场景切换
            parent->red = 1;
            brother->red = 0;
            if (isLeft)
            {
                leftRbRoate(parent);
            }
            else
            {
                rightRbRoate(parent);
            }
        }
        brother = isLeft ? parent->right : parent->left;
        //此时兄弟节点一定是黑色
        if (brother->left == NULL && brother->right == NULL)
        {
            //无红色节点可借  进行上滤
            brother->red = 1;
            parent->red = 0;
            fix_up_red_black_remove(parent);
            return;
        }
        if ((!isLeft && brother->right != NULL && brother->right->red)
         || (isLeft && brother->left != NULL && brother->left->red))
        {
            //靠近替换节点的子节点为红，兄弟节点置红，兄弟该字节置黑，旋转兄弟节点，使该子节点为父节点，替换场景
            brother->red = 0;
            if (isLeft)
            {
                rightRbRoate(brother);
            }
            else
            {
                leftRbRoate(brother);
            }
            brother = isLeft ? parent->right : parent->left;
        }
        //远离替换节点的子节点为红，父节点置黑，兄弟节点置为父节点颜色，兄弟该红色子节点置黑，对父节点进行旋转，使兄弟节点成为父节点的父节点
        if ((isLeft && brother->right != NULL && brother->right->red)
         || (!isLeft && brother->left != NULL && brother->left->red))
        {
            brother->red = parent->red;
            parent->red = 0;
            if (isLeft)
            {
                brother->right->red = 0;
                leftRbRoate(parent);
            }
            else
            {
                brother->left->red = 0;
                rightRbRoate(parent);
            }
        }
    }
}

void removeRedBlackValue(int value)
{
    RedBlackTree* curNode = rb_root;
    RedBlackTree* target = NULL;
    while (curNode != NULL)
    {
        if (curNode->value == value)
        {
            target = curNode;
            break;
        }
        if (curNode->value > value)
        {
            curNode = curNode->left;
        }
        else
        {
            curNode = curNode->right;
        }
    }
    if (target == NULL)
    {
        return;
    }
    //查找后继
    RedBlackTree* nextNode = target;
    while (nextNode->right != NULL)
    {
        nextNode = nextNode->right;
    }
    target->value = nextNode->value;
    //先协调  再删除
    fix_up_red_black_remove(nextNode);
    RedBlackTree* parent = nextNode->parent;
    int isLeft = parent->left != NULL && parent->left->value == nextNode->value ? 1 : 0;
    if (parent != NULL)
    {
        if (isLeft)
        {
            parent->left = NULL;
        }
        else
        {
            parent->right = NULL;
        }
    }
    else
    {
        rb_root = NULL;
    }
    free(nextNode);
}
void platRBPrintTreeSelf(RedBlackTree** tree, int size)
{
    if (!tree || size < 1)
    {
        free(tree);
        return;
    }

    if (*tree == NULL)
    {
        return;
    }
    
    
    RedBlackTree** next = (RedBlackTree**) calloc(size * 2, sizeof(RedBlackTree*));
    int curIndex = 0;
    for (size_t i = 0; i < size; i++)
    {
        printf("value:%d color:%d", tree[i]->value, tree[i]->red);
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
    platRBPrintTreeSelf(next, curIndex);
}

void printRbTree(RedBlackTree* root)
{
    RedBlackTree** tree =(RedBlackTree**) calloc(1, sizeof(RedBlackTree*));
    tree[0] = root;
    platRBPrintTreeSelf(tree, 1);
}


int totalBalckCount = -1;

int validBlackCount(RedBlackTree* root, int preCount)
{
    if (root == NULL)
    {
        //标准
        if (totalBalckCount == -1)
        {
            totalBalckCount = preCount;
            return 1;
        }
        if (totalBalckCount != preCount)
        {
            return 0;
        }
        return 1;
    }
    if (!root->red)
    {
        ++preCount;
    }
    else
    {
        if (root->left != NULL && root->right != NULL)
        {
            if (root->left->red || root->right->red)
            {
                return 0;
            }
        }
    }
    return validBlackCount(root->left, preCount) && validBlackCount(root->right, preCount);
}

int checkRBIsValid(RedBlackTree* root)
{
    if (root == NULL)
    {
        return 1;
    }
    if (root->red)
    {
        return 0;
    }
    return validBlackCount(root, 0);
    }

int main()
{
    // BinaryTree* head = initTree();
    // platPrintTree(head);

    // BinaryTree* root = buildBalanceTree();

    // for (size_t i = 1; i < 10; i++)
    // {
    //     root = insertBalanceTree(&root, i);
    // }
    // printf("the now tree depth is %d\n", maxDepth(root));

    // platPrintTree(root);
    // printf("the now tree depth is %d\n", maxDepth(root));

    // root = deleteBalanceTree(&root, 0);
    // platPrintTree(root);
    // printf("the now tree depth is %d\n", maxDepth(root));

    // root = insertBalanceTree(&root, 3);
    // platPrintTree(root);
    // printf("the now tree depth is %d\n", maxDepth(root));

    for (size_t i = 1; i < 10000; i++)
    {
        insertRedBlackValue(i);
    }
    //printRbTree(rb_root);
    //printf("the now tree depth is %d\n", maxRBDepth(rb_root));
    printf("the tree valid is %d\n", checkRBIsValid(rb_root));
    for (size_t i = 1; i < 2; i++)
    {
        removeRedBlackValue(i);
    }
    printf("the tree valid is %d\n", checkRBIsValid(rb_root));
    //printRbTree(rb_root);
    //printf("the now tree depth is %d\n", maxRBDepth(rb_root));
    return 0;
}