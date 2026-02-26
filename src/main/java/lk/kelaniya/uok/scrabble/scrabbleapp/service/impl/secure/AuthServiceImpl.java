package lk.kelaniya.uok.scrabble.scrabbleapp.service.impl.secure;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lk.kelaniya.uok.scrabble.scrabbleapp.dao.PerformanceDao;
import lk.kelaniya.uok.scrabble.scrabbleapp.dao.PlayerDao;
import lk.kelaniya.uok.scrabble.scrabbleapp.dao.secure.UserDao;
import lk.kelaniya.uok.scrabble.scrabbleapp.dto.secure.JWTAuthResponse;
import lk.kelaniya.uok.scrabble.scrabbleapp.dto.secure.SignIn;
import lk.kelaniya.uok.scrabble.scrabbleapp.dto.secure.UserDTO;
import lk.kelaniya.uok.scrabble.scrabbleapp.entity.PerformanceEntity;
import lk.kelaniya.uok.scrabble.scrabbleapp.entity.PlayerEntity;
import lk.kelaniya.uok.scrabble.scrabbleapp.entity.security.UserEntity;
import lk.kelaniya.uok.scrabble.scrabbleapp.security.jwt.JWTutils;
import lk.kelaniya.uok.scrabble.scrabbleapp.service.secure.AuthService;
import lk.kelaniya.uok.scrabble.scrabbleapp.util.EntityDTOConvert;
import lk.kelaniya.uok.scrabble.scrabbleapp.util.UtilData;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserDao userDao;
    private final JWTutils jwtutils;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final PlayerDao playerDao;

    // ✅ Add separately with @PersistenceContext
    @PersistenceContext
    private EntityManager entityManager;  // not final

//    @Override
//    public JWTAuthResponse signIn(SignIn signIn) {
//         authenticationManager.authenticate(
//                 new UsernamePasswordAuthenticationToken(signIn.getEmail(), signIn.getPassword()));
//        var userByEmail = userDao.findByEmail(signIn.getEmail()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
//        var generateToken=jwtutils.generateToken(userByEmail.getEmail(),userByEmail.getAuthorities());
//        return JWTAuthResponse.builder().token(generateToken).build();
//    }


//    @Override
//    public JWTAuthResponse signUp(UserDTO userDTO) {
//        userDTO.setUserId(UtilData.generateUserId());
//        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
//        var savedUser = userDao.save(entityDTOConvert.convertUserDTOToUserEntity(userDTO));
//        var generatedToken = jwtutils.generateToken(savedUser.getEmail(), savedUser.getAuthorities());
//        return JWTAuthResponse.builder().token(generatedToken).build();
//    }
@Override
public JWTAuthResponse signIn(SignIn signIn) {
    authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(signIn.getEmail(), signIn.getPassword()));
    var userByEmail = userDao.findByEmail(signIn.getEmail())
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));

    String playerId = userByEmail.getPlayer() != null
            ? userByEmail.getPlayer().getPlayerId()
            : null;

    var generateToken = jwtutils.generateToken(
            userByEmail.getEmail(),
            userByEmail.getAuthorities(),
            playerId);
    return JWTAuthResponse.builder().token(generateToken).build();
}


    @Override
    public JWTAuthResponse signUp(UserDTO registerDTO) {
        // Create player
        PlayerEntity player = new PlayerEntity();
        player.setPlayerId(UtilData.generatePlayerId());
        player.setFirstName(registerDTO.getFirstName());
        player.setLastName(registerDTO.getLastName());
        player.setAge(registerDTO.getAge());
        player.setGender(registerDTO.getGender());
        player.setDob(registerDTO.getDob());
        player.setEmail(registerDTO.getEmail());
        player.setPhone(registerDTO.getPhone());
        player.setAddress(registerDTO.getAddress());
        player.setFaculty(registerDTO.getFaculty());
        player.setAcademicLevel(registerDTO.getAcademicLevel());
        player.setAccountCreatedDate(LocalDate.now());
        player.setDeleted(false);

        // Create performance and link
        PerformanceEntity performance = new PerformanceEntity();
        performance.setPlayerId(player.getPlayerId());
        performance.setPlayer(player);
        performance.setTotalWins(0.0);
        performance.setTotalGamesPlayed(0);
        performance.setCumMargin(0);
        performance.setAvgMargin(0.0);
        performance.setPlayerRank(0);
        player.setPerformance(performance);

        // Use persist instead of save/merge ✅
        entityManager.persist(player);
        entityManager.flush();

        // Create user
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(UtilData.generateUserId());
        userEntity.setFirstName(registerDTO.getFirstName());
        userEntity.setLastName(registerDTO.getLastName());
        userEntity.setEmail(registerDTO.getEmail());
        userEntity.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        userEntity.setRole(registerDTO.getRole());
        userEntity.setPlayer(player);
        UserEntity savedUser = userDao.save(userEntity);

        var generatedToken = jwtutils.generateToken(
                savedUser.getEmail(),
                savedUser.getAuthorities(),
                savedUser.getPlayer().getPlayerId());
        return JWTAuthResponse.builder().token(generatedToken).build();
    }
}
