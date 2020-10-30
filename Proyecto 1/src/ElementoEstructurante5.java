import java.io.*;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class ElementoEstructurante5 {
    private String magicNumber;
    private String comment="";
    private BufferedReader fileBuffered;
    private String line;

    private Scanner ssize;
    private String size;
    private int sizeW;
    private int sizeH;
    private int maxGrayValue;

    private Scanner actualLine;

    private AtomicInteger[][] image;
    private File file;

    private String name;

    public ElementoEstructurante5(String name) throws IOException {
        this.name = name;
        erosion();
        dilatacion();
    }

    public void erosion() {
        for(i=1; i<fila-1; i++){
            for(j=1; j<colu-1; j++){
                int min =255;
                int k[5];
                k[0] = dibu[i+1][j+1];
                k[1] = dibu[i-1][j-1];
                k[2] = dibu[i][j];
                k[3] = dibu[i-1][j+1];
                k[4] = dibu[i+1][j-1];
                int l;
                for(l=0;l<5;l++){
                    if(k[l]<min){
                        min = k[l];
                    }
                }
                otra[i][j]=min;
            }
        }
    }

    public void dilatacion() {
        for(i=1; i<fila-1; i++){
            for(j=1; j<colu-1; j++){
                int max =0;
                int k[5];
                k[0] = dibu[i+1][j+1];
                k[1] = dibu[i-1][j-1];
                k[2] = dibu[i][j];
                k[3] = dibu[i-1][j+1];
                k[4] = dibu[i+1][j-1];
                int l;
                for(l=0;l<5;l++){
                    if(k[l]>max){
                        max = k[l];
                    }
                }
                otra[i][j]=max;
            }
        }

    }
}