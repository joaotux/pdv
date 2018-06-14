package net.originmobi.pdv.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.originmobi.pdv.model.ImagemProduto;

public interface ImagemProdutoRepository extends JpaRepository<ImagemProduto, Long> {
	
	public ImagemProduto findByProdutoCodigo(Long codigo);

}
