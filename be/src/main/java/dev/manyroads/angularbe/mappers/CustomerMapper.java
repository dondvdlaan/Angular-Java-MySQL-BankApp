package dev.manyroads.angularbe.mappers;


import dev.manyroads.angularbe.model.Customer;
import org.SwaggerCodeGen.model.CustomerDto;
import org.mapstruct.Mapper;

/**
 * Interface for mapping the CustomerDto and Customer classes
 * To use Spring IoC in our mapper, we need to add the componentModel
 */
@Mapper(componentModel = "spring")
public interface CustomerMapper {

    // From entity to domain
    // When a property has the same name as its target entity counterpart, it will be mapped implicitly
    Customer dtoToApi(CustomerDto customerDto);

    // From domain to entity
    // When a property has the same name as its target entity counterpart, it will be mapped implicitly
    CustomerDto apiToDto(Customer customer);
}
