import java.util.Scanner;
import java.io.*;
class River implements Serializable {
    String name;
    String location;
    int length;
}
public class Main2 {
    public static void main(String[] args) throws IOException, ClassNotFoundException{
        Scanner sc=new Scanner(System.in,"cp1251");
        File f=new File("C:\\Users\\User\\Desktop\\ПИЭ2курс3с\\аип\\lab_7\\лаб7\\River_2");
        FileOutputStream fos = new FileOutputStream(f);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        System.out.println("Введите количество рек = ");
        int count = sc.nextInt();
        River river = new River();
        for (int i = 0; i < count; i++) {
            System.out.print("Введите название реки " + (i + 1) + " = ");
            river.name = sc.next();
            System.out.print("Введите расположение реки " + (i + 1) + " = ");
            river.location = sc.next();
            System.out.print("Введите длину реки " + (i + 1) + " = ");
            river.length = sc.nextInt();
            oos.writeObject(river);
        }
        oos.flush();
        oos.close();
        int max = river.length;
        int min = river.length;
        FileInputStream fis = new FileInputStream(f);
        ObjectInputStream oin = new ObjectInputStream(fis);
        for (int i = 0; i < count; i++){
            river = (River) oin.readObject();
            if (max < river.length){
                max = river.length;
            }
            if(min > river.length){
                min = river.length;
            }
        }
        System.out.println("Название реки - " + river.name + ", расположение реки - " + river.location +  ", длина реки - " + max);
        System.out.println("Название реки - " + river.name + ", расположение реки - " + river.location +  ", длина реки - " + min);
        oos.close();
    }
}