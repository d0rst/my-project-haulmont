package com.example.application.views;

import com.example.application.data.entity.Genre;
import com.example.application.data.repository.GenreRepository;
import com.example.application.data.service.GenreService;
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

@Route(value = "genres", layout = MainView.class)
@PageTitle("Genres")
@CssImport("./styles/views/helloworld/hello-world-view.css")
@RouteAlias(value = "genres", layout = MainView.class)
public class GenreView extends Div {

    private Grid<Genre> grid = new Grid<>(Genre.class, false);

    private TextField name;

    private Button cancel = new Button("Cancel");
    private Button save = new Button("Save");
    private Button delete = new Button("Delete");

    private BeanValidationBinder<Genre> binder;

    private Genre genre;

    public GenreView(@Autowired GenreService genreService, GenreRepository genreRepository) {
        setId("genre-view");
        SplitLayout splitLayout = new SplitLayout();
        splitLayout.setSizeFull();

        createGridLayout(splitLayout);
        createEditorLayout(splitLayout);

        add(splitLayout);

        grid.addColumn("name").setAutoWidth(true);

        grid.setItems(genreRepository.getAllGenres());
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);
        grid.setHeightFull();

        grid.asSingleSelect().addValueChangeListener(event -> {
            if (event.getValue() != null) {
                Genre genreFromBackend = genreService.getGenreById(event.getValue().getGenreId());
                if (genreFromBackend != null) {
                    populateForm(genreFromBackend);
                } else {
                    refreshGrid();
                }
            } else {
                clearForm();
            }
        });

        binder = new BeanValidationBinder<>(Genre.class);

        binder.bindInstanceFields(this);

        cancel.addClickListener(e -> {
            clearForm();
            refreshGrid();
        });

        save.addClickListener(e -> {
            try {
                if (this.genre == null) {
                    this.genre = new Genre();
                }
                binder.writeBean(this.genre);

                genreRepository.update(this.genre);
                clearForm();
                refreshGrid();
                Notification.show("details stored.");
            } catch (ValidationException validationException) {
                Notification.show("An exception happened while trying to store the details.");
            }
        });

        delete.addClickListener(e -> {
            try {
                if (this.genre == null) {
                    this.genre = new Genre();
                }
                binder.writeBean(this.genre);

                genreRepository.delete(this.genre);
                clearForm();
                refreshGrid();
                Notification.show("details stored.");
            } catch (ValidationException validationException) {
                Notification.show("details block.");
                validationException.printStackTrace();
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
        name = new TextField("Name");

        Component[] fields = new Component[]{name};

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

    private void populateForm(Genre value) {
        this.genre = value;
        binder.readBean(this.genre);
    }
}
