package own.stu.netty.lecture;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.PooledByteBufAllocator;
import org.junit.Test;

public class OwnTest {

    @Test
    public void test1(){
        System.out.println(0x4_000_000_000_000_000L);
    }

    @Test
    public void test(){
//        System.out.println((int)'a');
//        System.out.println((int)'A');

//        System.out.println(validateAndCalculateChunkSize(2048, 11));

        ByteBufAllocator alloc = PooledByteBufAllocator.DEFAULT;

        //tiny规格内存分配 会变成大于等于16的整数倍的数：这里254 会规格化为256
//        ByteBuf byteBuf = alloc.directBuffer(9000);
        ByteBuf byteBuf = alloc.heapBuffer(254);

        //读写bytebuf
        byteBuf.writeInt(126);
        System.out.println(byteBuf.readInt());

        //很重要，内存释放
        byteBuf.release();
    }

    /*
    * Netty先向系统申请一整块连续内存，称为chunk，默认大小chunkSize = 16Mb
    *
    * Netty将chunk进一步拆分为page，默认每个chunk包含2048个page(pageSize = 8Kb)
    *
    * */

    private static int validateAndCalculateChunkSize(int pageSize, int maxOrder) {
        if (maxOrder > 14) {
            throw new IllegalArgumentException("maxOrder: " + maxOrder + " (expected: 0-14)");
        }

        // Ensure the resulting chunkSize does not overflow.
        int chunkSize = pageSize;
        for (int i = maxOrder; i > 0; i --) {
            if (chunkSize > MAX_CHUNK_SIZE / 2) {
                throw new IllegalArgumentException(String.format(
                        "pageSize (%d) << maxOrder (%d) must not exceed %d", pageSize, maxOrder, MAX_CHUNK_SIZE));
            }
            chunkSize <<= 1;
        }
        return chunkSize;
    }

    private static final int MAX_CHUNK_SIZE = (int) (((long) Integer.MAX_VALUE + 1) / 2);
}
