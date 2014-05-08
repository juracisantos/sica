package br.com.dynatec.entidade;

import br.com.dynatec.entidade.Usuario;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-04-21T17:11:37")
@StaticMetamodel(Grupo.class)
public class Grupo_ { 

    public static volatile SingularAttribute<Grupo, Integer> id;
    public static volatile SingularAttribute<Grupo, String> nome;
    public static volatile CollectionAttribute<Grupo, Usuario> usuarios;

}