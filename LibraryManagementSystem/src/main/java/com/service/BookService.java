package com.service;


import java.awt.print.Book;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import com.entity.Books;

public class BookService {

	private static SessionFactory factory=new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
	
	//insert
	public void insertBook(String title,String author,String category,double price,int stock)
	{
		Session session=factory.openSession();
		Transaction tx=session.beginTransaction();
		
		Books book=new Books();
		book.setTitle(title);
		book.setAuthor(author);
		book.setCategory(category);
		book.setPrice(price);
		book.setStock(stock);
		
		session.save(book);
		tx.commit();
		session.close();
		System.out.println("Book Inserted Successfully.");
	}
	
	//delete
	public void deleteBook(int bookId)
	{
		Session session=factory.openSession();
		Transaction tx=session.beginTransaction();
		
		String hql="Delete from Books b where b.id= :bookId";
		Query query=session.createQuery(hql);
		query.setParameter("bookId", bookId);
		query.executeUpdate();
		
		tx.commit();
		session.close();
		System.out.println("Book Deleted Succesfully");
	}
	
	//read
	public List<Books> getAllBooks()
	{
		Session session=factory.openSession();
		
		String hql="from Books";
		Query<Books> query=session.createQuery(hql);
		List<Books> books=query.getResultList();
		
		session.close();
		return books;
	}
	
	//update
	public void updateBook(String title,String author,String category,double price,int stock,int id)
	{
		Session session=factory.openSession();
		Transaction tx=session.beginTransaction();
		
		Books book=session.get(Books.class, id);
		if(book!=null)
		{
			book.setTitle(title);
			book.setAuthor(author);
			book.setCategory(category);
			book.setPrice(price);
			book.setStock(stock);
			session.saveOrUpdate(book);
			tx.commit();
			System.out.println("Book updated Succesfully");
		}
		else
		{
			System.out.println("Not Updated Succesfully.");
		}
		
		session.close();
	}
	
	public void updatePriceStock(double price,int stock,int id)
	{
		Session session=factory.openSession();
		Transaction tx=session.beginTransaction();
		
		Books book=session.get(Books.class, id);
		if(book!=null)
		{
			book.setPrice(price);
			book.setStock(stock);
			session.saveOrUpdate(book);
			tx.commit();
			System.out.println("Updated Succesfully");
		}
		else
		{
			System.out.println("Not Updated Succesfully.");
		}
		
		session.close();
	}
	
	
	//projection
	public void getBookByAuthor(String author)
	{
		Session session=factory.openSession();
		
		Criteria criteria=session.createCriteria(Books.class);
		criteria.add(Restrictions.eq("author", author));
		criteria.setProjection(Projections.property("title"));
		List<String> titles=criteria.list();
		for(String book:titles)
		{
			System.out.println(book);
		}

		session.close();
				
	}
	
	//Restriction 1:eq
	public void getBookBycategory(String category)
	{
		Session session=factory.openSession();
		
		Criteria criteria=session.createCriteria(Books.class);
		criteria.add(Restrictions.eq("category", category));
		List<Books> books=criteria.list();
		
		System.out.println("Book in Category:"+category);
		for(Books book:books)
		{
			System.out.println(book);
		}
		session.close();
	}
	
	//Restriction 2:lt
	public void getBooksCheaperThan(double price)
	{
		Session session=factory.openSession();
		Criteria criteria=session.createCriteria(Books.class);
		criteria.add(Restrictions.lt("price", price));
		List<Books> books=criteria.list();
		
		System.out.println("Book cheaper than "+price+":");
		for(Books book:books)
		{
			System.out.println(book);
		}
		session.close();
	}
	
	//Restriction 3:gt
	public void getBooksMoreExpensiveThan(double price)
	{
		Session session=factory.openSession();
		
		Criteria criteria=session.createCriteria(Books.class);
		criteria.add(Restrictions.gt("price", price));
		List<Books> books=criteria.list();
		
		System.out.println("Book more expensive than "+price+":");
		for(Books book:books)
		{
			System.out.println(book);
		}
		session.close();
	}
	
	//Restriction 4:like
	public void getBookByTitleKeyword(String keyword)
	{
		Session session=factory.openSession();
		Criteria criteria=session.createCriteria(Books.class);
		criteria.add(Restrictions.like("title","%"+keyword+"%"));
		List<Books> books=criteria.list();
		System.out.println("Book with titles containing \""+keyword+"\":");
		for(Books book:books)
		{
			System.out.println(book);
		}
		
	}
	 
	//Restriction 5:between
	public void getBooksInPriceRange(double minPrice,double maxPrice)
	{
		Session session=factory.openSession();
		Criteria criteria=session.createCriteria(Books.class);
		criteria.add(Restrictions.between("price", minPrice, maxPrice));
		List<Books> books=criteria.list();
		System.out.println("Book priced in between "+minPrice+"and"+maxPrice+":");
		for(Books book:books)
		{
			System.out.println(book);
		}
	}
}















