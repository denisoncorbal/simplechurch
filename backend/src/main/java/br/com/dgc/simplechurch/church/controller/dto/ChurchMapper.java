package br.com.dgc.simplechurch.church.controller.dto;

import java.util.List;

import br.com.dgc.simplechurch.church.controller.dto.request.CreateChurchRequestDto;
import br.com.dgc.simplechurch.church.controller.dto.response.CreateChurchResponseDto;
import br.com.dgc.simplechurch.church.controller.dto.response.ReadChurchResponseDto;
import br.com.dgc.simplechurch.church.model.Church;

public class ChurchMapper {
    public static Church createChurchRequestDtoToChurch(CreateChurchRequestDto createChurchRequestDto) {
        return new Church(createChurchRequestDto.getName(), createChurchRequestDto.getCnpj());
    }

    public static CreateChurchResponseDto churchToCreateChurchResponseDto(Church church) {
        return new CreateChurchResponseDto(church.getId(), church.getName());
    }

    public static ReadChurchResponseDto churchToReadChurchResponseDto(Church church) {
        return new ReadChurchResponseDto(church.getId(), church.getName(), church.getCnpj());
    }

    public static List<ReadChurchResponseDto> listChurchToListReadChurchResponseDto(List<Church> churches) {
        return churches.stream().map(ChurchMapper::churchToReadChurchResponseDto).toList();
    }
}
