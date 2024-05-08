package br.com.dgc.simplechurch.church.service;

import org.springframework.stereotype.Service;

import br.com.dgc.simplechurch.church.model.Church;
import br.com.dgc.simplechurch.church.repository.ChurchRepository;

@Service
public class ChurchService {
    private ChurchRepository churchRepository;

    public ChurchService(ChurchRepository churchRepository) {
        this.churchRepository = churchRepository;
    }

    public Church createChurch(Church church) {
        return this.churchRepository.save(church);
    }
}
