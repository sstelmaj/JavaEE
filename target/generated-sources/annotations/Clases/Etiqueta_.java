package Clases;

import Clases.Error_Etiqueta;
import Clases.Etiqueta;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-04-19T20:30:35", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(Etiqueta.class)
public class Etiqueta_ { 

    public static volatile ListAttribute<Etiqueta, Error_Etiqueta> error_Etiquetas;
    public static volatile ListAttribute<Etiqueta, Etiqueta> subEtiquetas;
    public static volatile SingularAttribute<Etiqueta, String> nombre;

}