import java.time.LocalDate;
import java.util.UUID;

public class Tareas extends gestionTareas{
    private String id;
    private String titulo;
    private String descripcion;
    private LocalDate fecVenci;
    private String prioridad;
    private String estado;

    public Tareas(){

    }

    public Tareas(String titulo, String descripcion, LocalDate fecVenci, String prioridad, String estado) {
        this.id = UUID.randomUUID().toString();
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fecVenci = fecVenci;
        this.prioridad = prioridad;
        this.estado = estado;
    }

    public String getId() {
        return id;
    }
    public String getTitulo() {
        return titulo;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public LocalDate getFecVenci() {
        return fecVenci;
    }
    public String getPrioridad() {
        return prioridad;
    }
    public String getEstado() {
        return estado;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public void setFecVenci(LocalDate fecVenci) {
        this.fecVenci = fecVenci;
    }
    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    @Override
    public String toString() {
        return "Tareas: [Id=" + id + 
               ", \nTitulo=" + titulo + 
               ", \nDescripcion=" + descripcion + 
               ", \nFecha de Vencimiento=" + fecVenci + 
               ", \nPrioridad=" + prioridad + 
               ", \nEstado=" + estado + "]";
    }

    
}
