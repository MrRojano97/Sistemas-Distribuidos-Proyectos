import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        
        PGMFile file1 = new PGMFile("baboonv2");
        //file1.print();
        //file1.load(new File(args[0]));
        //file1.load(new File("src//imgNueva.pgm"));
        file1.load(new File("src//baboon.pgm"));
        //file1.save("P2");
        //file1.save("P5");

        Scanner in = new Scanner(System.in);
        System.out.println("1= erosion, 2= dilatacion. Ingrese opcion: ");
        int techindex = in.nextInt();
        System.out.println("0=BASE, 1= struct1, 2= struct2, .. , 5=struct5. Ingrese opcion: ");
        int struct = in.nextInt();
        System.out.println("Ingrese numero de hilos: ");
        int numThreads = in.nextInt();
        String tech="";
        if(techindex == 1)
            tech = "erosion";
        else if(techindex == 2)
            tech = "dilatation";

        //MatrixImage example = new MatrixImage();
        //example.printMatrix();
       // file1.getImage().printMatrix();
        ImageProcessor control = new ImageProcessor(tech,struct,file1.getImage(), numThreads);
        //ImageProcessor control = new ImageProcessor(tech,struct,example);
        //System.out.println("------------------ Procesamiento Terminado --------------------");
        //example.printMatrix();
        //file1.getImage().printMatrix();

        file1.save(tech);
        //System.out.println("---------------------------------------------------------------");

    }

}
