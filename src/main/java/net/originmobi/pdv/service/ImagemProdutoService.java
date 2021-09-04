package net.originmobi.pdv.service;

import net.originmobi.pdv.model.ImagemProduto;
import net.originmobi.pdv.model.Produto;
import net.originmobi.pdv.repository.ImagemProdutoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Random;

@Service
public class ImagemProdutoService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ImagemProdutoRepository imagens;

    @Autowired
    private ProdutoService produtos;

    private Path diretorio;

    public String cadastrar(MultipartFile file, Long codigoProduto) throws IOException {

        Random vlAleatorio = new Random();

        // aqui pego o contexto da aplicação
        String contexto;
        try {
            contexto = new File(".").getCanonicalPath();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            return "erro ao pegar o contexto da aplicação";
        }

        // defino o diretorio onde será salva a imagem
        diretorio = Paths.get(contexto.replace("/bin", "") + "/webapps/pdv/WEB-INF/classes/static/imagens-produtos/");

        // altero o nome da imagem e o seu tipo para jpg
        String originalFilename = file.getOriginalFilename();
        String imagemNovaDescricao = originalFilename != null
                ? originalFilename.replaceAll(originalFilename,
                "imagem" + "-" + vlAleatorio.nextInt() + ".jpg")
                : null;

        try (FileOutputStream output = new FileOutputStream(diretorio.toString() + "/" + imagemNovaDescricao)) {
            output.write(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
            return "erro ao salvar aquivo no diretorio";
        }

        removeImagem(codigoProduto);

        return salvaImagemDB(imagemNovaDescricao, codigoProduto);

    }

    private String salvaImagemDB(String descricao, Long codigo) {
        String uri = diretorio.toString() + "/" + descricao;
        LocalDate dataAtual = LocalDate.now();
        Produto produto = produtos.busca(codigo);

        ImagemProduto imagemProduto = new ImagemProduto(descricao, uri, java.sql.Date.valueOf(dataAtual), produto);

        try {
            imagens.save(imagemProduto);
        } catch (Exception e) {
            e.printStackTrace();
            return "erro ao gravar imagem no banco de dados";
        }
        return "ok";
    }

    private void removeImagem(Long codigoProduto) {
        ImagemProduto imagem = imagens.findByProdutoCodigo(codigoProduto);

        if (imagem != null) {
            imagens.delete(imagem);

            Path path = Paths.get(imagem.getUri());

            FileSystemUtils.deleteRecursively(path.toFile());
        }
    }

    public ImagemProduto busca(Long codigoProduto) {
        ImagemProduto imagemPadrao = new ImagemProduto("image-upload.png", "", null, null);
        ImagemProduto imagem = imagens.findByProdutoCodigo(codigoProduto);
        return imagem == null ? imagemPadrao : imagem;
    }

}
