package uam.edu.ni.demo.DATA;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteDATA {
    private String nombres;
    private String apellidos;
    private String tipoCliente;
    private String ciudad;
    private LocalDate fechaNacimiento;
    private String tipoSolicitud;
    private List<String> serviciosInteres;
    private String rutaFotografia;

    public String getNombreCompleto(){
        return (nombres != null ? nombres : "") + " " + (apellidos != null ? apellidos : "" );

    }

    public String getServiciosFormateados() {
        if (serviciosInteres == null || serviciosInteres.isEmpty()){
            return "Ninguno";
        }
        return String.join(", ", serviciosInteres);
    }
}