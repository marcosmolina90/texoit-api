package br.com.molina.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.molina.controller.dto.ResultDto;
import br.com.molina.model.Filme;
import br.com.molina.repository.FilmeRepository;
import br.com.molina.service.FilmeService;

@RestController()
@RequestMapping("filmes")
public class FilmeController {

	@Autowired
	private FilmeRepository filmeRepository;

	@Autowired
	private FilmeService filmeService;

	@GetMapping()
	public ResultDto get() {
		return filmeService.getIterval();
	}

	@PostMapping()
	public Filme save(Filme dto) {
		return filmeRepository.save(dto);
	}
}
