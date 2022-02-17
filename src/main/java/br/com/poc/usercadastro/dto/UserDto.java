package br.com.poc.usercadastro.dto;

import br.com.poc.usercadastro.dynamo.model.UserEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserDto {
    private  String name;

    public UserEntity toEntity() {
        return new UserEntity(name);
    }
}
