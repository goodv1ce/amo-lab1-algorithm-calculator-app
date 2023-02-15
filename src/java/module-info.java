module ua.goodvice.amo.javaapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens ua.goodvice.amo.javaapp to javafx.fxml;
    exports ua.goodvice.amo.javaapp;
    exports ua.goodvice.amo.javaapp.Algorithms;
    opens ua.goodvice.amo.javaapp.Algorithms to javafx.fxml;
}