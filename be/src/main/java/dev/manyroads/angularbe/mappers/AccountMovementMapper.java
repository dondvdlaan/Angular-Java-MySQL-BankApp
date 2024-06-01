package dev.manyroads.angularbe.mappers;


import dev.manyroads.angularbe.model.AccountMovement;
import org.mapstruct.Mapper;

/**
 * Interface for mapping the AccountMovementEntity and AccountMovement classes
 * To use Spring IoC in our mapper, we need to add the componentModel
 */
@Mapper(componentModel = "spring")
public interface AccountMovementMapper {

    // From entity to domain
    // When a property has the same name as its target entity counterpart, it will be mapped implicitly
    AccountMovement entityToApi(AccountMovement accountMovementEntity);

    // From domain to entity
    // When a property has the same name as its target entity counterpart, it will be mapped implicitly
    AccountMovement apiToEntity(AccountMovement accountMovement);
}
