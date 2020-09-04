package own.stu.netty.rpcsim.common.codec.heartBeat;

import own.stu.netty.rpcsim.common.bean.RpcRequest;

public final class Beat {

    public static final int BEAT_INTERVAL = 30;
    public static final int BEAT_TIMEOUT = 3 * BEAT_INTERVAL;
    public static final String BEAT_ID = "BEAT_PING_PONG";

    private Beat() {

    }

    public static RpcRequest BEAT_PING() {
        return BeatHolder.BEAT_PING;
    }

    private static class BeatHolder {

        private static RpcRequest BEAT_PING;

        static {
            BEAT_PING = new RpcRequest();
            BEAT_PING.setRequestId(BEAT_ID);
        }
    }

}
