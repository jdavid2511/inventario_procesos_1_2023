package com.procesos.inventario.services;
import com.procesos.inventario.models.User;
import com.procesos.inventario.repository.UserRepository;
import com.procesos.inventario.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JWTUtil jwtUtil;

    public User getUser(Long id){

        return userRepository.findById(id).get();

    }

    @Override
    public Boolean createUser(User user) {
        try {
        User userSaved = userRepository.save(user);
            return true;
        }catch (Exception e){
        return false;
        }
    }

    @Override
    public List<User> allUsers() {
        return userRepository.findAll();
    }

    @Override
    public Boolean updateUser(Long id, User user) {
        try{
        User userBD = userRepository.findById(id).get();
        if (userBD == null){
            return false;
        }
        userBD.setFirstName(user.getFirstName());
        userBD.setLastName(user.getLastName());
        userBD.setAddress(user.getAddress());
        userBD.setBirthday(user.getBirthday());
        userBD.setEmail(user.getEmail());
        userBD.setPassword(user.getPassword());
        User userUp = userRepository.save(userBD);
        return true;
    }catch(Exception e){
        return false;
    }
    }

    @Override
    public String login(User user) {
        Optional <User> userdb = userRepository.findByEmail(user.getEmail());
        if (userdb.isEmpty()){
            throw new RuntimeException("Usuario no encontrado");
        }
        if (!userdb.get().getPassword().equals(user.getPassword())){
            throw new RuntimeException("La contraseña es incorrecta");
        }
        return jwtUtil.create(String.valueOf(userdb.get().getId()),
                String.valueOf(userdb.get().getEmail()));
    }
}
