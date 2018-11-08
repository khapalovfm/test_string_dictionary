import java.util.*;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

public class StringDictionary implements IStringDictionary {

    private String initialText;
    private Map<String, Set<String>> dict = new TreeMap<>();

    public StringDictionary(String str) {
        this.initialText = str;
    }

    @Override
    public BiConsumer<Character, LinkedHashSet<String>> addToDictionary() {
     return  (str, list) -> {
            String key = str.toString();
            LinkedHashSet<String> wordsStartedWithKey = list
                    .stream()
                    .filter(e -> e.startsWith(key))
                    .sorted((Comparator.comparing(String::length)
                            .reversed()
                            .thenComparing(String::compareTo)))
                    .collect(Collectors.toCollection(LinkedHashSet::new));

            if (wordsStartedWithKey.size() > 1) {
                dict.put(key, wordsStartedWithKey);
            }
        };
    }
    @Override
    public void groupByFirstSymbol() throws Exception {
        initialText = initialText.replaceAll("[^а-яА-Я\\s]", "");
        LinkedHashSet<String> splitArray = Arrays.stream(initialText.split(" ")).map(String::toLowerCase).collect(Collectors.toCollection(LinkedHashSet::new));
        if (splitArray.contains("")) {
            throw new Exception("String must split by one space");
        }
        Set<Character> keys = splitArray.stream().map(e -> e.charAt(0)).collect(Collectors.toSet());
        keys.forEach(e -> addToDictionary().accept(e, splitArray));
    }

    public String getDictionary() throws Exception {
        groupByFirstSymbol();
        return dict.toString();
    }
}
