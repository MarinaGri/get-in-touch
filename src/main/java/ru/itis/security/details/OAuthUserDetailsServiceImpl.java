package ru.itis.security.details;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.itis.dto.oauth.VkTokenDto;
import ru.itis.dto.oauth.VkUserDto;
import ru.itis.models.User;
import ru.itis.repositories.UsersRepository;
import ru.itis.utils.helpers.OAuthHelper;

import java.util.Optional;

@RequiredArgsConstructor
@Component("oAuthUserDetailsService")
public class OAuthUserDetailsServiceImpl implements UserDetailsService {

    private final UsersRepository usersRepository;

    private final OAuthHelper oAuthHelper;

    @Override
    public UserDetails loadUserByUsername(String code) throws UsernameNotFoundException {
        VkTokenDto tokenDto = oAuthHelper.getTokenDtoByCode(code);

        Optional<User> candidate = usersRepository.findByEmail(tokenDto.getEmail());
        if (candidate.isPresent()) {
            return new UserDetailsImpl(candidate.get());
        }
        VkUserDto userDto = oAuthHelper.getUserDtoByTokenDto(tokenDto);

        if(userDto.getFirstName() == null){
            throw new UsernameNotFoundException("User not found!");
        }

        User user = User.builder()
                .email(tokenDto.getEmail())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .state(User.State.ACTIVE)
                .role(User.Role.ROLE_USER)
                .vkId(tokenDto.getUserId())
                .token(tokenDto.getAccessToken())
                .build();

        return new UserDetailsImpl(usersRepository.save(user));
    }
}
