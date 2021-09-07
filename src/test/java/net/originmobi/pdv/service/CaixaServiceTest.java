package net.originmobi.pdv.service;

import net.originmobi.pdv.enumerado.caixa.CaixaTipo;
import net.originmobi.pdv.model.Caixa;
import net.originmobi.pdv.repository.CaixaRepository;
import net.originmobi.pdv.utilitarios.CaixaFactory;
import net.originmobi.pdv.utilitarios.UsuarioFactory;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
public class CaixaServiceTest {

    @InjectMocks
    private CaixaService caixaService;

    @Mock
    private CaixaRepository caixaRepositoryMock;

    @Mock
    private UsuarioService usuarioServiceMock;

    @BeforeEach
    void setUp () {

        BDDMockito.when(caixaRepositoryMock.caixaAberto())
                .thenReturn(Optional.of(CaixaFactory.createValidCaixaToBeClosed(CaixaTipo.valueOf("CAIXA"))));
        BDDMockito.when(caixaRepositoryMock.caixasAbertos())
                .thenReturn(CaixaFactory.createValidListCaixa(CaixaTipo.valueOf("CAIXA")));


        BDDMockito.when(usuarioServiceMock.buscaUsuario(ArgumentMatchers.anyString()))
                .thenReturn((UsuarioFactory.createUserValid()));

        BDDMockito.when(caixaRepositoryMock.save(ArgumentMatchers.any(Caixa.class)))
                .thenReturn(CaixaFactory.createValidCaixa(CaixaTipo.valueOf("CAIXA")));
    }

    @Test
    @DisplayName("Teste do metodo cadastro")
    public void cadastraCaixa() {
        Caixa caixa = CaixaFactory.createValidCaixa(CaixaTipo.valueOf("CAIXA"));
        Long cod = caixaService.cadastro(caixa);
        Assertions.assertThat(cod).isNotNull().isEqualTo(caixa.getCodigo());
    }
    @Test
    @DisplayName("Teste do metodo fechaCaixa")
    public void fechaCaixa() {

        BDDMockito.when(caixaRepositoryMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(CaixaFactory.createValidCaixaToBeClosed(CaixaTipo.valueOf("CAIXA"))));

        String expectedMsg = "Caixa fechado com sucesso";
        Caixa caixa = CaixaFactory.createValidCaixaToBeClosed(CaixaTipo.valueOf("CAIXA"));
        String msg = caixaService.fechaCaixa(caixa.getCodigo(), caixa.getUsuario().getSenha());
        assertEquals(expectedMsg, msg);

    }

    @Test
    @DisplayName("Teste do metodo caixaAberto")
    public void caixaAberto(){

        BDDMockito.when(caixaRepositoryMock.caixaAberto())
                .thenReturn(Optional.of(CaixaFactory.createValidCaixaToBeClosed(CaixaTipo.valueOf("CAIXA"))));

        Caixa cx = CaixaFactory.createValidCaixaToBeClosed(CaixaTipo.valueOf("CAIXA"));
        Optional<Caixa> caixa = caixaService.caixaAberto();
        Assertions.assertThat(caixa).isNotNull();
        Assertions.assertThat(caixa.get().getCodigo()).isNotNull().isEqualTo(cx.getCodigo());

    }

    @Test
    @DisplayName("Teste do metodo caixaAbertos")
    public void CaixasAbertos(){
        Long expectedCodigo = CaixaFactory.createValidCaixaToBeClosed(CaixaTipo.valueOf("CAIXA")).getCodigo();
        List<Caixa> caixas = caixaService.caixasAbertos();
        Assertions.assertThat(caixas)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(caixas.get(0).getCodigo()).isEqualTo(expectedCodigo);
    }

    @Test
    @DisplayName("Teste do metodo busca")
    public void busca(){

        BDDMockito.when(caixaRepositoryMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(CaixaFactory.createValidCaixa(CaixaTipo.valueOf("CAIXA"))));

        Caixa cx = CaixaFactory.createValidCaixa(CaixaTipo.valueOf("CAIXA"));
        Optional<Caixa> caixa = caixaService.busca(cx.getCodigo());
        assertNotNull(caixa);
        assertEquals(cx.getCodigo(), caixa.get().getCodigo());
    }

    @Test
    @DisplayName("Teste do metodo listaCaixasAbertosTipo com tipo = caixa")
    public void listaCaixasAbertosTipoCAIXA() {

        BDDMockito.when(caixaRepositoryMock.buscaCaixaTipo(ArgumentMatchers.any(CaixaTipo.class)))
                .thenReturn(CaixaFactory.createValidListCaixa(CaixaTipo.valueOf("CAIXA")));

        List<Caixa> cxs = CaixaFactory.createValidListCaixa(CaixaTipo.valueOf("CAIXA"));
        List<Caixa> caixas = caixaService.listaCaixasAbertosTipo(CaixaTipo.valueOf("CAIXA"));
        Assertions.assertThat(caixas)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(caixas.get(0).getCodigo()).isEqualTo(cxs.get(0).getCodigo());
        assertEquals(caixas.get(0).getTipo(), CaixaTipo.CAIXA);
    }

    @Test
    @DisplayName("Teste do metodo listaCaixasAbertosTipo com tipo = banco")
    public void listaCaixasAbertosTipoBANCO() {

        BDDMockito.when(caixaRepositoryMock.buscaCaixaTipo(ArgumentMatchers.any(CaixaTipo.class)))
                .thenReturn(CaixaFactory.createValidListCaixa(CaixaTipo.valueOf("BANCO")));

        List<Caixa> cxs = CaixaFactory.createValidListCaixa(CaixaTipo.valueOf("BANCO"));
        List<Caixa> caixas = caixaService.listaCaixasAbertosTipo(CaixaTipo.valueOf("BANCO"));
        Assertions.assertThat(caixas)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(caixas.get(0).getCodigo()).isEqualTo(cxs.get(0).getCodigo());
        assertEquals(caixas.get(0).getTipo(), CaixaTipo.BANCO);
    }
}
