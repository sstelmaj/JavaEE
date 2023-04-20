package Clases;

import Clases.Error_Tecnologia;
import Clases.Usuario;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-04-19T20:30:35", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(Solucion.class)
public class Solucion_ { 

    public static volatile SingularAttribute<Solucion, String> descripcion;
    public static volatile SingularAttribute<Solucion, String> codigo;
    public static volatile SingularAttribute<Solucion, Date> fechaSubida;
    public static volatile SingularAttribute<Solucion, String> link;
    public static volatile SingularAttribute<Solucion, String> imagen;
    public static volatile SingularAttribute<Solucion, Error_Tecnologia> error_Tecnologia;
    public static volatile SingularAttribute<Solucion, Usuario> usuario;
    public static volatile SingularAttribute<Solucion, Long> id;

}