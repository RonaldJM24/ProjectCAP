import java.util.Scanner;

public class App {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        int option;
        Tareas T = new Tareas();
        do{
            System.out.println("\n---- Menú Principal ----");
            System.out.println("1. Agregar tarea");
            System.out.println("2. Editar tarea");
            System.out.println("3. Eliminar tarea");
            System.out.println("4. Listar todas las tareas");
            System.out.println("5. Marcar tarea como completada");
            System.out.println("6. Ordenar tareas");
            System.out.println("7. Filtrar tareas");
            System.out.println("8. Salir");
            System.out.print("Ingrese una opción: ");
            option = obtenerEnteroValido(1, 8);

            switch (option) {
                case 1:
                    T.addTareas();
                    break;
                case 2:
                    T.editarTarea();
                    break;
                case 3:
                    //eliminarTarea();
                    break;
                case 4:
                    //listarTareas();
                    break;
                case 5:
                    //tareaCompletada();
                    break;
                case 6:
                    //ordenarTareas();
                    break;
                case 7:
                    //filtarTareas();
                    break;
                case 8:
                    System.out.println("Thanks you, Finalizando Ejecucion....");
                    break;
            }
        }while(option != 8);
    }

    static int obtenerEnteroValido(int min, int max) {
        int valor;
        while (true) {
            try {
                valor = Integer.parseInt(sc.nextLine());
                if (valor >= min && valor <= max) {
                    break;
                } else {
                    System.out.print("Ingrese un valor entre " + min + " y " + max + ": ");
                }
            } catch (NumberFormatException e) {
                System.out.print("Ingrese un número válido: ");
            }
        }
        return valor;
    }
}
