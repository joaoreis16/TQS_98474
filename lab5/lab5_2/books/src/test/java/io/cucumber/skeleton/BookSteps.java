package io.cucumber.skeleton;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// //////////// imports que não dão //////////////////

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
 
import cucumber.api.Format;

 
public class BookSteps {
	Library library = new Library();
	List<Book> result = new ArrayList<>();
 
	@Given("(one|another) book with the title '{string}', written by '{string}', published in {date_iso_local_date_time}")
	public void addNewBook(final String title, final String author, @Format("dd MMMMM yyyy") final Date published) {
		Book book = new Book(title, author, published);
		library.addBook(book);
	}
 
	@When("the customer searches for books published between {date_iso_local_date_time} and {date_iso_local_date_time}")
	public void setSearchParameters(@Format("yyyy") final Date from, @Format("yyyy") final Date to) {
		result = library.findBooks(from, to);
	}
 
	@Then("{int} books should have been found")
	public void verifyAmountOfBooksFound(final int booksFound) {
		assertThat(result.size(), equalTo(booksFound));
	}
 
	@Then("Book {int} should have the title '{string}'")
	public void verifyBookAtPosition(final int position, final String title) {
		assertThat(result.get(position - 1).getTitle(), equalTo(title));
	}
}