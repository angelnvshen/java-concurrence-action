package own.stu.rpc.thrift.helloWorld;

import org.apache.thrift.TException;
import own.stu.rpc.thrift.generated.DataException;
import own.stu.rpc.thrift.generated.Person;
import own.stu.rpc.thrift.generated.PersonService;

public class PersonServiceImpl implements PersonService.Iface {

  @Override
  public Person getPersonByName(String name) throws DataException, TException {
    System.out.println("getPersonByName from client param : " + name);
    Person person = new Person();
    person.setAge(10).setMarried(false).setName("颇丰");
    return person;
  }

  @Override
  public void savePerson(Person person) throws DataException, TException {
    System.out.println("savePerson from client");
    System.out.println(person.getAge());
    System.out.println(person.getName());
    System.out.println(person.isMarried());
  }
}
