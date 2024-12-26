package com.project.library;

import jakarta.persistence.*;

@Entity
@Table(name = "Book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_book")
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "year_of_publication")
    private Long yearOfPublication;

    @Column(name = "number_of_pages")
    private Long numberOfPages;

    @Column(name = "is_reserved", nullable = false)
    private Boolean isReserved = false;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getYearOfPublication() {
        return yearOfPublication;
    }

    public void setYearOfPublication(Long yearOfPublication) {
        this.yearOfPublication = yearOfPublication;
    }

    public Long getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(Long numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public Boolean getIsReserved() {
        return isReserved;
    }

    public void setIsReserved(Boolean isReserved) {
        this.isReserved = isReserved;
    }

    public <author> void setAuthor(String author) {
        this.setAuthor(author);
    }


    public <genre> void setGenre(String genre) {
        this.setGenre(genre);
    }
}
