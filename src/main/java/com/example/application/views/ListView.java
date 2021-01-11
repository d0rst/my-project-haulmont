package com.example.application.views;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import com.example.application.data.entity.Author;
import com.example.application.data.service.AuthorService;
import com.example.application.data.service.impl.AuthorServiceImpl;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import org.apache.commons.lang3.StringUtils;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.gridpro.GridPro;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.renderer.LocalDateRenderer;
import com.vaadin.flow.data.renderer.NumberRenderer;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "list", layout = MainView.class)
@PageTitle("list")
@CssImport("./styles/views/helloworld/hello-world-view.css")
@RouteAlias(value = "list", layout = MainView.class)
public class ListView extends Div {

    private Grid<Author> grid;
    private ListDataProvider<Author> dataProvider;

    private Grid.Column<Author> idColumn;
    private Grid.Column<Author> clientColumn;

    private BeanValidationBinder<Author> binder;

    private Author author;

    public ListView(@Autowired AuthorService authorService) {
        setId("list-view");
        setSizeFull();
        createGrid(authorService);
        add(grid);
    }

    private void createGrid(AuthorService authorService) {
        createGridComponent(authorService);
        addColumnsToGrid();
        addFiltersToGrid();

        grid.asSingleSelect().addValueChangeListener(event -> {
            if (event.getValue() != null) {
                Author authorFromBackend = authorService.getAuthorById(event.getValue().getAuthorId());
                if (authorFromBackend != null) {
                    populateForm(authorFromBackend);
                } else {
                    refreshGrid();
                }
            } else {
                clearForm();
            }
        });
    }

    private void createGridComponent(AuthorService authorService) {
        grid = new Grid<>();
        grid.setSelectionMode(SelectionMode.SINGLE);
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER, GridVariant.LUMO_COLUMN_BORDERS);
        grid.setHeight("100%");
        dataProvider = new ListDataProvider<>(authorService.getAllAuthors());
        grid.setDataProvider(dataProvider);
    }

    private void addColumnsToGrid() {
        createIdColumn();
        createClientColumn();
    }

    private void createIdColumn() {
        idColumn = grid.addColumn(Author::getAuthorId, "id").setHeader("ID").setWidth("120px").setFlexGrow(0);
    }

    private void createClientColumn() {
        clientColumn = grid.addColumn(new ComponentRenderer<>(author -> {
            HorizontalLayout hl = new HorizontalLayout();
            hl.setAlignItems(Alignment.CENTER);

            Span span = new Span();
            span.setClassName("name");
            span.setText(author.getFirstName());
            hl.add(span);
            return hl;
        })).setComparator(Author::getFirstName).setHeader("Client");
    }


    private void addFiltersToGrid() {
        HeaderRow filterRow = grid.appendHeaderRow();

        TextField idFilter = new TextField();
        idFilter.setPlaceholder("Filter");
        idFilter.setClearButtonVisible(true);
        idFilter.setWidth("100%");
        idFilter.setValueChangeMode(ValueChangeMode.EAGER);
        idFilter.addValueChangeListener(event -> dataProvider.addFilter(
                author -> StringUtils.containsIgnoreCase(Long.toString(author.getAuthorId()), idFilter.getValue())));
        filterRow.getCell(idColumn).setComponent(idFilter);

        TextField clientFilter = new TextField();
        clientFilter.setPlaceholder("Filter");
        clientFilter.setClearButtonVisible(true);
        clientFilter.setWidth("100%");
        clientFilter.setValueChangeMode(ValueChangeMode.EAGER);
        clientFilter.addValueChangeListener(event -> dataProvider
                .addFilter(author -> StringUtils.containsIgnoreCase(author.getFirstName(), clientFilter.getValue())));
        filterRow.getCell(clientColumn).setComponent(clientFilter);
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

};
