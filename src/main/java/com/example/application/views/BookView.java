package com.example.application.views;

import com.example.application.data.entity.Author;
import com.example.application.data.entity.Book;
import com.example.application.data.entity.Genre;
import com.example.application.data.repository.BookRepository;
import com.example.application.data.service.BookService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.listbox.ListBox;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.converter.StringToIntegerConverter;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Set;

@Route(value = "books", layout = MainView.class)
@PageTitle("Books")
@CssImport("./styles/views/helloworld/hello-world-view.css")
@RouteAlias(value = "", layout = MainView.class)
public class BookView extends Div {

    private final Grid<Book> grid = new Grid<>(Book.class, false);

    private TextField title;
    private TextField city;
    private TextField year;
    private final TextField filterText = new TextField();
    private final ListBox<String> publisher = new ListBox<>();

    private final Button cancel = new Button("Cancel");
    private final Button save = new Button("Save");
    private final Button delete = new Button("Delete");

    private final BeanValidationBinder<Book> binder;

    private Book book;

    public BookView(@Autowired BookService bookService, BookRepository bookRepository) {
        setId("author-view");
        SplitLayout splitLayout = new SplitLayout();
        splitLayout.setSizeFull();

        createGridLayout(splitLayout);
        createEditorLayout(splitLayout);

        filterText.setPlaceholder("Filter...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e ->
                grid.setItems(bookService.findAll(filterText.getValue())));

        grid.addColumn("title").setAutoWidth(true);
        grid.addColumn("city").setAutoWidth(true);
        grid.addColumn("publisher")
                .setComparator(Book::getPublisher)
                .setAutoWidth(true);
        grid.addColumn("year")
                .setComparator(Book::getYear)
                .setAutoWidth(true);

        grid.addColumn(book -> {
            Set<Author> authors = book.getAuthors();
            StringBuilder name = new StringBuilder();
            for (Author a: authors) {
                name.append(a.getLastName()).append(" ");
                name.append((a.getLastName()).charAt(0)).append(". ");
                name.append((a.getPatronymic()).charAt(0)).append(". ");
            }
            return name.toString();
        })
                .setHeader("Author")
                .setAutoWidth(true);

        grid.addColumn(book -> {
            Set<Genre> genres = book.getGenres();
            StringBuilder name = new StringBuilder();
            for (Genre g: genres) {
                name.append(g.getName()).append(" ");
            }
            return name.toString();
        })
                .setHeader("Genre")
                .setAutoWidth(true);

        grid.setItems(bookRepository.getAllBooks());
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);
        grid.setHeightFull();

        grid.asSingleSelect().addValueChangeListener(event -> {
            if (event.getValue() != null) {
                Book bookFromBackend = bookService.getBookById(event.getValue().getBookId());
                if (bookFromBackend != null) {
                    populateForm(bookFromBackend);
                } else {
                    refreshGrid();
                }
            } else {
                clearForm();
            }
        });

        binder = new BeanValidationBinder<>(Book.class);
        binder.forField(year)
                .withValidator(text -> text.length() == 4,
                        "Doesn't look like a year")
                .withConverter(
                        new StringToIntegerConverter("Must enter a number") {
                            protected NumberFormat getFormat(Locale locale) {
                                NumberFormat format = super.getFormat(locale);
                                format.setGroupingUsed(false);
                                return format;
                            }
                        })
                .bind(Book::getYear, Book::setYear);

        binder.forField(title)
                .asRequired("Every book must have a title")
                .bind(Book::getTitle, Book::setTitle);

        binder.bindInstanceFields(this);

        cancel.addClickListener(e -> {
            clearForm();
            refreshGrid();
        });

        save.addClickListener(e -> {
            if (this.book == null) {
                this.book = new Book();
            }
            try {
                binder.writeBean(this.book);
                bookRepository.update(this.book);
                clearForm();
                refreshGrid();
                Notification.show("details stored.");
            } catch (ValidationException validationException) {
                validationException.printStackTrace();
            }
        });

        delete.addClickListener(e -> {
            if (this.book == null) {
                this.book = new Book();
            }
            try {
                binder.writeBean(this.book);
                bookRepository.delete(this.book);
                clearForm();
                refreshGrid();
                Notification.show("details stored.");
            } catch (ValidationException validationException) {
                validationException.printStackTrace();
            }
        });
        add(filterText, splitLayout);
    }

    private void createEditorLayout(SplitLayout splitLayout) {
        Div editorLayoutDiv = new Div();
        editorLayoutDiv.setId("editor-layout");

        Div editorDiv = new Div();
        editorDiv.setId("editor");
        editorLayoutDiv.add(editorDiv);

        FormLayout formLayout = new FormLayout();
        title =  new TextField("title");

        city = new TextField("city");
        city.setPattern("[^0-9]+$");
        city.setPreventInvalidInput(true);
        year = new TextField("year");

        publisher.setItems("Москва", "Санкт-Петербург", "O’Reilly");
        publisher.setValue("Москва");


        Component[] fields = new Component[]{title, city, year};

        for (Component field : fields) {
            ((HasStyle) field).addClassName("full-width");
        }
        formLayout.add(fields);
        formLayout.add(publisher);
        editorDiv.add(formLayout);
        createButtonLayout(editorLayoutDiv);

        splitLayout.addToSecondary(editorLayoutDiv);
    }

    private void createButtonLayout(Div editorLayoutDiv) {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.setId("button-layout");
        buttonLayout.setWidthFull();
        buttonLayout.setSpacing(true);
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonLayout.add(save, cancel, delete);
        editorLayoutDiv.add(buttonLayout);
    }

    private void createGridLayout(SplitLayout splitLayout) {
        Div wrapper = new Div();
        wrapper.setId("grid-wrapper");
        wrapper.setWidthFull();
        splitLayout.addToPrimary(wrapper);
        wrapper.add(grid);
    }

    private void refreshGrid() {
        grid.select(null);
        grid.getDataProvider().refreshAll();
    }

    private void clearForm() {
        populateForm(null);
    }

    private void populateForm(Book value) {
        this.book = value;
        binder.readBean(this.book);
    }

}