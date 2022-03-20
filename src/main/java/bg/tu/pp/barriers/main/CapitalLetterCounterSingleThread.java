package bg.tu.pp.barriers.main;

import bg.tu.pp.barriers.util.file.CsvFileReader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public class CapitalLetterCounterSingleThread implements CapitalLetterCounterConfig {

    public static void main(String[] args) {
        try {
            long start = new Date().getTime();

            CsvFileReader csvFileReader = new CsvFileReader(FILE_PATH);
            processPostsByLine(csvFileReader);

            long end = new Date().getTime();

            System.out.println("Reading took: "+(end - start));
            System.out.println("Memory used: "+Runtime.getRuntime().totalMemory());
        } catch (FileNotFoundException e) {
            System.out.println(FILE_PATH+" does not exist!");
        }
    }

    private static void processPostsByLine(CsvFileReader csvFileReader) {
        int fileLinesSize = 0;
        float postsCapitalLettersPercentage = 0.0f;
        List<String> fileLine;

        try {
            fileLine = csvFileReader.getCsvLine();

            while (fileLine != null) {
                ++fileLinesSize;

                if (fileLine.size() >= 10) {
                    String textColumn = fileLine.get(9);

                    float linePercentage = processLine(textColumn);
                    postsCapitalLettersPercentage += linePercentage;
                } else {
                    System.out.println("Warning: inconsistent line: "+fileLinesSize+"! Content:"+fileLine);
                }

                fileLine = csvFileReader.getCsvLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Common percentage: "+(postsCapitalLettersPercentage/fileLinesSize));
    }

    @SuppressWarnings("unused")
    private static void processPostsByCell(CsvFileReader csvFileReader) {
        //
    }

    private static float processLine(String textColumn) {
        int capitalLetters = 0;
        int textLength = textColumn.length();

        if (textLength == 0) {
            return 0.0f;
        }

        for (int i=0; i<textLength; ++i) {
            char c = textColumn.charAt(i);

            if (c >= 'A' && c <= 'Z') {
                ++capitalLetters;
            }
        }

        return (float) ((capitalLetters*100) / textLength);
    }

}
