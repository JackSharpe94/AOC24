import com.sun.tools.javac.Main;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class DAY1STAR2 {
    public static void main(String[] args) {

        InputStream inputStream = null;

        try {
            ClassLoader classLoader = Main.class.getClassLoader();
            File file = new File(classLoader.getResource("distances.txt").getFile());
            inputStream = new FileInputStream(file);
            ArrayList<ArrayList<Long>> allData = readFromInputStream(inputStream);
            Collections.sort(allData.get(0));
            Collections.sort(allData.get(1));

            Long total = 0L;
            for (int i = 0; i < allData.get(0).size(); i++) {
                int count = 0;
                for (int j = 0; j < allData.get(1).size(); j++) {
                    if (Objects.equals(allData.get(1).get(j), allData.get(0).get(i))) {
                        count++;
                    }
                }
                total = total + allData.get(0).get(i) * count;
            }


            System.out.println(total);
        } catch (FileNotFoundException error) {
            error.printStackTrace();
        }
        finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }

    private static ArrayList<ArrayList<Long>> readFromInputStream(InputStream inputStream)
    {

        ArrayList<Long> firstList = new ArrayList<>();
        ArrayList<Long> secondList = new ArrayList<>();
        ArrayList<ArrayList<Long>> lists = new ArrayList<>();
        try  {
            String line;

            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            while ((line = br.readLine()) != null) {
                String[] splitData = line.split("   ");
                firstList.add(Long.parseLong(splitData[0]));
                secondList.add(Long.parseLong(splitData[1]));


            }

        } catch (IOException error) {
            error.printStackTrace();
        }
        lists.add(firstList);
        lists.add(secondList);
        return lists;
    }
}