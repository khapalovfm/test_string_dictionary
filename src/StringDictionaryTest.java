import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;


public class StringDictionaryTest {

    @Test
    public void exampleTest() throws Exception {
        String s = "сапог сарай арбуз болт бокс биржа";
        StringDictionary dictionary = new StringDictionary(s);
        Map<String, List<String>> testMap = new TreeMap<>();
        testMap.put("б", Arrays.asList("биржа", "бокс", "болт"));
        testMap.put("с", Arrays.asList("сапог", "сарай"));

        assertEquals(testMap.toString(), dictionary.getDictionary());
    }

    @Test(expected = Exception.class)
    public void wrongString() throws Exception {
        String s = "сапог                   сарай";
        StringDictionary dictionary = new StringDictionary(s);
        Map<String, List<String>> testMap = new TreeMap<>();
        testMap.put("б", Arrays.asList("биржа", "бокс", "болт"));
        testMap.put("с", Arrays.asList("сапог", "сарай"));

        assertEquals(testMap.toString(), dictionary.getDictionary());
    }

    @Test
    public void performance() throws Exception {
        String s = "Мой дядя самых честных правил" +
                "Когда не в шутку занемог " +
                "Он уважать себя заставил " +
                "И лучше выдумать не мог " +
                "Его пример другим наука " +
                "Но, боже мой, какая скука " +
                "С больным сидеть и день и ночь " +
                "Не отходя ни шагу прочь " +
                "Какое низкое коварство " +
                "Полуживого забавлять " +
                "Ему подушки поправлять, " +
                "Печально подносить лекарство " +
                "Вздыхать и думать про себя " +
                "Когда же черт возьмет тебя";

        StringDictionary dictionary = new StringDictionary(s);
        assertTrue(!dictionary.getDictionary().isEmpty());

        System.out.println(dictionary.getDictionary());

        StringDictionary dict = new StringDictionary(readFileAsString("/home/phil/stepic_java/stepic_java_webserver/untitled/src/text.txt"));

        System.out.println(dict.getDictionary());

    }

    private String readFileAsString(String filePath) throws IOException {
        StringBuffer fileData = new StringBuffer();
        BufferedReader reader = new BufferedReader(
                new FileReader(filePath));
        char[] buf = new char[1024];
        int numRead=0;
        while((numRead=reader.read(buf)) != -1){
            String readData = String.valueOf(buf, 0, numRead);
            fileData.append(readData);
        }
        reader.close();
        return fileData.toString();
    }




}