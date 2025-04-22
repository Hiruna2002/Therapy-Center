module SerenityMentalHealthTherapyCenter {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.jfoenix;
    requires lombok;
    requires org.hibernate.orm.core;
    requires jakarta.persistence;
    requires java.naming;
    requires jasperreports;
    requires jbcrypt;

    opens org.example.controller to javafx.fxml;
    opens org.example.config to org.hibernate.orm.core;
    opens org.example.entity to org.hibernate.orm.core;
    opens org.example.tm to javafx.base;

    exports org.example;

}