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
    /**
     * public static void main(String[] args) throws IOException {
     *  switch(args[1]) {
     *   case "1": new ElementoEstructural1(args[0]);break;
     *   case "2": new ElementoEstructural2(args[0]);break;
     *   case "3": new ElementoEstructural3(args[0]);break;
     *   case "4": new ElementoEstructural4(args[0]);break;
     *   case "5": new ElementoEstructural5(args[0]);break;
     *  }
     *}
     */

}
