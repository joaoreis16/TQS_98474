package io.hascode.tutorial.cucumber.book;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Library {
	private final List<Book> store = new ArrayList<>();

	public void addBook(final Book book) {
		store.add(book);
	}

	public List<Book> findBooks(final Date from, final Date to) {
		Calendar end = Calendar.getInstance();
		end.setTime(to);
		end.roll(Calendar.YEAR, 1);
		inspectLibrary();

		return store.stream().filter(book -> {
			return from.before(book.getPublished()) && end.getTime().after(book.getPublished());
		}).sorted(Comparator.comparing(Book::getPublished).reversed()).collect(Collectors.toList());
	}


	public List<Book> findByAuthor(final String author) {
	
		return store.stream().filter(book -> {
			return book.getAuthor().equals(author);
		}).sorted(Comparator.comparing(Book::getAuthor)).collect(Collectors.toList());

    }

	//Debug
	public void inspectLibrary() {
		System.out.println(store.size());
			for(int i=0;i<store.size();i++){
				System.out.println( store.get(i));
	} 
}
}

