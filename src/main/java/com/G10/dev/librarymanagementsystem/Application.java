package com.G10.dev.librarymanagementsystem;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.G10.dev.librarymanagementsystem.entity.Author;
import com.G10.dev.librarymanagementsystem.entity.Book;
import com.G10.dev.librarymanagementsystem.entity.Category;
import com.G10.dev.librarymanagementsystem.entity.Publisher;
import com.G10.dev.librarymanagementsystem.entity.Role;
import com.G10.dev.librarymanagementsystem.entity.User;
import com.G10.dev.librarymanagementsystem.repository.UserRepository;
import com.G10.dev.librarymanagementsystem.service.BookService;

@SpringBootApplication
public class Application {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private BookService bookService;

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner initialCreate() {
		return (args) -> {

			var book = new Book("AP1287", "Introduction to OOPS", "CXEF12389", "A book for beginners in OOPS");
			book.addAuthors(new Author("Md Anas Ali Usmani", "The greatest author that Earth ever witnessed"));
			book.addCategories(new Category("Prgramming"));
			book.addPublishers(new Publisher("Poki Enterprises Pvt. Ltd."));
			bookService.createBook(book);
            
			var book1 = new Book("BP567#R", "Spring Microservices", "KCXEF12389", "A book that teaches microservices in Spring");
			book1.addAuthors(new Author("Mohd Taha Rafi", "The greatest backend develeoper that Earth ever witnessed"));
			book1.addCategories(new Category("Spring Microservices"));
			book1.addPublishers(new Publisher("Boki Enterprises Pvt. Ltd."));
			bookService.createBook(book1);

			var book2 = new Book("GH67F#", "Spring Boot", "UV#JH", "A book that teaches Spring Boot in detail");            
			book2.addAuthors(new Author("Naman Khandelwar", "The greatest frontend develeoper that Earth ever witnessed"));
			book2.addCategories(new Category("Spring Boot"));
			book2.addPublishers(new Publisher("Roki Enterprises Pvt. Ltd."));
			bookService.createBook(book2);

			var user = new User("Md Anas Ali", "Usmani", "lcs2024054@iiitl.ac.in", passwordEncoder.encode("Jaijagat"),
					Arrays.asList(new Role("ROLE_ADMIN")));
			userRepository.save(user);

		};
	}
}
