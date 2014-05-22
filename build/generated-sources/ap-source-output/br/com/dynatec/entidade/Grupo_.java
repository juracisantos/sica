package br.com.dynatec.entidade;

import br.com.dynatec.entidade.Usuario;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-05-19T16:46:44")
@StaticMetamodel(Grupo.class)
public class Grupo_ { 

    public static volatile SingularAttribute<Grupo, Integer> id;
    public static volatile SingularAttribute<Grupo, String> nome;
    public static volatile CollectionAttribute<Grupo, Usuario> usuarios;

}