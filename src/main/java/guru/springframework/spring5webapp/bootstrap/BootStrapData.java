package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author eric = new Author("Eric", "Evans");
        Publisher pub = new Publisher("Maxwell Books", "StreetName", "Berlin", "GER", "12345");
        publisherRepository.save(pub);
        Book ddd = new Book("Domain driven design", "12343", pub);
        Book ejb = new Book("EJB ", "XLM123123", pub);


        pub.getBooks().add(ejb);
        pub.getBooks().add(ddd);
        eric.getBooks().add(ddd);
        eric.getBooks().add(ejb);
        ddd.getAuthors().add(eric);
        ejb.getAuthors().add(eric);

        authorRepository.save(eric);
        bookRepository.save(ddd);
        bookRepository.save(ejb);
        publisherRepository.save(pub);




        System.out.println("number of books in Repo:" + bookRepository.count());
        System.out.println("numbers of publishers books " + pub.getBooks().size());
    }
}
