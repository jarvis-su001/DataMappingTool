package eccok;

import eccok.utils.FileUtils;

public class MainTest {

    public static void main(String[] args) {
        String file ="D:/git/DataMappingTool/src/main/resources/dataMapping.properties";
        FileUtils.removeDuplicateLines(file);

    }

}
