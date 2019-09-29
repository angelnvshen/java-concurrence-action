package own.stu.rpc.thrift.helloWorld;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import own.stu.rpc.thrift.generated.DataException;
import own.stu.rpc.thrift.generated.Person;
import own.stu.rpc.thrift.generated.PersonService;
import own.stu.rpc.thrift.generated.PersonService.Client;

public class ThriftClient {

  public static void main(String[] args) {
    TTransport tTransport = new TFramedTransport(new TSocket("localhost", 8899), 600);
    TCompactProtocol tCompactProtocol = new TCompactProtocol(tTransport);
    PersonService.Client client = new Client(tCompactProtocol);

    try {
      tTransport.open();

      Person person = client.getPersonByName("劳务");
      System.out.println(person);
      System.out.println(" ------- ");

      Person person1 = new Person().setName("woc").setAge(20).setMarried(false);
      client.savePerson(person1);

    } catch (TTransportException e) {
      e.printStackTrace();
    } catch (DataException e) {
      e.printStackTrace();
    } catch (TException e) {
      e.printStackTrace();
    } finally {
      tTransport.close();
    }

  }
}
