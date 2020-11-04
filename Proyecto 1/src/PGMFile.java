import java.io.*;
import java.util.Scanner;

public class PGMFile {
    private String magicNumber;
    private String comment="";
    private BufferedReader fileBuffered;
    private String line;

    private Scanner ssize;
    private String size;
    private int sizeW;
    private int sizeH;
    private int maxGrayValue;

    //private Scanner actualLine;

    //private AtomicInteger[][] image;
    private MatrixImage image;
    private File file;

    private String name;


    public PGMFile(String name) throws IOException {
        this.name=name;
    }

    public void load(File file) throws IOException {
        fileBuffered= new BufferedReader(new FileReader(file));
        magicNumber = fileBuffered.readLine();    //Primera línea siempre llevará el número mágico
        line = fileBuffered.readLine(); //Segunda línea puede llevar comentario o tamaño

        if (line.startsWith("#")) { //Si la segunda línea comienza con un "#" se entiende que es un comentario
            comment = line;
            size = fileBuffered.readLine(); //String que contiene los dos tamaños
        }

        else { //Si ese no es el caso, entonces se trataba del String de los tamaños
            size = line;
        }

        ssize = new Scanner(size);

        sizeW = ssize.nextInt();
        sizeH = ssize.nextInt();

        maxGrayValue = Integer.parseInt(fileBuffered.readLine());

        //image = new AtomicInteger[sizeH][sizeW];
        image = new MatrixImage(sizeH,sizeW);

        byte[][] im = new byte[sizeH][sizeW];

        int count = 0;
        int b = 0;

        if (magicNumber.equals("P5")){
            FileInputStream fileInputStream = new FileInputStream(file);
            DataInputStream dis = new DataInputStream(fileInputStream);

            int numnewlines = 0;

            if (comment.equals(""))
                numnewlines = 3;
            else
                numnewlines = 4;

            while (numnewlines > 0) {
                char c;
                do {
                    c = (char)(dis.readUnsignedByte());
                } while (c != '\n');
                numnewlines--;
            }

            // read the image data

            image.fillMatrixP5(dis);
        }

        if (magicNumber.equals("P2")){
            image.fillMatrixP2(fileBuffered);

        }
    }

    public void print() {

        //Se imprime la cabecera
        System.out.println(magicNumber);
        if (comment!=""){
            System.out.println(comment);
        }
        System.out.println(size);
        System.out.println(maxGrayValue);

        //Se imprimen los valores
        /*for (int i=0;i<sizeH;i++){
            for(int j=0;j<sizeW;j++){
                //System.out.print(image[i][j] + " ");
                System.out.print(image.getAtomicInt(i,j) + " ");
            }
            System.out.println();
        }*/
        image.printMatrix();


    }

    public void save(String tech_selected) {


        try {
            FileWriter fstream = new FileWriter(name + "_"+tech_selected+"_P2.pgm");
            BufferedWriter out = new BufferedWriter(fstream);

            //Se escribe la cabecera
            if (comment != "") {
                out.write("P2" + "\n" + comment + "\n" + size + "\n" + maxGrayValue + "\n");
            } else {
                out.write("P2" + "\n" + size + "\n" + maxGrayValue + "\n");
            }

            //Se escriben los valores
            for (int i = 0; i < sizeH; i++) {
                for (int j = 0; j < sizeW; j++) {
                    //out.write(Integer.toString(image[i][j].get()));
                    out.write(Integer.toString(image.getAtomicInt(i,j).get()));
                    if (j != sizeW - 1)
                        out.write(" ");
                }
                if (i != sizeH - 1)
                    out.write("\n");
            }
            out.close();
        } catch (Exception e) {
            System.err.println("Error : " + e.getMessage());
        }


    }

    public String getMagicNumber() {
        return magicNumber;
    }

    public String getComment() {
        return comment;
    }

    public int getSizeW() {
        return sizeW;
    }

    public int getSizeH() {
        return sizeH;
    }

    public int getMaxGrayValue() {
        return maxGrayValue;
    }

    public void setMagicNumber(String magicNumber) {
        this.magicNumber = magicNumber;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setSizeW(int sizeW) {
        this.sizeW = sizeW;
    }

    public void setSizeH(int sizeH) {
        this.sizeH = sizeH;
    }

    public void setMaxGrayValue(int maxGrayValue) {
        this.maxGrayValue = maxGrayValue;
    }

    public MatrixImage getImage() {
        return image;
    }

    public void setImage(MatrixImage image) {
        this.image = image;
    }
}
