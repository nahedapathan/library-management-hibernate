package com.app;

import java.util.Scanner;

import com.entity.Books;
import com.service.BookService;

public class MainApp {

	public static void main(String[] args) {
		BookService system = new BookService();
		Scanner sc = new Scanner(System.in);
		boolean exit = false;

		while (!exit) {
			System.out.println("***Library Management System***");
			System.out.println("1.Add Book in Library.");
			System.out.println("2.Update All Book Data.");
			System.out.println("3.Update Book Price and Stock Using Id.");
			System.out.println("4.Delete Data by Id.");
			System.out.println("5.View All Books.");
			System.out.println("6.View Book by Category");
			System.out.println("7.Author Books Present.");
			System.out.println("8.View Books Cheaper than a Specific Price.");
			System.out.println("9.View Books More Expensive than a Specific Price.");
			System.out.println("10.Search Book Using keyword");
			System.out.println("11.View Books Priced Between Two Values.");
			System.out.println("12.Exit Application.");
			System.out.println("Enter the choice:");
			int choice = sc.nextInt();
			switch (choice) {
			case 1:
				System.out.print("Enter the book Title:");
				sc.nextLine(); // consume newline
				String title = sc.nextLine();
				System.out.print("Enter the book Author:");
				String author = sc.nextLine();
				System.out.print("Enter the book Category:");
				String category = sc.nextLine();
				System.out.print("Enter the book price:");
				double price = sc.nextDouble();
				System.out.print("Enter the Stock of Book:");
				int stock = sc.nextInt();

				system.insertBook(title, author, category, price, stock);
				break;

			case 2:
				System.out.print("Enter the Book ID to Update:");
				int uid = sc.nextInt();
				System.out.print("Enter the New book Title:");
				sc.nextLine(); // consume newline
				String utitle = sc.nextLine();
				System.out.print("Enter the New  book Author:");
				String uauthor = sc.nextLine();
				System.out.print("Enter the New book Category:");
				String ucategory = sc.nextLine();
				System.out.print("Enter the New book price:");
				double uprice = sc.nextDouble();
				System.out.print("Enter the New Stock of Book:");
				int ustock = sc.nextInt();
				
				system.updateBook(utitle, uauthor, ucategory, uprice, ustock, uid);

				break;

			case 3:System.out.print("Enter the Book Id to Update:");
		           int uppid=sc.nextInt();
				   System.out.print("Enter New Price: ");
			       double uppprice=sc.nextDouble();
			       System.out.print("Enter New Stock:");
			       int uppstock=sc.nextInt();
			       
			       system.updatePriceStock(uppprice, uppstock, uppid);

				break;

			case 4:
				System.out.print("Enter the Book ID:");
				int bookid = sc.nextInt();
				system.deleteBook(bookid);

				break;
			case 5:
				System.out.println("Displaying Books :");
				for (Books book : system.getAllBooks()) {
					System.out.println(book);
				}

				break;
			case 6:
				System.out.print("Enter the Category:");
				sc.nextLine();// Consume Line
				String bcategory = sc.nextLine();
				system.getBookBycategory(bcategory);

				break;

			case 7:
				System.out.print("Enter the Author:");
				sc.nextLine();// Consume Line
				String bauthor = sc.nextLine();
				system.getBookByAuthor(bauthor);

				break;

			case 8:
				System.out.print("Enter the price of Book :");
				double vprice=sc.nextDouble();
				system.getBooksCheaperThan(vprice);
				break;
			case 9:
				System.out.print("Enter the price of Book :");
				double eprice=sc.nextDouble();
				system.getBooksMoreExpensiveThan(eprice);
				break;
				
			case 10:
				System.out.print("Enter Keyword to Search in Title:");
				sc.nextLine();
				String keyword=sc.nextLine();
				system.getBookByTitleKeyword(keyword);
				break;
			case 11:
				System.out.print("Enter Minimum Price :");
				double minPrice=sc.nextDouble();
				System.out.print("Enter Maximum Price:");
				double maxPrice=sc.nextDouble();
				system.getBooksInPriceRange(minPrice, maxPrice);
				break;
			case 12:
				exit = true;
				System.out.println("System Exited Succesfully");

				break;

			default:
				System.out.println("Invalid Choice");
				break;
			}

		}

	}
}
