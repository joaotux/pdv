package net.originmobi.pdv.service;

import net.originmobi.pdv.model.Categoria;
import net.originmobi.pdv.repository.CategoriaRepository;
import net.originmobi.pdv.utilitarios.CategoriaFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;


@ExtendWith(SpringExtension.class)
public class CategoriaServiceTest {

    @InjectMocks
    private CategoriaService categoriaService;

    @Mock
    private CategoriaRepository categoriaRepositoryMock;

    @BeforeEach
    void setUp (){

    BDDMockito.when(categoriaRepositoryMock.findAll())
            .thenReturn(CategoriaFactory.createValidListCategoria());

    BDDMockito.when(categoriaRepositoryMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(CategoriaFactory.createValidCategoria()));

    BDDMockito.when(categoriaRepositoryMock.save(ArgumentMatchers.any(Categoria.class)))
                .thenReturn(CategoriaFactory.createValidCategoria());
    }


    @Test
    @DisplayName("Teste do Metodo lista. Retorna uma lista de categorias")
    public void listaReturnsListOfCategorias() {

        String expectedDescricao = CategoriaFactory.createValidCategoria().getDescricao();

        List<Categoria> categorias = categoriaService.lista();

        Assertions.assertThat(categorias)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(categorias.get(0).getDescricao()).isEqualTo(expectedDescricao);
    }

    @Test
    @DisplayName("Teste do Metodo busca. Retorna uma categoria buscando pelo id")
    public void buscaReturnsCategoriasById() {

        Long expectedId = CategoriaFactory.createValidCategoria().getCodigo();

        Optional<Categoria> categoria = categoriaService.busca(11L);

        Assertions.assertThat(categoria).isNotNull();

        Assertions.assertThat(categoria.get().getCodigo()).isNotNull().isEqualTo(expectedId);
    }

    @Test
    @DisplayName("Teste do metodo cadastrar")
    public void cadastraCategoria(){
        Assertions.assertThatCode(() -> categoriaService.cadastrar(CategoriaFactory.createValidCategoria())).doesNotThrowAnyException();
    }
}
