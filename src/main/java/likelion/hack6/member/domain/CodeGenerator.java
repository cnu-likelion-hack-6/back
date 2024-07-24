package likelion.hack6.member.domain;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class CodeGenerator {

    private final String[] pwdChars = new String[]{
            "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
            "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z",
            "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m",
            "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"
    };

    public String generate(int length) {
        int begin = 0;
        int end = pwdChars.length;
        return ThreadLocalRandom.current()
                .ints(begin, end)
                .limit(length)
                .mapToObj(it -> pwdChars[it])
                .collect(Collectors.joining());
    }
}
