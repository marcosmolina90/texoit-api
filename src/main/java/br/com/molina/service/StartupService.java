package br.com.molina.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import br.com.molina.model.Filme;
import br.com.molina.repository.FilmeRepository;

@Service
public class StartupService {

	@Autowired
	private FilmeRepository filmeRepository;
	
	public void loadCsv() throws IOException, CsvException {
		List<Filme> result = new ArrayList<Filme>();
		InputStream resourceAsStream = FilmeRepository.class.getResourceAsStream("/movielist.csv");
		try (CSVReader reader = new CSVReader(new InputStreamReader(resourceAsStream))) {
			List<String[]> r = reader.readAll();
			String[] campos = r.get(0)[0].split(";");
			r.remove(0);
			r.forEach(x -> {
				String line = "";
				for (var i = 0; i < x.length; i++) {

					line = line + (i != 0 ? "," : "") + x[i].trim();
				}

				String[] split = line.split(";");
				Filme filme = new Filme();
				for (var i = 0; i < split.length; i++) {
					if (campos[i].equals("year")) {
						filme.setYear(Integer.valueOf(split[i]));
					}
					if (campos[i].equals("title")) {
						filme.setTitle(split[i]);
					}
					if (campos[i].equals("studios")) {
						filme.setStudios(Arrays.asList(split[i].split(",")));
					}
					if (campos[i].equals("studios")) {
						filme.setStudios(Arrays.asList(split[i].split(",")));
					}
					if (campos[i].equals("producers")) {
						filme.setProducers(Arrays.asList(split[i].split(",")));
					}
					if (campos[i].equals("winner")) {
						filme.setWinner(("YES").equalsIgnoreCase(split[i]) || ("SIM").equalsIgnoreCase(split[i])
								|| ("TRUE").equalsIgnoreCase(split[i]));
					}

				}
				filme = filmeRepository.save(filme);
				result.add(filme);
				filmeRepository.flush();

			});
		}

	}

}
