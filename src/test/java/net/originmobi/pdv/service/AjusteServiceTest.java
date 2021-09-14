package net.originmobi.pdv.service;

import net.originmobi.pdv.enumerado.EntradaSaida;
import net.originmobi.pdv.model.Ajuste;
import net.originmobi.pdv.repository.AjusteRepository;
import net.originmobi.pdv.utilitarios.AjusteFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = { SecurityContextHolder.class, SecurityContext.class })
public class AjusteServiceTest {

    @InjectMocks
    private AjusteService ajusteService;

    @Mock
    private AjusteRepository ajusteRepositoryMock;

    @Mock
    private ProdutoService produtoServiceMock;

    @BeforeEach
    void setUp(){

        BDDMockito.when(ajusteRepositoryMock.findById(ArgumentMatchers.anyLong()))
            .thenReturn(Optional.of(AjusteFactory.createValidAjuste()));


        BDDMockito.doNothing().when(ajusteRepositoryMock).deleteById(ArgumentMatchers.anyLong());

        BDDMockito.doNothing().when(produtoServiceMock).ajusteEstoque(Mockito.anyLong(),
                                                                     Mockito.anyInt(),
                                                                     Mockito.any(EntradaSaida.class),
                                                                     Mockito.anyString(),
                                                                     Mockito.any(java.sql.Date.class));
    }

    @Test
    @DisplayName("Teste do metodo Busca (findById)")
    public void busca(){
        Ajuste expectedAjuste = AjusteFactory.createValidAjuste();
        Optional<Ajuste> ajuste = ajusteService.busca(expectedAjuste.getCodigo());
        assertEquals(expectedAjuste.getCodigo(), ajuste.get().getCodigo());
    }

    @Test
    @DisplayName("Teste do metodo novo")
    @WithMockUser("teste")
    public void novo(){

        BDDMockito.when(ajusteRepositoryMock.save(ArgumentMatchers.any(Ajuste.class)))
                .thenReturn(AjusteFactory.createValidAjuste());
        Long expectedCodigo = AjusteFactory.createValidAjuste().getCodigo();
        Long codigoSalvo = ajusteService.novo();
        assertEquals(expectedCodigo, codigoSalvo);
    }

    @Test
    @DisplayName("Teste do metodo processar")
    public void processar(){
        BDDMockito.when(ajusteRepositoryMock.save(ArgumentMatchers.any(Ajuste.class)))
                .thenReturn(AjusteFactory.createValidAjusteProcessado());
        String expectedResposta = "Ajuste realizado com sucesso";
        Ajuste ajusteAProcessar = AjusteFactory.createValidAjuste();
        String resposta = ajusteService.processar(ajusteAProcessar.getCodigo(), ajusteAProcessar.getObservacao());
        assertEquals(expectedResposta, resposta);
    }

    @Test
    @DisplayName("Teste do metodo remover")
    public void remover(){
        Assertions.assertThatCode(() ->ajusteService.remover(AjusteFactory.createValidAjuste()))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("Teste do metodo remover tentado remover um ajuste ja procesado")
    public void removerAjusteProcessado(){
        RuntimeException runtimeException = assertThrows(RuntimeException.class,
                () ->ajusteService.remover(AjusteFactory.createValidAjusteProcessado()));
       assertTrue(runtimeException.getMessage().contains("O ajuste já esta processado"));
    }
}
