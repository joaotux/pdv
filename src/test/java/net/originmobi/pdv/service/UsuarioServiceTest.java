package net.originmobi.pdv.service;

import net.originmobi.pdv.model.Usuario;
import net.originmobi.pdv.repository.UsuarioRepository;
import net.originmobi.pdv.utilitarios.GrupoUsuarioFactory;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
public class UsuarioServiceTest {

    @InjectMocks
    private UsuarioService usuarioService;

    @Mock
    private UsuarioRepository usuarioRepositoryMock;
    @Mock
    private GrupoUsuarioService grupoUsuarioServiceMock;

    @BeforeEach
    void setUp (){

        BDDMockito.when(usuarioRepositoryMock.findAll())
                .thenReturn(UsuarioFactory.createListUserValid());

        BDDMockito.when(usuarioRepositoryMock.save(ArgumentMatchers.any(Usuario.class)))
                .thenReturn(UsuarioFactory.createUserValid());
    }

    @Test
    @DisplayName("Teste do metodo cadastrar")
    public void cadastrar (){

        //vai dar erro pois nao tem try catch na classe UsuarioService
        BDDMockito.when(usuarioRepositoryMock.findByUserEquals(ArgumentMatchers.anyString()))
                .thenReturn(null);

        BDDMockito.when(usuarioRepositoryMock.findByPessoaCodigoEquals(ArgumentMatchers.anyLong()))
                .thenReturn(null);

        Usuario usuario = UsuarioFactory.createUserValidToInsert();
        String expectedMsg = "Usuário salvo com sucesso";
        String msg = usuarioService.cadastrar(usuario);

        assertEquals(expectedMsg, msg);
    }

    @Test
    @DisplayName("Teste do metodo cadastrar com usuario já existente")
    public void cadastrarComUserExistente(){


        BDDMockito.when(usuarioRepositoryMock.findByUserEquals(ArgumentMatchers.anyString()))
                .thenReturn(UsuarioFactory.createUserValid());

        //vai dar erro pois nao tem try catch na classe UsuarioService
        BDDMockito.when(usuarioRepositoryMock.findByPessoaCodigoEquals(ArgumentMatchers.anyLong()))
                .thenReturn(null);

        Usuario usuario = UsuarioFactory.createUserValidToInsert();
        String expectedMsg = "Usuário já existe";
        String msg = usuarioService.cadastrar(usuario);

        assertEquals(expectedMsg, msg);
    }

    @Test
    @DisplayName("Teste do metodo cadastrar com usuario já vinculado a uma pessoa")
    public void casdastrarUserJaVinculado (){

        //vai dar erro pois nao tem try catch na classe UsuarioService
        BDDMockito.when(usuarioRepositoryMock.findByUserEquals(ArgumentMatchers.anyString()))
                .thenReturn(null);

        BDDMockito.when(usuarioRepositoryMock.findByPessoaCodigoEquals(ArgumentMatchers.anyLong()))
                .thenReturn(UsuarioFactory.createUserValidWithPeopleAndGroup());

        Usuario usuario = UsuarioFactory.createUserValidToInsert();
        String expectedMsg = "Pessoa já vinculada a outro usuário";
        String msg = usuarioService.cadastrar(usuario);

        assertEquals(expectedMsg, msg);
    }

    @Test
    @DisplayName("Teste do metodo cadastrar para atulizar")
    public void cadastrarAtualizando(){

        Usuario usuario = UsuarioFactory.createUserValid();
        String expectedMsg = "Usuário atualizado com sucesso";
        String msg = usuarioService.cadastrar(usuario);

        assertEquals(expectedMsg, msg);
    }

    @Test
    @DisplayName("Teste do metodo lista")
    public void  lista(){

        Long expectedCod = UsuarioFactory.createListUserValid().get(0).getCodigo();
        List<Usuario> usuarios = usuarioService.lista();
        Long cod = usuarios.get(0).getCodigo();
        Assertions.assertThat(usuarios)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);

        assertEquals(expectedCod, cod);

    }

    @Test
    @DisplayName("Teste do metodo addGrupo")
    public void addGrupo(){

        BDDMockito.when(usuarioRepositoryMock.findByCodigoIn(ArgumentMatchers.anyLong()))
                .thenReturn(UsuarioFactory.createUserValid());
        BDDMockito.when(grupoUsuarioServiceMock.buscaGrupo(ArgumentMatchers.anyLong()))
                .thenReturn(GrupoUsuarioFactory.createGrupoUsuarioValid());

        Long userCod = UsuarioFactory.createUserValid().getCodigo();
        Long groupCod = GrupoUsuarioFactory.createGrupoUsuarioValid().getCodigo();
        String expectedMsg = "ok";
        String msg = usuarioService.addGrupo(userCod, groupCod);

        assertEquals(expectedMsg, msg);
    }

    @Test
    @DisplayName("Teste do metodo addGrupo tentando inserir um usuario em grupo que ele já inserido ")
    public void addGrupoExistente(){
        //Deu erro pro algum motivo misterioso
        BDDMockito.when(usuarioRepositoryMock.findByCodigoIn(ArgumentMatchers.anyLong()))
                .thenReturn(UsuarioFactory.createUserValidWithPeopleAndGroup());
        BDDMockito.when(grupoUsuarioServiceMock.buscaGrupo(ArgumentMatchers.anyLong()))
                .thenReturn(GrupoUsuarioFactory.createGrupoUsuarioValid());

        Long userCod = UsuarioFactory.createUserValidWithPeopleAndGroup().getCodigo();
        Long groupCod = GrupoUsuarioFactory.createGrupoUsuarioValid().getCodigo();
        String expectedMsg = "ja existe";
        String msg = usuarioService.addGrupo(userCod, groupCod);

        assertEquals(expectedMsg, msg);
    }

    @Test
    @DisplayName("Teste do metodo removeGrupo")
    public void removeGrupo(){

        BDDMockito.when(usuarioRepositoryMock.findByCodigoIn(ArgumentMatchers.anyLong()))
                .thenReturn(UsuarioFactory.createUserValid());

        BDDMockito.when(grupoUsuarioServiceMock.buscaGrupo(ArgumentMatchers.anyLong()))
                .thenReturn(GrupoUsuarioFactory.createGrupoUsuarioValid());

        BDDMockito.when(grupoUsuarioServiceMock.buscaGrupos(ArgumentMatchers.any(Usuario.class)))
                .thenReturn(UsuarioFactory.createUserValid().getGrupoUsuario());

        Long userCod = UsuarioFactory.createUserValid().getCodigo();
        Long groupCod = GrupoUsuarioFactory.createGrupoUsuarioValid().getCodigo();
        String expectedMsg = "ok";
        String msg = usuarioService.removeGrupo(userCod, groupCod);
        assertEquals(expectedMsg, msg);

    }

    @Test
    @DisplayName("Teste do metodo buscaUsuario")
    public void buscaUsuario(){
        BDDMockito.when(usuarioRepositoryMock.findByUserEquals(ArgumentMatchers.anyString()))
                .thenReturn(UsuarioFactory.createUserValid());

        Usuario expectedUser = UsuarioFactory.createUserValid();
        String name = "gerente";
        Usuario user = usuarioService.buscaUsuario(name);

        assertNotNull(user);
        assertEquals(expectedUser.getCodigo(), user.getCodigo());
    }
}
