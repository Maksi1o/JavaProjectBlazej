package pd14;

import pd14.dto.CreateBookRequest;
import pd14.dto.CreateLoanRequest;
import pd14.dto.RegisterMemberRequest;
import pd14.dto.ReturnBookRequest;
import pd14.exceptions.*;
import pd14.repository.BookRepository;
import pd14.repository.LoanRepository;
import pd14.repository.MemberRepository;
import pd14.service.BookService;
import pd14.service.LoanService;
import pd14.service.MemberService;
import pd14.service.impl.BookServiceImpl;
import pd14.service.impl.LoanServiceImpl;
import pd14.service.impl.MemberServiceImpl;

public class App {
    public static void main(String[] args) {
        BookRepository bookRepository = new BookRepository();
        MemberRepository memberRepository = new MemberRepository();
        LoanRepository loanRepository = new LoanRepository();

        BookService bookService = new BookServiceImpl(bookRepository);
        MemberService memberService = new MemberServiceImpl(memberRepository);
        LoanService loanService = new LoanServiceImpl(loanRepository, memberRepository, bookRepository);

        CreateBookRequest test1 = new CreateBookRequest("978-1", "Clean Code", "Robert Martin", 2008, 1);
        CreateBookRequest test2 = new CreateBookRequest("978-2", "Effective Java", "Joshua Bloch", 2018, 2);
        CreateBookRequest test3 = new CreateBookRequest("978-3", "Refactoring", "Martin Fowler", 1999, 1);

        RegisterMemberRequest jan = new RegisterMemberRequest("Jan", "jan@mail.com");

        CreateLoanRequest tester1 = new CreateLoanRequest(1L, 1L);
        CreateLoanRequest tester2 = new CreateLoanRequest(1L, 2L);
        CreateLoanRequest tester3 = new CreateLoanRequest(1L, 3L);
        CreateLoanRequest unknownMember = new CreateLoanRequest(99L, 1L);

        ReturnBookRequest returnFirstLoan = new ReturnBookRequest(1L);

        System.out.println(bookService.addBook(test1));
        System.out.println(bookService.addBook(test2));
        System.out.println(bookService.addBook(test3));
        try {
            System.out.println(bookService.addBook(test1));
        } catch (BookAlreadyExistsException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(memberService.register(jan));
        try {
            System.out.println(memberService.register(jan));
        } catch (MemberAlreadyExistsException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(bookService.search("JAVA"));
        System.out.println(loanService.borrow(tester1));
        try {
            System.out.println(loanService.borrow(tester1));
        } catch (BookNotAvailableException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(bookService.findAvailable());
        System.out.println(loanService.borrow(tester2));
        System.out.println(loanService.borrow(tester2));
        try {
            System.out.println(loanService.borrow(tester3));
        } catch (LoanLimitExceededException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(memberService.getActiveLoans(tester1.memberId()));
        System.out.println(loanService.returnBook(returnFirstLoan));
        try {
            System.out.println(loanService.returnBook(returnFirstLoan));
        } catch (LoanAlreadyReturnedException e) {
            System.out.println(e.getMessage());
        }
        try {
            System.out.println(loanService.borrow(unknownMember));
        } catch (MemberNotFoundException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(loanService.findOverdue());
    }
}

//TODO: Wydzielenie metody assignId do innej klasy?
//TODO: Jak zrobic przeterminowane wypozyczenie?
//TODO: klasa dla requireMember/loan/book?
//TODO: Enum jako status wypozyczenia?
//TODO: Dodatkowa walidacja przy mailu?
//TODO: Czy cos jeszcze moge wydzielic do innej klasy?
//TODO: Validation na pewno jako klasa narzedziowa?
//TODO: czy moge skompresowac jakies klasy/czy jest to w ogole worth?
//TODO: dodac sufit do wypozyczen zeby nie mozna bylo wpisac 999999999?
