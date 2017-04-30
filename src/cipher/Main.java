package cipher;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        String text = "помехоустойчивое кодирование - это кодирование с возсожностью восстановления потерянных или ошибочно принятых данных";
        int[] key = {2, 3, 10, 4, 1, 5, 6, 7, 8, 11, 15, 14, 12, 13, 9, 0};
        encrypt(text, key);
    }

    public static void encrypt(String text, int[] key) {
        TreeMap<Character, Integer> alphabet = new TreeMap<>();
        try (FileInputStream fileInputStream = new FileInputStream("text.txt"); BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream)) {
            Scanner scanner = new Scanner(bufferedInputStream);
            while (scanner.hasNext()) {
                alphabet.put(scanner.next().charAt(0), scanner.nextInt());
            }
            System.out.println("Зашифрованное сообщение: ");
            for (int i = 0; i < text.length(); i++) {
                if (Character.isAlphabetic(text.charAt(i)) && alphabet.containsKey(text.charAt(i))) {
                    System.out.print(String.format("%s ",
                            Integer.toBinaryString(Integer.parseInt(Integer.toString(alphabet.get(text.charAt(i))), 2)^Integer.parseInt(Integer.toBinaryString(key[i % key.length]), 2))));
                }
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
