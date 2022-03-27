package hello.proxy;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.junit.jupiter.api.Test;

public class JavaTest {

    @ToString
    @Getter
    @Setter
    static class Parent{
        String name = "parent";
        int age = 30;

        public void printName(){
            System.out.println("name = " + name);
        }
    }

    @ToString(callSuper = true)
    @Getter
    @Setter
    static class Child extends Parent{
        String childName = "child";
        String name = "child";
        int age = 10;
    }

    @Test
    void testJava(){
        Parent parent = new Parent();
        parent.printName();

        Child child = new Child();
        child.printName();

        Parent childparent = new Child();
        childparent.printName();

        System.out.println("=======stop=========");
    }

}
