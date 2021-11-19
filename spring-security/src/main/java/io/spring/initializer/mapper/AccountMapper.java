package io.spring.initializer.mapper;

import io.spring.initializer.domain.Account;
import io.spring.initializer.dto.AccountDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AccountMapper extends GenericMapper<AccountDto, Account> {

  AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

}
