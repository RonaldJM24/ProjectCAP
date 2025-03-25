import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public abstract class gestionTareas {

    Scanner sc = new Scanner(System.in);
    private List<Tareas> listTareas = new ArrayList<>();
    public void addTareas(){
        LocalDate fechaVencimiento;
        System.out.println("\n--- Agregar Tarea ---");
        System.out.print("Título: ");
        String titulo = getStringVacio("Título");
        System.out.print("Descripción: ");
        String descripcion = getStringVacio("Descripción");
        do{
            System.out.print("Fecha de vencimiento: ");
            fechaVencimiento = validaFecha(sc.nextLine());
        }while(fechaVencimiento == null);
        
        System.out.print("Prioridad (Baja, Media, Alta): ");
        String prioridad = getPrioridadValida();
 
        Tareas nuevaTarea = new Tareas(titulo, descripcion, fechaVencimiento, prioridad, "Pendiente");
        listTareas.add(nuevaTarea);
        System.out.println("Tarea agregada con éxito. " + listTareas.toString());
    }

    public void editarTarea() {
        System.out.println("\n--- Editar Tarea ---");
        System.out.print("Ingrese el ID de la tarea a editar: ");
        String id = sc.nextLine();
 
        Tareas tareaAEditar = buscarTareaPorId(id);
        if (tareaAEditar == null) {
            System.out.println("No se encontró ninguna tarea con ese ID.");
            return;
        }else{
            System.out.println("ID de tarea a Editar: " + id);
            System.out.print("Nuevo título: ");
            tareaAEditar.setTitulo(getStringVacio("Título"));
            System.out.print("Nueva descripción: ");
            tareaAEditar.setDescripcion(getStringVacio("Descripción"));
            System.out.print("Nueva fecha de vencimiento: ");
            tareaAEditar.setFecVenci(validaFecha(sc.nextLine()));
            System.out.print("Nueva prioridad (Baja, Media, Alta): ");
            tareaAEditar.setPrioridad(getPrioridadValida());
    
            System.out.println("Tarea editada con éxito." + listTareas.toString());
        }
    }
    
    public void eliminarTarea(){
        System.out.println("\n--- Eliminar Tarea ---");
        System.out.print("Ingrese el ID de la tarea a eliminar: ");
        String id = sc.nextLine();
 
        Tareas tareaAEliminar = buscarTareaPorId(id);
        if (tareaAEliminar == null) {
            System.out.println("No se encontró ninguna tarea con ese ID.");
            return;
        }
 
        listTareas.remove(tareaAEliminar);
        System.out.println("Tarea eliminada con éxito.");
    }

    public void listarTareas() {
        System.out.println("\n--- Lista de Todas las Tareas ---");
        if (listTareas.isEmpty()) {
            System.out.println("No hay tareas registradas.");
            return;
        }
 
        for (Tareas tarea : listTareas) {
            System.out.println(tarea);
        }
    }

    public void tareaCompletada() {
        System.out.println("\n--- Marcar Tarea como Completada ---");
        System.out.print("Ingrese el ID de la tarea: ");
        String id = sc.nextLine();
 
        Tareas tareaACompletar = buscarTareaPorId(id);
        if (tareaACompletar == null) {
            System.out.println("No se encontró ninguna tarea con ese ID.");
            return;
        }
 
        tareaACompletar.setEstado("Completada");
        System.out.println("Tarea marcada como completada." + listTareas.toString());
    }

    public void ordenarTareasMenu() {
        int opcion;
        do {
            System.out.println("\n--- Ordenar Tareas ---");
            System.out.println("1. Ordenar por prioridad");
            System.out.println("2. Ordenar por fecha de vencimiento");
            System.out.println("3. Volver al menú principal");
            System.out.print("Ingrese una opción: ");
            opcion = obtenerEnteroValido(1, 3);
 
            switch (opcion) {
                case 1:
                    ordenarPorPrioridad();
                    break;
                case 2:
                    ordenarPorFechaVencimiento();
                    break;
                case 3:
                    break;
            }
        } while (opcion != 3);
    }

    public void filtrarTareasMenu() {
        int opcion;
        do {
            System.out.println("\n--- Filtrar Tareas ---");
            System.out.println("1. Mostrar tareas completadas");
            System.out.println("2. Mostrar tareas pendientes");
            System.out.println("3. Volver al menú principal");
            System.out.print("Ingrese una opción: ");
            opcion = obtenerEnteroValido(1, 3);
 
            switch (opcion) {
                case 1:
                    filtrarTareasPorEstado("Completada");
                    break;
                case 2:
                    filtrarTareasPorEstado("Pendiente");
                    break;
                case 3:
                    break;
            }
        } while (opcion != 3);
    }

    public void filtrarTareasPorEstado(String estado) {
        System.out.println("\n--- Tareas " + estado + " ---");
        boolean hayTareas = false;
        for (Tareas tarea : listTareas) {
            if (tarea.getEstado().equalsIgnoreCase(estado)) {
                System.out.println(tarea);
                hayTareas = true;
            }
        }
        if (!hayTareas) {
            System.out.println("No hay tareas " + estado + ".");
        }
    }

    private String getStringVacio(String campo){
        String valor;
        do {
            valor = sc.nextLine().trim();
            if (valor.isEmpty()) {
                System.out.print(campo + " no puede estar vacío. Ingrese nuevamente: ");
            }
        } while (valor.isEmpty());
        return valor;
    }

    private LocalDate validaFecha(String fechaVen) {

        try {
            // Formato esperado de la fecha
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            // Intentamos parsear la fecha
            LocalDate fecha = LocalDate.parse(fechaVen, formatter);
            return fecha;
            
        } catch (DateTimeParseException e) {
            System.out.println("Error: la fecha ingresada no es válida. Formato esperado: dd/MM/yyyy");
            return null;

        }
        
    }

    private String getPrioridadValida() {
        String prioridad;
        while (true) {
            prioridad = sc.nextLine().trim();
            if (prioridad.equalsIgnoreCase("Baja") || prioridad.equalsIgnoreCase("Media") || prioridad.equalsIgnoreCase("Alta")) {
                break;
            } else {
                System.out.print("Prioridad inválida. Ingrese Baja, Media o Alta: ");
            }
        }
        return prioridad;
    }

    private Tareas buscarTareaPorId(String id) {
        for (Tareas tarea : listTareas) {
            if (tarea.getId() == id) {
                return tarea;
            }
        }
        return null;
    }

    public int obtenerEnteroValido(int min, int max) {
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

    public void ordenarPorPrioridad() {
        listTareas.sort(Comparator.comparing(Tareas::getPrioridad));
        System.out.println("Tareas ordenadas por prioridad.");
        listarTareas();
    }
 
    public void ordenarPorFechaVencimiento() {
        listTareas.sort(Comparator.comparing(Tareas::getFecVenci));
        System.out.println("Tareas ordenadas por fecha de vencimiento.");
        listarTareas();
    }


}
