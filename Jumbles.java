/*
Jumbles Project v4
CS 0401
Hoffman
Andrew Truelove
*/

import java.io.*;
import java.util.*;

public class Jumbles
{
    /**
     * @param args
     * @throws Exception
     */
    public static void main(String [] args) throws Exception{
        long startTime = System.nanoTime();
        if(args.length < 2)
        {
            System.out.println("java Jumbles <dictionary file> <jumbles file> ");
            System.exit(0);
        }

        ArrayList<String> jumbles = new ArrayList<String>();
        ArrayList<String> dWord = new ArrayList<String>();
        BufferedReader dFile = new BufferedReader(new FileReader(args[0]));
        BufferedReader jFile = new BufferedReader(new FileReader(args[1]));
        int longestJumble = 0;

        while(jFile.ready())
            jumbles.add(jFile.readLine());
        jFile.close();

        Collections.sort(jumbles);

        for(String word : jumbles)
            if(word.length()>longestJumble)
                longestJumble = word.length();

        while(dFile.ready())
        {
            String dictWord = dFile.readLine();
            if(dictWord.length() <= longestJumble)
                dWord.add(dictWord);
        }
        dFile.close();

        for(String jumble : jumbles)
        {
            ArrayList<String> results = new ArrayList<String>();
            for(String word : dWord)
                if(word.length() == jumble.length() && canon(word).equals(canon(jumble)))
                    results.add(word);

            String line = jumble + " ";
            Collections.sort(results);
            for(String word : results)
                line+= word + " ";
            System.out.println(line);
        }
        long endTime = System.nanoTime();
        long timeTotal = endTime - startTime;
        System.out.println("Program took 0."+(timeTotal/1000000)+"s");
    }
   static String canon (String word)
   {
       char [] letters = word.toCharArray();
       Arrays.sort(letters);
       return new String(letters);
   }
}
