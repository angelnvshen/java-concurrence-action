package own.jdk.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class UnsafePlayer {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, InstantiationException {
        //通过反射实例化Unsafe
        Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
        theUnsafe.setAccessible(true);
        Unsafe unsafe = (Unsafe) theUnsafe.get(null);

        Player player = (Player) unsafe.allocateInstance(Player.class);
        player.setName("li lei");
        System.out.println(player.getName());
    }

    static class Player {
        private String name;

        private Player() {
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
