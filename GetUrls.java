import java.util.Scanner;
import java.io.*;

public class GetUrls{

    //example: java Main www.google.com https://

    public static void main(String[] args){
    
        try{
            Process skini_sajt = Runtime.getRuntime().exec("wget -O page.html " + args[0]);
            skini_sajt.waitFor();
        
        }catch(Exception e){
            System.out.println("Greska kod skidanja sajta");
            System.exit(1);
        }
    
        StringBuilder output = new StringBuilder("");
        StringBuilder sb = new StringBuilder("");

        try{
            Scanner sc = new Scanner(new BufferedInputStream(new FileInputStream("page.html")));

            while(sc.hasNextLine()){
                sb.setLength(0);
                sb.append(sc.nextLine());
        
                int index = sb.indexOf(args[1]);
                
        
                while(index != -1){
                
                    int i = index;
                    while(i < sb.length() && sb.charAt(i) != ' ' && sb.charAt(i) != '"' && sb.charAt(i) != ';'){
                        output.append(sb.charAt(i));
                        i++;
                    }
                    output.append("\n");
    
                    sb = new StringBuilder(sb.toString().substring(i,sb.length()));
                   
                    index = sb.toString().indexOf(args[1]);
                }
            }
        
        }catch(Exception e){
            System.out.println("Fajl nije nadjen: " + e.getMessage());
            System.exit(1);
        }finally{
            System.out.println("Urls from " + args[0] + " :");
            System.out.print(output.toString());
        }
    }

}
