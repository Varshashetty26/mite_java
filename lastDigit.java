
import java.util.Scanner;//import package for user import
public class Main()
{
    static void lastDigit(int num1,int num2){  // Function to check if last digit is same
        boolean result=num1%10 == num2%10;
        System.out.print(result);    //if last digit same-->true else-->false
        }
	public static void main(String[] args) {
	    Scanner sc=new Scanner(System.in);
	    int number1=sc.nextInt(); // first number
	    int number2=sc.nextInt(); //second number
	    lastDigit(number1,number2);
		System.out.println("Hello varsha shetty");
	}
}
