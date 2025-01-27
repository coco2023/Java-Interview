public class Singleton {
          private static Singleton instance;

          // private constructure to prevent instantiation
          private Singleton() {
          }

          // Thread-safe lazy initialization
          public static synchronized Singleton getInstance() {
                    if (instance == null) {
                              instance = new Singleton();
                    }
                    return instance;
          }

          public void showMessage() {
                    System.out.println("Singleton instance invoked");
          }

          public static void main(String[] args) {
                    Singleton singleton = new Singleton();
                    singleton.showMessage();
          }
}
