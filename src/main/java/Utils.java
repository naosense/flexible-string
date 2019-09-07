import java.util.List;


/**
 * Created by pingao on 2019/9/7.
 */
public class Utils {
    public static void pprint(String p, String text, List<Integer> idx) {
        for (int i = 0; i < idx.size(); i++) {
            if (i == 0) {
                System.out.println("0\t" + text);
            }
            System.out.print((i + 1) + "\t");
            for (int j = 0; j < idx.get(i); j++) {
                System.out.print(" ");
            }
            System.out.println(p);
            System.out.println();
        }
    }
}
