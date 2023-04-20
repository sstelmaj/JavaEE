package Clases;

import Clases.Error_Etiqueta;
import Clases.Error_Tecnologia;
import Clases.Usuario;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-04-19T20:30:35", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(Error.class)
public class Error_ { 

    public static volatile SingularAttribute<Error, String> descripcion;
    public static volatile ListAttribute<Error, Error_Etiqueta> error_Etiquetas;
    public static volatile SingularAttribute<Error, String> repositorio;
    public static volatile SingularAttribute<Error, String> codigo;
    public static volatile SingularAttribute<Error, Date> fechaSubida;
    public static volatile SingularAttribute<Error, String> consola;
    public static volatile ListAttribute<Error, Error_Tecnologia> error_Tecnologias;
    public static volatile SingularAttribute<Error, String> link;
    public static volatile SingularAttribute<Error, String> titulo;
    public static volatile SingularAttribute<Error, String> imagen;
    public static volatile SingularAttribute<Error, Usuario> usuario;
    public static volatile SingularAttribute<Error, Long> id;

}