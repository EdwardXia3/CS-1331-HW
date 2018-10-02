public class ArrayFun {

    public static int[] a1 = {1,2,3};

    public static void printA1() {
        ///{1,2,3}
        for(int i = 0; i < a1.length; i++) {
            System.out.print(a1[i]);

            if (i < (a1.length - 1)) {
                System.out.print(",");
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        printA1();

    }
}