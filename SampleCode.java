import java.util.*;
import java.lang.*;

public class SampleCode
{
    public static void main(String args[])
    {
        System.out.println("Hello, World!");
        Scanner sc= new Scanner(System.in);
        System.out.println(" Your name :");
        String s = sc.nextLine();
        System.out.println("Your Name is "+s);
        System.out.println("Enter your age : ");
        Integer age = sc.nextInt();
        System.out.println("Your age is  : "+age);
    }
}