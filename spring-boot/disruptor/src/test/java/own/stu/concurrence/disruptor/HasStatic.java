package own.stu.concurrence.disruptor;

class Base{
    public Base(String s){
        System.out.print("B");
    }
}
public class HasStatic extends Base{
    public HasStatic (String s) {
        super("");
        System.out.print("D");
    }
    private static int x = 100;
    static final int i = 2345;
    final float j = 0.22f;

    public static void main(String args[]) {

        System.out.print(getNumber(0));
        /*HasStatic hs1 = new HasStatic();
        hs1.x++;
        HasStatic hs2 = new HasStatic();
        hs2.x++;
        hs1 = new HasStatic();
        hs1.x++;
        HasStatic.x--;
        System.out.println("x=" + x);*/
    }

    public static int getNumber(int num) {
        try {
            int result = 2 / num;
            return result;
        } catch (Exception exception) {
            return 0;
        } finally {
            if (num == 0) {
                return -1;
            }
            if (num == 1) {
                return 1;
            }
        }
    }
}