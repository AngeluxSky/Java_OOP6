package org.example;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

class Order {
    private String clientName;
    private String product;
    private int qnt;
    private int price;

    public Order(String clientName, String product, int qnt, int price) {
        this.clientName = clientName;
        this.product = product;
        this.qnt = qnt;
        this.price = price;
    }
    public String getClientName() {
        return clientName;
    }
    public String getProduct() {
        return product;
    }
    public int getQnt() {
        return qnt;
    }
    public double getPrice() {
        return price;
    }
}
class OrderWriter {
    private String fileName;
    public OrderWriter(String fileName) {
        this.fileName = fileName;
    }

    public void saveToJson(Order order) {
        try (FileWriter writer = new FileWriter(fileName, false)) {
            writer.write("{\n");
            writer.write("\"clientName\":\"" + order.getClientName() + "\",\n");
            writer.write("\"product\":\"" + order.getProduct() + "\",\n");
            writer.write("\"qnt\":" + order.getQnt() + ",\n");
            writer.write("\"price\":" + order.getPrice() + "\n");
            writer.write("}\n");
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}

class ConsoleInput {
    private Scanner scanner;
    public ConsoleInput() {
        scanner = new Scanner(System.in);
    }

    public String prompt(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }

    public int promptInt(String message) {
        System.out.print(message);
        return Integer.parseInt(scanner.nextLine());
    }
}
public class Main {
    public static void main(String[] args) {
        System.out.println("Введите заказ:");

        ConsoleInput consoleInput = new ConsoleInput();
        String clientName = consoleInput.prompt("Client name: ");
        String product = consoleInput.prompt("Product: ");
        int qnt = consoleInput.promptInt("Quantity: ");
        int price = consoleInput.promptInt("Price: ");

        Order order = new Order(clientName, product, qnt, price);
        OrderWriter orderWriter = new OrderWriter("order.json");
        orderWriter.saveToJson(order);
    }
}