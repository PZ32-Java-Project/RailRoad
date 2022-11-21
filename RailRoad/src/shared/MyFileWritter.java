package shared;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class MyFileWritter {
    public static void Write(String str)
    {
        try(FileWriter writer = new FileWriter("Log.txt", true))
        {
            LocalDateTime time = LocalDateTime.now();
            writer.write(str + " "+time+"\n");
            writer.flush();
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }
}
