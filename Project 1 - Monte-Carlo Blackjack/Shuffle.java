
/**
* Author: Daniel Yu
* 
* To practice creating and manipulating Arraylists
*/
import java.util.ArrayList;
import java.util.Random;

public class Shuffle {
    public static void main(String[] args) {
        ArrayList<Integer> arr0 = new ArrayList<Integer>();
        ArrayList<Integer> arr1 = new ArrayList<Integer>();
        ArrayList<Integer> arr2 = arr0;
        Random r = new Random();
        for (int a = 0; a < 10; a++) {
            int temp = r.nextInt(100);
            System.out.println(temp);
            arr0.add(temp);
            arr1.add(temp);
        }
        for (int a = 0; a < 10; a++) {
            System.out.println(arr0.get(a));
        }
        System.out.println(arr0);
        System.out.println(arr1);
        System.out.println(arr2);
        System.out.println("arr0 == arr1: " + (arr0 == arr1) + "\narr1 == arr2: " + (arr1 == arr2) + "\narr2 == arr0: "
                + (arr2 == arr0));
        System.out.println("arr0.equals(arr1): " + (arr0.equals(arr1)) + "\narr1.equals(arr2): " + (arr1.equals(arr2))
                + "\narr2.equals(arr0): " + (arr2.equals(arr0)));
        for (int a = 0; a <10; a++) {
            int temp = r.nextInt(arr0.size());
            int num = arr0.get(temp);
            arr0.remove(temp);
            System.out.println(num + " " + arr0);
           
        }

        for (int i = arr1.size() - 1; i > 0; i--) {
            int j = (int) (Math.random() * i);
            int temp = arr1.get(i);
            arr1.set(i, arr1.get(j));
            arr1.set(j, temp);
        }
    }
}
