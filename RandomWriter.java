/*
*
* srhodes17@georgefox.edu
*
* Program 11
*
* 2018-12-02
*
* Files used: result and PrideAndPrejudice.txt
*
* prefix length = 5
*
* */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.Buffer;
import java.util.*;


/**
 * Description/Purpose - Hashmap data structure for random writer
 *
 * @param <E> - Element value
 */

public class RandomWriter<E>
{
    private HashMap<String, ArrayList<Character>> map = new HashMap();


    /**
     * Description/Purpose - Hashmap contructor
     *
     */

    public RandomWriter()
    {
        map = new HashMap();
    }


    /**
     * Description/Purpose - check for errors
     *
     * @return the validity of error checking
     */
    public boolean errorCheck()
    {
        if (map.size() == 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     *Description/Purpose - output generation of values
     *
     * @param outputLength - length of the output integer
     * @return value outputted
     */

    public String genOutput(int outputLength)
    {
        Random rand = new Random();

        int num = rand.nextInt(map.size());

        Set<String> keys = map.keySet();

        String[] keyArray = keys.toArray(new String[keys.size()]);

        String prefix = keyArray[num];

        StringBuffer result = new StringBuffer();

        while (result.length() < outputLength)
        {
            ArrayList<Character> list = map.get(prefix);

            if (list == null)
            {
                break;
            }
            num = rand.nextInt(list.size());

            result.append(list.get(num));

            prefix = prefix.substring(1) + list.get(num);
        }

        return result.toString();
    }



    /**
     *Description/Purpose - prefix processed into a file
     *
     * @param prefixLength - prefix to be processed
     * @param inputFile - Files: result or PrideAndPrejudice.txt used to process prefixlength
     */

    public void processFile(int prefixLength, String inputFile) {
        try {

            File file = new File(inputFile);

            FileReader fileReader = new FileReader(file);

            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;

            StringBuffer stringBuffer = new StringBuffer();

            while ((line = bufferedReader.readLine()) != null)
            {

                stringBuffer.append(line);

                stringBuffer.append(" ");
            }

            String document = stringBuffer.toString().trim();

            String prefix;

            Character after;

            for (int i = 0; i < document.length() - prefixLength; i++)
            {
                prefix = document.substring(i, i + prefixLength);

                after = document.charAt(i + prefixLength);

                if (map.containsKey(prefix))
                {
                    ArrayList<Character> list = map.get(prefix);

                    list.add(after);
                }
                else
                {
                    ArrayList<Character> list = new ArrayList<>();

                    list.add(after);

                    map.put(prefix, list);
                }
            }

            fileReader.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Description/Purpose - Display keys and values
     *
     */

    public void printMap()
    {
        Set<String> keys = map.keySet();

        for (String key : keys)
        {

            ArrayList<Character> values = map.get(key);

            System.out.println(key + ": " + values);

        }
    }

    public static void main(String[] args)
    {

        if (args.length < 3)
        {
            System.out.println("RandomWriter <prefix_length> <output_length> <input.txt>[<input2.txt>[...]]");

            System.exit(1);
        }

        int prefixLength = 0;

        int outputLength = 0;

        try
        {

            prefixLength = Integer.parseInt(args[0]);

            outputLength = Integer.parseInt(args[1]);

        }
        catch (NumberFormatException nfe)
        {
            System.out.println("Please change either the prefix length or output length to a valid integer");
        }


        RandomWriter rw = new RandomWriter();

        for (int i = 2; i < args.length; i++)
        {
            rw.processFile(prefixLength, args[i]);
        }

        //rw.printMap();
        if (rw.errorCheck())
        {
            System.out.println("This is the errorCheck: ");

            System.exit(2);
        }

        String output = rw.genOutput(outputLength);

        if (output.length() < outputLength)
        {
            System.out.println("Not enough output length ");

            System.exit(2);
        }

        System.out.println(output);

        System.exit(0);
    }
}








