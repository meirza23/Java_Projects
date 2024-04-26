import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Main2 {

    public static void main(String[] args) throws IOException {
        // "bocek.txt" dosyasının içeriğini okuyun
        StringBuilder bocekDesenBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader("bocek.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                bocekDesenBuilder.append(line).append(System.lineSeparator());
            }
        }

        // Diziyi bir karakter dizisine dönüştürün
        char[][] bocekDesen = new char[3][6];
        String[] bocekDesenStr = bocekDesenBuilder.toString().split(System.lineSeparator());
        for (int i = 0; i < bocekDesen.length; i++) {
            bocekDesen[i] = bocekDesenStr[i].toCharArray();
        }

        // Arazi matrisini oluşturma
        Arazi arazi = new Arazi("test_arazi.txt");
        char[][] araziMatrisi = arazi.getAraziMatrisi();

        // Arazi matrisinde döngü yaparak böcek sayısını sayın
        int bugCount = 0;
        for (int i = 0; i < araziMatrisi.length - bocekDesen.length + 1; i++) {
            for (int j = 0; j < araziMatrisi[i].length - bocekDesen[0].length + 1; j++) {
                boolean bulundu = true;
                for (int k = 0; k < bocekDesen.length; k++) {
                    for (int l = 0; l < bocekDesen[k].length; l++) {
                        if (araziMatrisi[i + k][j + l] != bocekDesen[k][l]) {
                            bulundu = false;
                            break;
                        }
                    }
                    if (!bulundu) {
                        break;
                    }
                }
                if (bulundu) {
                    bugCount++;
                }
            }
        }

        // Bulunan böcek sayısını yazdırın
        System.out.println("Bulunan böcek sayısı: " + bugCount);// Hocam test_arazi.txt dosyasında bir tane böceğin şekli kayabiliyor dosyayı attığınız zaman 1 eksik sonuç bulursa diye yazıyorum.
    }
}

class Arazi {
    private char[][] araziMatrisi;

    public Arazi(String dosyaAdi) throws IOException {
        // Dosya okuma işlemi
        BufferedReader reader = new BufferedReader(new FileReader("test_arazi.txt"));
        String line = reader.readLine();

        // Matris boyutunu hesaplama
        int satirSayisi = 0;
        int sutunSayisi = line.length();

        while (line != null) {
            satirSayisi++;
            line = reader.readLine();
        }

        reader.close();

        // Matrisi oluşturma
        araziMatrisi = new char[satirSayisi][sutunSayisi];

        // Dosyayı tekrar okuma ve matrisi doldurma
        reader = new BufferedReader(new FileReader(dosyaAdi));
        line = reader.readLine();

        int satirIndex = 0;
        while (line != null) {
            araziMatrisi[satirIndex] = line.toCharArray();
            line = reader.readLine();
            satirIndex++;
        }

        reader.close();
    }

    public char[][] getAraziMatrisi() {
        return araziMatrisi;
    }
}
