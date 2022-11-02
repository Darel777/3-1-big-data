1.下列不在大数据“4V”特点的是：**D**
A Volume大体量	B Variety 多样性	C Velocity 时效性	**D Veracity 准确性**	E Value 大价值

2.社交网络的商业模式是**长尾模式**

3.数据挖掘是什么？发现具有**有效性、可用性、出乎意料、可理解**的模型。

4.贝叶斯定理

5.不是hadoop的默认集群运行模式：**微服务式（默认的是独立式，伪分布式，全分布式）**

6.Hadoop**不支持**数据随机读写，Hbase**支持**数据随机读写。

7.Hadoop1.0默认的调度器策略是**先进先出调度器**

8.hadoop系统的启动顺序：namenode（管理者）-datanode（文件存储单位）-secondarynamenode（秘书）-resourcemanager-nodemanager

9.在实验集群的master节点使用**jps**命令查看进程时，终端出现**Namenode，JobTracker，Secondary NameNode**能说明Hadoop主节点启动成功。

10.MapReduce编程模型，键值对key，value的key必须实现**WritableComparable**。

11.若不针对MapReduce编程模型的key和value值进行特别设置，**Average**是MapReduce不适宜的运算。

12.**JobTracker**负责**MapReduce**任务调度

13.MapReduce适用于**PB**以上的海量数据**离线**处理。

14.MapReduce计算过程中，相同的key默认会被发送到同一个**reduce task**处理。

15.链式MapReduce计算中，对任意一个MapReduce作业，Map和Reduce阶段有**无限个Mapper**，只有**一个Reducer**。

16.MapReduce的input split**默认是**一个block。

17.MapReduce是**并行化程序执行系统**。

18.HDFS中的block默认保存**3个备份**

19.HDFS1.0默认的块大小是**64MB**

20.HDFS 2.x的默认块大小：**128MB**

21.**namenode是HDFS集群的主节点**，负责维护整个HDFS系统的文件目录树，和各个路径/文件的块信息。

22.tasktracker运行在hdfs的哪个程序上：**datanode**

23.HDFS是**分布式文件系统**。

24.**DataNode**负责HDFS数据存储。

25.HBase是**面向列的分布式数据库管理系统，基于HDFS**。

26.HBase**有列族**。

27.HBase对于**NULL**的列，不需要占用存储空间。

28.HBase的**Region**组成中，必须有**MemStore**项。

29.客户端首次查询HBase数据库时，首先要从**ROOT表**开始查找。

30.HBase是**分布式列式**存储系统，记录按照**列族**集中存放。

31.HBase数据库的**BlockCache**缓存的数据块中，**普通的数据块**不一定能提高效率。

32.ZooKeeper用于解决分布式环境的**一致性问题**。

33.Client段上传文件的时候下列哪项正确？B
A数据经过NameNode传递给DataNode
**BClient段将文件切分为Block，依次上传**
CClient只上传数据到一台DataNode，然后由NameNode负责Block复制工作
D以上都不正确

34.client从**namenode**获取文件信息，然后访问**datanode**。

35.spark：前身shark，是基于hive的Spark SQL，常用的库有Streaming，GraphX，MLlib

36.以下哪个不是spark的库：**Storm**

37.设计分布式数据仓库Hive的数据表时，为取样更加高效，一般可以对表中的连续字段进行**分桶**操作。

38.以下哪一项属于非结构化数据：**视频监控、xml文件**

39.metadata信息从**内存**中读取。

40.NoSql 数据库类型：键值数据库、列存储数据库、文档型数据库、Graph图形数据库。

41.pagerank遇到的问题：**dead ends，spider traps**。

42.用于度量集合距离的是**jaccard**。

43.knn算法的核心思想是，**如果样本在特征空间的k个最相邻的样本中大部分属于某一类，那么该样本也属于这一类**，并且具有这类样本的特征。**knn算法既可以用于分类，又可以用于回归。**优点是简单有效，缺点是解释性不强。

44.ID3作为经典的**决策树算法**，基于**信息熵**来选择最佳的测试属性。C4.5算法克服了ID3的两个缺点：信息增益时选择分支比较多的属性值，且不能处理**连续属性**。

45.硬聚类和软聚类的区别：**硬聚类每个文件只归属一个聚类；软聚类每个文件可以归属多于一个聚类。**

46.kmeans是**迭代求解的聚类分析算法**。步骤是随机选取K个对象作为初始的聚类中心，计算每个对象与各个种子聚类之间的距离，把每个对象分配给距离最近的聚类中心。

47.启发式算法：
仿动物：粒子群优化、蚂蚁优化、鱼群算法、蜂群算法
仿植物：向光性算法、杂草优化算法
仿人类：和声搜索算法

48.协同过滤算法：利用兴趣相投、拥有共同经验之群体的喜好来推荐用户感兴趣的信息
优点：对任何种类的item适用
缺点：需要较多用户数量，用户的选择稀疏（很难有完全相同的用户），无法评价未被打分的item，推荐取向从众趋同（流行度偏置）

49.下列关于HDFS为存储MapReduce并行切分和处理的数据做的设计，错误的是 **B**
A FSDataInputStream扩展了DataInputStream以支持随机读
**B 为实现细粒度并行，输入分片越小越好**
C 一台机器可能被指派从输入文件的任意位置开始处理一个分片
D输入分片（input split）是一种记录的逻辑划分，HDFS数据块（block）是对输入数据的物理分割。

50.JobTracker通常与**NameNode**在一个节点启动

51.Hadoop的性能瓶颈主要是**磁盘**

52.HBase依靠**HDFS**存储底层数据

53.HBase依靠**Mapreduce**提供强大计算能力

54.Hadoop**不支持**数据随机读写