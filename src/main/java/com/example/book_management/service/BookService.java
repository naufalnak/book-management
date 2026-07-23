package com.example.book_management.service;

import com.example.book_management.dto.BookRequest;
import com.example.book_management.dto.BookResponse;
import com.example.book_management.entity.Book;
import com.example.book_management.exception.ResourceNotFoundException;
import com.example.book_management.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public List<BookResponse> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(BookResponse::fromEntity)
                .toList();
    }

    public BookResponse getBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book dengan id " + id + " tidak ditemukan"));
        return BookResponse.fromEntity(book);
    }

    public BookResponse createBook(BookRequest request) {
        Book book = new Book();
        book.setTitle(request.getTitle());
        book.setAuthor(request.getAuthor());
        book.setIsbn(request.getIsbn());
        book.setStock(request.getStock());

        Book saved = bookRepository.save(book);
        return BookResponse.fromEntity(saved);
    }

    public BookResponse updateBook(Long id, BookRequest request) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book dengan id " + id + " tidak ditemukan"));

        book.setTitle(request.getTitle());
        book.setAuthor(request.getAuthor());
        book.setIsbn(request.getIsbn());
        book.setStock(request.getStock());

        Book updated = bookRepository.save(book);
        return BookResponse.fromEntity(updated);
    }

    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new ResourceNotFoundException("Book dengan id " + id + " tidak ditemukan");
        }
        bookRepository.deleteById(id);
    }

    public List<BookResponse> searchByTitle(String title) {
        return bookRepository.findByTitleContainingIgnoreCase(title)
                .stream()
                .map(BookResponse::fromEntity)
                .toList();
    }
}
