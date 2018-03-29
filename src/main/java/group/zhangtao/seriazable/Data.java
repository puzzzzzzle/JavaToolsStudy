package group.zhangtao.seriazable;

import java.io.*;

public class Data implements Serializable {
    private static final long serialVersionUID = -496326689807475L;

    public String getHello() {
        return hello;
    }

    public void setHello(String hello) {
        this.hello = hello;
    }

    private String hello = "hello";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name = "tao";

    @Override
    public String toString() {
        return hello + " " + name;
    }

    public static void main(String[] args) {
        try (
                FileOutputStream fileOutputStream = new FileOutputStream("./TestFile/object.ser", false);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        ) {
            Data data = new Data();
            data.setHello("welcome!");
            objectOutputStream.writeObject(data);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (
                FileInputStream fileInputStream = new FileInputStream("./TestFile/object.ser");
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        ) {
            Data data = (Data) objectInputStream.readObject();
            System.out.println(data.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
