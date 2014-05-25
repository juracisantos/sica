package br.com.dynatec.persistencia;

import br.com.dynatec.entidade.Grupo;
import java.util.List;

public class GrupoDao extends PersistenciaJpa {

    public List<Grupo> findAll() {
        return findAll(Grupo.class, "select e from Grupo e");
    }
    
    public Grupo findByNome(String nome) {
        return find(Grupo.class, "select e from Grupo e where e.nome = ?1", nome);
    }
    
    public Grupo salvar(Grupo grupo) throws Exception {
        return save(Grupo.class, grupo);
    }
    
    public Grupo find(Integer idGrupo) {
        return find(Grupo.class, idGrupo);
    }
}
