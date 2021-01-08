package com.example.application.views;

import com.example.application.data.entity.Author;
import com.example.application.data.repository.AuthorRepository;
import com.example.application.data.service.AuthorService;
import com.vaadin.flow.component.ItemLabelGenerator;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@Route(value = "test", layout = MainView.class)
@PageTitle("test")
@CssImport("./styles/views/helloworld/hello-world-view.css")
@RouteAlias(value = "test", layout = MainView.class)

public class TestView extends VerticalLayout {
    private Grid<Author> grid;
    private TextField toStringFilter;
    private ComboBox<Month> monthFilter;
    private ComboBox<DayOfWeek> dayOfWeekFilter;

    public TestView(@Autowired AuthorService authorService, AuthorRepository authorRepository) {
        grid = new Grid<>(Author.class, false);
        List<Author> authors = List.of(
                new Author( "2", "2", "2") ,
                new Author( "4", "2", "25") ,
                new Author( "5", "4", "42") ,
                new Author( "6", "6", "2") ,
                new Author( "2", "8", "452")
        );
        grid.setItems(authorService.getAllAuthors());
        grid.addColumn("firstName").setAutoWidth(true);
        grid.addColumn("lastName").setAutoWidth(true);
        grid.addColumn("patronymic").setAutoWidth(true);


//        grid.addColumn("firstname").setHeader("Firstname");
//        grid.addColumn("lastName" ).setHeader("LastName");
//        grid.addColumn("patronymic").setHeader("Patronymic");

//        prepareFilterFields();
        add(grid);
    }
//
//    private void prepareFilterFields() {
//        HeaderRow headerRow = grid.appendHeaderRow();
//        toStringFilter = gridTextFieldFilter("tostring", headerRow);
//        monthFilter = gridComboBoxFilter("month", headerRow, Month::toString, Month.values());
//    }

//    private <T> ComboBox<T> gridComboBoxFilter(String columnKey, HeaderRow headerRow, ItemLabelGenerator<T> itemLabelGenerator, T... items) {
//        ComboBox<T> filter = new ComboBox<>();
//        filter.addValueChangeListener(event -> this.onFilterChange());
//        filter.setItemLabelGenerator(itemLabelGenerator);
//        filter.setItems(items);
//        filter.setWidthFull();
//        filter.setClearButtonVisible(true);
//        headerRow.getCell(grid.getColumnByKey(columnKey)).setComponent(filter);
//        return filter;
//    }

//    private TextField gridTextFieldFilter(String columnKey, HeaderRow headerRow) {
//        TextField filter = new TextField();
//        filter.setValueChangeMode(ValueChangeMode.TIMEOUT);
//        filter.addValueChangeListener(event -> this.onFilterChange());
//        filter.setWidthFull();
//        headerRow.getCell(grid.getColumnByKey(columnKey)).setComponent(filter);
//        return filter;
//    }

//    private void onFilterChange(){
//        ListDataProvider<Author> listDataProvider = (ListDataProvider<Author>) grid.getDataProvider();
//        // Since this will be the only active filter, it needs to account for all values of my filter fields
//        listDataProvider.setFilter(item -> {
//            boolean toStringFilterMatch = true;
//            boolean yearFilterMatch = true;
//            boolean monthFilterMatch = true;
//
//            if(!toStringFilter.isEmpty()){
//                toStringFilterMatch = item.toString().contains(toStringFilter.getValue());
//            }
//            if(!yearFilter.isEmpty()){
//                yearFilterMatch = String.valueOf(item.getYear()).contains(yearFilter.getValue());
//            }
//            if(!monthFilter.isEmpty()){
//                monthFilterMatch = item.getMonth().equals(monthFilter.getValue());
//            }
//
//            return toStringFilterMatch && yearFilterMatch && monthFilterMatch;
//        });
//    }
}