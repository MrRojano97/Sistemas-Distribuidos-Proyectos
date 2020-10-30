import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        
        //PGMFile file1 = new PGMFile("MacacoRemake");
        //file1.print();
        //file1.load(new File(args[0]));
        //file1.load(new File("src//Macaco_outP2.pgm"));
        //file1.save("P2");
        //file1.save("P5");

        Scanner in = new Scanner(System.in);
        System.out.println("1= erosion, 2= dilatacion. Ingrese opcion: ");
        int techindex = in.nextInt();
        System.out.println("1= struct1, 2= struct2, .. , 5=struct5. Ingrese opcion: ");
        int struct = in.nextInt();
        String tech="";
        if(techindex == 1)
            tech = "erosion";
        else if(techindex == 2)
            tech = "dilatation";
        MatrixImage example = new MatrixImage();
        example.printMatrix();
        //ImageProcessor control = new ImageProcessor(tech,struct,file1.getImage());
        ImageProcessor control = new ImageProcessor(tech,struct,example);
        System.out.println("------------------ Procesamiento Terminado --------------------");
        example.printMatrix();
        System.out.println("---------------------------------------------------------------");
    }

}
