package br.com.dynatec.entidade;

import br.com.dynatec.entidade.Grupo;
import br.com.dynatec.entidade.Pessoa;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-05-19T16:46:44")
@StaticMetamodel(Usuario.class)
public class Usuario_ { 

    public static volatile SingularAttribute<Usuario, Integer> id;
    public static volatile SingularAttribute<Usuario, Date> updatedAt;
    public static volatile SingularAttribute<Usuario, Grupo> grupo;
    public static volatile SingularAttribute<Usuario, String> email;
    public static volatile SingularAttribute<Usuario, Boolean> ativo;
    public static volatile SingularAttribute<Usuario, Date> createdAt;
    public static volatile SingularAttribute<Usuario, Boolean> eAdmin;
    public static volatile SingularAttribute<Usuario, Pessoa> pessoa;
    public static volatile SingularAttribute<Usuario, String> nome;
    public static volatile SingularAttribute<Usuario, String> senha;

}