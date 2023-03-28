import java.io.*;
import java.util.*;

public class Main
{
    
    public static void main(String[] args) throws Exception
    {
        ArrayList<String> dizi = new ArrayList<String>();
        BufferedReader br = new BufferedReader(new FileReader(args[0]));
        BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"));
    

        String inputFile  = args[0];
        //String inputFile  = "src/input.txt";
        File file = new File(inputFile);
         
        Scanner sc = new Scanner(file);
        
        while (br.hasNextLine())
        {   
            dizi.add(br.nextLine());
        }
        
        
        
        for (int i = 0; i < dizi.size();i++) 
        {
            switch (dizi.get(i)) 
            {
                case "Armstrong numbers up to:":
                    writeToFile("Armstrong numbers up to: " + dizi.get(i+1)+"\n");
                    int sayi = Integer.parseInt(dizi.get(i+1));
                    for (int j = 1; j < sayi; j++) 
                    {
                        int basamak = 0;
                        int kalan = 0;
                        int toplam = 0;
                        int sayi2 = j;
                        while(sayi2 != 0) 
                        {
                            sayi2 = sayi2 / 10;
                            basamak++;
                        }
                        sayi2 = j;
                        while(sayi2 != 0) 
                        {
                            kalan = sayi2 % 10;
                            toplam = toplam + (int)Math.pow(kalan, basamak);
                            sayi2 = sayi2 / 10;
                        }
                        if(toplam == j) {
                            writeToFile(j + " ");
                        }
                    }
                    writeToFile("\n");
                    writeToFile("\n");
                    break;
                
                
                
                
                case "Emirp numbers up to:" :
                    int sayi3 = Integer.parseInt(dizi.get(i+1));
                    writeToFile("Emirp numbers up to: " + sayi3 + "\n");    
                    if (sayi3>0)
                        {
                            for (int a =10 ; a<=sayi3;a++){

                                if (bolenler(a) == a + 1)
                                {
                                    int tersi = 0;
                                    int b = a;
                                    while(b != 0) 
                                    {
                                        int kalan = b % 10;
                                        tersi = tersi * 10 + kalan;
                                        b = b / 10;  
                                    }
                                    if (bolenler(tersi) == tersi + 1)
                                    {
                                        writeToFile(a + " ");
                    
                                    } 
                                }   
                            }
                        }
                        writeToFile("\n");
                        writeToFile("\n");
                            break;
                case "Abundant numbers up to:":
                    
                    int sayi4 = Integer.parseInt(dizi.get(i+1));
                    writeToFile("Abundant numbers up to: " + sayi4+"\n");
                    for(int y = 0; y < Abundant(sayi4).size(); y++) {
                        writeToFile((Abundant(sayi4).get(y)).toString() + " ");
                    }
                    writeToFile("\n");
                    writeToFile("\n");
                   
                    break;

                case "Descending order sorting:" :
                    
                    writeToFile("Descending order sorting:\n");
                    ArrayList<Integer> sayilar = new ArrayList<Integer>(sayiBulucu(i, dizi));
                    writeToFile(sayilar.get(0) + "\n");
                    for (int k = 1; k < sayilar.size(); k++) 
                    {
                        int yerlestirilecek_deger = sayilar.get(k); // yeni elemanı al
                        int j = k - 1;

                        //elemanı yerleştir
                        while (j >= 0 && sayilar.get(j) < yerlestirilecek_deger) {
                            sayilar.set(j + 1, sayilar.get(j));
                            j--;
                        }

                        sayilar.set(j + 1, yerlestirilecek_deger); // yeni elemanı yerleştir

                      //yazdırma kısmı
                        for (int z = 0; z <=k; z++) {
                            writeToFile(sayilar.get(z) + " ");
                        }
                        writeToFile("\n");
                    }
                    
                        writeToFile("\n");
                        break;
                    
                        case "Ascending order sorting:" :
                            ArrayList<Integer> liste = new ArrayList<Integer>(Arrays.asList(1, 3, 5, 2));
                            writeToFile("Ascending order sorting:\n");
                            writeToFile(liste.get(0)+"\n");

                           
                            for (int t = 1; t < liste.size(); t++) 
                            {
                                
                                int yerlestirilecek_deger = liste.get(t); // yeni eleman al
                                int r = t - 1;
                    
                                
                                while (r >= 0 && liste.get(r) < yerlestirilecek_deger) {
                                    liste.set(r + 1, liste.get(r));
                                    r--;
                                }
                    
                                liste.set(r + 1, yerlestirilecek_deger); // elemanı yerleştir
                    
                                //yazdır
                                for (int k = t; k >= 0; k--) {
                                    writeToFile(liste.get(k) + " ");
                                }
                                writeToFile("\n");
                            }
                            writeToFile("\n");
                        break;
                        case "Exit" : 
                            writeToFile("Finished...");
                            System.exit(0);
                        break;
                }      
        }   

  


       
        br.close();
    }

    static boolean sayi_kontrol(String strNum)
     {
        try 
        {
            Double.parseDouble(strNum);
        } 
        catch (NumberFormatException nfe) 
        {
            return false;
        }
        return true;
    }
    static int bolenler (int sayi)
    {
        
        int toplam = 0;
        for (int i = 1; i < sayi+1; i++) 
        {
            if (sayi%i==0)
            {
                toplam += i;
            }
        }
        return toplam;
    }
    static ArrayList<Integer> Abundant(int sayi)
    {
        ArrayList<Integer> sayilarin_listesi = new ArrayList<Integer>();
        for (int a = 1; a <= sayi; a++) 
        {
            if (bolenler(a) > 2 *a)
            {
                sayilarin_listesi.add(a);
                
             }
        }
        return sayilarin_listesi;
    }
    static ArrayList<Integer> sayiBulucu(int satirIndex,ArrayList<String> dizi)
    {
        ArrayList<Integer> sayilar = new ArrayList<Integer>();
        while (dizi.get(satirIndex+1) !="-1" && sayi_kontrol(dizi.get(satirIndex+1)))
        {
            sayilar.add(Integer.parseInt(dizi.get(satirIndex+1)));
            satirIndex++;
            
        }
        sayilar.remove(sayilar.size()-1);
        return sayilar;
    }
    public static void writeToFile(String text) {
        try {
            bw.write(text);
            bw.close();
        } catch (Exception e) {
            writeToFile("HATA!!!!! " + e.getMessage());
        }
    }

}
