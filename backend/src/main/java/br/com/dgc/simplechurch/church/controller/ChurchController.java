package br.com.dgc.simplechurch.church.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.dgc.simplechurch.church.controller.dto.ChurchMapper;
import br.com.dgc.simplechurch.church.controller.dto.request.CreateChurchRequestDto;
import br.com.dgc.simplechurch.church.controller.dto.response.CreateChurchResponseDto;
import br.com.dgc.simplechurch.church.controller.dto.response.ReadChurchResponseDto;
import br.com.dgc.simplechurch.church.model.Church;
import br.com.dgc.simplechurch.church.service.ChurchService;

@RestController
@RequestMapping("/api/v1/church")
public class ChurchController {
    private ChurchService churchService;

    public ChurchController(ChurchService churchService) {
        this.churchService = churchService;
    }

    @PostMapping
    public ResponseEntity<CreateChurchResponseDto> createChurch(
            @RequestBody CreateChurchRequestDto createChurchRequestDto) {
        Church church = ChurchMapper.createChurchRequestDtoToChurch(createChurchRequestDto);
        church = this.churchService.createChurch(church);
        return ResponseEntity.status(HttpStatus.CREATED).body(ChurchMapper.churchToCreateChurchResponseDto(church));
    }

    @GetMapping
    public ResponseEntity<List<ReadChurchResponseDto>> readAllChurches() {
        List<Church> churches = this.churchService.readAllChurches();
        return ResponseEntity.ok(ChurchMapper.listChurchToListReadChurchResponseDto(churches));
    }

    @PutMapping
    public ResponseEntity<?> updateChurch() {
        return null;
    }

    @DeleteMapping
    public ResponseEntity<?> deleteChurch() {
        return null;
    }
}
