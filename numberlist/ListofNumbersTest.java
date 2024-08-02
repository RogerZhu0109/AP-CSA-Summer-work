public class ListofNumbersTest {
    public static void main(String[] args) {
        ListofNumbers list = new ListofNumbers(20);
        System.out.println("max:" + list.max());
        System.out.println("Min:" + list.min());
        list.printMode();
        list.printLs();
    }
}
