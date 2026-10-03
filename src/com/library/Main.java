package com.library;

import com.library.dao.BookDAO;
import com.library.model.Book;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BookDAO bookDAO = new BookDAO();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== LIBRARY MANAGEMENT SYSTEM ===");
            System.out.println("1. Add Book");
            System.out.println("2. View All Books");
            System.out.println("3. Update Book Price");
            System.out.println("4. Delete Book");
            System.out.println("5. Exit");
            System.out.print("Select choice (1-5): ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Book Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter Author Name: ");
                    String author = scanner.nextLine();
                    System.out.print("Enter Price: ");
                    double price = scanner.nextDouble();

                    if (bookDAO.addBook(new Book(title, author, price))) {
                        System.out.println("✅ Book added successfully!");
                    } else {
                        System.out.println("❌ Failed to add book.");
                    }
                    break;

                case 2:
                    List<Book> books = bookDAO.getAllBooks();
                    System.out.println("\n-------------------------------------------------------------");
                    System.out.printf("%-10s %-25s %-20s %-10s\n", "ID", "Title", "Author", "Price");
                    System.out.println("-------------------------------------------------------------");
                    for (Book b : books) {
                        System.out.printf("%-10d %-25s %-20s %-10.2f\n", 
                            b.getBookId(), b.getTitle(), b.getAuthor(), b.getPrice());
                    }
                    break;

                case 3:
                    System.out.print("Enter Book ID to Update: ");
                    int updateId = scanner.nextInt();
                    System.out.print("Enter New Price: ");
                    double newPrice = scanner.nextDouble();

                    if (bookDAO.updateBookPrice(updateId, newPrice)) {
                        System.out.println("✅ Price updated successfully!");
                    } else {
                        System.out.println("❌ Book ID not found.");
                    }
                    break;

                case 4:
                    System.out.print("Enter Book ID to Delete: ");
                    int deleteId = scanner.nextInt();

                    if (bookDAO.deleteBook(deleteId)) {
                        System.out.println("✅ Book deleted successfully!");
                    } else {
                        System.out.println("❌ Book ID not found.");
                    }
                    break;

                case 5:
                    System.out.println("Exiting Application...");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid Option! Please select between 1 and 5.");
            }
        }
    }
}