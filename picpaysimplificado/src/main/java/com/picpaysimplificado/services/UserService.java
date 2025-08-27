package com.picpaysimplificado.services;

import com.picpaysimplificado.domain.user.User;
import com.picpaysimplificado.domain.user.UserType;
import com.picpaysimplificado.dtos.UserDTO;
import com.picpaysimplificado.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public void validateTransaction(User sender, BigDecimal amount) throws Exception {
        if(sender.getUserType() == UserType.MERCHANT){
            throw new Exception("Usuário do tipo logista não esta autorizado a realizar transacao");
        }

        if(sender.getBalance().compareTo(amount) < 0){
            throw new Exception("Usuário não tem saldo insuficiente");
        }
    }

    public User findUserByid(Long id) throws Exception {
        return this.repository.findUserById(id).orElseThrow(() -> new Exception("Usuário não encontrado"));
    }

    public User createUser(UserDTO userDTO){
        User user = new User(userDTO);
        this.saveUser(user);
        return user;
    }

    public List<User> getAllUsers(){
        return this.repository.findAll();
    }

    public void saveUser(User user){
        this.repository.save(user);
    }
}
