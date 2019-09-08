import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by pingao on 2019/9/8.
 */
public class BNDM implements Search {
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
            char pj = p.charAt(j);
            B.put(pj, B.getOrDefault(pj, 0) | (1 << m - 1) >> j);
        }

        int pos = 0, mask = 1 << m - 1;
        List<Integer> idx = new ArrayList<>();
        while (pos <= n - m) {
            int j = m, last = m;
            int D = -1;
            while (D != 0) {
                if (pos + j - 1 < 0) {
                    break;
                }
                D &= B.getOrDefault(text.charAt(pos + j - 1), 0);
                j--;
                if ((D & mask) != 0) {
                    if (j > 0) {
                        last = j;
                    } else {
                        idx.add(pos);
                    }
                }
                D <<= 1;
            }
            pos += last;
        }
        return idx;
    }

    public static void main(String[] args) {
        Utils.pprint("", "", new BNDM());
        Utils.pprint("a", "a", new BNDM());
        Utils.pprint("a", "", new BNDM());
        Utils.pprint("", "a", new BNDM());
        Utils.pprint("a", "ba", new BNDM());
        Utils.pprint("announce", "annual_announce", new BNDM());
        Utils.pprint("ATATA", "AGATACGATATATAC", new BNDM());
    }
}
