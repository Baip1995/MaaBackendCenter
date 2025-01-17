package plus.maa.backend.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import plus.maa.backend.repository.UserRepository;
import plus.maa.backend.repository.entity.MaaUser;
import plus.maa.backend.service.model.LoginUser;

/**
 * @author AnselYuki
 */
@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    /**
     * 查询用户信息
     *
     * @param email 用户使用邮箱登录
     * @return 用户详细信息
     * @throws UsernameNotFoundException 用户名未找到
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        MaaUser user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        //数据封装成UserDetails返回
        return new LoginUser(user);
    }
}
