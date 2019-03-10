package uz.dukon.utils;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.util.Callback;
import uz.dukon.controllers.model.ProductTable;
import uz.dukon.controllers.newmodel.CartTable;

import java.math.BigDecimal;

/**
 * Created by Jack on 17.02.2019.
 */
public class HelpfullUtils {
        public void filter(TextField textField){
            textField.textProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue,
                                    String newValue) {
                    if (!newValue.matches("\\d*")) {
                        textField.setText(newValue.replaceAll("[^\\d]", ""));
                    }
                }
            });
        }
        public static void setCenterText(TableColumn<ProductTable,String> table)
        {
            table.setCellFactory(new Callback<TableColumn<ProductTable, String>, TableCell<ProductTable, String>>() {
                @Override
                public TableCell<ProductTable, String> call(TableColumn<ProductTable, String> param) {
                    TableCell<ProductTable,String> cell = new TableCell<ProductTable,String>(){
                        @Override
                        protected void updateItem(String item, boolean empty) {
                            super.updateItem(item, empty);
                            if(item != null)
                            {
                                super.setText(item.toString());

                            }
                            else
                            {
                                super.setText("");

                            }

                        }
                    };

                    cell.setAlignment(Pos.CENTER);
                    return cell;
                }
            });
        }
    public static void setCenterGraphic(TableColumn<ProductTable,Button> table)
    {
        table.setCellFactory(new Callback<TableColumn<ProductTable, Button>, TableCell<ProductTable, Button>>() {
            @Override
            public TableCell<ProductTable, Button> call(TableColumn<ProductTable, Button> param) {
                TableCell<ProductTable,Button> cell = new TableCell<ProductTable,Button>(){
                    @Override
                    protected void updateItem(Button item, boolean empty) {
                        super.updateItem(item, empty);
                        if(item != null)
                        {
                            super.setGraphic(item);

                        }
                        else
                        {
                            super.setGraphic(null);

                        }

                    }
                };

                cell.setAlignment(Pos.CENTER);
                return cell;
            }
        });
    }
    public static void setCenterLong(TableColumn<ProductTable,Long> table)
    {
        table.setCellFactory(new Callback<TableColumn<ProductTable, Long>, TableCell<ProductTable, Long>>() {
            @Override
            public TableCell<ProductTable, Long> call(TableColumn<ProductTable, Long> param) {
                TableCell<ProductTable,Long> cell = new TableCell<ProductTable,Long>(){
                    @Override
                    protected void updateItem(Long item, boolean empty) {
                        super.updateItem(item, empty);
                        if(item != null)
                        {
                            super.setText(item.toString());

                        }
                        else
                        {
                            super.setText("");

                        }

                    }
                };

                cell.setAlignment(Pos.CENTER);
                return cell;
            }
        });
    }

    public static void setCenterBigDecimal(TableColumn<ProductTable,BigDecimal> table)
    {
        table.setCellFactory(new Callback<TableColumn<ProductTable, BigDecimal>, TableCell<ProductTable, BigDecimal>>() {
            @Override
            public TableCell<ProductTable, BigDecimal> call(TableColumn<ProductTable, BigDecimal> param) {
                TableCell<ProductTable,BigDecimal> cell = new TableCell<ProductTable,BigDecimal>(){
                    @Override
                    protected void updateItem(BigDecimal item, boolean empty) {
                        super.updateItem(item, empty);
                        if(item != null)
                        {
                            super.setText(item.toString());

                        }
                        else
                        {
                            super.setText("");

                        }

                    }
                };

                cell.setAlignment(Pos.CENTER);
                return cell;
            }
        });
    }

    public static void setCenterStringForCartTable(TableColumn<CartTable,String> table)
    {
        table.setCellFactory(new Callback<TableColumn<CartTable, String>, TableCell<CartTable, String>>() {
            @Override
            public TableCell<CartTable, String> call(TableColumn<CartTable, String> param) {
                TableCell<CartTable,String> cell = new TableCell<CartTable,String>(){
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if(item != null)
                        {
                            super.setText(item.toString());

                        }
                        else
                        {
                            super.setText("");

                        }

                    }
                };

                cell.setAlignment(Pos.CENTER);
                return cell;
            }
        });
    }

        }

