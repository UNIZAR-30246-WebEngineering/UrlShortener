package urlshortener.demo.utils;

public class StringChecker {

    private StringChecker() {
        throw new IllegalStateException("Utility class");
    }

    public static int checkString2Int(String string){
        
        if (string == null){
            return 500;
        }

        int num = Integer.parseInt(string);

        if(num < 30){
            return 500;
        }

        if(num > 2048){
            return 500;
        }

        return num;
    }

}