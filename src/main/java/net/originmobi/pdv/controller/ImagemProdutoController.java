package net.originmobi.pdv.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.originmobi.pdv.service.ImagemProdutoService;

@Controller
@RequestMapping("/imagem")
public class ImagemProdutoController {

	@Autowired
	private ImagemProdutoService imagens;

	@PostMapping("/upload")
	public @ResponseBody String upload(@RequestParam("files") MultipartFile file, @RequestParam("codigo") Long codigo,
			RedirectAttributes attributes) throws IOException {

		if (file.isEmpty())
			return "sem imagem";

		return imagens.cadastrar(file, codigo);
	}

}
