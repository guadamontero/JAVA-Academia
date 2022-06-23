import com.company.dao.EstudianteDAOH2;
import com.company.model.Estudiante;
import com.company.service.EstudianteService;

public class Main {
    public static void main(String[] args) {

        Estudiante estudiante = new Estudiante();

        estudiante.setId(1L);
        estudiante.setNombre("Guadalupe");
        estudiante.setApellido("Montero");

        EstudianteService estudianteService = new EstudianteService();
        //Le seteamos una estrategia de presistencia, es decir un DAO
        estudianteService.setEstudianteIDao(new EstudianteDAOH2());

        estudianteService.guardarEstudiante(estudiante);

        //estudianteService.eliminarEstudiante(1L);






    }
}