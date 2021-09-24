package br.com.molina.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.molina.controller.dto.IntervaDto;
import br.com.molina.controller.dto.ResultDto;
import br.com.molina.model.Filme;
import br.com.molina.repository.FilmeRepository;

@RestController()
@RequestMapping("filmes")
public class FilmeController {

	@Autowired
	private FilmeRepository filmeRepository;

	@GetMapping()
	public ResultDto get() {
		List<Filme> filmes = filmeRepository.findAll();
		List<IntervaDto> listIntervals = new ArrayList<>();
		List<String> producers = new ArrayList<String>();
		filmes.stream().forEach(f -> {
			f.getProducers().stream().forEach(p -> {
				if (!producers.contains(p)) {
					producers.add(p);
				}
			});
		});

		for (String p : producers) {
			// filtra filmes vencedores de pordutores
			List<Filme> filmesProducer = filmes.stream()
					.filter(filme -> filme.getProducers().contains(p) && filme.isWinner()).collect(Collectors.toList());
			// Se produtor possui mais que 1 filme
			if (filmesProducer.size() > 1) {
				// make intervalo
				List<Integer> years = filmesProducer.stream().mapToInt(fp -> fp.getYear()).boxed().sorted()
						.collect(Collectors.toList());
				if (years.size() == 1) {
					IntervaDto min = new IntervaDto();
					min.setProducer(p);
					min.setInterval(0);
					min.setPreviousWin(years.get(0));
					min.setFollowingWin(years.get(0));
					listIntervals.add(min);
				} else {
					for (int i = years.size() - 1; i > 0; i--) {
						int interval = years.get(i) - years.get(i - 1);
						IntervaDto min = new IntervaDto();
						min.setProducer(p);
						min.setInterval(interval);
						min.setPreviousWin(years.get(i - 1));
						min.setFollowingWin(years.get(i));
						listIntervals.add(min);

					}
				}

			}

		}

		ResultDto result = new ResultDto();
		int min = listIntervals.stream().mapToInt(l -> l.getInterval()).min().getAsInt();
		List<IntervaDto> listMins = listIntervals.stream().filter(l -> l.getInterval() == min).distinct()
				.collect(Collectors.toList());
		int max = listIntervals.stream().mapToInt(l -> l.getInterval()).max().getAsInt();

		List<IntervaDto> listMaxs = listIntervals.stream().filter(l -> l.getInterval() == max).distinct()
				.collect(Collectors.toList());
		result.setMin(listMins);
		result.setMax(listMaxs);
		return result;

	}

	@PostMapping()
	public Filme save(Filme dto) {
		return filmeRepository.save(dto);
	}
}
