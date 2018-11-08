import java.util.LinkedHashSet;
import java.util.List;
import java.util.function.BiConsumer;

public interface IStringDictionary {

    BiConsumer<Character, LinkedHashSet<String>> addToDictionary();
    void groupByFirstSymbol() throws Exception;
}
