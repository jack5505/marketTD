package uz.dukon.utils;

import java.io.File;

public interface FxmlUrl {
    interface Main
    {
        String MAIN_FXML = "/uz/ipc/warehouse/application/main/Main.fxml";
    }

    interface Product
    {
        String askDeletedView = "/uz/dukon/application/product/modals/AskDeleted.fxml";
        String productModal = "/uz/dukon/application/product/modals/AddProduct.fxml";
        String addType = "/uz/dukon/application/product/modals/AddType.fxml";
        String exitWindow = "/uz/dukon/ExitScreen.fxml";
        String askAboutDelete = "/uz/dukon/application/product/content/Notification.fxml";
        String sellConfirm = "/uz/dukon/application/product/content/SellConfirm.fxml";
        String itemInfo = "/uz/dukon/application/product/content/ItemInfo.fxml";
        String operationWithCart = "/uz/dukon/application/product/content/OperationWithCart.fxml";
        String PurchaseFinish = "/uz/dukon/application/product/modals/PurchaseFinishScreen.fxml";
        String reports = "/uz/dukon/application/product/content/TotalReport.fxml";
        String delete = "/uz/dukon/application/product/modals/DeletProductsView.fxml";
        String count = "/uz/dukon/application/product/modals/count.fxml";
        String productsView = "/uz/dukon/application/product/content/ProductContent.fxml";
    }
    interface Print{
        String path = new File("").getAbsolutePath();
        String printCheck = "/print/report.jasper";
    }

}
