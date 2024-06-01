package dev.manyroads.angularbe.mappers;


import dev.manyroads.angularbe.model.Account;
import dev.manyroads.angularbe.model.AccountDTO;
import org.mapstruct.Mapper;

/**
 * Interface for mapping the AccountEntity and Account classes
 * To use Spring IoC in our mapper, we need to add the componentModel
 */
@Mapper(componentModel = "spring")
public interface AccountMapper {

    // From entity to domain
    // When a property has the same name as its target entity counterpart, it will be mapped implicitly
    AccountDTO entityToApi(Account account);

    // From domain to entity
    // When a property has the same name as its target entity counterpart, it will be mapped implicitly
    Account apiToEntity(AccountDTO accountDTO);
}
