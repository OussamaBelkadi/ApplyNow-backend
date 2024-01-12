package example.brief.MyRh.mappers;

import example.brief.MyRh.dtos.paiement.AccountDTO;
import example.brief.MyRh.entities.Account;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AccountMapper {
    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    Account toEntity(AccountDTO accountDTO);
    AccountDTO toDTO(Account account);
}
