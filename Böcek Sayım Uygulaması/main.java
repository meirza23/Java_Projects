import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        ArrayList<Bocek> bocekListesi = new ArrayList<>();
        char[][] arazi;

        // Arazı dosyasını oku ve 2D char dizisine aktar
        try {
            Arazi araziObjesi = new Arazi("arazi.txt");
            arazi = araziObjesi.getArazi();
        } catch (IOException e) {
            System.out.println("Arazı dosyası bulunamadı.");
            return;
        }

        // Böcek desen dosyasını oku ve böcekleri listeye ekle
        try {
            Scanner scanner = new Scanner(new File("arazi.txt"));
            int satir = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                int sutun = 0; // sutun değişkenini sıfırla
                for (int i = 0; i < line.length(); i++) {
                    char karakter = line.charAt(i);
                    if (karakter == 'O') {
                        Bocek bocek = new Bocek(karakter, satir, sutun);
                        bocekListesi.add(bocek);
                    }
                    sutun++; // sutun değişkenini artır
                }
                satir++;
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Böcek dosyası bulunamadı.");
            return;
        }



        // Arazide kaç tane böcek var?
        int toplamBocekSayisi = 0;
        for (Bocek bocek : bocekListesi) {
            int satir = bocek.getSatir();
            int sutun = bocek.getSutun();
            if (satir >= arazi.length || sutun >= arazi[0].length) {
                continue;
            }
            if (arazi[satir][sutun] == bocek.getKarakter()) {
                toplamBocekSayisi++;
            }
        }

        System.out.println("Toplam " + (toplamBocekSayisi) + " tane böcek bulundu.");
    }
}
import java.io.BufferedReader;
        import java.io.IOException;
        import java.nio.file.Files;
        import java.nio.file.Path;

public class Arazi {
    private final char[][] arazi;

    public Arazi(String dosyaAdi) throws IOException {
        Path path = Path.of("arazi.txt");
        int satirSayisi = (int) Files.lines(path).count();
        arazi = new char[satirSayisi][];
        int i = 0;
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            while ((line = reader.readLine()) != null) {
                arazi[i++] = line.toCharArray();
            }
        }
    }

    public char[][] getArazi() {
        return arazi;
    }
}

public class Bocek {
    private char karakter;
    private int satir;
    private int sutun;

    public Bocek(char karakter, int satir, int sutun) {
        this.karakter = karakter;
        this.satir = satir;
        this.sutun = sutun;
    }

    public char getKarakter() {
        return karakter;
    }

    public void setKarakter(char karakter) {
        this.karakter = karakter;
    }

    public int getSatir() {
        return satir;
    }

    public void setSatir(int satir) {
        this.satir = satir;
    }

    public int getSutun() {
        return sutun;
    }

    public void setSutun(int sutun) {
        this.sutun = sutun;
    }
}
