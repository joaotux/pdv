package net.originmobi.pdv.utilitarios;
import com.ibm.icu.util.TimeZone;
import net.originmobi.pdv.model.Categoria;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class CategoriaFactory {

    public static Categoria createValidCategoria(){
        Categoria cat = new Categoria();
        cat.setCodigo(11L);
        cat.setDataCadastro(new Date(System.currentTimeMillis()));
        cat.setDescricao("Categoria valida");
        return cat;
    }
    public static List<Categoria> createValidListCategoria(){
        Categoria cat = createValidCategoria();
        List<Categoria> categorias = new ArrayList<>();
        categorias.add(cat);
        return categorias;
    }
}
