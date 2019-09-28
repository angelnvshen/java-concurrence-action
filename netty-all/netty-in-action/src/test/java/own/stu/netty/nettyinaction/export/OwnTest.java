package own.stu.netty.nettyinaction.export;

import commonj.sdo.DataObject;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by CHANEL on 2018/7/15.
 */
public class OwnTest {

    @Test
    public void testSome() throws Exception {
//        String templateFilePath =  "C:\\Users\\CHANEL\\Desktop\\export.xls";
        String templateFilePath_2 =  "C:\\Users\\CHANEL\\Desktop\\export.xlsx";
        String outputFilePath =  "C:\\Users\\CHANEL\\Desktop\\data.txt";
        ExcelTemplate template=new ExcelTemplate(templateFilePath_2,outputFilePath) ;

//        List<DataObject> resultset = new ArrayList<>();
//        FileOutputStream fos = new FileOutputStream(outputFilePath);
//        ObjectOutputStream outputStream = new ObjectOutputStream(fos) ;
//        template.generate(outputStream, resultset);//resultset为ArrayList对象,数据行以Map封装

        List<Object> lst = template.importData(templateFilePath_2, "own.stu.netty.nettyinaction.export.People");

        System.out.println(lst);
    }
}

class People{
    private  String name;
    private  String age;
    private  String sfhm;
    private  String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSfhm() {
        return sfhm;
    }

    public void setSfhm(String sfhm) {
        this.sfhm = sfhm;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

