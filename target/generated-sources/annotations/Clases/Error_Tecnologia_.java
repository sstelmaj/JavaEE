package Clases;

import Clases.Error;
import Clases.Solucion;
import Clases.Tecnologia;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-04-19T20:30:35", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(Error_Tecnologia.class)
public class Error_Tecnologia_ { 

    public static volatile SingularAttribute<Error_Tecnologia, Tecnologia> tecnologia;
    public static volatile ListAttribute<Error_Tecnologia, Solucion> soluciones;
    public static volatile SingularAttribute<Error_Tecnologia, Error> error;

}