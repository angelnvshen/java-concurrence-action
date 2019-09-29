package own.stu.rpc.thrift.helloWorld;

import org.apache.thrift.TProcessorFactory;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.server.THsHaServer.Args;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TTransportException;
import own.stu.rpc.thrift.generated.PersonService;
import own.stu.rpc.thrift.generated.PersonService.Processor;

public class ThriftServer {

  public static void main(String[] args) throws TTransportException {

    TNonblockingServerSocket socket = new TNonblockingServerSocket(8899);

    THsHaServer.Args args1 = new Args(socket).minWorkerThreads(2).maxWorkerThreads(4);

    PersonService.Processor<PersonServiceImpl> processor = new Processor<>(new PersonServiceImpl());
    args1.protocolFactory(new TCompactProtocol.Factory());
    args1.transportFactory(new TFramedTransport.Factory());
    args1.processorFactory(new TProcessorFactory(processor));

    TServer server = new THsHaServer(args1);
    System.out.println("Thrift server started");

    server.serve();
  }
}
