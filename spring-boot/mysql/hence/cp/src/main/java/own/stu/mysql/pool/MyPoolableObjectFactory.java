package own.stu.mysql.pool;

import org.apache.commons.pool.BasePoolableObjectFactory;

public class MyPoolableObjectFactory extends BasePoolableObjectFactory<MyObject> {
    @Override
    public MyObject makeObject() throws Exception {
        MyObject obj = new MyObject("new obj", false, " i am new");
        return obj;
    }

    @Override
    public void activateObject(MyObject obj) throws Exception {
        obj.setState("i am ok");
        obj.setValidate(true);
        System.out.println(obj.getName() + " 对象已经激活");
    }

    @Override
    public void destroyObject(MyObject obj) throws Exception {
        obj.setState("i am done");
        obj.setValidate(false);
        System.out.println(obj.getName() + " 对象已经销毁");
    }

    /**
     * 归还对象时 钝化对象 比如某些对象用完之后需要休眠一段时间
     */
    @Override
    public void passivateObject(MyObject obj) throws Exception {
        obj.setPassivateFlag(true);
        System.out.println(obj.getName() + " 钝化完成");

    }

    /**
     * testOnBorrow设置为true时，创建对象时调用该方法验证对象的有效性，
     * 如果无效直接抛出异常throw new Exception("ValidateObject failed");
     * testOnReturn设置为true时，归还对象时 验证对象是否还有效 比如连接是否还在，
     * 如果无效了则不往对象池中放对象。
     */
    @Override
    public boolean validateObject(MyObject obj) {
        System.out.println(obj.getName() + " 验证对象 " + obj.isValidate());
        return obj.isValidate() ? true : false;
    }
}
