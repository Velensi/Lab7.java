//Метод RandomAccessFile отличается от других методов работы с файлами тем,
// что позволяет выполнять произвольный доступ к данным внутри файла
//rw-режиме чтения и записи (read-write)
//aaa111111111111111111111111
//aaaaaaaaa111111111111111111
import java.io.*;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        try {
            File folder = new File("C:\\Users\\User\\Desktop\\ПИЭ2курс3с\\аип\\lab_7\\лаб7");
            if (!folder.exists())
                folder.mkdir();

            File f1 = new File("C:\\Users\\User\\Desktop\\ПИЭ2курс3с\\аип\\lab_7\\лаб7\\River_1.txt");
            if (f1.exists()) f1.delete();
            f1.createNewFile();

            RandomAccessFile rf = new RandomAccessFile(f1,"rw");
            long fSize = rf.length();
            rf.seek(0);
            Scanner sc = new Scanner(System.in);
            System.out.print("Введите количество рек для записи в файл" + " => ");
            int kol = sc.nextInt();
            sc.nextLine();

            String name, location;
            int len;
            int max = 0;
            int min = 9999999;
            for (int i = 0; i < kol; i++) {
                System.out.print("Введите название " + (i + 1) + " реки => ");
                name = sc.next();
                rf.seek(rf.length());
                rf.writeUTF(name);
                for (int j = 0; j < 20 - name.length(); j++)
                    rf.writeByte(1);
                System.out.print("Введите его расположение => ");
                location = sc.next();
                rf.writeUTF(location);
                for (int j = 0; j < 20 - location.length(); j++)
                    rf.writeByte(1);
                System.out.print("Введите его длину => ");
                len = sc.nextInt();
                if (len > max){
                    max = len;
                }
                if (len < min){
                    min = len;
                }
                sc.nextLine();
                rf.writeInt(len);
            }

            rf.seek(0);
            System.out.println("Информация о реках");
            System.out.println("Название \t\t Расположение \t\t Длина");
            for (int i = 0; i < kol; i++) {
                name = rf.readUTF();
                for (int j = 0; j < 20 - name.length(); j++)
                    rf.readByte();
                location = rf.readUTF();
                for (int j = 0; j < 20 - location.length(); j++)
                    rf.readByte();
                len = rf.readInt();
                if (len == max){
                    System.out.println(name + "\t\t\t" + location + "\t\t\t" + len);
                }
                if (len == min) {
                    System.out.println(name + "\t\t\t" + location + "\t\t\t" + len);
                }
            }
            rf.close();
        } catch (IOException e) {
            System.out.println("End of file " + e);
        }
    }
}