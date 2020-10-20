import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        PGMFile file1 = new PGMFile("Macaco");
        //file1.print();
        file1.load(new File(args[0]));
        file1.save("P2");
        file1.save("P5");
    }

}
