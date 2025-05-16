package com.ajadrez.ajedrez;

// Clase base para toda aplicación JavaFX
import javafx.application.Application;

// Permite cerrar la aplicación de forma segura
import javafx.application.Platform;

// Posicionamiento de nodos dentro de contenedores (VBox, etc.)
import javafx.geometry.Insets;
import javafx.geometry.Pos;

// Permite crear y configurar la escena principal (ventana)
import javafx.scene.Scene;

// Botón interactivo
import javafx.scene.control.Button;

// Efecto visual (en este caso, sombra en el texto)
import javafx.scene.effect.DropShadow;

// Clase para manejar imágenes (como el fondo)
import javafx.scene.image.Image;

// Contenedores para organizar elementos (VBox, HBox, StackPane, etc.)
import javafx.scene.layout.*;

// Colores (para textos, fondos, efectos, etc.)
import javafx.scene.paint.Color;

// Configuración de la fuente del texto (tipo, tamaño, peso)
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

// La clase Stage representa la ventana principal
import javafx.stage.Stage;

// Para cargar imágenes desde recursos
import java.io.InputStream;

// Para manejar y guardar múltiples partidas
import java.util.ArrayList;
import java.util.List;

public class AjedrezApp extends Application {

    // Lista para guardar las partidas creadas
    private List<Partida> partidas = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) {
        try {
            // Cargar imagen de fondo desde la carpeta resources
            InputStream is = getClass().getResourceAsStream("/imagenes/fondo.jpg");
            if (is == null) throw new RuntimeException("No se encontró la imagen /imagenes/fondo.jpg");
            Image backgroundImage = new Image(is);

            // Crear un fondo con la imagen cargada
            BackgroundImage background = new BackgroundImage(
                    backgroundImage,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.CENTER,
                    new BackgroundSize(100, 100, true, true, true, true)
            );

            // Crear el contenedor principal (VBox con espacio entre nodos)
            VBox root = new VBox(40);
            root.setAlignment(Pos.CENTER); // Centra los elementos en la ventana
            root.setPadding(new Insets(30)); // Márgenes internos
            root.setBackground(new Background(background)); // Establece el fondo con la imagen

            // Crear el título del juego
            Text title = new Text("AJEDREZ");
            title.setFont(Font.font("Verdana", FontWeight.BOLD, 100)); // Fuente grande y en negrita
            title.setFill(Color.GOLD); // Color del texto

            // Aplicar sombra al título
            DropShadow shadow = new DropShadow();
            shadow.setColor(Color.BLACK);
            shadow.setRadius(5);
            title.setEffect(shadow);

            // Crear botones del menú
            Button btnIniciar = createStyledButton("Iniciar Juego");
            Button btnVerPartidas = createStyledButton("Ver Partidas");
            Button btnSalir = createStyledButton("Salir");

            // Acción del botón Iniciar Juego
            btnIniciar.setOnAction(e -> {
                System.out.println("Iniciando el juego de ajedrez...");
                crearYGuardarPartida(); // Crea una nueva partida y la guarda

                // Aquí se puede cambiar a la escena del tablero usando el patrón MVC
                // Por ejemplo:
                // TableroController controlador = new TableroController();
                // controlador.mostrar(primaryStage);
            });

            // Acción del botón Ver Partidas
            btnVerPartidas.setOnAction(e -> mostrarPartidas());

            // Acción del botón Salir
            btnSalir.setOnAction(e -> Platform.exit());

            // Agregar todos los nodos al VBox
            root.getChildren().addAll(title, btnIniciar, btnVerPartidas, btnSalir);

            // Crear y mostrar la escena principal
            Scene scene = new Scene(root, 800, 600);
            primaryStage.setTitle("Ajedrez MVC");
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (Exception e) {
            // Manejo de errores si algo sale mal
            e.printStackTrace();
            System.err.println("Error al cargar la aplicación: " + e.getMessage());
        }
    }

    // Crea una nueva partida y la guarda
    private void crearYGuardarPartida() {
        Partida nueva = new Partida(); // Crear nueva instancia (asegúrate de tener esta clase)
        guardarPartida(nueva); // Guardar en la lista
    }

    // Agrega la partida a la lista
    private void guardarPartida(Partida partida) {
        partidas.add(partida);
        // Aquí se puede guardar también en archivo o base de datos si se desea
    }

    // Muestra todas las partidas guardadas en consola
    private void mostrarPartidas() {
        System.out.println("Mostrando partidas guardadas...");
        for (Partida partida : partidas) {
            System.out.println(partida);
        }
    }

    // Crea un botón estilizado con efectos
    private Button createStyledButton(String text) {
        Button button = new Button(text);
        button.setPrefWidth(250); // Ancho fijo
        button.setPrefHeight(50); // Alto fijo
        button.setFont(Font.font("Verdana", FontWeight.BOLD, 16)); // Fuente y tamaño

        // Estilo inicial
        button.setStyle(
                "-fx-background-color: rgba(80, 40, 0, 0.8);" +
                "-fx-text-fill: white;" +
                "-fx-border-color: goldenrod;" +
                "-fx-border-width: 2px;" +
                "-fx-cursor: hand;"
        );

        // Estilo al pasar el ratón
        button.setOnMouseEntered(e -> button.setStyle(
                "-fx-background-color: rgba(120, 60, 0, 0.9);" +
                "-fx-text-fill: white;" +
                "-fx-border-color: gold;" +
                "-fx-border-width: 2px;" +
                "-fx-cursor: hand;"
        ));

        // Estilo al quitar el ratón
        button.setOnMouseExited(e -> button.setStyle(
                "-fx-background-color: rgba(80, 40, 0, 0.8);" +
                "-fx-text-fill: white;" +
                "-fx-border-color: goldenrod;" +
                "-fx-border-width: 2px;" +
                "-fx-cursor: hand;"
        ));

        return button;
    }

    // Método principal para lanzar la aplicación
    public static void main(String[] args) {
        launch(args);
    }
}
