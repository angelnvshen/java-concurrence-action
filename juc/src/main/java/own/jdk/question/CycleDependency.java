package own.jdk.question;

public class CycleDependency {


  class A {

    private B b;

    A(B b) {
      this.b = b;
    }
  }

  class B {

    private C c;

    B(C c) {
      this.c = c;
    }
  }

  class C {

    private A a;

    C(A a) {
      this.a = a;
    }
  }

}
