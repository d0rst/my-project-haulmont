package com.example.application.views;

import com.example.application.data.entity.Author;
import com.example.application.data.entity.Book;
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
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import org.springframework.beans.factory.annotation.Autowired;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.component.textfield.TextField;

@Route(value = "books", layout = MainView.class)
@PageTitle("Books")
@CssImport("./styles/views/helloworld/hello-world-view.css")
@RouteAlias(value = "books", layout = MainView.class)
public class BookView extends Div {

    private Grid<Book> grid = new Grid<>(Book.class, false);

    private TextField title;
    private TextField city;
    private TextField publisher;
    private TextField year;

    private Button cancel = new Button("Cancel");
    private Button save = new Button("Save");

    private BeanValidationBinder<Book> binder;

    private Book book;

    public BookView(@Autowired BookService bookService, BookRepository bookRepository) {
        setId("book-view");
        SplitLayout splitLayout = new SplitLayout();
        splitLayout.setSizeFull();

        createGridLayout(splitLayout);
        createEditorLayout(splitLayout);

        add(splitLayout);

        grid.addColumn("title").setAutoWidth(true);
        grid.addColumn("city").setAutoWidth(true);
        grid.addColumn(Book::getPublisher).setHeader("publisher").setAutoWidth(true);
        grid.addColumn(Book::getYear).setHeader("year").setAutoWidth(true);
        grid.setItems(bookRepository.getAllBooks());

//        grid.setItems(query -> {
//            return authorRepository.getAllAuthors( // (1)
//                    PageRequest.of(query.getPage(), // (2)
//                            query.getPageSize()) // (3)
//            ).stream(); // (4)
//        });


//        grid.setDataProvider(
//                (sortOrders, offset, limit) ->
//                        authorService.getAllAuthors(offset, limit).stream(),
//                () -> authorService.count()
//        );
//
//        DataProvider dataProvider = new ;

//        grid.setDataProvider(new CrudServiceDataProvider<>(authorService));
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

        binder.bindInstanceFields(this);

        cancel.addClickListener(e -> {
            clearForm();
            refreshGrid();
        });

        save.addClickListener(e -> {
            try {
                if (this.book == null) {
                    this.book = new Book();
                }
                binder.writeBean(this.book);

                bookRepository.update(this.book);
                clearForm();
                refreshGrid();
                Notification.show("Person details stored.");
            } catch (ValidationException validationException) {
                Notification.show("An exception happened while trying to store the person details.");
            }
        });

    }

    private void createEditorLayout(SplitLayout splitLayout) {
        Div editorLayoutDiv = new Div();
        editorLayoutDiv.setId("editor-layout");

        Div editorDiv = new Div();
        editorDiv.setId("editor");
        editorLayoutDiv.add(editorDiv);

        FormLayout formLayout = new FormLayout();
        title = new TextField("Title");
        city  = new TextField("City");
        publisher  = new TextField("Publisher");
        year = new TextField("Year");

        Component[] fields = new Component[]{title, city, publisher, year};

        for (Component field : fields) {
            ((HasStyle) field).addClassName("full-width");
        }
        formLayout.add(fields);
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
        buttonLayout.add(save, cancel);
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
