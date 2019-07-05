package own.stu.com.redis.dataStructure.string;

/**
 * simple dynamic String
 *
 * 相比于 c中的 字符串实现的优点 ：
 *  1： 获取长度的复杂度是 O(n); SDS的是O(1)。
 *  2： 防止缓冲区溢出
 *  3：  空间预分配 A:SDS字符串修改之后 长度 <1MB，策略：2*len(SDS)+1,SDS的长度是修改之后的长度，即free = len
 *                B:SDS字符串修改之后 长度 >=1MB，策略：free = 1MB
 *       惰性释放空间：即不会立即使用内存重新分配来回收缩短后多出来的字节
 *  4：二进制安全：可以包含'\0'字节的字符串
 */
public class SDS {

  // 记录buf中已使用的字节长度，等于SDS中保存字符串的长度
  private Integer len;

  // buf 未使用的字节的长度
  private Integer free;

  // 数据空间
  private char[] buf;
}
