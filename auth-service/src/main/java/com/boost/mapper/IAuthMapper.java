package com.boost.mapper;

import com.boost.dto.request.ActivateRequestDto;
import com.boost.dto.request.LoginRequestDto;
import com.boost.dto.request.RegisterRequestDto;
import com.boost.dto.response.LoginResponseDto;
import com.boost.dto.response.RegisterResponseDto;
import com.boost.repository.entity.Auth;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface IAuthMapper {

    IAuthMapper INSTANCE=Mappers.getMapper(IAuthMapper.class);

    Auth toAuth(final RegisterRequestDto dto);

    Auth toAuth(final LoginRequestDto dto);
    LoginRequestDto toLoginRequestDto(final Auth auth);

    Auth toAuth(final LoginResponseDto dto);
    LoginResponseDto toLoginResponseDto(final Auth auth);

    RegisterResponseDto toRegisterResponseDto(final Auth auth);

    Auth toAuth(final ActivateRequestDto dto);
}
