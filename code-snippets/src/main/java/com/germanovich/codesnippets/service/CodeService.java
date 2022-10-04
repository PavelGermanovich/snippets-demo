package com.germanovich.codesnippets.service;

import com.germanovich.codesnippets.dto.CodeDto;
import com.germanovich.codesnippets.dto.CodeMapper;
import com.germanovich.codesnippets.model.Code;
import com.germanovich.codesnippets.repository.CodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CodeService {
    private final CodeMapper codeMapper;
    private final CodeRepository codeRepository;

    @Autowired
    public CodeService(CodeMapper codeMapper, CodeRepository codeRepository) {
        this.codeMapper = codeMapper;
        this.codeRepository = codeRepository;
    }

    @Transactional
    public UUID saveNewCode(CodeDto codeDtoRequest) {
        Code code = codeMapper.toEntity(codeDtoRequest);
        code.setDate(LocalDateTime.now());
        code = codeRepository.save(code);
        return code.getId();
    }

    public CodeDto getCodeById(UUID id) {
        Code code = codeRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if (code.isViewsRestrictionApplied()) {
            handleViewRestrictions(code);
        }
        if (code.isTimeRestrictionApplied()) {
            handleTimeRestrictions(code);
        }
        return codeMapper.toDto(code);
    }

    private void handleViewRestrictions(Code code) {
        if (code.getViews() > 0) {
            code.setViews(code.getViews() - 1);
            codeRepository.save(code);
        } else {
            codeRepository.delete(code);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    private void handleTimeRestrictions(Code code) {
        int secRemains = code.getTime() - (int) ChronoUnit.SECONDS.between(code.getDate(), LocalDateTime.now());
        if (secRemains <= 0) {
            codeRepository.delete(code);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else {
            code.setTime(secRemains);
        }
    }

    public List<CodeDto> getLatestCodeSnippets() {
        List<Code> result = codeRepository.findLatestSnippets();
        return result.stream().map(codeMapper::toDto).collect(Collectors.toList());
    }
}
