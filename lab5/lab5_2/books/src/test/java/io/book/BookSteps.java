package io.book;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.hascode.tutorial.cucumber.book.Book;
import io.hascode.tutorial.cucumber.book.Library;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.time.LocalDateTime;
import java.time.ZoneOffset;


public class BookSteps {
	Library library = new Library();
	List<Book> result = new ArrayList<>();
 
	@Given("a book with the title {string}, written by {string}, published in {iso8601Date}")
	public void addNewBook(final String title, final String author, LocalDateTime published) {
		Date published_date = Date.from(published.toInstant(ZoneOffset.UTC));
		Book book = new Book(title, author, published_date);
		library.addBook(book);
	}

	@And("another book with the title {string}, written by {string}, published in {iso8601Date}")
	public void addAnotherBook(final String title, final String author, LocalDateTime published) {
		Date published_date = Date.from(published.toInstant(ZoneOffset.UTC));
		Book book = new Book(title, author, published_date);
		library.addBook(book);
	}

	@When("the customer searches for books published between {iso8601Date} and {iso8601Date}")
	public void setSearchParameters(LocalDateTime from, LocalDateTime to) {
		Date from_date = Date.from(from.toInstant(ZoneOffset.UTC));
		Date to_date = Date.from(to.toInstant(ZoneOffset.UTC));
		result = library.findBooks(from_date, to_date);
	}
 
	@Then("{int} books should have been found")
	public void verifyAmountOfBooksFound(final int booksFound) {
		assertThat(result.size(), equalTo(booksFound));
	}
 
	@And("Book {int} should have the title {string}")
	public void verifyBookAtPosition(final int position, final String title) {
		assertThat(result.get(position - 1).getTitle(), equalTo(title));
	}


	@ParameterType("([0-9]{4})-([0-9]{2})-([0-9]{2})")
    public LocalDateTime iso8601Date(String year, String month, String day){
        return LocalDateTime.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day),0, 0);
    }

}