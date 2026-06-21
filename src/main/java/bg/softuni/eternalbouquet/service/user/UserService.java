<<<<<<< HEAD
package bg.softuni.eternalbouquet.service.user;

import bg.softuni.eternalbouquet.mapper.user.UserMapper;
import bg.softuni.eternalbouquet.model.dto.user.UserDto;
import bg.softuni.eternalbouquet.model.dto.user.UserLoginRequest;
import bg.softuni.eternalbouquet.model.dto.user.UserRegisterRequest;
import bg.softuni.eternalbouquet.model.entity.bouquet.Bouquet;
import bg.softuni.eternalbouquet.model.entity.user.User;
import bg.softuni.eternalbouquet.model.entity.user.UserRole;
import bg.softuni.eternalbouquet.repository.bouquet.BouquetRepository;
import bg.softuni.eternalbouquet.repository.user.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final BouquetRepository bouquetRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, UserMapper userMapper, BouquetRepository bouquetRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.bouquetRepository = bouquetRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDto login(UserLoginRequest userLoginRequest) {
        Optional<User> optionalUser = userRepository.findByEmail(userLoginRequest.getEmail());

        if(optionalUser.isEmpty() ||
                !passwordEncoder.matches(userLoginRequest.getPassword(), optionalUser.get().getPassword())){
            throw new RuntimeException("User or password mismatch");
        }

        return UserMapper.toDto(optionalUser.get());
    }


    public void register(UserRegisterRequest userRegisterRequest) {
        Optional<User> optionalUser = userRepository.findByUsername(userRegisterRequest.getEmail());

        if (optionalUser.isPresent()){
            throw new RuntimeException("User with this name already exists!");
        }

        String encodedPassword = passwordEncoder.encode(userRegisterRequest.getPassword());
        userRegisterRequest.setPassword(encodedPassword);

        User userEntity = userMapper.toEntity(userRegisterRequest);

        userRepository.save(userEntity);

        UserMapper.toDto(userEntity);
    }

    public UserDto findById(UUID id){
        User user = userRepository.findById(id)
                .orElseThrow(
                        () -> new RuntimeException("User with if [%s] already exists".formatted(id)));

        return UserMapper.toDto(user);
    }

    public User findEntityById(UUID id){
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public void addToFavourites(UUID userId, UUID bouquetId){
        User user = findEntityById(userId);

        Bouquet bouquet = bouquetRepository.findById(bouquetId)
                .orElseThrow(() -> new RuntimeException("Bouquet not found"));

        if(!user.getFavouriteBouquets().contains(bouquet)){
            user.getFavouriteBouquets().add(bouquet);
            userRepository.save(user);
        }
    }

    public void removeFromFavourites(UUID userId, UUID bouquetId){
        User user = findEntityById(userId);

        Bouquet bouquet = bouquetRepository.findById(bouquetId)
                .orElseThrow(() -> new RuntimeException("Bouquet not found"));

        user.getFavouriteBouquets().remove(bouquet);

        userRepository.save(user);
    }

    public UserDto getById(UUID userId) {
        return null;
    }

    public List<Bouquet> getFavouriteBouquets(UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found."));

        return user.getFavouriteBouquets();
    }

    public void switchRole(UUID userId) {

        User user = userRepository.findEntityById(userId);

        if (user.getRole() == UserRole.ADMIN) {
            user.setRole(UserRole.USER);
        } else {
            user.setRole(UserRole.ADMIN);
        }

        userRepository.save(user);
    }

    public List<UserDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::toDto)
                .toList();
    }
=======
package bg.softuni.eternalbouquet.service.user;public class UserService {
>>>>>>> 6069ab8 (feat: add domain entities repositories and service layer)
}
