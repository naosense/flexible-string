import java.util.*;


/**
 * Created by pingao on 2019/9/8.
 */
public class ShiftOr implements Search {

    @Override
    public List<Integer> search(String p, String text) {
        int m = p.length(), n = text.length();
        if (m > 32) {
            throw new IllegalArgumentException("search pattern length must < 32");
        }
        if (m == 0) {
            return Collections.emptyList();
        }
        Map<Character, Integer> B = new HashMap<>();
        for (int j = 0; j < m; j++) {
            char c = p.charAt(j);
            B.put(c, B.getOrDefault(c, -1) & ~(1 << j));
        }

        int D = -1, mask = ~(1 << m - 1);
        List<Integer> idx = new ArrayList<>();
        for (int pos = 0; pos < n; pos++) {
            D = (D << 1) | B.getOrDefault(text.charAt(pos), -1);
            if ((D | mask) == mask) {
                idx.add(pos - m + 1);
            }
        }
        return idx;
    }

    public static void main(String[] args) {
        Utils.pprint("", "", new ShiftOr());
        Utils.pprint("a", "a", new ShiftOr());
        Utils.pprint("a", "", new ShiftOr());
        Utils.pprint("", "a", new ShiftOr());
        Utils.pprint("a", "ba", new ShiftOr());
        Utils.pprint("announce", "annual_announce", new ShiftOr());
        Utils.pprint("ATATA", "AGATACGATATATAC", new ShiftOr());
    }
}
