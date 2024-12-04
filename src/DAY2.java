import com.sun.tools.javac.Main;

import java.io.*;
import java.util.ArrayList;

public class DAY2 {
    public static void main(String[] args) {

        InputStream inputStream = null;

        try {
            ClassLoader classLoader = Main.class.getClassLoader();
            File file = new File(classLoader.getResource("levels.txt").getFile());
            inputStream = new FileInputStream(file);
            ArrayList<Boolean> isSafeList = readFromInputStream(inputStream);


            System.out.println(isSafeList.stream().filter(value -> value).count());
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

    private static ArrayList<Boolean> readFromInputStream(InputStream inputStream)
    {
        ArrayList<Boolean> isSafeList = new ArrayList<>();
        try  {
            String line;

            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            while ((line = br.readLine()) != null) {
                String[] level = line.split(" ");
                boolean isSafe = false;
                boolean isValidReport = false;

                // is all ascending
                for (int i = 1; i < level.length; i++) {
                    int value = Integer.parseInt(level[i]);
                    int lastValue = Integer.parseInt(level[i - 1]);

                    //Check all increasing
                    if (value > lastValue) {
                        isValidReport = true;
                    } else {
                        isValidReport = false;
                        break;
                    }
                }

                // is all descending
                if (!isValidReport) {
                    for (int j = 1; j < level.length; j++) {
                        int value = Integer.parseInt(level[j]);
                        int lastValue = Integer.parseInt(level[j - 1]);

                        if (value < lastValue) {
                            isValidReport = true;
                        } else {
                            isValidReport = false;
                            break;
                        }
                    }
                }

                if (isValidReport) {
                    for (int k = 1; k < level.length; k++) {
                        int value = Integer.parseInt(level[k]);
                        int lastValue = Integer.parseInt(level[k - 1]);

                        if ((value - lastValue) == 0) {
                            isSafe = false;
                            break;
                        }

                        if (Math.abs(value - lastValue) <= 3) {
                            isSafe = true;
                        } else {
                            isSafe = false;
                            break;
                        }

                    }
                }


                isSafeList.add(isSafe);



            }

        } catch (IOException error) {
            error.printStackTrace();
        }

        return isSafeList;
    }
}

