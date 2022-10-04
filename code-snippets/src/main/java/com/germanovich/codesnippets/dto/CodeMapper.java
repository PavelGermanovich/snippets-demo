package com.germanovich.codesnippets.dto;

import com.germanovich.codesnippets.model.Code;
import lombok.Data;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Component
@DependsOn("modelMapper")
@Data
public class CodeMapper {
    private ModelMapper modelMapper;
    private Converter<LocalDateTime, String> convertDateToString = (src) -> src.getSource()
            .format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));

    public CodeMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        modelMapper.createTypeMap(Code.class, CodeDto.class)
                .addMappings(mapper -> mapper.using(convertDateToString).map(Code::getDate, CodeDto::setDate));
    }

    public CodeDto toDto(Code code) {
        return Objects.isNull(code) ? null : modelMapper.map(code, CodeDto.class);
    }

    public Code toEntity(CodeDto codeDto) {
        Code code = modelMapper.map(codeDto, Code.class);
        if (codeDto.getTime() > 0) {
            code.setTimeRestrictionApplied(true);
        }
        if (codeDto.getViews() > 0) {
            code.setViewsRestrictionApplied(true);
        }
        return code;
    }
}
