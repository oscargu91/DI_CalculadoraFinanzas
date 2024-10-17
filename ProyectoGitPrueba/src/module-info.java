module ProyectoGitPrueba {
    requires javafx.controls;
    requires javafx.graphics;
    requires java.xml;
    exports com.carballeira.aplicacion.view; 
    
    opens com.carballeira.aplicacion.view to javafx.graphics, javafx.controls;
    opens com.carballeira.aplicacion.model to javafx.graphics,javafx.controls;
    opens com.carballeira.aplicacion.controller to javafx.graphics,javafx.controls;
    
}
