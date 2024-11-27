package week3;

public class Lamda {
  public static void main(String[] args) {
    
    PrintableFan var = (s) -> s + " hahaha " + s;
    printThing(var);
  }
  
  static void printThing(PrintableFan thing) {
    
    String a = thing.outprint("variable");
    System.out.print(a);
    
  }
}
