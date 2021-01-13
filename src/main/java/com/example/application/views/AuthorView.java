package com.example.application.views;

import com.example.application.data.entity.Author;
import com.example.application.data.repository.AuthorRepository;
import com.example.application.data.service.AuthorService;
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
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import org.springframework.beans.factory.annotation.Autowired;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.component.textfield.TextField;

@Route(value = "authors", layout = MainView.class)
@PageTitle("Authors")
@CssImport("./styles/views/helloworld/hello-world-view.css")
@RouteAlias(value = "authors", layout = MainView.class)
public class AuthorView extends Div {

    private final Grid<Author> grid = new Grid<>(Author.class, false);

    private TextField firstName;
    private TextField lastName;
    private TextField patronymic;

    private final TextField filterText;


    private final Button cancel = new Button("Cancel");
    private final Button save = new Button("Save");
    private final Button delete = new Button("Delete");

    private final BeanValidationBinder<Author> binder;

    private Author author;

    public AuthorView(@Autowired AuthorService authorService, AuthorRepository authorRepository) {
        setId("author-view");
        SplitLayout splitLayout = new SplitLayout();
        splitLayout.setSizeFull();

        filterText = new TextField();
        filterText.setPlaceholder("Filter...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e ->
                grid.setItems(authorService.findAll(filterText.getValue())));

        grid.addColumn("firstName").setAutoWidth(true);
        grid.addColumn("lastName").setAutoWidth(true);
        grid.addColumn("patronymic").setAutoWidth(true);

        grid.setItems(authorRepository.getAllAuthors());
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);
        grid.setHeightFull();

        grid.asSingleSelect().addValueChangeListener(event -> {
            if (event.getValue() != null) {
                Author authorFromBackend = authorService.getAuthorById(event.getValue().getId());
                if (authorFromBackend != null) {
                    populateForm(authorFromBackend);
                } else {
                    refreshGrid();
                }
            } else {
                clearForm();
            }
        });

        binder = new BeanValidationBinder<>(Author.class);

        binder.bindInstanceFields(this);

        cancel.addClickListener(e -> {
            clearForm();
            refreshGrid();
        });

        save.addClickListener(e -> {
            try {
                if (this.author == null) {
                    this.author = new Author();
                }
                binder.writeBean(this.author);

                authorRepository.update(this.author);
                clearForm();
                refreshGrid();
                Notification.show("details stored.");
            } catch (ValidationException validationException) {
                Notification.show("An exception happened while trying to store the details.");
            }
        });

        delete.addClickListener(e -> {
            try {
                if (this.author == null) {
                    this.author = new Author();
                }
                binder.writeBean(this.author);

                authorRepository.delete(this.author);
                clearForm();
                refreshGrid();
                Notification.show("details deleted");
            } catch (ValidationException validationException) {
                Notification.show("An exception occurred while trying to delete detail.");
            }
        });

        createGridLayout(splitLayout);
        createEditorLayout(splitLayout);
        add(filterText, splitLayout);
    }

    private void createEditorLayout(SplitLayout splitLayout) {
        Div editorLayoutDiv = new Div();
        editorLayoutDiv.setId("editor-layout");

        Div editorDiv = new Div();
        editorDiv.setId("editor");
        editorLayoutDiv.add(editorDiv);

        FormLayout formLayout = new FormLayout();
        firstName = new TextField("First Name");
        firstName.setPattern("^[A-Za-zА-Яа-яЁё\\s-]+$");
        firstName.setPreventInvalidInput(true);
        lastName = new TextField("Last Name");
        lastName.setPattern("^[A-Za-zА-Яа-яЁё\\s-]+$");
        lastName.setPreventInvalidInput(true);
        patronymic = new TextField("Patronymic");
        patronymic.setPattern("^[A-Za-zА-Яа-яЁё\\s-]+$");
        patronymic.setPreventInvalidInput(true);

        Component[] fields = new Component[]{firstName, lastName, patronymic};

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
        buttonLayout.setHeightFull();
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

    private void populateForm(Author value) {
        this.author = value;
        binder.readBean(this.author);
    }
}
