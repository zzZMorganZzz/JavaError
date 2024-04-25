import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Console;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class consoleApp {

    public static String FName;
    public static String Name;
    public static String LName;
    public static String Num;
    public static String Gender;


    public static void checkParam (String inpuString) throws Exception
    {
        String[] param =inpuString.split(" ");

        if (param.length!=5)
        {
            throw new Exception("Не правильное кол-во параметров");
        }
        FName = param[0].toString();
        Name = param[1].toString();
        LName = param[2].toString();
        if(param[3].length()!=11)
        {
            throw new Exception("Не правильный номер телефона");
        }
        Num = param[3].toString();
        Gender =param[4];

    }

    public static void main(String[] args)
        throws IOException
    {
        try 
        {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String consoleParam = reader.readLine();

        checkParam(consoleParam);
        
        File folder = new File(new java.io.File(".").getCanonicalPath());
        File[] files = folder.listFiles();
        boolean isExist = false;
        for (File file : files) 
        {
            if (file.getName().equals(String.format("%s.txt", FName))) 
            {
                isExist = true;
            }
        }
        
        String txt = String.format("%s %s %s \n", FName +" "+ Name +" "+ LName, Num, Gender);
        if (isExist)
        {
            Files.write(Paths.get(String.format("%s.txt", FName)), txt.getBytes(), StandardOpenOption.APPEND);
        }
        else
        {
            Writer writer = null;
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(String.format("%s.txt", FName)), "utf-8"));
            writer.write(txt);
            writer.close();       
        }
         

        } 
        catch (NumberFormatException e)
        {
            System.out.println("Не правильный формат телефона");    

        }

        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
    }

    
}