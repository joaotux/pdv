package net.originmobi.pdv.service;

import net.originmobi.pdv.model.Titulo;
import net.originmobi.pdv.repository.TituloRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TituloService {

    private final Logger logger = LoggerFactory.getLogger(TituloService.class);

    @Autowired
    private TituloRepository titulos;

    public List<Titulo> lista() {
        return titulos.findAll();
    }

    public Optional<Titulo> busca(Long codigo) {
        return titulos.findById(codigo);
    }

    public void cadastro(Titulo titulo) {
        try {
            titulos.save(titulo);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public String remover(Long codigo) {
        try {
            titulos.deleteById(codigo);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new RuntimeException("Erro ao tentar remover este registro, chame o suporte");
        }

        return "Registro removido com sucesso";
    }

}
