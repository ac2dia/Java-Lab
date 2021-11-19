package io.spring.initializer.mapper;

import io.spring.initializer.domain.Account;
import io.spring.initializer.dto.AccountDto;
import io.spring.initializer.dto.AccountDto.AccountDtoBuilder;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-11-19T21:40:08+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_282 (AdoptOpenJDK)"
)
public class AccountMapperImpl implements AccountMapper {

    @Override
    public AccountDto toDto(Account e) {
        if ( e == null ) {
            return null;
        }

        AccountDtoBuilder accountDto = AccountDto.builder();

        accountDto.username( e.getUsername() );
        accountDto.password( e.getPassword() );
        accountDto.role( e.getRole() );

        return accountDto.build();
    }

    @Override
    public Account toEntity(AccountDto d) {
        if ( d == null ) {
            return null;
        }

        Account account = new Account();

        return account;
    }

    @Override
    public List<AccountDto> toDtoList(List<Account> es) {
        if ( es == null ) {
            return null;
        }

        List<AccountDto> list = new ArrayList<AccountDto>( es.size() );
        for ( Account account : es ) {
            list.add( toDto( account ) );
        }

        return list;
    }

    @Override
    public List<Account> toEntityList(List<AccountDto> ds) {
        if ( ds == null ) {
            return null;
        }

        List<Account> list = new ArrayList<Account>( ds.size() );
        for ( AccountDto accountDto : ds ) {
            list.add( toEntity( accountDto ) );
        }

        return list;
    }

    @Override
    public void updateFromDto(AccountDto dto, Account entity) {
        if ( dto == null ) {
            return;
        }
    }
}
