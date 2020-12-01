package Utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.stream.Collectors;

public class FileUtils {

    private static final String PATH = new File("").getAbsolutePath() + "\\src\\main\\resources\\";

    public static List<String> getLinesFromInput(String fileName) throws FileNotFoundException {
        return getBufferedReaderFromFile(fileName)
                .lines()
                .collect(Collectors.toList());
    }

    public static List<Integer> getIntegersFromInput(String fileName) throws FileNotFoundException {
        return getBufferedReaderFromFile(fileName)
                .lines()
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.toList());
    }

    public static List<Long> getLongsFromInput(String fileName) throws FileNotFoundException {
        return getBufferedReaderFromFile(fileName)
                .lines()
                .mapToLong(Long::parseLong)
                .boxed()
                .collect(Collectors.toList());
    }

    private static BufferedReader getBufferedReaderFromFile(String fileName) throws FileNotFoundException {
        File file = new File(PATH + fileName);
        return new BufferedReader((new FileReader(file)));
    }
}
