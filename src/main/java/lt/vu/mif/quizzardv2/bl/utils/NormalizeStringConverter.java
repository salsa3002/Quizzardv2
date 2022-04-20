package lt.vu.mif.quizzardv2.bl.utils;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;

import java.text.Normalizer;
import java.util.Locale;

@UtilityClass
public class NormalizeStringConverter {

    public String normalize(String input) {
        if (StringUtils.isBlank(input)) {
            return null;
        }

        input = removeUnnecessaryWhitespaces(input.replaceAll("[^\\p{IsAlphabetic}\\p{IsDigit}\\p{Space}]", "").toLowerCase(Locale.ROOT));

        return Normalizer
                .normalize(input, Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "");
    }

    public String normalizeEmail(String input) {
        if (StringUtils.isBlank(input)) {
            return null;
        }

        input = removeUnnecessaryWhitespaces(input.replaceAll("[^\\p{IsAlphabetic}\\p{IsDigit}\\p{Space}.@]", "").toLowerCase(Locale.ROOT));

        return Normalizer
                .normalize(input, Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "");
    }

    public String removeUnnecessaryWhitespaces(String word) {
        String stripedWord = word.strip();
        return stripedWord.replaceAll("\\s+", " ");
    }
}