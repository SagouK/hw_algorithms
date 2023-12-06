package hw;



public class Main {

    public static void main(String[] args) {
        LeftHandedRedBlackTree tree = new LeftHandedRedBlackTree();
        for (int i = 0; i < 10; i++) {
           tree.add(i);
            System.out.println("------------------------");
            System.out.println("add - " + i);
            System.out.println(tree);
            System.out.println("------------------------");
        }

        System.out.println(tree);

    }

}