https://blog.csdn.net/cy973071263/article/details/104163611  
# 对象是否可以回收
## 引用计数法
每个对象添加一个引用计数，为0标识对象没有被使用，但是会出现相互引用的case，基本所有jvm没有使用该算法
## 可达性分析
从“GC Roots”的对象作为起始，节点所走过的引用链之外的对象判定为对象不可用  
GC Roots：方法区常量引用对象、类静态属性引用对象、虚拟机栈引用对象、本地方法栈引用对象  
再判断：标记对象不可用后不是立刻回收，会检测对象是或否重写finalize()方法或者该方法已被虚拟机调度用，若是则立即回收，否则加入F-Queue队列，进行第二次标记（虚拟机建立一个低优先级线程执行该方法，但是并保证一定会运行结束，因为如果该方法出现死循环等极端情况会导致内存回收系统崩溃。如果该方法执行结束还是没有加入到引用链则进行回收）
# 常量回收
如果一个常量没有被任务对象引用则会被回收
# 类回收
1. 类所有实例都被回收
2. 加载该类的ClassLoader被回收
3. 该类的java.lang.Class对象没有被引用  
满足以上三个条件可以进行回收（类回收是删除JVM中该类相关的所有信息）
# 回收算法
## 标记清除算法
1. 标记、清除效率都不高
2. 造成内存碎片，无法分配大对象，频繁触发GC
## 复制算法
1. 效率高，没有内存碎片
2. 只能使用一半的内存，适用于新生代，不适用于老年代
## 标记整理算法
1. 标记阶段和标记清除算法一致，整理是让内存紧凑在一起，解决了内存碎片、内存浪费问题
2. 适用于老年代
## 分代收集算法
1. 先用商业虚拟机基本采用该方法
2. 将java堆分成年轻代、老年代，根据各个年代的特点使用不同的回收算法
# 收集器
## Serial收集器 
1. 历史最悠久、单线程（回收期间会STW），单CPU广泛使用
2. 新生代复制算法，老年代（需要其他收集器配合）标记整理算法
## ParNew收集器
1. Serial的多线程版本，但是回收期间也会STW
2. 除了Serial收集器，只有它能与CMS收集器（真正意义上的并发收集器）配合工作
## Parallel Scavenge收集器
1. 似于ParNew 收集器。它是jdk1.8的默认是默认收集器。
2. 新生代采用复制算法，老年代（需要其他收集器配合）采用标记-整理算法。
3. Parallel Scavenge收集器执行的时候多条垃圾收集线程并行工作，在多核CPU下效率更高，应用线程仍然处于等待状态（STW），但是因为他是多个GC线程并行执行垃圾回收，所以垃圾回收的比较快，应用线程等待的时间比Serial收集器少很多。 
## Serial Old收集器
Parallel Scavenge收集器的老年代版本。使用多线程和“标记-整理”算法。在注重吞吐量以及CPU资源的场合，都可以优先考虑 Parallel Scavenge收集器和Parallel Old收集器。
## CMS收集器（Concurrent Mark Sweep）
以获取最短回收停顿时间为目标的收集器。它而非常符合在注重用户体验的应用上使用。是HotSpot虚拟机第一款真正意义上的并发收集器，它第一次实现了让垃圾收集线程与用户线程（基本上）同时工作。
1. 初始标记（CMS initial mark）： 暂停所有的其他线程，并记录下直接与root相连的对象，速度很快。多线程标记。
2. 并发标记（CMS concurrent mark）： 同时开启并发标记线程和用户线程，用一个闭包结构去从GC Root开始对堆中对象进行可达性分析，找出存活的对象可达对象。但在这个阶段结束，这个闭包结构并不能保证包含当前所有的可达对象。因为用户线程可能会不断的更新引用域，所以GC线程无法保证可达性分析的实时性。所以这个算法里会跟踪记录这些发生引用更新的地方。
3. 重新标记（CMS remark）： 重新标记阶段就是为了修正并发标记期间因为用户程序继续运行而导致标记产生变动的那一部分对象的标记记录，这个阶段的停顿时间一般会比初始标记阶段的时间稍长，远远比并发标记阶段时间短
4. 并发清除（CMS concurrent sweep）： 开启用户线程，同时GC线程开始对为标记的区域做清扫。在这个期间就会产生浮动垃圾，就是在并发清理期间用户线程执行期间还是有可能产生垃圾，这些垃圾在本次GC中是不能被回收的，这些垃圾就是浮动垃圾。浮动垃圾只能等到下次GC被清除。
5. 并发重置：准备进行下一次GC  
CMS收集器开启后，年轻代使用STW式的并行收集（ParNew收集器），老年代回收采用CMS进行垃圾回收，对延迟的关注也主要体现在老年代CMS上
### CMS主要优点
1. 并发收集、低停顿
2. 对CPU资源敏感；无法处理浮动垃圾；它使用的回收算法-“标记-清除”算法会导致收集结束时会有大量空间碎片产生。
## G1（Garbage-First)）收集器
https://tech.meituan.com/2016/09/23/g1.html  
https://zhuanlan.zhihu.com/p/52841787  
G1 (Garbage-First)是一款面向服务器的垃圾收集器,主要针对配备多颗处理器及大容量内存的机器. 以极高概率满足GC停顿时间要求的同时,还具备高吞吐量性能特征。同优秀的CMS垃圾回收器一样，G1也是关注最小时延的垃圾回收器，也同样适合大尺寸堆内存的垃圾收集，官方也推荐使用G1来代替选择CMS。G1收集器在jdk1.9后成为了JVM的默认垃圾收集器。  
G1收集器放弃了之前的收集器中所使用的分代思想，引入分区(Region)的思路，弱化了分代的概念，合理利用垃圾收集各个周期的资源，解决了其他收集器甚至CMS的众多缺陷。
# GC参数
https://www.huaweicloud.com/articles/b86de23d6c3d5a161b25b1013a388d8d.html  
 -Djava.util.logging.config.file=/home/www/mars-ide/.server/conf/logging.properties   
 -Djava.util.logging.manager=org.apache.juli.ClassLoaderLogManager   
 -Djdk.tls.ephemeralDHKeySize=2048   
 -Xms20g   
 -Xmx20g   
 -XX:MetaspaceSize=256m   
 -XX:MaxMetaspaceSize=256m   
 -Xmn10g   
 -XX:MaxDirectMemorySize=512m   
 -XX:SurvivorRatio=8   
 -XX:+UseConcMarkSweepGC   
 -XX:+UseCMSCompactAtFullCollection   
 -XX:CMSMaxAbortablePrecleanTime=5000   
 -XX:+CMSClassUnloadingEnabled   
 -XX:CMSInitiatingOccupancyFraction=80   
 -XX:+UseCMSInitiatingOccupancyOnly   
 -XX:+CMSScavengeBeforeRemark   
 -XX:+ParallelRefProcEnabled   
 -XX:+ExplicitGCInvokesConcurrent   
 -Dsun.rmi.dgc.server.gcInterval=2592000000   
 -Dsun.rmi.dgc.client.gcInterval=2592000000   
 -XX:ParallelGCThreads=24   
 -Xloggc:/home/www/mars-ide/logs/gc.log   
 -XX:+PrintGCDetails   
 -XX:+PrintGCDateStamps   
 -XX:+HeapDumpOnOutOfMemoryError   
 -XX:HeapDumpPath=/home/www/mars-ide/logs/java.hprof   
 -Djava.awt.headless=true   
 -Dsun.net.client.defaultConnectTimeout=10000   
 -Dsun.net.client.defaultReadTimeout=30000   
 -Dfile.encoding=UTF-8   
 -Dcom.sun.managemen
